package com.Enum_Class;

import java.util.Collection;

import com.Order;
import com.OrderPrinter;

public enum PrintStrategy implements OrderPrinter {
    SUMMARY {
        @Override
        public void print(Collection<Order> orders) {
            System.out.println("*********************** SUMMARY **************************");
            double total = 0;
            int i = 0;
            for (Order order : orders) {
                System.out.println(i++ + ". " + order.getId() + "\t"
                        + order.getDate() + "\t"
                        + order.getItems().size() + "\t"
                        + order.getTotal());
                total += order.getTotal();
            }
            System.out.println("**********************************************************");
            System.out.println("\t\t\t Total " + total);
        }
    },
    DETAIL {
        @Override
        public void print(Collection<Order> orders) {
            System.out.println("*********************** DETAIL ***************************");
            for (Order order : orders) {
                System.out.println("Order Id: " + order.getId() + "\t" + order.getDate());
                order.getItems().forEach((name, price) -> System.out.println("\t" + name + "\t" + price));
                System.out.println("\tTotal: " + order.getTotal());
                System.out.println("----------------------------------------------------------");
            }
        }
    };
}
