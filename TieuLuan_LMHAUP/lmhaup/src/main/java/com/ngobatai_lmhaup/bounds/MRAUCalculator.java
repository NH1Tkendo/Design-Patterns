package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;

public class MRAUCalculator {
    // mrau(P) = sum over T of mrau(P, T) by Definition 11
    public double mrauOf(TAList list) {
        double s = 0.0;
        int len = list.len;
        for (TAListEntry e : list.entries) {
            if (e.nRLUI != 0) {
                // Case 1: nRLUI != 0
                s += (e.util + e.sRLU) / (len + e.nRLUI);
            } else if (e.sRLU != 0.0) {
                // Case 2: nRLUI = 0 but sRLU != 0
                s += (e.util + e.sRLU) / (len + 1.0);
            }
            // Case 3: nRLUI = 0 and sRLU = 0 -> mrau = 0, không cần cộng gì
        }
        return s;
    }

    // au(P) over DB for decision to output
    public double auOf(TAList list) {
        double s = 0.0;
        int len = list.len;
        for (TAListEntry e : list.entries)
            s += e.util / len;
        return s;
    }
}
