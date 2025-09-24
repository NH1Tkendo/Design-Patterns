package com.ngobatai_lab4.Bai1;

public class Client {
    public static void main(String[] args) {
        QuanLyVe qlv = new QuanLyVe();

        VeTronGoi a = new VeTronGoi("TP01", "Nguyen Van A", 2004, 4);

        VeTungPhan b = new VeTungPhan("TP001", "B", 2004, 23);

        qlv.nhapDanhSach(a);
        qlv.nhapDanhSach(b);

        System.out.println("Số tiền mà ban quản lý thu được là " + qlv.tinhTongTien());

        System.out.println("Số vé từng phần mà ban quản lý bán được là " + qlv.demVeTungPhan());
    }
}
