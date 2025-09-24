package com.ngobatai_lab4.Bai12;

// Bai12.java
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class Main12 {
    static class BigFivePerson {
        String name;
        int O, C, E, A, N;

        public BigFivePerson(String name, int O, int C, int E, int A, int N) {
            this.name = name;
            this.O = O;
            this.C = C;
            this.E = E;
            this.A = A;
            this.N = N;
        }

        public String interpret() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(":\n");
            sb.append(String.format("O=%d, C=%d, E=%d, A=%d, N=%d\n", O, C, E, A, N));
            if (C > 70)
                sb.append("- High Conscientiousness: organized, disciplined.\n");
            else if (C < 30)
                sb.append("- Low Conscientiousness: risk: careless, unreliable.\n");
            if (N > 70)
                sb.append("- High Neuroticism: risk: emotional instability.\n");
            if (E < 30)
                sb.append("- Low Extraversion: may avoid info/social.\n");
            if (O > 70)
                sb.append("- High Openness: creative, open to experience.\n");
            return sb.toString();
        }

        public boolean isHighRisk() {
            return (C < 30) || (N > 70) || (E < 30 && N > 70);
        }
    }

    public static class BigFiveUI extends JFrame {
        private java.util.List<BigFivePerson> list = new ArrayList<>();
        private DefaultTableModel tm;
        private JTable table;
        private JTextField tfName, tfInput; // tfInput accepts either "O93-C74-..." or "93 74 31 96 5"

        public BigFiveUI() {
            setTitle("Bai 12 - Big Five");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 500);
            setLocationRelativeTo(null);
            initUI();
        }

        private void initUI() {
            JPanel top = new JPanel();
            top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
            tfName = new JTextField(15);
            tfInput = new JTextField(40);
            top.add(new JLabel("Name:"));
            top.add(tfName);
            top.add(new JLabel("Input scores: either 'O93-C74-E31-A96-N5' or '93 74 31 96 5'"));
            top.add(tfInput);
            JButton btnAdd = new JButton("Add person");
            top.add(btnAdd);

            tm = new DefaultTableModel(new Object[] { "Name", "O", "C", "E", "A", "N" }, 0);
            table = new JTable(tm);

            JButton btnDescribe = new JButton("Describe selected");
            JButton btnRisk = new JButton("Show high-risk list");

            add(top, BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            JPanel bottom = new JPanel();
            bottom.add(btnDescribe);
            bottom.add(btnRisk);
            add(bottom, BorderLayout.SOUTH);

            btnAdd.addActionListener(e -> {
                try {
                    String name = tfName.getText().trim();
                    String in = tfInput.getText().trim();
                    int[] v = parseInput(in);
                    BigFivePerson p = new BigFivePerson(name, v[0], v[1], v[2], v[3], v[4]);
                    list.add(p);
                    tm.addRow(new Object[] { p.name, p.O, p.C, p.E, p.A, p.N });
                    JOptionPane.showMessageDialog(this, "Added " + name);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
                }
            });

            btnDescribe.addActionListener(e -> {
                int r = table.getSelectedRow();
                if (r < 0) {
                    JOptionPane.showMessageDialog(this, "Select a person");
                    return;
                }
                BigFivePerson p = list.get(r);
                JOptionPane.showMessageDialog(this, p.interpret());
            });

            btnRisk.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (BigFivePerson p : list)
                    if (p.isHighRisk())
                        sb.append(p.name).append(" (O").append(p.O).append("-C").append(p.C).append("-E").append(p.E)
                                .append("-A").append(p.A).append("-N").append(p.N).append(")\n");
                JOptionPane.showMessageDialog(this, (sb.length() == 0 ? "No high-risk people." : sb.toString()));
            });
        }

        // parse input like O93-C74-E31-A96-N5 or "93 74 31 96 5"
        private int[] parseInput(String s) throws Exception {
            int[] res = new int[5];
            if (s.contains("O") || s.contains("C") || s.contains("E")) {
                // split by - then parse numbers
                String[] parts = s.split("-");
                if (parts.length != 5)
                    throw new Exception("Expect 5 parts like Oxx-Cxx-...");
                for (int i = 0; i < 5; i++) {
                    String t = parts[i].replaceAll("[^0-9]", "");
                    res[i] = Integer.parseInt(t);
                }
            } else {
                String[] parts = s.split("\\s+");
                if (parts.length != 5)
                    throw new Exception("Expect 5 numbers");
                for (int i = 0; i < 5; i++)
                    res[i] = Integer.parseInt(parts[i]);
            }
            for (int v : res)
                if (v < 0 || v > 100)
                    throw new Exception("Scores must be 0..100");
            return res;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BigFiveUI ui = new BigFiveUI();
            ui.setVisible(true);
        });
    }
}
