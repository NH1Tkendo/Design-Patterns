package com.ngobatai_lab5;

class Singleton {
    private static class SingletonHelper {
        static final Singleton instance = new Singleton();
    }

    private Singleton() {
    };

    public static Singleton getInstance() {
        return SingletonHelper.instance;
    }
}

public class ClassLoader {
    public static void main(String[] args) {
        System.out.println("---Singleton Patern---");
        Singleton single1 = Singleton.getInstance();
        Singleton single2 = Singleton.getInstance();

        if (single1.equals(single2)) {
            System.out.println("Unique instance");
        }
    }
}
