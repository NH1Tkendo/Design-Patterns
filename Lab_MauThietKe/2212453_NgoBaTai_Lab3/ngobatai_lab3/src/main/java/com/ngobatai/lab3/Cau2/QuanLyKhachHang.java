package com.ngobatai.lab3.Cau2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class QuanLyKhachHang {
    private Queue<KhachHang> hangDoiChoVe; // Hàng đợi khách chờ mua vé (FIFO)
    private List<KhachHang> danhSachDaMuaVe; // Danh sách khách đã mua vé (để thống kê)
    private static final String TEN_FILE = "danh_sach_khach_hang.txt";

    public QuanLyKhachHang() {
        hangDoiChoVe = new LinkedList<>();
        danhSachDaMuaVe = new ArrayList<>();
        // Tự động nạp danh sách từ file khi khởi tạo
        napDanhSachTuFile();
    }

    // Thêm khách hàng mới vào hàng đợi
    public void themKhachHangMoi(String soCMND, String tenKhachHang, String gaDen, double giaTien) {
        // Kiểm tra CMND đã tồn tại chưa
        KhachHang khachHangCu = timKhachHangTheoCMND(soCMND);
        if (khachHangCu != null) {
            // Cập nhật thông tin ga và giá tiền
            khachHangCu.setGaDen(gaDen);
            khachHangCu.setGiaTien(giaTien);
            System.out.println("Đã cập nhật thông tin cho khách hàng CMND: " + soCMND);
        } else {
            // Thêm khách hàng mới
            KhachHang khachHangMoi = new KhachHang(soCMND, tenKhachHang, gaDen, giaTien);
            hangDoiChoVe.offer(khachHangMoi);
            System.out.println("Đã thêm khách hàng mới vào hàng đợi: " + tenKhachHang);
        }
    }

    // Tìm khách hàng theo CMND trong hàng đợi
    private KhachHang timKhachHangTheoCMND(String soCMND) {
        for (KhachHang kh : hangDoiChoVe) {
            if (kh.getSoCMND().equals(soCMND)) {
                return kh;
            }
        }
        return null;
    }

    // Bán vé cho khách hàng đầu tiên trong hàng đợi (FIFO)
    public boolean banVeChoKhachHang() {
        if (hangDoiChoVe.isEmpty()) {
            System.out.println("Không có khách hàng nào trong hàng đợi!");
            return false;
        }

        KhachHang khachHang = hangDoiChoVe.poll(); // Lấy và xóa khách đầu tiên
        danhSachDaMuaVe.add(khachHang); // Thêm vào danh sách đã mua vé

        System.out.println("Đã bán vé thành công cho:");
        System.out.println(khachHang.toString());
        System.out.printf("Số tiền thu được: %,.0f VND\n", khachHang.getGiaTien());
        return true;
    }

    // Hiển thị danh sách khách hàng đang chờ
    public void hienThiDanhSachKhachHang() {
        if (hangDoiChoVe.isEmpty()) {
            System.out.println("Không có khách hàng nào đang chờ mua vé!");
            return;
        }

        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ĐANG CHỜ MUA VÉ ===");
        System.out.println("Thứ tự | " + "CMND".substring(0, Math.min("CMND".length(), 12)) +
                " | Tên khách hàng".substring(0, Math.min("Tên khách hàng".length(), 20)) +
                " | Ga đến".substring(0, Math.min("Ga đến".length(), 15)) + " | Giá vé");
        System.out.println("-".repeat(70));

        int thuTu = 1;
        for (KhachHang kh : hangDoiChoVe) {
            System.out.printf("%6d | %s\n", thuTu++, kh.toString());
        }
        System.out.printf("\nTổng cộng: %d khách hàng đang chờ\n", hangDoiChoVe.size());
    }

    // Hủy khách hàng ra khỏi danh sách
    public boolean huyKhachHang(String soCMND) {
        Iterator<KhachHang> iterator = hangDoiChoVe.iterator();
        while (iterator.hasNext()) {
            KhachHang kh = iterator.next();
            if (kh.getSoCMND().equals(soCMND)) {
                iterator.remove();
                System.out.println("Đã hủy khách hàng: " + kh.getTenKhachHang() + " khỏi hàng đợi.");
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng với CMND: " + soCMND);
        return false;
    }

    // Thống kê tình hình bán vé
    public void thongKeTinhHinhBanVe() {
        double tongTienThu = danhSachDaMuaVe.stream().mapToDouble(KhachHang::getGiaTien).sum();

        System.out.println("\n=== THỐNG KÊ TÌNH HÌNH BÁN VÉ ===");
        System.out.printf("Số khách hàng đang chờ mua vé: %d\n", hangDoiChoVe.size());
        System.out.printf("Số khách hàng đã mua vé: %d\n", danhSachDaMuaVe.size());
        System.out.printf("Tổng số tiền đã thu được: %,.0f VND\n", tongTienThu);

        if (!danhSachDaMuaVe.isEmpty()) {
            System.out.println("\n--- Chi tiết khách hàng đã mua vé ---");
            for (int i = 0; i < danhSachDaMuaVe.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, danhSachDaMuaVe.get(i).toString());
            }
        }
    }

    // Lưu danh sách khách hàng chờ vào file
    public void luuDanhSachVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEN_FILE, false))) {
            for (KhachHang kh : hangDoiChoVe) {
                writer.println(kh.toFileString());
            }
            System.out.println("Đã lưu danh sách " + hangDoiChoVe.size() +
                    " khách hàng vào file: " + TEN_FILE);
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    // Nạp danh sách khách hàng từ file
    private void napDanhSachTuFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEN_FILE))) {
            String line;
            int soLuong = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    KhachHang kh = KhachHang.fromFileString(line);
                    if (kh != null) {
                        hangDoiChoVe.offer(kh);
                        soLuong++;
                    }
                }
            }
            if (soLuong > 0) {
                System.out.println("Đã nạp " + soLuong + " khách hàng từ file: " + TEN_FILE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File danh sách khách hàng chưa tồn tại. Sẽ tạo mới khi lưu.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    // Hiển thị danh sách các ga đang chờ mua vé
    public void hienThiDanhSachGaDangCho() {
        if (hangDoiChoVe.isEmpty()) {
            System.out.println("Không có ga nào đang chờ mua vé!");
            return;
        }

        Set<String> danhSachGa = new LinkedHashSet<>(); // Dùng LinkedHashSet để giữ thứ tự và không trùng lặp
        for (KhachHang kh : hangDoiChoVe) {
            danhSachGa.add(kh.getGaDen());
        }

        System.out.println("\n=== DANH SÁCH CÁC GA ĐANG CHỜ MUA VÉ ===");
        int stt = 1;
        for (String ga : danhSachGa) {
            System.out.printf("%d. %s\n", stt++, ga);
        }
        System.out.printf("\nTổng cộng: %d ga khác nhau\n", danhSachGa.size());
    }

    // Hiển thị danh sách các ga và số vé tương ứng
    public void hienThiDanhSachGaVaSoVe() {
        if (hangDoiChoVe.isEmpty()) {
            System.out.println("Không có ga nào đang chờ mua vé!");
            return;
        }

        Map<String, Integer> thongKeGa = new LinkedHashMap<>();

        // Đếm số vé cho mỗi ga
        for (KhachHang kh : hangDoiChoVe) {
            String ga = kh.getGaDen();
            thongKeGa.put(ga, thongKeGa.getOrDefault(ga, 0) + 1);
        }

        System.out.println("\n=== DANH SÁCH CÁC GA VÀ SỐ VÉ TƯƠNG ỨNG ===");
        System.out.println("STT | Ga đến".substring(0, Math.min("Ga đến".length(), 20)) + " | Số vé chờ");
        System.out.println("-".repeat(40));

        int stt = 1;
        int tongSoVe = 0;
        for (Map.Entry<String, Integer> entry : thongKeGa.entrySet()) {
            System.out.printf("%3d | %-20s | %8d\n", stt++, entry.getKey(), entry.getValue());
            tongSoVe += entry.getValue();
        }

        System.out.println("-".repeat(40));
        System.out.printf("Tổng cộng: %d ga | %d vé chờ\n", thongKeGa.size(), tongSoVe);
    }

    // Getter để kiểm tra hàng đợi có trống không
    public boolean hangDoiTrong() {
        return hangDoiChoVe.isEmpty();
    }

    public int soKhachDangCho() {
        return hangDoiChoVe.size();
    }

    public int soKhachDaMua() {
        return danhSachDaMuaVe.size();
    }
}