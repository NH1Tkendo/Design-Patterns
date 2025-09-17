/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai7;

import java.util.ArrayList;

public class QuanLyHangHoa {
    private final ArrayList<HangHoa> danhSach;

    public QuanLyHangHoa() {
        danhSach = new ArrayList<>();
    }

    public boolean themHangHoa(HangHoa h) {
        for (HangHoa hh : danhSach) {
            if (hh.getMaHang().equals(h.getMaHang())) {
                return false;
            }
        }
        danhSach.add(h);
        return true;
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach hang hoa:");
        for (HangHoa h : danhSach) {
            h.displayInfo();
        }
    }
}
