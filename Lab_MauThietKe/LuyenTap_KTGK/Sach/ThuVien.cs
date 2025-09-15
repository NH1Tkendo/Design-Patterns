using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace Sach
{
    public class ThuVien
    {
        private List<Sach> danhSach;

        public ThuVien()
        {
            danhSach = new List<Sach>();
        }

        public void themSach(Sach sach)
        {
            danhSach.Add(sach);
        }

        public void xuatDanhSach()
        {
            Console.WriteLine("DANH SÁCH CÁC LOẠI SÁCH:");
            Console.WriteLine("========================");
            foreach (Sach s in danhSach)
            {
                Console.WriteLine(s);
            }
        }

        public void TinhTongThanhTienChoTungLoai()
        {
            double TongSGK = 0;
            double TongSTK = 0;

            foreach(Sach s in danhSach)
            {
                if (s is SachGiaoKhoa)
                    TongSGK += s.TinhThanhTien();
                if (s is SachThamKhao)
                    TongSTK += s.TinhThanhTien();
            }

            Console.WriteLine($"Tổng thành tiền của sách giáo khoa là {TongSGK}");
            Console.WriteLine($"Tổng thành tiền của sách tham khảo là {TongSTK}");
        }

        public void Tinh_TBC_DonGia_STK()
        {
            List<SachThamKhao> list = new List<SachThamKhao>();

            foreach (Sach s in danhSach)
            {
                if (s is SachThamKhao)
                    list.Add((SachThamKhao)s);
            }

            if (list.Count > 0)
            {
                double tong = 0;
                foreach (var s in list)
                {
                    tong += s.getDonGia();
                }
                Console.WriteLine($"Trung bình cộng đơn giá của sách tham khảo là {tong/list.Count}");
            }
            else
                Console.WriteLine("Không tồn tại sách tham khảo nào");
        }

        public void Xuat_SGK_Theo_NXB(String NXB)
        {
            bool isTrue = false;

            foreach(var s in danhSach)
            {
                if(s is SachGiaoKhoa && s.getNhaXuatBan() == NXB)
                {
                    Console.WriteLine(s);
                    isTrue = true;
                }
            }

            if (!isTrue) 
            {
                Console.WriteLine("Không có sách giáo khoa nào hết");
            }
        }
    }
}
