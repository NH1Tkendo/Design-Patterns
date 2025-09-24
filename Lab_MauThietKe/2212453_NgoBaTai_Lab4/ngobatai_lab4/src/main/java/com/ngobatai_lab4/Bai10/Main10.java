/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Bai10;

/**
 *
 * @author PC728
 */
// Bai10.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/*
  Bai10.java
  Chạy: javac Bai10.java && java Bai10
*/
public class Main10 {
    // ---------- Model ----------
    static class Player {
        int[] jumps = new int[7]; // jump strengths
        int height; // player's body height
        int[] fights = new int[5]; // strengths in 5 rounds

        public Player(int[] jumps, int height, int[] fights) {
            System.arraycopy(jumps, 0, this.jumps, 0, Math.min(jumps.length, 7));
            this.height = height;
            System.arraycopy(fights, 0, this.fights, 0, Math.min(fights.length, 5));
        }

        public Player copy() {
            return new Player(Arrays.copyOf(jumps,7), height, Arrays.copyOf(fights,5));
        }

        @Override
        public String toString() {
            return "Jumps=" + Arrays.toString(jumps) + ", height=" + height + ", fights=" + Arrays.toString(fights);
        }
    }

    static abstract class Obstacle {
        public abstract boolean pass(Player p); // mutates player as needed (e.g., apple)
        public abstract String summary();
    }

    static class Forest extends Obstacle {
        int[] spikes = new int[7];
        int appleBoost; // the apple increases each jump by this value

        public Forest(int appleBoost, int[] spikes) {
            this.appleBoost = appleBoost;
            System.arraycopy(spikes, 0, this.spikes, 0, Math.min(spikes.length,7));
        }

        @Override
        public boolean pass(Player p) {
            // apple applies first: increase player's jumps by appleBoost
            if (appleBoost != 0) {
                for (int i = 0; i < 7; i++) p.jumps[i] += appleBoost;
            }
            // check each jump
            for (int i = 0; i < 7; i++) {
                if (p.jumps[i] <= spikes[i]) return false; // fail if jump <= spike height
            }
            return true;
        }

        @Override
        public String summary() {
            return String.format("Forest(apple=%d, spikes=%s)", appleBoost, Arrays.toString(spikes));
        }
    }

    static class Tunnel extends Obstacle {
        int[] heights;

        public Tunnel(int[] heights) {
            this.heights = Arrays.copyOf(heights, heights.length);
        }

        @Override
        public boolean pass(Player p) {
            // rule from statement: if player.height <= min height of tunnel => pass
            int minH = Integer.MAX_VALUE;
            for (int h : heights) if (h < minH) minH = h;
            return p.height <= minH;
        }

        @Override
        public String summary() {
            return "Tunnel(heights=" + Arrays.toString(heights) + ")";
        }
    }

    static class Monster extends Obstacle {
        int[] powers = new int[5];

        public Monster(int[] powers) {
            System.arraycopy(powers, 0, this.powers, 0, Math.min(powers.length,5));
        }

        @Override
        public boolean pass(Player p) {
            int win = 0, lose = 0;
            for (int i = 0; i < 5; i++) {
                if (p.fights[i] > powers[i]) win++; else lose++;
            }
            return win > lose;
        }

        @Override
        public String summary() {
            return "Monster(powers=" + Arrays.toString(powers) + ")";
        }
    }

    static class TreasureGame {
        List<Obstacle> obstacles = new ArrayList<>();

        public void addObstacle(Obstacle o) { obstacles.add(o); }
        public void clear() { obstacles.clear(); }

