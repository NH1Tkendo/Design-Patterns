/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai11;

public class Quad extends Shape {
    protected final int side;

    public Quad(int x, int y, int side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Ve hinh vuong tai (" + x + ", " + y + ") voi canh " + side);
    }

    @Override
    public void erase() {
        System.out.println("Xoa hinh vuong tai (" + x + ", " + y + ")");
    }
}
