package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAListEntry;

// trường hợp 3: nRLUI = 0 and sRLU = 0
public class MRAUZeroStrategy implements MRAUComputationStrategy {

    @Override
    public double compute(TAListEntry entry, int patternLength) {
        // mrau(P,T) = 0 when both nRLUI and sRLU are 0
        return 0.0;
    }

    @Override
    public boolean isApplicable(TAListEntry entry) {
        return entry.nRLUI == 0 && entry.sRLU == 0.0;
    }

    @Override
    public String getStrategyName() {
        return "MRAU Zero (nRLUI=0, sRLU=0)";
    }
}