        public SimulationResult run(Player p) {
            Player cur = p.copy();
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle o = obstacles.get(i);
                boolean ok = o.pass(cur);
                if (!ok) return new SimulationResult(false, i, cur);
            }
            return new SimulationResult(true, -1, cur);
        }
    }

    static class SimulationResult {
        boolean success;
        int failIndex; // if success=true => -1
        Player finalPlayer;

        public SimulationResult(boolean s, int idx, Player finalPlayer) {
            this.success = s; this.failIndex = idx; this.finalPlayer = finalPlayer;
        }
    }

    // ---------- GUI ----------
    public static class TreasureGameUI extends JFrame {
        private DefaultListModel<String> modelList = new DefaultListModel<>();
        private JList<String> jlist = new JList<>(modelList);
        private TreasureGame game = new TreasureGame();

        // input fields
        private JComboBox<String> cbType;
        private JTextField tfApple, tfSpikes, tfTunnelHeights, tfMonsterPowers;
        private JTextArea taLog;

        // player inputs
        private JTextField[] tfJumps = new JTextField[7];
        private JTextField tfHeight;
        private JTextField[] tfFights = new JTextField[5];

        public TreasureGameUI() {
            setTitle("Bai 10 - Tim kho bau (Treasure Game)");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(900, 700);
            setLocationRelativeTo(null);
            initUI();
        }

        private void initUI() {
            JPanel main = new JPanel(new BorderLayout(8,8));
            getContentPane().add(main);

            // Left panel: add obstacle
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            left.setBorder(BorderFactory.createTitledBorder("Add Obstacle"));

            cbType = new JComboBox<>(new String[]{"Forest (apple+7 spikes)","Tunnel (heights)","Monster (5 powers)"});
            left.add(cbType);
            left.add(new JLabel("Forest: appleBoost (int)"));
            tfApple = new JTextField("0");
            left.add(tfApple);
            left.add(new JLabel("Forest spikes (7 ints, space separated)"));
            tfSpikes = new JTextField("2 2 2 2 2 2 2");
            left.add(tfSpikes);

            left.add(new JLabel("Tunnel heights (space separated)"));
            tfTunnelHeights = new JTextField("3 5 2");
            left.add(tfTunnelHeights);

            left.add(new JLabel("Monster powers (5 ints, space separated)"));
            tfMonsterPowers = new JTextField("1 3 6 9 3");
            left.add(tfMonsterPowers);

            JPanel btnPanel = new JPanel(new FlowLayout());
            JButton btnAdd = new JButton("Add obstacle");
            JButton btnRemove = new JButton("Remove selected");
            JButton btnClear = new JButton("Clear obstacles");
            btnPanel.add(btnAdd); btnPanel.add(btnRemove); btnPanel.add(btnClear);
            left.add(btnPanel);

            main.add(left, BorderLayout.WEST);

            // Center: obstacle list & player input
            JPanel center = new JPanel(new BorderLayout(6,6));
            center.setBorder(BorderFactory.createTitledBorder("Obstacles list / Player"));

            JPanel listPanel = new JPanel(new BorderLayout());
            listPanel.add(new JScrollPane(jlist), BorderLayout.CENTER);
            center.add(listPanel, BorderLayout.CENTER);

            // Player panel
            JPanel pPlayer = new JPanel();
            pPlayer.setLayout(new BoxLayout(pPlayer, BoxLayout.Y_AXIS));
            pPlayer.setBorder(BorderFactory.createTitledBorder("Player"));

            JPanel pJumps = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pJumps.add(new JLabel("Jumps (7): "));
            for (int i = 0; i < 7; i++) {
                tfJumps[i] = new JTextField(3); tfJumps[i].setText("" + (i+3));
                pJumps.add(tfJumps[i]);
            }
            pPlayer.add(pJumps);

            JPanel pHeight = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pHeight.add(new JLabel("Height: "));
            tfHeight = new JTextField(4); tfHeight.setText("3");
            pHeight.add(tfHeight);
            pPlayer.add(pHeight);

            JPanel pFights = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pFights.add(new JLabel("Fights (5): "));
            for (int i = 0; i <5; i++){ tfFights[i] = new JTextField(3); tfFights[i].setText("" + (i+2)); pFights.add(tfFights[i]); }
            pPlayer.add(pFights);

            JButton btnSimulate = new JButton("Simulate");
            pPlayer.add(btnSimulate);

            center.add(pPlayer, BorderLayout.SOUTH);

            main.add(center, BorderLayout.CENTER);

            // Right: log
            JPanel right = new JPanel(new BorderLayout());
            right.setBorder(BorderFactory.createTitledBorder("Result / Log"));
            taLog = new JTextArea();
            taLog.setEditable(false);
            right.add(new JScrollPane(taLog), BorderLayout.CENTER);

            main.add(right, BorderLayout.EAST);

            // Actions
            btnAdd.addActionListener(e -> onAdd());
            btnRemove.addActionListener(e -> onRemove());
            btnClear.addActionListener(e -> {
                game.clear();
                modelList.clear();
                taLog.append("Cleared obstacles.\n");
            });
            btnSimulate.addActionListener(e -> onSimulate());
        }

        private void onAdd() {
            int idx = cbType.getSelectedIndex();
            try {
                if (idx == 0) { // forest
                    int apple = Integer.parseInt(tfApple.getText().trim());
                    String[] parts = tfSpikes.getText().trim().split("\\s+");
                    if (parts.length < 7) { JOptionPane.showMessageDialog(this,"Forest needs 7 spikes"); return; }
                    int[] s = new int[7];
                    for (int i = 0; i < 7; i++) s[i] = Integer.parseInt(parts[i]);
                    Forest f = new Forest(apple, s);
                    game.addObstacle(f);
                    modelList.addElement(f.summary());
                    taLog.append("Added: " + f.summary() + "\n");
                } else if (idx == 1) { // tunnel
                    String[] parts = tfTunnelHeights.getText().trim().split("\\s+");
                    int[] h = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) h[i] = Integer.parseInt(parts[i]);
                    Tunnel t = new Tunnel(h);
                    game.addObstacle(t);
                    modelList.addElement(t.summary());
                    taLog.append("Added: " + t.summary() + "\n");
                } else { // monster
                    String[] parts = tfMonsterPowers.getText().trim().split("\\s+");
                    if (parts.length < 5) { JOptionPane.showMessageDialog(this,"Monster needs 5 powers"); return; }
                    int[] p = new int[5];
                    for (int i = 0; i < 5; i++) p[i] = Integer.parseInt(parts[i]);
                    Monster m = new Monster(p);
                    game.addObstacle(m);
                    modelList.addElement(m.summary());
                    taLog.append("Added: " + m.summary() + "\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nhap so khong hop le: " + ex.getMessage());
            }
        }

        private void onRemove() {
            int sel = jlist.getSelectedIndex();
            if (sel >= 0) {
                modelList.remove(sel);
                game.obstacles.remove(sel);
                taLog.append("Removed obstacle index " + sel + "\n");
            }
        }

        private void onSimulate() {
            try {
                int[] jumps = new int[7];
                for (int i = 0; i < 7; i++) jumps[i] = Integer.parseInt(tfJumps[i].getText().trim());
                int height = Integer.parseInt(tfHeight.getText().trim());
                int[] fights = new int[5];
                for (int i = 0; i < 5; i++) fights[i] = Integer.parseInt(tfFights[i].getText().trim());
                Player p = new Player(jumps, height, fights);
                taLog.append("Starting simulation with player: " + p + "\n");
                SimulationResult res = game.run(p);
                if (res.success) {
                    taLog.append("Player succeeded! final state: " + res.finalPlayer + "\n");
                    JOptionPane.showMessageDialog(this, "Player found the treasure!\n" + res.finalPlayer);
                } else {
                    taLog.append("Player failed at obstacle index " + res.failIndex + " (" + game.obstacles.get(res.failIndex).summary() + ")\n");
                    JOptionPane.showMessageDialog(this, "Player failed at index " + res.failIndex + "\nObstacle: " + game.obstacles.get(res.failIndex).summary());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nhap so khong hop le: " + ex.getMessage());
            }
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TreasureGameUI ui = new TreasureGameUI();
            ui.setVisible(true);
        });
    }
}

