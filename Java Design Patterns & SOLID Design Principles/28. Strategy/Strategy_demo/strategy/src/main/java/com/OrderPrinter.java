package com;

import java.util.Collection;

//Strategy
public interface OrderPrinter {
    void print(Collection<Order> order);
}
