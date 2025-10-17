package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAListEntry;

// trường hợp 1: nRLUI != 0
public class MRAUWithRemainingItemsStrategy implements MRAUComputationStrategy {

    @Override
    public double compute(TAListEntry entry, int patternLength) {
        // mrau(P,T) = (util(P,T) + sRLU(P,T)) / (|P| + nRLUI(P,T))
        return (entry.util + entry.sRLU) / (patternLength + entry.nRLUI);
    }

    @Override
    public boolean isApplicable(TAListEntry entry) {
        return entry.nRLUI != 0;
    }

    @Override
    public String getStrategyName() {
        return "MRAU with Remaining Items (nRLUI != 0)";
    }
}
