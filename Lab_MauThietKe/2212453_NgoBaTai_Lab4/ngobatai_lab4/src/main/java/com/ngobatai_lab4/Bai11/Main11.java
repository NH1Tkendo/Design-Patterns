package com.ngobatai_lab4.Bai11;

// Bai11.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

// ===== Abstract Customer =====
abstract class Customer {
    protected String id, name;
    protected int quantity;
    protected double unitPrice;

    public Customer(String id, String name, int quantity, double unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public abstract double getAmount();

    public Object[] toRow() {
        return new Object[] { id, name, quantity, unitPrice, getAmount() };
    }

    @Override
    public String toString() {
        return id + " - " + name + " : " + getAmount();
    }
}

// ===== Việt Nam Customer =====
class VietnamCustomer extends Customer {
    private int quota = 50;

    public VietnamCustomer(String id, String name, int quantity, double unitPrice) {
        super(id, name, quantity, unitPrice);
    }

    @Override
    public double getAmount() {
        if (quantity <= quota)
            return quantity * unitPrice;
        return quota * unitPrice + (quantity - quota) * unitPrice * 2;
    }
}

// ===== Foreign Customer =====
class ForeignCustomer extends Customer {
    public ForeignCustomer(String id, String name, int quantity, double unitPrice) {
        super(id, name, quantity, unitPrice);
    }

    @Override
    public double getAmount() {
        return quantity * unitPrice;
    }
}

// ===== Manager =====
class CustomerManager {
    List<Customer> customers = new ArrayList<>();

    public void add(Customer c) {
        customers.add(c);
    }

    public double avgForeignAmount() {
        double sum = 0;
        int count = 0;
        for (Customer c : customers) {
            if (c instanceof ForeignCustomer) {
                sum += c.getAmount();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }
}

// ===== GUI =====
public class Main11 extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtId, txtName, txtQty, txtPrice;
    private JComboBox<String> cboType;
    private JLabel lblAvgForeign;
    private CustomerManager manager = new CustomerManager();

    public Main11() {
        setTitle("Customer Management");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Top input
        JPanel pInput = new JPanel(new FlowLayout());
        txtId = new JTextField(5);
        txtName = new JTextField(10);
        txtQty = new JTextField(5);
        txtPrice = new JTextField(7);
        cboType = new JComboBox<>(new String[] { "Vietnam", "Foreign" });
        JButton btnAdd = new JButton("Add");

        pInput.add(new JLabel("ID"));
        pInput.add(txtId);
        pInput.add(new JLabel("Name"));
        pInput.add(txtName);
        pInput.add(new JLabel("Qty"));
        pInput.add(txtQty);
        pInput.add(new JLabel("Price"));
        pInput.add(txtPrice);
        pInput.add(new JLabel("Type"));
        pInput.add(cboType);
        pInput.add(btnAdd);

        // Table
        model = new DefaultTableModel(new String[] { "ID", "Name", "Qty", "Unit Price", "Total" }, 0);
        table = new JTable(model);

        // Bottom label
        lblAvgForeign = new JLabel("Average foreign amount: 0");

        add(pInput, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(lblAvgForeign, BorderLayout.SOUTH);

        // Button event
        btnAdd.addActionListener(e -> addCustomer());
    }

    private void addCustomer() {
        String id = txtId.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String type = (String) cboType.getSelectedItem();

        Customer c;
        if ("Vietnam".equals(type))
            c = new VietnamCustomer(id, name, qty, price);
        else
            c = new ForeignCustomer(id, name, qty, price);

        manager.add(c);
        model.addRow(c.toRow());
        lblAvgForeign.setText("Average foreign amount: " + manager.avgForeignAmount());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main11().setVisible(true));
    }
}
