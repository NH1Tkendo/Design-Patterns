package com.ngobatai_lab7.Bai2;

public class TotalPriceCaculator {
    private PriceStrategy price_strategy;

    public void setStrategy(PriceStrategy price_strategy) {
        this.price_strategy = price_strategy;
    }

    public double compute(double money) {
        double afterDiscount = price_strategy.applyDiscounts(money);
        double afterTax = price_strategy.addTaxes(afterDiscount);
        double finalAmount = price_strategy.convertCurrency(afterTax);
        return finalAmount;
    }
}
