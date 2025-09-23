package com.Enum_Class;

import java.util.LinkedList;

import com.Order;
import com.PrintService;

public class Client {
    private static LinkedList<Order> orders = new LinkedList<>();

    public static void main(String[] args) {
        createOrders();
        System.out.println("Enum implement");
        // Dùng strategy SUMMARY
        PrintService summaryService = new PrintService(PrintStrategy.SUMMARY);
        summaryService.printOrders(orders);

        System.out.println();

        // Dùng strategy DETAIL
        PrintService detailService = new PrintService(PrintStrategy.DETAIL);
        detailService.printOrders(orders);
    }

    private static void createOrders() {
        Order o = new Order("100");
        o.addItem("Soda", 2);
        o.addItem("Chips", 10);
        orders.add(o);

        o = new Order("200");
        o.addItem("Cake", 20);
        o.addItem("Cookies", 5);
        orders.add(o);

        o = new Order("300");
        o.addItem("Burger", 8);
        o.addItem("Fries", 5);
        orders.add(o);
    }
}
