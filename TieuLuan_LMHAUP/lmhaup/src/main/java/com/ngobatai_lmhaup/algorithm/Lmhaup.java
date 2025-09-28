package com.ngobatai_lmhaup.algorithm;

import java.util.*;

import com.ngobatai_lmhaup.core.Pattern;
import com.ngobatai_lmhaup.core.Transaction;
import com.ngobatai_lmhaup.strategy.PruningStrategy;
import com.ngobatai_lmhaup.talist.TAList;

public class Lmhaup {
    private PruningStrategy strategy;

    public Lmhaup(PruningStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Pattern> mine(List<Transaction> db, double minUtil) {
        List<Pattern> haups = new ArrayList<>();

        // Bước 1: xây dựng TA-List cho item 1-length (chưa code chi tiết)
        List<TAList> taLists = buildInitialTALists(db);

        // Bước 2: khai phá theo đệ quy
        for (TAList taList : taLists) {
            Pattern p = evaluatePattern(taList);
            if (!strategy.canPrune(p, minUtil)) {
                explorePattern(p, taList, minUtil, haups);
            }
        }
        return haups;
    }

    private List<TAList> buildInitialTALists(List<Transaction> db) {
        // TODO: xây dựng TA-List từ database
        return new ArrayList<>();
    }

    private Pattern evaluatePattern(TAList taList) {
        // TODO: tính au, tmaub, mrau cho pattern
        return new Pattern(taList.getPattern());
    }

    private void explorePattern(Pattern p, TAList taList, double minUtil, List<Pattern> haups) {
        if (p.getAu() >= minUtil) {
            haups.add(p);
        }
        // TODO: join TA-Lists, tạo super-pattern và tiếp tục đệ quy
    }
}
