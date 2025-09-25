package com.ngobatai_lab7.Bai2;

public interface PriceStrategy {
    double applyDiscounts(double money);

    double addTaxes(double money);

    double convertCurrency(double money);
}
