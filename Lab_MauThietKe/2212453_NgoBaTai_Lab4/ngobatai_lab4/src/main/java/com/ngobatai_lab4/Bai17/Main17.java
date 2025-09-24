package com.ngobatai_lab4.Bai17;

// Bai17.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.*;

class Author {
    String id, name, field, phone;

    public Author(String id, String name, String field, String phone) {
        this.id = id;
        this.name = name;
        this.field = field;
        this.phone = phone;
    }
}

enum JournalCat {
    ISI, SCOPUS, NON_INDEX
}

enum ConfCat {
    CORE, NONCORE
}

class Article {
    String id, title, doi;
    int year;
    String mainAuthorId;
    List<String> coAuthorIds = new ArrayList<>();
    boolean isJournal;
    JournalCat jcat;
    ConfCat ccat;
    boolean belongsProject;
    String projectId; // optional

    public double baseCredits() {
        if (isJournal) {
            switch (jcat) {
                case ISI:
                    return 5000;
                case SCOPUS:
                    return 2500;
                default:
                    return 1000;
            }
        } else {
            return (ccat == ConfCat.CORE) ? 1000 : 500;
        }
    }

    public double effectiveCredits() {
        double v = baseCredits();
        if (belongsProject)
            v *= 0.65;
        return v;
    }

    // share for an author id
    public double shareForAuthor(String authorId) {
        double v = effectiveCredits();
        if (authorId.equals(mainAuthorId)) {
            if (coAuthorIds.isEmpty())
                return v; // all to main
            return v * 0.5;
        } else {
            if (coAuthorIds.isEmpty())
                return 0;
            if (coAuthorIds.contains(authorId)) {
                return (v * 0.5) / coAuthorIds.size();
            } else
                return 0;
        }
    }
}

class ResearchProject {
    String id, title;
    String regDate;
    int durationMonths;
    String acceptanceDate;
    // required counts: ISI, Scopus, NonIndex, ConfCore, ConfNonCore
    int reqISI, reqScopus, reqNonIndex, reqCore, reqNonCore;

    public ResearchProject(String id, String title) {
        this.id = id;
        this.title = title;
    }
}

public class Main17 extends JFrame {
    List<Article> articles = new ArrayList<>();
    Map<String, ResearchProject> projects = new HashMap<>();
    DefaultTableModel tm = new DefaultTableModel(
            new Object[] { "ID", "Title", "Year", "MainAuthor", "IsJournal", "Cat", "Project" }, 0);

    // inputs
    JTextField tfArtId = new JTextField(6), tfTitle = new JTextField(12), tfYear = new JTextField(5);
    JTextField tfMainAuthor = new JTextField(8), tfCoAuthors = new JTextField(20); // comma sep
    JCheckBox cbIsJournal = new JCheckBox("IsJournal", true);
    JComboBox<String> cbJournalCat = new JComboBox<>(new String[] { "ISI", "SCOPUS", "NON_INDEX" });
    JComboBox<String> cbConfCat = new JComboBox<>(new String[] { "CORE", "NONCORE" });
    JCheckBox cbBelongsProject = new JCheckBox("Belongs to project");
    JTextField tfProjectId = new JTextField(6);

