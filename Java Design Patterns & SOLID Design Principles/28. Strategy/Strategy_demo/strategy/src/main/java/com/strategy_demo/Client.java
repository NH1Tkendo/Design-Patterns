package com.strategy_demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import com.Order;
import com.OrderPrinter;
import com.PrintService;

public class Client {

    private static LinkedList<Order> orders = new LinkedList<>();

    public static void main(String[] args) {
        createOrders();

        // print all orders
        // PrintService service1 = new PrintService(new SummaryPrinter());
        // service1.printOrders(orders);

        // PrintService service2 = new PrintService(new DetailPrinter());
        // service2.printOrders(orders);
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine(); // detail hoac summary
        sc.close();

        PrintService service = new PrintService(selectPrinter(option));
        service.printOrders(orders);
    }

    private static OrderPrinter selectPrinter(String option) {
        Map<String, OrderPrinter> printers = new HashMap<>();
        printers.put("summary", new SummaryPrinter());
        printers.put("detail", new DetailPrinter());
        return printers.get(option); // tự động lấy strategy theo key
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
