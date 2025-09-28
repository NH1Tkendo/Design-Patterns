package com.ngobatai_lmhaup.talist;

import java.util.*;

public class TAList {
    private String pattern; // ví dụ "a", "a,c"
    private List<TALTuple> tuples;

    public TAList(String pattern) {
        this.pattern = pattern;
        this.tuples = new ArrayList<>();
    }

    public String getPattern() {
        return pattern;
    }

    public List<TALTuple> getTuples() {
        return tuples;
    }

    public void addTuple(TALTuple tuple) {
        tuples.add(tuple);
    }
}
