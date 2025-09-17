/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuanLyPerson {
    private ArrayList<Person> danhSach;
    private int tongSo;

    public QuanLyPerson(int dungLuong) {
        danhSach = new ArrayList<>(dungLuong);
        tongSo = 0;
    }

    public boolean themPerson(Person p) {
        if (tongSo >= danhSach.size()) {
            // Tang dung luong 50%
            int newSize = danhSach.size() + danhSach.size() / 2;
            ArrayList<Person> newList = new ArrayList<>(newSize);
            newList.addAll(danhSach);
            danhSach = newList;
        }
        danhSach.add(p);
        tongSo++;
        return true;
    }

    public void xoaPerson(String hoTen) {
        for (int i = 0; i < tongSo; i++) {
            if (danhSach.get(i).getHoTen().equals(hoTen)) {
                danhSach.remove(i);
                tongSo--;
                return;
            }
        }
    }

    public void sapXep() {
        Collections.sort(danhSach.subList(0, tongSo), Comparator.comparing(Person::getHoTen));
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach:");
        for (int i = 0; i < tongSo; i++) {
            System.out.println(danhSach.get(i));
            System.out.println("------------------------");
        }
    }
}
