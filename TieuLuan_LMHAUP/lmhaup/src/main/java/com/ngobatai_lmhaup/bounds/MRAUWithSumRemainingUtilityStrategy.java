package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAListEntry;

// trường hợp 2: nRLUI = 0 but sRLU != 0
public class MRAUWithSumRemainingUtilityStrategy implements MRAUComputationStrategy {

    @Override
    public double compute(TAListEntry entry, int patternLength) {
        // mrau(P,T) = (util(P,T) + sRLU(P,T)) / (|P| + 1)
        return (entry.util + entry.sRLU) / (patternLength + 1.0);
    }

    @Override
    public boolean isApplicable(TAListEntry entry) {
        return entry.nRLUI == 0 && entry.sRLU != 0.0;
    }

    @Override
    public String getStrategyName() {
        return "MRAU with Sum Remaining Utility (nRLUI=0, sRLU!=0)";
    }
}
