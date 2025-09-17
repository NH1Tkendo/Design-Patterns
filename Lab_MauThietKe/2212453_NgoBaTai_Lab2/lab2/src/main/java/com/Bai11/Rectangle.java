/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai11;

public class Rectangle extends Quad {
    private final int length;

    public Rectangle(int x, int y, int side, int length) {
        super(x, y, side);
        this.length = length;
    }

    @Override
    public void draw() {
        System.out.println("Ve hinh chu nhat tai (" + x + ", " + y + ") voi canh " + side + " va chieu dai " + length);
    }

    @Override
    public void erase() {
        System.out.println("Xoa hinh chu nhat tai (" + x + ", " + y + ")");
    }
}
