package com.ngobatai_lmhaup.miner;

import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;
import java.util.HashMap;
import java.util.Map;

public class Joiner {

    // Cache để lưu utility của các patterns theo TID
    private Map<String, Map<Integer, Double>> utilityCache = new HashMap<>();

    /**
     * Set cache từ bên ngoài
     */
    public void setUtilityCache(Map<String, Map<Integer, Double>> cache) {
        this.utilityCache = cache;
    }

    /**
     * Join two TA-Lists theo thuật toán LMHAUP
     * 
     * @param P         First TA-List
     * @param Q         Second TA-List
     * @param prefixLen Length of common prefix (0 for 1-item + 1-item)
     * @return Joined TA-List
     */
    public TAList join(TAList P, TAList Q, int prefixLen) {
        // 1. Tạo itemset mới: R = P ∪ {item cuối của Q}
        int[] newItemset = new int[P.len + 1];
        System.arraycopy(P.itemset, 0, newItemset, 0, P.len);
        newItemset[P.len] = Q.itemset[Q.len - 1];

        TAList R = new TAList(newItemset);

        // 2. Join các tuple có cùng TID
        int i = 0, j = 0;
        while (i < P.entries.size() && j < Q.entries.size()) {
            TAListEntry entryP = P.entries.get(i);
            TAListEntry entryQ = Q.entries.get(j);

            if (entryP.tid == entryQ.tid) {
                double util;

                if (prefixLen == 0) {
                    // Join hai 1-itemsets: u(R,T) = u(P,T) + u(Q,T)
                    util = entryP.util + entryQ.util;

                } else {
                    // Join patterns có prefix chung X
                    // P = X ∪ {a}, Q = X ∪ {b}, R = X ∪ {a, b}
                    // u(R,T) = u(P,T) + u(Q,T) - u(X,T)

                    // Lấy utility của prefix từ cache
                    double prefixUtil = getPrefixUtility(P, entryP.tid, prefixLen);

                    // Tính utility của pattern mới
                    util = entryP.util + entryQ.util - prefixUtil;
                }

                // Theo thuật toán LMHAUP:
                // sRLU(R,T) = min(sRLU(P,T), sRLU(Q,T))
                // nRLUI(R,T) = min(nRLUI(P,T), nRLUI(Q,T))
                double sRLU = Math.min(entryP.sRLU, entryQ.sRLU);
                int nRLUI = Math.min(entryP.nRLUI, entryQ.nRLUI);

                R.add(new TAListEntry(entryP.tid, util, sRLU, nRLUI));
                i++;
                j++;
            } else if (entryP.tid < entryQ.tid) {
                i++;
            } else {
                j++;
            }
        }

        return R;
    }

    /**
     * Lấy utility của prefix từ cache
     * 
     * @param pattern   Pattern hiện tại
     * @param tid       Transaction ID
     * @param prefixLen Độ dài prefix
     * @return Utility của prefix trong transaction này
     */
    private double getPrefixUtility(TAList pattern, int tid, int prefixLen) {
        // Tạo prefix itemset
        int[] prefixItemset = new int[prefixLen];
        System.arraycopy(pattern.itemset, 0, prefixItemset, 0, prefixLen);

        // Tạo key cho cache
        String prefixKey = itemsetToString(prefixItemset);

        // Lấy từ cache
        Map<Integer, Double> tidUtilMap = utilityCache.get(prefixKey);
        if (tidUtilMap != null && tidUtilMap.containsKey(tid)) {
            return tidUtilMap.get(tid);
        }

        // Nếu không có trong cache, tính lại từ pattern entries
        // Đây là fallback case - không nên xảy ra thường xuyên
        double fallbackUtil = calculatePrefixUtilityFallback(pattern, tid, prefixLen);

        // Cache lại để lần sau không phải tính
        if (tidUtilMap == null) {
            tidUtilMap = new HashMap<>();
            utilityCache.put(prefixKey, tidUtilMap);
        }
        tidUtilMap.put(tid, fallbackUtil);

        return fallbackUtil;
    }

    /**
     * Fallback: Tính utility của prefix khi không có trong cache
     * Thuật toán: Tìm lại trong cache các sub-patterns và tính ngược lại
     */
    private double calculatePrefixUtilityFallback(TAList pattern, int tid, int prefixLen) {
        // Nếu prefixLen = 0, return 0
        if (prefixLen == 0) {
            return 0.0;
        }

        // Tạo prefix array
        int[] prefixArray = new int[prefixLen];
        System.arraycopy(pattern.itemset, 0, prefixArray, 0, prefixLen);

        // Thử tính từ các 1-itemsets trong prefix
        double estimatedUtil = 0.0;
        boolean foundAll = true;

        for (int i = 0; i < prefixLen; i++) {
            String itemKey = prefixArray[i] + ",";
            Map<Integer, Double> tidUtilMap = utilityCache.get(itemKey);
            if (tidUtilMap != null && tidUtilMap.containsKey(tid)) {
                estimatedUtil += tidUtilMap.get(tid);
            } else {
                foundAll = false;
                break;
            }
        }

        if (!foundAll) {
            // Log warning chỉ cho pattern dài (>= 4 items)
            if (prefixLen >= 4) {
                System.err.println("WARNING: Prefix utility not found in cache for " +
                        itemsetToString(prefixArray) +
                        " (pattern length: " + pattern.len + ", TID=" + tid + ")");
            }
            // Trả về 0 như một ước lượng an toàn
            return 0.0;
        }

        return estimatedUtil;
    }

    /**
     * Convert itemset thành string để làm key
     */
    private String itemsetToString(int[] itemset) {
        StringBuilder sb = new StringBuilder();
        for (int item : itemset) {
            sb.append(item).append(",");
        }
        return sb.toString();
    }
}
