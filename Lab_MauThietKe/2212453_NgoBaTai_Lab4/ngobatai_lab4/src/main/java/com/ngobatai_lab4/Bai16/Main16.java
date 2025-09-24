package com.ngobatai_lab4.Bai16;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.*;

abstract class Product {
    String id, title;
    double price;

    public Product(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public abstract Object[] row();
}

class Picture extends Product {
    double width, height;
    String painter;

    public Picture(String id, String title, double price, double width, double height, String painter) {
        super(id, title, price);
        this.width = width;
        this.height = height;
        this.painter = painter;
    }

    public Object[] row() {
        return new Object[] { id, "Picture", title, price, width, height, painter };
    }
}

class CD extends Product {
    String singer, company;

    public CD(String id, String title, double price, String singer, String company) {
        super(id, title, price);
        this.singer = singer;
        this.company = company;
    }

    public Object[] row() {
        return new Object[] { id, "CD", title, price, "-", "-", singer + "/" + company };
    }
}

class Customer {
    String id, name, phone;

    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String toString() {
        return id + " - " + name;
    }
}

class Invoice {
    String id;
    Customer customer;
    String date; // simplified as string
    List<Product> products = new ArrayList<>();

    public Invoice(String id, Customer cust, String date) {
        this.id = id;
        this.customer = cust;
        this.date = date;
    }

    public void add(Product p) {
        products.add(p);
    }

    public double total() {
        double s = 0;
        for (Product p : products)
            s += p.price;
        return s;
    }

    public Object[] row() {
        return new Object[] { id, customer.id, customer.name, date, total() };
    }
}

public class Main16 extends JFrame {
    // UI components
    DefaultTableModel tmInvoices = new DefaultTableModel(
            new Object[] { "InvID", "CustID", "CustName", "Date", "Total" }, 0);
    DefaultTableModel tmProducts = new DefaultTableModel(
            new Object[] { "ProdID", "Type", "Title", "Price", "W", "H", "Extra" }, 0);
    JTable tableInvoices = new JTable(tmInvoices);
    JTable tableProducts = new JTable(tmProducts);

    List<Invoice> invoices = new ArrayList<>();
    Invoice currentInvoice = null;

    // invoice input
    JTextField tfInvId = new JTextField(6), tfCustId = new JTextField(6), tfCustName = new JTextField(10),
            tfCustPhone = new JTextField(10), tfDate = new JTextField(8);

    // product input
    JComboBox<String> cbProdType = new JComboBox<>(new String[] { "Picture", "CD" });
    JTextField tfProdId = new JTextField(6), tfTitle = new JTextField(10), tfPrice = new JTextField(6);
    // picture fields
    JTextField tfW = new JTextField(4), tfH = new JTextField(4), tfPainter = new JTextField(8);
    // cd fields
    JTextField tfSinger = new JTextField(8), tfCompany = new JTextField(8);

    public Main16() {
        setTitle("Bai16 - Art Shop Invoices");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("InvID"));
        top.add(tfInvId);
        top.add(new JLabel("CustID"));
        top.add(tfCustId);
        top.add(new JLabel("CustName"));
        top.add(tfCustName);
        top.add(new JLabel("Phone"));
        top.add(tfCustPhone);
        top.add(new JLabel("Date"));
        top.add(tfDate);
        JButton btnNewInv = new JButton("New Invoice"), btnSaveInv = new JButton("Save Invoice");
        top.add(btnNewInv);
        top.add(btnSaveInv);

        // products panel
        JPanel prodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prodPanel.add(new JLabel("ProdType"));
        prodPanel.add(cbProdType);
        prodPanel.add(new JLabel("ID"));
        prodPanel.add(tfProdId);
        prodPanel.add(new JLabel("Title"));
        prodPanel.add(tfTitle);
        prodPanel.add(new JLabel("Price"));
        prodPanel.add(tfPrice);
        prodPanel.add(new JLabel("W"));
        prodPanel.add(tfW);
        prodPanel.add(new JLabel("H"));
        prodPanel.add(tfH);
        prodPanel.add(new JLabel("Painter"));
        prodPanel.add(tfPainter);
        prodPanel.add(new JLabel("Singer"));
        prodPanel.add(tfSinger);
        prodPanel.add(new JLabel("Company"));
        prodPanel.add(tfCompany);
        JButton btnAddProd = new JButton("Add Product to Invoice");
        prodPanel.add(btnAddProd);

