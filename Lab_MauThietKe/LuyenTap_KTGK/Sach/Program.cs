using Sach;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LuyenTap
{
    public class Program
    {
        static void Main(string[] args)
        {
            ThuVien tv = new ThuVien();

            tv.themSach(new SachGiaoKhoa("SGK1", new DateTime(2024, 1, 20), 25000, 10, "ABC", true));
            tv.themSach(new SachGiaoKhoa("SGK2", new DateTime(2025, 1, 19), 25000, 10, "ABCD", false));
            tv.themSach(new SachThamKhao("STK1", new DateTime(2022, 4, 26), 25000, 10, "CBA", 5000));
            tv.themSach(new SachThamKhao("STK2", new DateTime(2022, 6, 14), 25000, 10, "DCBA", 5000));

            tv.xuatDanhSach();
            tv.TinhTongThanhTienChoTungLoai();
            tv.Tinh_TBC_DonGia_STK();
            tv.Xuat_SGK_Theo_NXB("ABC");
        }
    }
}
