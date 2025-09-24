package com.ngobatai_lab4.Bai14;

// Bai14.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

abstract class SIM {
    String serial;
    String network; // +001 +002 +003
    String phone; // 9 digits string
    boolean active;

    public SIM(String serial, String network, String phone, boolean active) {
        this.serial = serial;
        this.network = network;
        this.phone = phone;
        this.active = active;
    }

    public abstract String type();

    public abstract Object[] rowData();
}

class PrepaidSIM extends SIM {
    double balance; // total balance (assume)
    int rechargeCount;

    public PrepaidSIM(String serial, String network, String phone, boolean active, double balance, int rechargeCount) {
        super(serial, network, phone, active);
        this.balance = balance;
        this.rechargeCount = rechargeCount;
    }

    public double promoAmount() {
        if (rechargeCount < 5)
            return 0.10 * balance;
        if (rechargeCount <= 10)
            return 0.20 * balance;
        return 0.50 * balance;
    }

    @Override
    public String type() {
        return "Prepaid";
    }

    @Override
    public Object[] rowData() {
        return new Object[] { serial, network, phone, active ? "Act" : "Non-act", type(), balance, rechargeCount, "",
                "" };
    }
}

class PostpaidSIM extends SIM {
    String plan; // LCAP, MCAP, HCAP
    double debt;

    public PostpaidSIM(String serial, String network, String phone, boolean active, String plan, double debt) {
        super(serial, network, phone, active);
        this.plan = plan;
        this.debt = debt;
    }

    public double promoAmount() {
        switch (plan) {
            case "LCAP":
                return 0.05 * debt;
            case "MCAP":
                return 0.10 * debt;
            case "HCAP":
                return 0.15 * debt;
            default:
                return 0;
        }
    }

    @Override
    public String type() {
        return "Postpaid";
    }

    @Override
    public Object[] rowData() {
        return new Object[] { serial, network, phone, active ? "Act" : "Non-act", type(), "", "", plan, debt };
    }
}

class SIMManager {
    List<SIM> sims = new ArrayList<>();

    void add(SIM s) {
        sims.add(s);
    }

    double totalPrepaidPromoForATT() { // AT&T = +002
        double sum = 0;
        for (SIM s : sims)
            if (s instanceof PrepaidSIM && "+002".equals(s.network)) {
                sum += ((PrepaidSIM) s).promoAmount();
            }
        return sum;
    }

    PostpaidSIM postpaidWithMaxDebt() {
        PostpaidSIM best = null;
        double max = Double.NEGATIVE_INFINITY;
        for (SIM s : sims)
            if (s instanceof PostpaidSIM) {
                if (((PostpaidSIM) s).debt > max) {
                    max = ((PostpaidSIM) s).debt;
                    best = (PostpaidSIM) s;
                }
            }
        return best;
    }
}

public class Main14 extends JFrame {
    private SIMManager manager = new SIMManager();
    private DefaultTableModel tm = new DefaultTableModel(
            new Object[] { "Serial", "Network", "Phone", "Active", "Type", "Balance", "RechargeCount", "Plan", "Debt" },
            0);
    private JTable table = new JTable(tm);

    // input fields
    private JTextField tfSerial = new JTextField(8);
    private JComboBox<String> cbNetwork = new JComboBox<>(new String[] { "+001", "+002", "+003" });
    private JTextField tfPhone = new JTextField(10);
    private JCheckBox cbActive = new JCheckBox("Active", true);
    private JComboBox<String> cbType = new JComboBox<>(new String[] { "Prepaid", "Postpaid" });
    // prepaid fields
    private JTextField tfBalance = new JTextField("0", 8);
    private JTextField tfRechargeCount = new JTextField("0", 4);
    // postpaid fields
    private JComboBox<String> cbPlan = new JComboBox<>(new String[] { "LCAP", "MCAP", "HCAP" });
    private JTextField tfDebt = new JTextField("0", 8);

    public Main14() {
        setTitle("Bai14 - SIM Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Serial:"));
        top.add(tfSerial);
        top.add(new JLabel("Network:"));
        top.add(cbNetwork);
        top.add(new JLabel("Phone:"));
        top.add(tfPhone);
        top.add(cbActive);
        top.add(new JLabel("Type:"));
        top.add(cbType);

        // prepaid panel
        JPanel pPre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pPre.add(new JLabel("Balance:"));
        pPre.add(tfBalance);
        pPre.add(new JLabel("RechargeCount:"));
        pPre.add(tfRechargeCount);

        // postpaid panel
        JPanel pPost = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pPost.add(new JLabel("Plan:"));
        pPost.add(cbPlan);
        pPost.add(new JLabel("Debt:"));
        pPost.add(tfDebt);

        JButton btnAdd = new JButton("Add SIM");
        JButton btnPromoATT = new JButton("Total Prepaid Promo (AT&T)");
        JButton btnMaxDebt = new JButton("Find Postpaid with Max Debt");

        btnAdd.addActionListener(e -> onAddSIM());
        btnPromoATT.addActionListener(e -> {
            double v = manager.totalPrepaidPromoForATT();
            JOptionPane.showMessageDialog(this, String.format("Total prepaid promo for AT&T: %.2f", v));
        });
        btnMaxDebt.addActionListener(e -> {
            PostpaidSIM p = manager.postpaidWithMaxDebt();
            if (p == null)
                JOptionPane.showMessageDialog(this, "No postpaid SIMs");
            else
                JOptionPane.showMessageDialog(this, String.format("Max debt postpaid: serial=%s network=%s debt=%.2f",
                        p.serial, p.network, p.debt));
        });

        // Layout
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(top);
        north.add(pPre);
        north.add(pPost);
        JPanel ctrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ctrl.add(btnAdd);
        ctrl.add(btnPromoATT);
        ctrl.add(btnMaxDebt);
        north.add(ctrl);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void onAddSIM() {
        try {
            String serial = tfSerial.getText().trim();
            String network = (String) cbNetwork.getSelectedItem();
            String phone = tfPhone.getText().trim();
            boolean active = cbActive.isSelected();
            String type = (String) cbType.getSelectedItem();
            if (type.equals("Prepaid")) {
                double bal = Double.parseDouble(tfBalance.getText().trim());
                int rc = Integer.parseInt(tfRechargeCount.getText().trim());
                PrepaidSIM p = new PrepaidSIM(serial, network, phone, active, bal, rc);
                manager.add(p);
                tm.addRow(p.rowData());
            } else {
                String plan = (String) cbPlan.getSelectedItem();
                double debt = Double.parseDouble(tfDebt.getText().trim());
                PostpaidSIM pp = new PostpaidSIM(serial, network, phone, active, plan, debt);
                manager.add(pp);
                tm.addRow(pp.rowData());
            }
            clearInputs();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void clearInputs() {
        tfSerial.setText("");
        tfPhone.setText("");
        tfBalance.setText("0");
        tfRechargeCount.setText("0");
        tfDebt.setText("0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main14().setVisible(true));
    }
}
