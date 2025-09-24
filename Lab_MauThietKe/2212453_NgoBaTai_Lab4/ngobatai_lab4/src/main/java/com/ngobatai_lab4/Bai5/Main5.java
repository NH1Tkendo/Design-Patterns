package com.ngobatai_lab4.Bai5;

/**
 *
 * @author PC728
 */
// Bai05.java
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main5 {
    // Model
    public static abstract class UIComponent {
        String type;
        int x, y, w, h;
        Color fg, bg;
        String text;

        public UIComponent(String type, int x, int y, int w, int h, Color fg, Color bg, String text) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.fg = fg;
            this.bg = bg;
            this.text = text;
        }

        public String toString() {
            return String.format("%s [%d,%d %dx%d] fg=%s bg=%s txt=%s", type, x, y, w, h, colorToString(fg),
                    colorToString(bg), text);
        }

        private String colorToString(Color c) {
            return String.format("(%d,%d,%d)", c.getRed(), c.getGreen(), c.getBlue());
        }
    }

    public static class LabelComp extends UIComponent {
        public LabelComp(int x, int y, int w, int h, Color fg, Color bg, String text) {
            super("Label", x, y, w, h, fg, bg, text);
        }
    }

    public static class ButtonComp extends UIComponent {
        public ButtonComp(int x, int y, int w, int h, Color fg, Color bg, String text) {
            super("Button", x, y, w, h, fg, bg, text);
        }
    }

    // Utils: HSB based checks
    private static float[] rgbToHsb(Color c) {
        return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
    }

    private static String rgbToStr(Color c) {
        return String.format("(%d,%d,%d)", c.getRed(), c.getGreen(), c.getBlue());
    }

    private static Color complement(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

    private static float hueDeg(float h) {
        return h * 360f;
    }

    // GUI
    public static class UI extends JFrame {
        DefaultTableModel tm = new DefaultTableModel(new Object[] { "Type", "x", "y", "w", "h", "fg", "bg", "text" },
                0);
        java.util.List<UIComponent> comps = new ArrayList<>();
        JTable table;
        JSpinner sx, sy, sw, sh, sfgR, sfgG, sfgB, sbgR, sbgG, sbgB;
        JTextField tfText;
        JComboBox<String> cbType;
        JColorChooser baseChooser;

        public UI() {
            setTitle("Bai05 - Phối màu giao diện");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1000, 600);
            setLocationRelativeTo(null);
            init();
        }

        void init() {
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            cbType = new JComboBox<>(new String[] { "Label", "Button" });
            left.add(new JLabel("Loại:"));
            left.add(cbType);
            sx = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 1));
            sy = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 1));
            sw = new JSpinner(new SpinnerNumberModel(100, 1, 2000, 1));
            sh = new JSpinner(new SpinnerNumberModel(30, 1, 2000, 1));
            left.add(new JLabel("x,y,w,h:"));
            left.add(sx);
            left.add(sy);
            left.add(sw);
            left.add(sh);
            JPanel pfg = new JPanel();
            pfg.add(new JLabel("fg R G B"));
            sfgR = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
            sfgG = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
            sfgB = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
            pfg.add(sfgR);
            pfg.add(sfgG);
            pfg.add(sfgB);
            left.add(pfg);
            JPanel pbg = new JPanel();
            pbg.add(new JLabel("bg R G B"));
            sbgR = new JSpinner(new SpinnerNumberModel(255, 0, 255, 1));
            sbgG = new JSpinner(new SpinnerNumberModel(255, 0, 255, 1));
            sbgB = new JSpinner(new SpinnerNumberModel(255, 0, 255, 1));
            pbg.add(sbgR);
            pbg.add(sbgG);
            pbg.add(sbgB);
            left.add(pbg);
            left.add(new JLabel("Text:"));
            tfText = new JTextField("Sample", 15);
            left.add(tfText);
            JButton btnAdd = new JButton("Add component");
            left.add(btnAdd);
            btnAdd.addActionListener(e -> addComp());
            getContentPane().add(left, BorderLayout.WEST);

            table = new JTable(tm);
            getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

            JPanel right = new JPanel();
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            baseChooser = new JColorChooser(Color.WHITE);
            right.add(new JLabel("Base color (for checking rules)"));
            right.add(baseChooser);
            JButton btnCheckFirst = new JButton("Check first comp complementary?");
            JButton btnCheckRules = new JButton("Check rules vs base color");
            right.add(btnCheckFirst);
            right.add(btnCheckRules);
            JTextArea ta = new JTextArea(12, 30);
            ta.setEditable(false);
            right.add(new JScrollPane(ta));
            getContentPane().add(right, BorderLayout.EAST);

            btnCheckFirst.addActionListener(e -> {
                if (comps.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No components");
                    return;
                }
                UIComponent c = comps.get(0);
                Color expected = complement(c.bg);
                boolean equal = expected.equals(c.fg);
                ta.append("Comp0 bg=" + rgbToStr(c.bg) + " fg=" + rgbToStr(c.fg) + " complement(bg)="
                        + rgbToStr(expected) + " => " + (equal ? "YES" : "NO") + "\n");
            });

            btnCheckRules.addActionListener(e -> {
                Color base = baseChooser.getColor();
                float baseHue = hueDeg(rgbToHsb(base)[0]);
                for (int i = 0; i < comps.size(); i++) {
                    UIComponent c = comps.get(i);
                    float h = hueDeg(rgbToHsb(c.bg)[0]);
                    float diff = Math.abs(h - baseHue);
                    if (diff > 180)
                        diff = 360 - diff;
                    String rule;
                    if (diff <= 30)
                        rule = "Analogous (close hue)";
                    else if (Math.abs(diff - 180) <= 30)
                        rule = "Complementary (approx opposite)";
                    else if (Math.abs(diff - 120) <= 25 || Math.abs(diff - 240) <= 25)
                        rule = "Triadic (approx 120deg)";
                    else
                        rule = "No match (other)";
                    ta.append(String.format("Comp[%d] bg=%s -> hue diff=%.1f => %s\n", i, rgbToStr(c.bg), diff, rule));
                }
            });
        }

        void addComp() {
            try {
                int x = (Integer) sx.getValue(), y = (Integer) sy.getValue(), w = (Integer) sw.getValue(),
                        h = (Integer) sh.getValue();
                Color fg = new Color((Integer) sfgR.getValue(), (Integer) sfgG.getValue(), (Integer) sfgB.getValue());
                Color bg = new Color((Integer) sbgR.getValue(), (Integer) sbgG.getValue(), (Integer) sbgB.getValue());
                String text = tfText.getText().trim();
                String t = (String) cbType.getSelectedItem();
                UIComponent comp = t.equals("Label") ? new LabelComp(x, y, w, h, fg, bg, text)
                        : new ButtonComp(x, y, w, h, fg, bg, text);
                comps.add(comp);
                tm.addRow(new Object[] { comp.type, comp.x, comp.y, comp.w, comp.h, rgbToStr(comp.fg),
                        rgbToStr(comp.bg), comp.text });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }
}
