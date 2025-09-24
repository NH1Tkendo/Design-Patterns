package com.ngobatai_lab4.Bai9;

/**
 *
 * @author PC728
 */
// Bai09.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main9 {
    enum ABO {
        O, A, B, AB
    }

    enum Rh {
        POS, NEG
    }

    static class Person {
        String name;
        ABO abo;
        Rh rh;

        public Person(String name, ABO abo, Rh rh) {
            this.name = name;
            this.abo = abo;
            this.rh = rh;
        }

        public String toString() {
            return name + " " + abo + (rh == Rh.POS ? "+" : "-");
        }
    }

    // genotype utility for ABO
    // allele representation: "I(A)" = "A", "I(B)"="B", "i"="O"
    static Map<ABO, String[]> possibleGenotypes = new HashMap<>();
    static {
        possibleGenotypes.put(ABO.O, new String[] { "ii" });
        possibleGenotypes.put(ABO.A, new String[] { "AA", "Ai" });
        possibleGenotypes.put(ABO.B, new String[] { "BB", "Bi" });
        possibleGenotypes.put(ABO.AB, new String[] { "AB" });
    }

    // get alleles list from genotype code like "Ai" -> ['A','i']
    static char[] alleles(String g) {
        return g.toCharArray();
    }

    // combine genotypes of parents produce possible child phenotypes
    static Set<ABO> possibleChildrenABO(ABO p1, ABO p2) {
        Set<ABO> res = new HashSet<>();
        String[] g1 = possibleGenotypes.get(p1);
        String[] g2 = possibleGenotypes.get(p2);
        for (String a : g1)
            for (String b : g2) {
                char[] A = alleles(a), B = alleles(b);
                for (char x : A)
                    for (char y : B) {
                        // child genotype = x+y (order doesn't matter)
                        String child = "" + x + y;
                        // normalize e.g. "iA" -> sort to "Ai" so we can match
                        char c1 = child.charAt(0), c2 = child.charAt(1);
                        String norm = (c1 <= c2) ? ("" + c1 + c2) : ("" + c2 + c1);
                        // determine phenotype
                        ABO ph = genotypeToABO(norm);
                        res.add(ph);
                    }
            }
        return res;
    }

    static ABO genotypeToABO(String g) {
        // sorted normalized genotype
        if (g.equals("ii"))
            return ABO.O;
        if (g.equals("AA") || g.equals("Ai") || g.equals("iA"))
            return ABO.A;
        if (g.equals("BB") || g.equals("Bi") || g.equals("iB"))
            return ABO.B;
        if (g.equals("AB") || g.equals("BA"))
            return ABO.AB;
        // fallback
        if (g.indexOf('A') >= 0 && g.indexOf('B') >= 0)
            return ABO.AB;
        if (g.indexOf('A') >= 0)
            return ABO.A;
        if (g.indexOf('B') >= 0)
            return ABO.B;
        return ABO.O;
    }

    // transfusion compatibility
    static boolean canDonate(Person donor, Person receiver) {
        // ABO rules
        boolean aboOk = false;
        switch (donor.abo) {
            case O:
                aboOk = true;
                break;
            case A:
                aboOk = (receiver.abo == ABO.A || receiver.abo == ABO.AB);
                break;
            case B:
                aboOk = (receiver.abo == ABO.B || receiver.abo == ABO.AB);
                break;
            case AB:
                aboOk = (receiver.abo == ABO.AB);
                break;
        }
        if (!aboOk)
            return false;
        // Rh rules: donor Rh- can give to both; donor Rh+ only to receiver Rh+
        if (donor.rh == Rh.NEG)
            return true;
        return receiver.rh == Rh.POS;
    }

    // GUI
    public static class UI extends JFrame {
        java.util.List<Person> list = new ArrayList<>();
        DefaultListModel<String> lm = new DefaultListModel<>();
        JList<String> jlist = new JList<>(lm);

        public UI() {
            setTitle("Bai09 - Nhom mau ABO + Rh");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(900, 500);
            setLocationRelativeTo(null);
            init();
        }

        void init() {
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            JTextField tfName = new JTextField(12);
            JComboBox<String> cbABO = new JComboBox<>(new String[] { "O", "A", "B", "AB" });
            JComboBox<String> cbRh = new JComboBox<>(new String[] { "+", "-" });
            JButton btnAdd = new JButton("Add person");
            left.add(new JLabel("Name"));
            left.add(tfName);
            left.add(new JLabel("ABO"));
            left.add(cbABO);
            left.add(new JLabel("Rh"));
            left.add(cbRh);
            left.add(btnAdd);
            add(left, BorderLayout.WEST);

            add(new JScrollPane(jlist), BorderLayout.CENTER);

            JPanel right = new JPanel();
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            JButton btnCheckGen = new JButton("Check triple (father,mother,child)");
            JButton btnFindDonors = new JButton("Find donors for selected X");
            right.add(btnCheckGen);
            right.add(btnFindDonors);
            add(right, BorderLayout.EAST);

            btnAdd.addActionListener(e -> {
                String name = tfName.getText().trim();
                ABO abo = ABO.valueOf((String) cbABO.getSelectedItem());
                Rh rh = cbRh.getSelectedItem().equals("+") ? Rh.POS : Rh.NEG;
                Person p = new Person(name, abo, rh);
                list.add(p);
                lm.addElement(p.toString());
            });

            btnCheckGen.addActionListener(e -> {
                if (list.size() < 3) {
                    JOptionPane.showMessageDialog(this, "Need at least 3 persons");
                    return;
                }
                String s = JOptionPane.showInputDialog(this,
                        "Enter indexes father,mother,child (0-based, comma-separated) e.g. 0,1,2:");
                if (s == null)
                    return;
                try {
                    String[] parts = s.split(",");
                    int fa = Integer.parseInt(parts[0].trim()), ma = Integer.parseInt(parts[1].trim()),
                            ch = Integer.parseInt(parts[2].trim());
                    Person P = list.get(fa), M = list.get(ma), C = list.get(ch);
                    Set<ABO> poss = possibleChildrenABO(P.abo, M.abo);
                    boolean aboOk = poss.contains(C.abo);
                    // Rh check: if both parents NEG then child must be NEG; else child could be POS
                    // or NEG (we accept POS or NEG as possible)
                    boolean rhOk = !(P.rh == Rh.NEG && M.rh == Rh.NEG && C.rh == Rh.POS);
                    JOptionPane.showMessageDialog(this,
                            String.format("ABO possible? %b (possible: %s)\nRh possible? %b", aboOk, poss, rhOk));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input");
                }
            });

            btnFindDonors.addActionListener(e -> {
                int idx = jlist.getSelectedIndex();
                if (idx < 0) {
                    JOptionPane.showMessageDialog(this, "Select person X");
                    return;
                }
                Person x = list.get(idx);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++)
                    if (i != idx) {
                        Person donor = list.get(i);
                        if (canDonate(donor, x))
                            sb.append(i).append(": ").append(donor).append("\n");
                    }
                JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No donors" : sb.toString());
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }
}
