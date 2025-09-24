package com.ngobatai_lab4.Bai7;

/**
 *
 * @author PC728
 */
// Bai07.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main7 {
    static abstract class Gate {
        String type;

        public Gate(String type) {
            this.type = type;
        }

        public abstract boolean pass(Prince p);

        public abstract String summary();
    }

    static class BusinessGate extends Gate {
        long unit;
        int qty;

        public BusinessGate(long unit, int qty) {
            super("Business");
            this.unit = unit;
            this.qty = qty;
        }

        public boolean pass(Prince p) {
            long need = unit * qty;
            if (p.money >= need) {
                p.money -= need;
                return true;
            }
            return false;
        }

        public String summary() {
            return String.format("Business unit=%d qty=%d", unit, qty);
        }
    }

    static class AcademicGate extends Gate {
        int req;

        public AcademicGate(int req) {
            super("Academic");
            this.req = req;
        }

        public boolean pass(Prince p) {
            return p.intel >= req;
        }

        public String summary() {
            return "Academic req=" + req;
        }
    }

    static class PowerGate extends Gate {
        int power;

        public PowerGate(int power) {
            super("Power");
            this.power = power;
        }

        public boolean pass(Prince p) {
            if (p.strength >= power) {
                p.strength -= power;
                return true;
            }
            return false;
        }

        public String summary() {
            return "Power power=" + power;
        }
    }

    static class Prince {
        long money;
        int intel;
        int strength;

        public Prince(long m, int i, int s) {
            money = m;
            intel = i;
            strength = s;
        }

        public String toString() {
            return "money=" + money + " intel=" + intel + " strength=" + strength;
        }
    }

    public static class UI extends JFrame {
        java.util.List<Gate> gates = new ArrayList<>();
        DefaultListModel<String> lm = new DefaultListModel<>();
        JList<String> list = new JList<>(lm);

        public UI() {
            setTitle("Bai07 - Hoang tu vuot cong");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(900, 500);
            setLocationRelativeTo(null);
            init();
        }

        void init() {
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            JComboBox<String> cb = new JComboBox<>(new String[] { "Business", "Academic", "Power" });
            left.add(cb);
            JTextField tf1 = new JTextField("1000", 8), tf2 = new JTextField("1", 8);
            left.add(new JLabel("Param1 (unit or req or power)"));
            left.add(tf1);
            left.add(new JLabel("Param2 (qty for business, ignored otherwise)"));
            left.add(tf2);
            JButton btnAdd = new JButton("Add Gate");
            left.add(btnAdd);
            btnAdd.addActionListener(e -> {
                String t = (String) cb.getSelectedItem();
                try {
                    if (t.equals("Business")) {
                        long unit = Long.parseLong(tf1.getText().trim());
                        int qty = Integer.parseInt(tf2.getText().trim());
                        gates.add(new BusinessGate(unit, qty));
                        lm.addElement(gates.get(gates.size() - 1).summary());
                    } else if (t.equals("Academic")) {
                        int req = Integer.parseInt(tf1.getText().trim());
                        gates.add(new AcademicGate(req));
                        lm.addElement(gates.get(gates.size() - 1).summary());
                    } else {
                        int pw = Integer.parseInt(tf1.getText().trim());
                        gates.add(new PowerGate(pw));
                        lm.addElement(gates.get(gates.size() - 1).summary());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid params");
                }
            });
            left.add(new JButton(new AbstractAction("Clear Gates") {
                public void actionPerformed(ActionEvent e) {
                    gates.clear();
                    lm.clear();
                }
            }));

            getContentPane().add(left, BorderLayout.WEST);
            getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);

            JPanel right = new JPanel();
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            JTextField tfMoney = new JTextField("100000", 10), tfIntel = new JTextField("50", 5),
                    tfStr = new JTextField("50", 5);
            right.add(new JLabel("Prince money:"));
            right.add(tfMoney);
            right.add(new JLabel("Prince intel:"));
            right.add(tfIntel);
            right.add(new JLabel("Prince strength:"));
            right.add(tfStr);
            JButton btnSim = new JButton("Simulate");
            right.add(btnSim);
            JTextArea ta = new JTextArea(15, 25);
            ta.setEditable(false);
            right.add(new JScrollPane(ta));
            getContentPane().add(right, BorderLayout.EAST);

            btnSim.addActionListener(e -> {
                try {
                    long m = Long.parseLong(tfMoney.getText().trim());
                    int i = Integer.parseInt(tfIntel.getText().trim());
                    int s = Integer.parseInt(tfStr.getText().trim());
                    Prince p = new Prince(m, i, s);
                    ta.append("Start prince: " + p + "\n");
                    boolean ok = true;
                    for (int idx = 0; idx < gates.size(); idx++) {
                        Gate g = gates.get(idx);
                        boolean pass = g.pass(p);
                        ta.append("Gate " + idx + ": " + g.summary() + " -> " + (pass ? "PASS" : "FAIL") + "\n");
                        if (!pass) {
                            ok = false;
                            ta.append("Failed at gate " + idx + "\n");
                            break;
                        }
                    }
                    if (ok)
                        ta.append("Prince saved princess! Remaining: " + p + "\n");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid prince params");
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }
}
