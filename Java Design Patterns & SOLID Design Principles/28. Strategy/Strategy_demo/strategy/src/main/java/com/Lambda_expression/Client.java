package com.Lambda_expression;

import java.util.LinkedList;

import com.Order;
import com.PrintService;

public class Client {
    private static LinkedList<Order> orders = new LinkedList<>();

    public static void main(String[] args) {
        createOrders();

        System.out.println("Lambda implement");
        // Strategy 1: SummaryPrinter (in tóm tắt đơn hàng)
        PrintService summaryService = new PrintService(orders -> {
            double total = 0;
            int i = 0;
            System.out.println("*********************** INVOICE SUMMARY **************************");
            for (Order order : orders) {
                System.out.println(i++ + ". " + order.getId() + "\t" + order.getDate()
                        + "\t" + order.getItems().size() + " items \t" + order.getTotal());
                total += order.getTotal();
            }
            System.out.println("*****************************************************************");
            System.out.println("\t\t\t Total: " + total);
        });

        summaryService.printOrders(orders);

        // Strategy 2: DetailPrinter (in chi tiết từng món)
        PrintService detailService = new PrintService(orders -> {
            System.out.println("*********************** INVOICE DETAIL **************************");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId() + "\tDate: " + order.getDate());
                System.out.println("Items:");
                order.getItems().forEach((name, qty) -> {
                    System.out.println("   - " + name + " x " + qty);
                });
                System.out.println("Order Total: " + order.getTotal());
                System.out.println("---------------------------------------------------------------");
            }
            System.out.println("*****************************************************************");
        });

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
