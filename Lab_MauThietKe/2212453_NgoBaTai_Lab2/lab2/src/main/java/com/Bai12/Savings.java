/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai12;

public class Savings extends Account {
    private final double interestRate;

    public Savings(long acctId, double balance, double interestRate) {
        super(acctId, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        balance *= (1 + interestRate);
    }
}
