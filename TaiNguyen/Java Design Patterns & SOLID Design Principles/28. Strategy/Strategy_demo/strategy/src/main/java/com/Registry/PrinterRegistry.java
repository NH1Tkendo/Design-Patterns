package com.Registry;

import java.util.HashMap;
import java.util.Map;

import com.OrderPrinter;
import com.strategy_demo.DetailPrinter;
import com.strategy_demo.SummaryPrinter;

public class PrinterRegistry {
    private static final Map<String, OrderPrinter> registry = new HashMap<>();

    static {
        registry.put("summary", new SummaryPrinter());
        registry.put("detail", new DetailPrinter());
    }

    public static OrderPrinter getPrinter(String type) {
        return registry.get(type.toLowerCase());
    }
}