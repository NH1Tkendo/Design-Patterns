package com.ngobatai_lab5;

enum Singleton {
    SINGLETON;
}

public class EnumSingleton {
    public static void main(String[] args) {
        System.out.println("---Singleton Patern---");
        Singleton single1 = Singleton.SINGLETON;
        Singleton single2 = Singleton.SINGLETON;

        if (single1.equals(single2)) {
            System.out.println("Unique instance");
        }
    }
}
