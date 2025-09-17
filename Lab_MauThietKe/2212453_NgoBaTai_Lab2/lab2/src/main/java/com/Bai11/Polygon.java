/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai11;

public class Polygon extends Shape {
    private final int sides;

    public Polygon(int x, int y, int sides) {
        super(x, y);
        this.sides = sides;
    }

    @Override
    public void draw() {
        System.out.println("Ve da giac tai (" + x + ", " + y + ") voi " + sides + " canh");
    }

    @Override
    public void erase() {
        System.out.println("Xoa da giac tai (" + x + ", " + y + ")");
    }
}
