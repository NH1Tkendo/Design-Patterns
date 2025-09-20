package com.ngobatai_lab5;

class Singleton {
    private static Singleton instance;

    private Singleton() {
    };

    private synchronized static void createInstance() {
        if (instance == null)
            instance = new Singleton();
    }

    public static Singleton getInstance() {
        if (instance == null)
            createInstance();
        return instance;
    }
}

public class DoubleCheckedLocking {
    public static void main(String[] args) {
        System.out.println("---Singleton Patern---");
        Singleton single1 = Singleton.getInstance();
        Singleton single2 = Singleton.getInstance();

        if (single1.equals(single2)) {
            System.out.println("Unique instance");
        }
    }
}
