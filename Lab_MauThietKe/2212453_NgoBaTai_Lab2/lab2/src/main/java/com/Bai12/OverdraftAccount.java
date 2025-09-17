/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai12;

public class OverdraftAccount extends Account {
    private final double overdraftLimit;

    public OverdraftAccount(long acctId, double balance, double overdraftLimit) {
        super(acctId, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
        } else {
            System.out.println("Vuot han muc vay no!");
        }
    }

    @Override
    public void applyInterest() {
        if (balance < 0) {
            balance *= 1.15; // Lãi phạt 15%
        }
    }
}
