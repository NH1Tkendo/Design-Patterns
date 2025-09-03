using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sach
{
    public class SachThamKhao:Sach
    {
        private double thue {  get; set; }

        public SachThamKhao(string maSach, DateTime ngayNhap, double donGia, int soLuong, string nhaXuatBan, double thue)
            : base(maSach, ngayNhap, donGia, soLuong, nhaXuatBan)
        {
            this.thue = thue;
        }

        public override double TinhThanhTien()
        {
            return base.TinhThanhTien() + this.thue;
        }

        public override string ToString()
        {
            return base.ToString() + $"\t{this.thue}";
        }
    }
}
