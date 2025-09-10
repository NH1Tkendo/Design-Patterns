package com.ngobatai;

import Cau1.HinhChuNhat;
import Cau2.Vehicle;
import Cau3.Account;
import Cau4.Rational;
import Cau5.HangThucPham;
import Cau6.SinhVien;
import Cau7.CD;
import Cau7.DanhSachCD;
import java.util.*;
import java.text.*;

public class Main {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Bai 1: Tinh dien tich, chu vi HCN");
            System.out.println("2. Bai 2: Quan ly xe");
            System.out.println("3. Bai 3: Tai khoan ngan hang");
            System.out.println("4. Bai 4: Phan so");
            System.out.println("5. Bai 5: Hang thuc pham");
            System.out.println("6. Bai 6: Sinh vien");
            System.out.println("7. Bai 7: CD");
            System.out.println("8. Thoat");
            System.out.print("Chon cong viec: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> runBai1();
                case 2 -> runBai2();
                case 3 -> runBai3();
                case 4 -> runBai4();
                case 5 -> runBai5();
                case 6 -> runBai6();
                case 7 -> runBai7();
                case 8 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 8);
    }

    private static void runBai1() {
        System.out.println("Nhap chieu dai: ");
        double dai = scanner.nextDouble();
        System.out.println("Nhap chieu rong: ");
        double rong = scanner.nextDouble();
        HinhChuNhat hcn = new HinhChuNhat(dai, rong);
        System.out.println(hcn);
    }

    private static void runBai2() {
        int subChoice;
        do {
            System.out.println("\nBai 2: Quan ly xe");
            System.out.println("1. Nhap thong tin va tao cac doi tuong xe1, xe2, xe3");
            System.out.println("2. Xuat bang ke khai tien thue truoc ba cua cac xe");
            System.out.println("3. Tro ve menu chinh");
            System.out.print("Chon: ");
            subChoice = scanner.nextInt();
            scanner.nextLine();

            switch (subChoice) {
                case 1 -> inputVehicles();
                case 2 -> displayTaxTable();
                case 3 -> {
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (subChoice != 3);
    }

    private static void runBai3() {
        Account acc1 = new Account(123456789L, "Nguyen Van A", 1000000);
        System.out.println("Tai khoan 1 ban dau:\n" + acc1);

        acc1.napTien(500000);
        System.out.println("Sau nap 500000:\n" + acc1);

        acc1.rutTien(200000);
        System.out.println("Sau rut 200000:\n" + acc1);

        acc1.daoHan();
        System.out.println("Sau dao han:\n" + acc1);

        Account acc2 = new Account(987654321L, "Tran Thi B");
        System.out.println("Tai khoan 2:\n" + acc2);

        acc1.chuyenKhoan(acc2, 300000);
        System.out.println("Sau chuyen 300000 tu acc1 sang acc2:");
        System.out.println("Acc1:\n" + acc1);
        System.out.println("Acc2:\n" + acc2);
    }

    private static void runBai4() {
        Rational r1 = new Rational(2, 4);
        Rational r2 = new Rational(1, 3);
        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);
        System.out.println("Add: " + r1.add(r2));
        System.out.println("Subtract: " + r1.subtract(r2));
        System.out.println("Multiply: " + r1.multiply(r2));
        System.out.println("Divide: " + r1.divide(r2));
        System.out.println("Reciprocal r1: " + r1.reciprocal());
        System.out.println("r1 equals 1/2: " + r1.equals(new Rational(1, 2)));
    }

    private static void runBai5() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            System.out.print("Nhap ma hang: ");
            String ma = scanner.nextLine();
            System.out.print("Nhap ten hang: ");
            String ten = scanner.nextLine();
            System.out.print("Nhap don gia: ");
            double gia = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Nhap ngay san xuat (dd/MM/yyyy): ");
            Date nsx = sdf.parse(scanner.nextLine());
            System.out.print("Nhap ngay het han (dd/MM/yyyy): ");
            Date hhh = sdf.parse(scanner.nextLine());
            HangThucPham htp = new HangThucPham(ma, ten, gia, nsx, hhh);
            System.out.println(htp);
            System.out.println("Da het han: " + (htp.hetHan() ? "Co" : "Khong"));
        } catch (ParseException e) {
            System.out.println("Ngay khong hop le!");
        }
    }

    private static void runBai6() {
        System.out.print("Nhap so luong sinh vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<SinhVien> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("\nSinh vien " + (i + 1) + ":");
            System.out.print("Ma SV: ");
            int ma = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ho ten: ");
            String ten = scanner.nextLine();
            System.out.print("Dia chi: ");
            String dc = scanner.nextLine();
            System.out.print("So dien thoai (7 so): ");
            String sdt = scanner.nextLine();
            list.add(new SinhVien(ma, ten, dc, sdt));
        }
        Collections.sort(list, Comparator.comparingInt(SinhVien::getMaSV));
        System.out.println("\nDanh sach sinh vien sap xep theo ma tang dan:");
        for (SinhVien sv : list) {
            System.out.println(sv);
            System.out.println("----------------");
        }
    }

    private static void runBai7() {
        DanhSachCD ds = new DanhSachCD(10); // Gioi han 10 CD
        int subChoice;
        do {
            System.out.println("\nBai 7: Quan ly CD");
            System.out.println("1. Them CD");
            System.out.println("2. So luong CD");
            System.out.println("3. Tong gia thanh");
            System.out.println("4. Sap xep giam dan theo gia thanh");
            System.out.println("5. Sap xep tang dan theo tua CD");
            System.out.println("6. Xuat toan bo danh sach");
            System.out.println("7. Tro ve menu chinh");
            System.out.print("Chon: ");
            subChoice = scanner.nextInt();
            scanner.nextLine();

            switch (subChoice) {
                case 1 -> {
                    System.out.print("Ma CD: ");
                    int ma = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tua CD: ");
                    String tua = scanner.nextLine();
                    System.out.print("Ca sy: ");
                    String cs = scanner.nextLine();
                    System.out.print("So bai hat: ");
                    int sbh = scanner.nextInt();
                    System.out.print("Gia thanh: ");
                    double gt = scanner.nextDouble();
                    scanner.nextLine();
                    CD cd = new CD(ma, tua, cs, sbh, gt);
                    if (ds.themCD(cd)) {
                        System.out.println("Them thanh cong.");
                    } else {
                        System.out.println("Them that bai (trung ma hoac day).");
                    }
                }
                case 2 -> System.out.println("So luong CD: " + ds.soLuong());
                case 3 -> System.out.println("Tong gia thanh: " + ds.tongGiaThanh());
                case 4 -> {
                    ds.sapXepGiamGiaThanh();
                    System.out.println("Da sap xep giam dan theo gia thanh.");
                }
                case 5 -> {
                    ds.sapXepTangTuaCD();
                    System.out.println("Da sap xep tang dan theo tua CD.");
                }
                case 6 -> ds.xuatDanhSach();
                case 7 -> {
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (subChoice != 7);
    }

    private static void inputVehicles() {
        vehicles.clear();
        System.out.println("Nhap thong tin cho 3 xe:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nXe " + i + ":");
            System.out.print("Ten chu xe: ");
            String ownerName = scanner.nextLine();
            System.out.print("Loai xe: ");
            String model = scanner.nextLine();
            System.out.print("Dung tich xylanh (cc): ");
            int engineCapacity = scanner.nextInt();
            System.out.print("Tri gia xe (VND): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            vehicles.add(new Vehicle(ownerName, model, engineCapacity, price));
        }
        System.out.println("Da nhap thong tin cho 3 xe.");
    }

    private static void displayTaxTable() {
        if (vehicles.isEmpty()) {
            System.out.println("Chua co thong tin xe nao!");
            return;
        }
        System.out.println("\nBang ke khai tien thue truoc ba:");
        System.out.println("Ten chu xe\tLoai xe\tDung tich\tTri gia\tThue phai nop");
        for (Vehicle v : vehicles) {
            System.out.printf("%s\t\t%s\t%d\t%.0f\t%.0f%n",
                    v.getOwnerName(), v.getModel(), v.getEngineCapacity(),
                    v.getPrice(), v.calculateTax());
        }
    }
}
