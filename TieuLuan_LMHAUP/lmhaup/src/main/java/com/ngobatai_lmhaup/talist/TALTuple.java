package com.ngobatai_lmhaup.talist;

public class TALTuple {
    private int tid;
    private double util;
    private double sRLU;
    private int nRLUI;

    public TALTuple(int tid, double util, double sRLU, int nRLUI) {
        this.tid = tid;
        this.util = util;
        this.sRLU = sRLU;
        this.nRLUI = nRLUI;
    }

    public int getTid() {
        return tid;
    }

    public double getUtil() {
        return util;
    }

    public double getsRLU() {
        return sRLU;
    }

    public int getnRLUI() {
        return nRLUI;
    }
}
