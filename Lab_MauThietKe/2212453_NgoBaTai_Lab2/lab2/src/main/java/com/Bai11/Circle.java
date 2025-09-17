/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai11;

public class Circle extends Shape {
    private final int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Ve hinh tron tai (" + x + ", " + y + ") voi ban kinh " + radius);
    }

    @Override
    public void erase() {
        System.out.println("Xoa hinh tron tai (" + x + ", " + y + ")");
    }
}
