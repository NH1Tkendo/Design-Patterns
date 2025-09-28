package com.ngobatai_lmhaup.strategy;

import com.ngobatai_lmhaup.core.Pattern;

public class MrauPruning implements PruningStrategy {
    @Override
    public boolean canPrune(Pattern pattern, double minUtil) {
        return pattern.getMrau() < minUtil;
    }
}
