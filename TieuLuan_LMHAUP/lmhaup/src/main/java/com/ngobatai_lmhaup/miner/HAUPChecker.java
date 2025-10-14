package com.ngobatai_lmhaup.miner;

import java.util.ArrayList;
import java.util.List;

/**
 * Class kiểm tra và thêm HAUPs
 */
public class HAUPChecker {

    private final List<int[]> haupItemsets;
    private final List<Double> haupAU;
    private final double minutil;
    private final int maxHAUPs;
    private boolean limitWarningShown = false;

    public HAUPChecker(double minutil, int maxHAUPs) {
        this.haupItemsets = new ArrayList<>();
        this.haupAU = new ArrayList<>();
        this.minutil = minutil;
        this.maxHAUPs = maxHAUPs;
    }

    /**
     * Kiểm tra và thêm HAUP nếu thỏa mãn
     * 
     * @return true nếu được thêm vào, false nếu không
     */
    public boolean checkAndAdd(int[] itemset, double au) {
        if (au >= minutil) {
            if (haupItemsets.size() < maxHAUPs) {
                haupItemsets.add(itemset);
                haupAU.add(au);
                System.out.printf("Found HAUP: %s, AU=%.2f%n",
                        itemsetToDisplayString(itemset), au);
                return true;
            } else if (!limitWarningShown) {
                System.out.printf("⚠ HAUP limit reached (%d). Stopping adding new HAUPs...%n", maxHAUPs);
                limitWarningShown = true;
            }
        }
        return false;
    }

    /**
     * Get danh sách HAUPs
     */
    public List<int[]> getHAUPItemsets() {
        return haupItemsets;
    }

    /**
     * Get danh sách AU của HAUPs
     */
    public List<Double> getHAUPAU() {
        return haupAU;
    }

    /**
     * Get số lượng HAUPs đã tìm được
     */
    public int getHAUPCount() {
        return haupItemsets.size();
    }

    /**
     * Convert itemset thành string để hiển thị
     */
    private String itemsetToDisplayString(int[] itemset) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < itemset.length; i++) {
            sb.append(itemset[i]);
            if (i < itemset.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
