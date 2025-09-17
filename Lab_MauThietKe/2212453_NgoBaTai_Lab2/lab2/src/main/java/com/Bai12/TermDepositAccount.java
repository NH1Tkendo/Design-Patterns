/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai12;

public class TermDepositAccount extends Account {
    private final int termMonths;
    private final double interestRate;

    public TermDepositAccount(long acctId, double balance, int termMonths, double interestRate) {
        super(acctId, balance);
        this.termMonths = termMonths;
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Khong the rut truoc thoi han!");
    }

    @Override
    public void applyInterest() {
        balance *= Math.pow(1 + interestRate, termMonths / 12.0);
    }
}
