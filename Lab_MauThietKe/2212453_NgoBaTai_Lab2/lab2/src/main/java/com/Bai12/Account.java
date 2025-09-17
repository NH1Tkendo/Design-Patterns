/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai12;

abstract class Account {
    protected double balance;
    protected long acctId;

    public Account(long acctId, double balance) {
        this.acctId = acctId;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("So du khong du!");
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void applyInterest();
}
