package com.ngobatai_lmhaup.bounds;

import java.util.ArrayList;
import java.util.List;

import com.ngobatai_lmhaup.struct.TAListEntry;

public class MRAUStrategySelector {

    private final List<MRAUComputationStrategy> strategies;

    public MRAUStrategySelector() {
        strategies = new ArrayList<>();

        strategies.add(new MRAUWithRemainingItemsStrategy());
        strategies.add(new MRAUWithSumRemainingUtilityStrategy());
        strategies.add(new MRAUZeroStrategy());
    }

    public MRAUComputationStrategy selectStrategy(TAListEntry entry) {
        for (MRAUComputationStrategy strategy : strategies) {
            if (strategy.isApplicable(entry)) {
                return strategy;
            }
        }

        return new MRAUZeroStrategy();
    }

    public double computeMRAU(TAListEntry entry, int patternLength) {
        MRAUComputationStrategy strategy = selectStrategy(entry);
        return strategy.compute(entry, patternLength);
    }
}
