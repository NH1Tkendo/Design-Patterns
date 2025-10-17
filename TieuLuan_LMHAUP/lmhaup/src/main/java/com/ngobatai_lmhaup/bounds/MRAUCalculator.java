package com.ngobatai_lmhaup.bounds;

import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;

public class MRAUCalculator {

    // Strategy Selector để chọn strategy phù hợp
    private final MRAUStrategySelector strategySelector;

    // Constructor mặc định - sử dụng strategy selector
    public MRAUCalculator() {
        this.strategySelector = new MRAUStrategySelector();
    }

    // Constructor cho phép inject custom strategy selector (để test hoặc customize)
    public MRAUCalculator(MRAUStrategySelector strategySelector) {
        this.strategySelector = strategySelector;
    }

    // Tính mrau(P) theo Definition 11
    // Sử dụng Strategy Pattern để chọn cách tính phù hợp cho từng transaction
    public double mrauOf(TAList list) {
        double s = 0.0;
        int len = list.len;

        // Sử dụng Strategy Pattern thay vì if-else
        for (TAListEntry e : list.entries) {
            s += strategySelector.computeMRAU(e, len);
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
