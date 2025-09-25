package com.ngobatai_lab7.Bai2;

public class EuroPriceStrategy implements PriceStrategy {

    private static final double DISCOUNT_RATE = 0.1; // 10%
    private static final double TAX_RATE = 0.20; // 20%
    private static final double EUR_TO_VND = 27000; // tỷ giá giả định

    @Override
    public double applyDiscounts(double money) {
        return money * (1 - DISCOUNT_RATE);
    }

    @Override
    public double addTaxes(double money) {
        return money * (1 + TAX_RATE);
    }

    @Override
    public double convertCurrency(double money) {
        return money * EUR_TO_VND;
    }

}
