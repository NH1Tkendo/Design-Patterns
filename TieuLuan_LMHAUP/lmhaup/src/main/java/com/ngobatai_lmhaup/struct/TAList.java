package com.ngobatai_lmhaup.struct;

import java.util.ArrayList;
import java.util.List;

public class TAList {
    public final int[] itemset;
    public final int len;
    public final List<TAListEntry> entries = new ArrayList<>(); // sorted by tid

    public TAList(int[] itemset) {
        this.itemset = itemset;
        this.len = itemset.length;
    }

    public void add(TAListEntry e) {
        entries.add(e);
    }
}
