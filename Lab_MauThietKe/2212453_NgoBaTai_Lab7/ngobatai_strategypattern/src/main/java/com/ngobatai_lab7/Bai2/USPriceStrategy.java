package com.ngobatai_lab7.Bai2;

public class USPriceStrategy implements PriceStrategy {

    @Override
    public double applyDiscounts(double money) {
        return money * 0.9;
    }

    @Override
    public double addTaxes(double money) {
        return money * 1.08;
    }

    @Override
    public double convertCurrency(double money) {
        double rate = 25000; // tỷ giá giả định
        return money * rate;
    }

}