    public Main17() {
        setTitle("Bai17 - Articles & Research Credits");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("ArtID"));
        top.add(tfArtId);
        top.add(new JLabel("Title"));
        top.add(tfTitle);
        top.add(new JLabel("Year"));
        top.add(tfYear);
        top.add(new JLabel("MainAuthorID"));
        top.add(tfMainAuthor);
        top.add(new JLabel("CoAuthors (comma)"));
        top.add(tfCoAuthors);
        top.add(cbIsJournal);
        top.add(cbJournalCat);
        top.add(cbConfCat);
        top.add(cbBelongsProject);
        top.add(new JLabel("ProjID"));
        top.add(tfProjectId);
        JButton btnAdd = new JButton("Add Article");
        top.add(btnAdd);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tm)), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCompute = new JButton("Compute total credits for author");
        JButton btnAddProj = new JButton("Add Project");
        JButton btnCheckProj = new JButton("Check Project Acceptance");
        bottom.add(btnCompute);
        bottom.add(btnAddProj);
        bottom.add(btnCheckProj);
        add(bottom, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                Article a = new Article();
                a.id = tfArtId.getText().trim();
                a.title = tfTitle.getText().trim();
                a.year = Integer.parseInt(tfYear.getText().trim());
                a.mainAuthorId = tfMainAuthor.getText().trim();
                String co = tfCoAuthors.getText().trim();
                a.coAuthorIds.clear();
                if (!co.isEmpty()) {
                    String[] parts = co.split(",");
                    for (String p : parts)
                        a.coAuthorIds.add(p.trim());
                }
                a.isJournal = cbIsJournal.isSelected();
                if (a.isJournal) {
                    a.jcat = JournalCat.valueOf((String) cbJournalCat.getSelectedItem());
                } else {
                    a.ccat = ConfCat.valueOf((String) cbConfCat.getSelectedItem());
                }
                a.belongsProject = cbBelongsProject.isSelected();
                a.projectId = tfProjectId.getText().trim();
                articles.add(a);
                tm.addRow(new Object[] { a.id, a.title, a.year, a.mainAuthorId, a.isJournal,
                        a.isJournal ? a.jcat : a.ccat, a.projectId });
                JOptionPane.showMessageDialog(this,
                        "Article added. Credits base=" + a.baseCredits() + " effective=" + a.effectiveCredits());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        btnCompute.addActionListener(e -> {
            String aid = JOptionPane.showInputDialog(this, "Enter author id to compute total credits:");
            if (aid == null || aid.trim().isEmpty())
                return;
            double total = 0;
            for (Article a : articles)
                total += a.shareForAuthor(aid.trim());
            JOptionPane.showMessageDialog(this, "Total research credits for " + aid + " = " + total);
        });

        btnAddProj.addActionListener(e -> {
            String pid = JOptionPane.showInputDialog(this, "Project id:");
            if (pid == null)
                return;
            String title = JOptionPane.showInputDialog(this, "Project title:");
            ResearchProject p = new ResearchProject(pid, title);
            try {
                String in = JOptionPane.showInputDialog(this,
                        "Enter required counts as: ISI,Scopus,NonIndex,Core,NonCore (e.g. 1,2,0,1,0)");
                String[] vals = in.split(",");
                p.reqISI = Integer.parseInt(vals[0].trim());
                p.reqScopus = Integer.parseInt(vals[1].trim());
                p.reqNonIndex = Integer.parseInt(vals[2].trim());
                p.reqCore = Integer.parseInt(vals[3].trim());
                p.reqNonCore = Integer.parseInt(vals[4].trim());
                projects.put(pid, p);
                JOptionPane.showMessageDialog(this, "Project added: " + pid);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for required counts");
            }
        });

        btnCheckProj.addActionListener(e -> {
            String pid = JOptionPane.showInputDialog(this, "Enter project id to check:");
            if (pid == null)
                return;
            ResearchProject p = projects.get(pid.trim());
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Project not found");
                return;
            }
            // count published under this project
            int cIsi = 0, cSc = 0, cNon = 0, cCore = 0, cNonCore = 0;
            for (Article a : articles)
                if (a.belongsProject && pid.equals(a.projectId)) {
                    if (a.isJournal) {
                        switch (a.jcat) {
                            case ISI:
                                cIsi++;
                                break;
                            case SCOPUS:
                                cSc++;
                                break;
                            default:
                                cNon++;
                                break;
                        }
                    } else {
                        if (a.ccat == ConfCat.CORE)
                            cCore++;
                        else
                            cNonCore++;
                    }
                }
            boolean ok = cIsi >= p.reqISI && cSc >= p.reqScopus && cNon >= p.reqNonIndex && cCore >= p.reqCore
                    && cNonCore >= p.reqNonCore;
            String msg = String.format(
                    "Published counts: ISI=%d Scopus=%d NonIndex=%d Core=%d NonCore=%d\nRequired: ISI=%d Scopus=%d NonIndex=%d Core=%d NonCore=%d\nAccepted? %b",
                    cIsi, cSc, cNon, cCore, cNonCore, p.reqISI, p.reqScopus, p.reqNonIndex, p.reqCore, p.reqNonCore,
                    ok);
            JOptionPane.showMessageDialog(this, msg);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main17().setVisible(true));
    }
}
