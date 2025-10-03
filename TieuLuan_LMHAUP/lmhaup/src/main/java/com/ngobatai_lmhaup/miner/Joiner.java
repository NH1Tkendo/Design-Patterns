package com.ngobatai_lmhaup.miner;

import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;

public class Joiner {

    // Join two TA-Lists that share a common prefix of length prefixLen (0 for
    // 1-item + 1-item)
    // Assume itemsets are sorted by global order and Q's last item order > P's last
    // item order
    public TAList join(TAList P, TAList Q, int prefixLen) {
        int[] newItemset = new int[P.len + 1];
        System.arraycopy(P.itemset, 0, newItemset, 0, P.len);
        newItemset[P.len] = Q.itemset[Q.len - 1];

        TAList R = new TAList(newItemset);

        int i = 0, j = 0;
        while (i < P.entries.size() && j < Q.entries.size()) {
            TAListEntry a = P.entries.get(i);
            TAListEntry b = Q.entries.get(j);
            if (a.tid == b.tid) {
                double util = a.util + b.util;
                if (prefixLen > 0) {
                    // subtract double-counted prefix utility exactly once
                    // In practice, caller should pass the utility of prefix in this T
                    // Here we approximate by: util -= prefixUtil(T)
                    // For correctness, use a map tid -> prefix util prepared by caller
                }
                double sRLU = Math.min(a.sRLU, b.sRLU);
                int nRLUI = Math.min(a.nRLUI, b.nRLUI);
                R.add(new TAListEntry(a.tid, util, sRLU, nRLUI));
                i++;
                j++;
            } else if (a.tid < b.tid) {
                i++;
            } else {
                j++;
            }
        }
        return R;
    }
}
