package com.strategy_demo;

import java.util.Collection;

//Strategy
public interface OrderPrinter {
    void print(Collection<Order> order);
}
