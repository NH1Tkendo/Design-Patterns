package Cau7;

import java.util.Arrays;
import java.util.Comparator;

public class DanhSachCD {
    private CD[] dsCD;
    private int count;

    public DanhSachCD(int maxSize) {
        dsCD = new CD[maxSize];
        count = 0;
    }

    public boolean themCD(CD cd) {
        if (count >= dsCD.length) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (dsCD[i].getMaCD() == cd.getMaCD()) {
                return false;
            }
        }
        dsCD[count++] = cd;
        return true;
    }

    public int soLuong() {
        return count;
    }

    public double tongGiaThanh() {
        double tong = 0;
        for (int i = 0; i < count; i++) {
            tong += dsCD[i].getGiaThanh();
        }
        return tong;
    }

    public void sapXepGiamGiaThanh() {
        Arrays.sort(dsCD, 0, count, (cd1, cd2) -> Double.compare(cd2.getGiaThanh(), cd1.getGiaThanh()));
    }

    public void sapXepTangTuaCD() {
        Arrays.sort(dsCD, 0, count, Comparator.comparing(CD::getTuaCD));
    }

    public void xuatDanhSach() {
        if (count == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(dsCD[i]);
            System.out.println("----------------");
        }
    }
}
