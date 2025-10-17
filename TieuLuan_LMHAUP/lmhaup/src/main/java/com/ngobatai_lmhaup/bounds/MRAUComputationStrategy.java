package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAListEntry;

/**
 * Strategy interface for different MRAU computation methods
 */
public interface MRAUComputationStrategy {

    double compute(TAListEntry entry, int patternLength);

    boolean isApplicable(TAListEntry entry);

    String getStrategyName();
}