        // bottom controls
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnTotalRevenue = new JButton("Total Revenue");
        JButton btnTopCustomers = new JButton("Top Customers");
        bottom.add(btnTotalRevenue);
        bottom.add(btnTopCustomers);

        add(top, BorderLayout.NORTH);
        add(prodPanel, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(tableProducts),
                new JScrollPane(tableInvoices));
        split.setDividerLocation(250);
        add(split, BorderLayout.SOUTH);
        add(bottom, BorderLayout.PAGE_END);

        btnNewInv.addActionListener(evt -> {
            String invId = tfInvId.getText().trim();
            String cid = tfCustId.getText().trim(), cname = tfCustName.getText().trim(),
                    phone = tfCustPhone.getText().trim(), date = tfDate.getText().trim();
            if (invId.isEmpty() || cid.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter invoice id and customer id");
                return;
            }
            Customer c = new Customer(cid, cname, phone);
            currentInvoice = new Invoice(invId, c, date);
            tmProducts.setRowCount(0);
            JOptionPane.showMessageDialog(this,
                    "New invoice created (not yet saved). Add products and then Save Invoice.");
        });

        btnAddProd.addActionListener(evt -> {
            if (currentInvoice == null) {
                JOptionPane.showMessageDialog(this, "Create a new invoice first");
                return;
            }
            try {
                String type = (String) cbProdType.getSelectedItem();
                String pid = tfProdId.getText().trim();
                String title = tfTitle.getText().trim();
                double price = Double.parseDouble(tfPrice.getText().trim());
                if (type.equals("Picture")) {
                    double w = Double.parseDouble(tfW.getText().trim()), h = Double.parseDouble(tfH.getText().trim());
                    String painter = tfPainter.getText().trim();
                    Picture pic = new Picture(pid, title, price, w, h, painter);
                    currentInvoice.add(pic);
                    tmProducts.addRow(pic.row());
                } else {
                    String singer = tfSinger.getText().trim(), comp = tfCompany.getText().trim();
                    CD cd = new CD(pid, title, price, singer, comp);
                    currentInvoice.add(cd);
                    tmProducts.addRow(cd.row());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid product input: " + ex.getMessage());
            }
        });

        btnSaveInv.addActionListener(evt -> {
            if (currentInvoice == null) {
                JOptionPane.showMessageDialog(this, "No invoice to save");
                return;
            }
            invoices.add(currentInvoice);
            tmInvoices.addRow(currentInvoice.row());
            currentInvoice = null;
            tmProducts.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Invoice saved.");
        });

        btnTotalRevenue.addActionListener(evt -> {
            double total = 0;
            for (Invoice inv : invoices)
                total += inv.total();
            JOptionPane.showMessageDialog(this, "Total revenue = " + total);
        });

        btnTopCustomers.addActionListener(evt -> {
            if (invoices.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No invoices");
                return;
            }
            Map<String, Double> sumByCust = new HashMap<>();
            Map<String, String> custName = new HashMap<>();
            for (Invoice inv : invoices) {
                String cid = inv.customer.id;
                sumByCust.put(cid, sumByCust.getOrDefault(cid, 0.0) + inv.total());
                custName.put(cid, inv.customer.name);
            }
            double max = Collections.max(sumByCust.values());
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Double> entry : sumByCust.entrySet()) {
                if (Math.abs(entry.getValue() - max) < 1e-9) {
                    sb.append(entry.getKey())
                            .append(" - ")
                            .append(custName.get(entry.getKey()))
                            .append(" total=")
                            .append(entry.getValue())
                            .append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, "Top customers:\n" + sb.toString());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main16().setVisible(true));
    }
}
