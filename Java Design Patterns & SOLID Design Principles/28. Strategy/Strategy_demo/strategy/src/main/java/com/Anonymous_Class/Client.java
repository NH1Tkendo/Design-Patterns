package com.Anonymous_Class;

import java.util.Collection;
import java.util.LinkedList;

import com.Order;
import com.OrderPrinter;
import com.PrintService;

public class Client {
    private static LinkedList<Order> orders = new LinkedList<>();

    public static void main(String[] args) {
        createOrders();
        System.out.println("Anonymous class implement");
        // print all orders
        PrintService service1 = new PrintService(new OrderPrinter() {
            @Override
            public void print(Collection<Order> orders) {
                double total = 0;
                int i = 0;
                System.out.println("*********************** SUMMARY **************************");
                for (Order order : orders) {
                    System.out.println(i++ + ". " + order.getId() + "\t" + order.getDate()
                            + "\t" + order.getItems().size() + "\t" + order.getTotal());
                    total += order.getTotal();
                }
                System.out.println("**********************************************************");
                System.out.println("\t\t\t Total " + total);
            }
        });

        service1.printOrders(orders);
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
