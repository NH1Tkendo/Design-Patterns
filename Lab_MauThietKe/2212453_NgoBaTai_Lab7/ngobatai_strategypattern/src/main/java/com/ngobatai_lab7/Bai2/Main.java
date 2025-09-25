package com.ngobatai_lab7.Bai2;

public class Main {
    public static void main(String[] args) {
        TotalPriceCaculator context = new TotalPriceCaculator();

        PriceStrategy us = new USPriceStrategy();
        PriceStrategy euro = new EuroPriceStrategy();

        context.setStrategy(us);
        double priceUSD = context.compute(100);
        context.setStrategy(euro);
        double priceEUR = context.compute(100);

        System.out.println("100 USD (sau xử lý): " + priceUSD + " VND");
        System.out.println("100 EUR (sau xử lý): " + priceEUR + " VND");
    }
}
