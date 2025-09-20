package com.ngobatai.lab3.Cau3;

import java.util.TreeSet;

public class SetOperation {
    private TreeSet<Integer> A;
    private TreeSet<Integer> B;

    // Constructor
    public SetOperation(TreeSet<Integer> A, TreeSet<Integer> B) {
        this.A = A;
        this.B = B;
    }

    // Hợp
    public TreeSet<Integer> union() {
        TreeSet<Integer> hop = new TreeSet<>(A);
        hop.addAll(B);
        return hop;
    }

    // Giao
    public TreeSet<Integer> intersection() {
        TreeSet<Integer> giao = new TreeSet<>(A);
        giao.retainAll(B);
        return giao;
    }

    // Hiệu A - B
    public TreeSet<Integer> differenceAB() {
        TreeSet<Integer> hieu = new TreeSet<>(A);
        hieu.removeAll(B);
        return hieu;
    }

    // Hiệu B - A
    public TreeSet<Integer> differenceBA() {
        TreeSet<Integer> hieu = new TreeSet<>(B);
        hieu.removeAll(A);
        return hieu;
    }
}
