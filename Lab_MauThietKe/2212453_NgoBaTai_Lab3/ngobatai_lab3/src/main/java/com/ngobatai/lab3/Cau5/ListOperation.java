package com.ngobatai.lab3.Cau5;

import java.util.LinkedList;

public class ListOperation {
    // Đưa phần tử list2 vào list1
    public static void addAll(LinkedList<String> list1, LinkedList<String> list2) {
        list1.addAll(list2);
    }

    // Chuyển toàn bộ phần tử thành chữ hoa
    public static void toUpperCase(LinkedList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toUpperCase());
        }
    }

    // Xoá toàn bộ phần tử
    public static void clearList(LinkedList<String> list) {
        list.clear();
    }
}
