package com.ngobatai.lab3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

import com.ngobatai.lab3.Cau1.PhongLyThuyet;
import com.ngobatai.lab3.Cau1.PhongMayTinh;
import com.ngobatai.lab3.Cau1.PhongThiNghiem;
import com.ngobatai.lab3.Cau1.QuanLyPhongHoc;
import com.ngobatai.lab3.Cau3.SetOperation;
import com.ngobatai.lab3.Cau5.ListOperation;

public class Main {
    public static void main(String[] args) {
        // Cau1();
        // Cau3();
        // Cau4();
        // Cau5();
        // Cau6();
        // Cau7();
        // Cau9();
        // Cau10();
    }

    public static void Cau1() {
        QuanLyPhongHoc quanLy = new QuanLyPhongHoc();

        // Tạo dữ liệu mẫu
        System.out.println("=== KHỞI TẠO DỮ LIỆU PHÒNG HỌC ===");

        // Thêm phòng lý thuyết
        PhongLyThuyet plt1 = new PhongLyThuyet("LT001", "A1", 50.0, 5, true);
        PhongLyThuyet plt2 = new PhongLyThuyet("LT002", "A1", 60.0, 4, false);

        // Thêm phòng máy tính
        PhongMayTinh pmt1 = new PhongMayTinh("MT001", "B2", 80.0, 8, 50);
        PhongMayTinh pmt2 = new PhongMayTinh("MT002", "B2", 90.0, 10, 65);

        // Thêm phòng thí nghiệm
        PhongThiNghiem ptn1 = new PhongThiNghiem("TN001", "C3", 100.0, 12, "Hóa học", 40, true);
        PhongThiNghiem ptn2 = new PhongThiNghiem("TN002", "C3", 120.0, 10, "Sinh học", 35, false);

        // Thêm vào danh sách
        quanLy.themPhongHoc(plt1);
        quanLy.themPhongHoc(plt2);
        quanLy.themPhongHoc(pmt1);
        quanLy.themPhongHoc(pmt2);
        quanLy.themPhongHoc(ptn1);
        quanLy.themPhongHoc(ptn2);

        // quanLy.timTheoMaPhong("TN001");
        // quanLy.hienThiTatCaPhong();
        // quanLy.hienThiPhongDatChuan();

        // quanLy.sapXepGiamDanTheoDienTich();
        // quanLy.hienThiTatCaPhong();

        // quanLy.sapXepTangDanTheoDayNha();
        // quanLy.hienThiTatCaPhong();

        // quanLy.sapXepTangDanTheoSoBongDen();
        // quanLy.hienThiTatCaPhong();

        // quanLy.capNhatSoMayTinh("MT001", 60);

        // quanLy.xoaPhongHoc("MT001", null);
    }

    public static void Cau3() {
        Scanner sc = new Scanner(System.in);

        // Nhập tập A
        System.out.print("Nhập số phần tử tập A: ");
        int n = sc.nextInt();
        TreeSet<Integer> A = new TreeSet<>();
        System.out.println("Nhập các phần tử tập A:");
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }

        // Nhập tập B
        System.out.print("Nhập số phần tử tập B: ");
        int m = sc.nextInt();
        TreeSet<Integer> B = new TreeSet<>();
        System.out.println("Nhập các phần tử tập B:");
        for (int i = 0; i < m; i++) {
            B.add(sc.nextInt());
        }

        // Tạo đối tượng xử lý
        SetOperation op = new SetOperation(A, B);

        // Xuất kết quả
        System.out.println("Hợp (A ∪ B): " + op.union());
        System.out.println("Giao (A ∩ B): " + op.intersection());
        System.out.println("Hiệu (A - B): " + op.differenceAB());
        System.out.println("Hiệu (B - A): " + op.differenceBA());

        sc.close();
    }

    public static void Cau4() {
        ArrayList<String> colors1 = new ArrayList<>();
        colors1.add("Red");
        colors1.add("Green");
        colors1.add("Blue");
        colors1.add("Yellow");
        colors1.add("Black");

        // Tạo ArrayList màu thứ hai
        ArrayList<String> colors2 = new ArrayList<>();
        colors2.add("Green");
        colors2.add("Yellow");
        colors2.add("Pink");

        System.out.println("Danh sách 1 ban đầu: " + colors1);
        System.out.println("Danh sách 2: " + colors2);

        // Dùng Iterator để loại bỏ phần tử trong colors1 nếu có trong colors2
        Iterator<String> it = colors1.iterator();
        while (it.hasNext()) {
            String color = it.next();
            if (colors2.contains(color)) {
                it.remove(); // Xóa phần tử hiện tại
            }
        }

        // Kết quả sau khi loại bỏ
        System.out.println("Danh sách 1 sau khi loại bỏ phần tử trùng với danh sách 2: " + colors1);
    }

    public static void Cau5() {
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("apple");
        list1.add("banana");
        list1.add("cherry");

        // Tạo LinkedList 2
        LinkedList<String> list2 = new LinkedList<>();
        list2.add("orange");
        list2.add("grape");

        System.out.println("List1 ban đầu: " + list1);
        System.out.println("List2 ban đầu: " + list2);

        // Thêm phần tử list2 vào list1
        ListOperation.addAll(list1, list2);
        System.out.println("Sau khi thêm list2 vào list1: " + list1);

        // Chuyển list1 sang chữ hoa
        ListOperation.toUpperCase(list1);
        System.out.println("List1 sau khi chuyển sang chữ hoa: " + list1);

        // Xóa toàn bộ phần tử trong list1
        ListOperation.clearList(list1);
        System.out.println("List1 sau khi xóa: " + list1);
    }

    public static void Cau6() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Nhập chuỗi
        System.out.print("Nhập chuỗi: ");
        String input = br.readLine();

        // Đảo chuỗi
        String reversed = new StringBuilder(input).reverse().toString();

        // In kết quả
        System.out.println("Chuỗi nghịch đảo: " + reversed);
    }

    public static void Cau7() {
        try {
            BufferedInputStream bStream = new BufferedInputStream(
                    new FileInputStream("D:\\FBStoryVault\\Readme.md"));

            int ch = 0;
            while ((ch = bStream.read()) != -1) {
                System.out.print((char) ch);
            }

            bStream.close();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    public static void Cau9() {
        String filename = "data.bin";

        // Ghi dữ liệu
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeInt(123);
            dos.writeDouble(45.67);
            dos.writeBoolean(true);
            dos.writeUTF("Xin chào Java!");
            System.out.println("Đã ghi dữ liệu vào file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            int n = dis.readInt();
            double d = dis.readDouble();
            boolean b = dis.readBoolean();
            String s = dis.readUTF();

            System.out.println("Đọc từ file:");
            System.out.println("Int: " + n);
            System.out.println("Double: " + d);
            System.out.println("Boolean: " + b);
            System.out.println("String: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Cau10() {
        String path = "D:\\";
        File folder = new File(path);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Thư mục không tồn tại.");
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println("<DIR> " + f.getName());
                } else {
                    System.out.println("      " + f.getName());
                }
            }
        }
    }
}