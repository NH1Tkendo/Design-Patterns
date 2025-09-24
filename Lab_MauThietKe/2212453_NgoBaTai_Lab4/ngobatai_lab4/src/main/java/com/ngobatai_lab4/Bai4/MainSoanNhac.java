package com.ngobatai_lab4.Bai4;

import java.util.*;

public class MainSoanNhac {
    private static Scanner sc = new Scanner(System.in);
    private static Score score = new Score();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            menu();
            int ch = readInt("Chọn: ");
            switch (ch) {
                case 1 -> handleAdd();
                case 2 -> score.printScore();
                case 3 -> System.out.println("Số dấu lặng (nốt đen): " + score.countQuarterRests());
                case 4 -> {
                    Optional<Pitch> hp = score.highestPitch();
                    if (hp.isPresent())
                        System.out.println("Nốt cao nhất: " + hp.get());
                    else
                        System.out.println("Không có nốt nào.");
                }
                case 5 -> {
                    int idx = readInt("Nhập chỉ số (1..n) cần xóa: ");
                    if (score.removeAt(idx))
                        System.out.println("Đã xóa.");
                    else
                        System.out.println("Chỉ số không hợp lệ.");
                }
                case 0 -> exit = true;
                default -> System.out.println("Không hợp lệ!");
            }
        }
        System.out.println("Kết thúc chương trình.");
    }

    private static void menu() {
        System.out.println("\n=== TRÌNH SOẠN NHẠC (Bài 4) ===");
        System.out.println("1. Thêm ký hiệu");
        System.out.println("2. Hiển thị bản nhạc");
        System.out.println("3. Đếm dấu lặng là NỐT ĐEN");
        System.out.println("4. Tìm nốt cao nhất");
        System.out.println("5. Xóa ký hiệu theo chỉ số");
        System.out.println("0. Thoát");
    }

    private static void handleAdd() {
        System.out.println("1. Thêm Nốt");
        System.out.println("2. Thêm Dấu lặng");
        int t = readInt("Chọn: ");
        if (t == 1) {
            Pitch p = choosePitch();
            Duration d = chooseDuration();
            score.add(new Note(p, d));
            System.out.println("Đã thêm nốt.");
        } else if (t == 2) {
            Duration d = chooseDuration();
            score.add(new Rest(d));
            System.out.println("Đã thêm dấu lặng.");
        } else {
            System.out.println("Không hợp lệ.");
        }
    }

    private static Duration chooseDuration() {
        Duration[] ds = Duration.values();
        for (int i = 0; i < ds.length; i++) {
            System.out.printf("%d. %s%n", i + 1, ds[i].label());
        }
        int c = readInt("Chọn trường độ: ");
        if (c < 1 || c > ds.length)
            return Duration.QUARTER;
        return ds[c - 1];
    }

    private static Pitch choosePitch() {
        Pitch[] ps = Pitch.values();
        for (int i = 0; i < ps.length; i++) {
            System.out.printf("%d. %s%n", i + 1, ps[i]);
        }
        int c = readInt("Chọn cao độ: ");
        if (c < 1 || c > ps.length)
            return Pitch.C;
        return ps[c - 1];
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }
    }
}
