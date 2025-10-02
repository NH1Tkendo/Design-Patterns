package com.ngobatai_lmhaup.struct;

public class TAListEntry {
    public int tid;
    public double util; // u(P, T)
    public double sRLU; // sum of remaining large utility
    public int nRLUI; // number of remaining large items
    public int nextInTid;

    public TAListEntry(int tid, double util, double sRLU, int nRLUI) {
        this.tid = tid;
        this.util = util;
        this.sRLU = sRLU;
        this.nRLUI = nRLUI;
        this.nextInTid = -1;
    }
}
