package com.ngobatai_lmhaup.strategy;

import com.ngobatai_lmhaup.core.Pattern;

public interface PruningStrategy {
    boolean canPrune(Pattern pattern, double minUtil);
}
