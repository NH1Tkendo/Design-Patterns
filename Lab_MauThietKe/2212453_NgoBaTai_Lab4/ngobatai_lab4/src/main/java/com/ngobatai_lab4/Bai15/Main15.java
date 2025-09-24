package com.ngobatai_lab4.Bai15;

// Bai15.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.*;

public class Main15 extends JFrame {
    static class Animal {
        String species; // "Mosquito","Butterfly","Frog"
        int[] stages; // durations

        public Animal(String species, int[] stages) {
            this.species = species;
            this.stages = stages;
        }

        public Object[] row() {
            return new Object[] { species, Arrays.toString(stages) };
        }
    }

    // default typical ranges per species and stage (min,max)
    // We allow user to edit them via dialog if needed
    Map<String, int[][]> typical = new HashMap<>();

    List<Animal> list = new ArrayList<>();
    DefaultTableModel tm = new DefaultTableModel(new Object[] { "Species", "Stages" }, 0);
    JTable table = new JTable(tm);

    JTextField tfSpecies = new JTextField(10);
    JTextField tfStages = new JTextField(20);

    public Main15() {
        setTitle("Bai15 - Life cycle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        initDefaults();
        initUI();
    }

    private void initDefaults() {
        // For simplicity we define 4-stage animals
        // Mosquito: egg(1-3), larva(3-14), pupa(1-5), adult->first egg(2-14)
        typical.put("Mosquito", new int[][] { { 1, 3 }, { 3, 14 }, { 1, 5 }, { 2, 14 } });
        // Butterfly: egg(3-8), larva(10-30), pupa(7-20), adult->egg(1-5)
        typical.put("Butterfly", new int[][] { { 3, 8 }, { 10, 30 }, { 7, 20 }, { 1, 5 } });
        // Frog (approx): egg(2-14), tadpole(30-120), metamorphosis(7-30),
        // adult->breed(30-365)
        typical.put("Frog", new int[][] { { 2, 14 }, { 30, 120 }, { 7, 30 }, { 30, 365 } });
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> cbSpecies = new JComboBox<>(new String[] { "Mosquito", "Butterfly", "Frog" });
        tfStages.setToolTipText("Enter durations comma separated, e.g. 2,9,3,5");
        JButton btnAdd = new JButton("Add");
        top.add(new JLabel("Species:"));
        top.add(cbSpecies);
        top.add(new JLabel("Stages:"));
        top.add(tfStages);
        top.add(btnAdd);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCheck = new JButton("Check abnormal indices");
        JButton btnMaxMos = new JButton("Max mosquito egg->pupa");
        JButton btnEditRanges = new JButton("Edit typical ranges");
        right.add(btnCheck);
        right.add(btnMaxMos);
        right.add(btnEditRanges);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(right, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            String sp = (String) cbSpecies.getSelectedItem();
            String s = tfStages.getText().trim();
            if (s.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter stages");
                return;
            }
            try {
                String[] parts = s.split(",");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++)
                    arr[i] = Integer.parseInt(parts[i].trim());
                if (arr.length != 4) {
                    JOptionPane.showMessageDialog(this, "Expect 4 stages. Example: 2,9,3,5");
                    return;
                }
                Animal a = new Animal(sp, arr);
                list.add(a);
                tm.addRow(a.row());
                tfStages.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        btnCheck.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                Animal a = list.get(i);
                int[][] ranges = typical.get(a.species);
                boolean abnormal = false;
                if (ranges == null) {
                    abnormal = false;
                } else {
                    for (int j = 0; j < ranges.length && j < a.stages.length; j++) {
                        int v = a.stages[j];
                        int min = ranges[j][0], max = ranges[j][1];
                        if (v < min || v > max) {
                            abnormal = true;
                            break;
                        }
                    }
                }
                if (abnormal)
                    sb.append(i).append(": ").append(a.species).append(" ").append(Arrays.toString(a.stages))
                            .append("\n");
            }
            if (sb.length() == 0)
                sb.append("No abnormal animals found.");
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnMaxMos.addActionListener(e -> {
            int bestIndex = -1;
            int bestSum = -1;
            for (int i = 0; i < list.size(); i++) {
                Animal a = list.get(i);
                if ("Mosquito".equals(a.species)) {
                    int sum = a.stages[0] + a.stages[1]; // egg->pupa = first two stages
                    if (sum > bestSum) {
                        bestSum = sum;
                        bestIndex = i;
                    }
                }
            }
            if (bestIndex == -1)
                JOptionPane.showMessageDialog(this, "No mosquitoes in list");
            else
                JOptionPane.showMessageDialog(this, "Max mosquito at index " + bestIndex + " sum egg->pupa = " + bestSum
                        + "\n" + Arrays.toString(list.get(bestIndex).stages));
        });

        btnEditRanges.addActionListener(e -> editRangesDialog());
    }

    private void editRangesDialog() {
        // Simple dialog to let user edit ranges textually
        StringBuilder sb = new StringBuilder();
        for (String sp : typical.keySet()) {
            sb.append(sp).append(":");
            int[][] r = typical.get(sp);
            for (int i = 0; i < r.length; i++)
                sb.append(String.format(" [%d-%d]", r[i][0], r[i][1]));
            sb.append("\n");
        }
        String input = JOptionPane.showInputDialog(this,
                "Edit ranges (format example shown). Current:\n" + sb.toString() +
                        "\nTo change, enter lines like: Mosquito:1-3,3-14,1-5,2-14",
                "");
        if (input == null)
            return;
        // parse multiple lines (split by ;)
        String[] lines = input.split(";");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split(":");
            if (parts.length < 2)
                continue;
            String sp = parts[0].trim();
            String rangesStr = parts[1].trim();
            String[] segs = rangesStr.split(",");
            int[][] newRanges = new int[segs.length][2];
            boolean ok = true;
            for (int i = 0; i < segs.length; i++) {
                String s = segs[i].trim();
                if (!s.contains("-")) {
                    ok = false;
                    break;
                }
                String[] mm = s.split("-");
                try {
                    newRanges[i][0] = Integer.parseInt(mm[0].trim());
                    newRanges[i][1] = Integer.parseInt(mm[1].trim());
                } catch (Exception ex) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                typical.put(sp, newRanges);
        }
        JOptionPane.showMessageDialog(this, "Ranges updated (if parsing succeeded).");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main15().setVisible(true));
    }
}
