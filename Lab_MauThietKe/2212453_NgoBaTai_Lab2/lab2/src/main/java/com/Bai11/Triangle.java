/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai11;

public class Triangle extends Shape {
    private final int base;
    private final int height;

    public Triangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Ve hinh tam giac tai (" + x + ", " + y + ") voi day " + base + " va chieu cao " + height);
    }

    @Override
    public void erase() {
        System.out.println("Xoa hinh tam giac tai (" + x + ", " + y + ")");
    }
}
