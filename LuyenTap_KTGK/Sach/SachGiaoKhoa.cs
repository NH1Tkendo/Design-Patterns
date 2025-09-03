using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sach
{
    public class SachGiaoKhoa:Sach
    {
        private Boolean TinhTrang { get; set; }

        public SachGiaoKhoa(string maSach, DateTime ngayNhap, double donGia, int soLuong, string nhaXuatBan, Boolean TinhTrang)
            : base(maSach, ngayNhap, donGia, soLuong, nhaXuatBan)
        {
            this.TinhTrang = TinhTrang;
        }

        public override double TinhThanhTien()
        {
            double thanhTien =  base.TinhThanhTien();
            if(!TinhTrang)
                return thanhTien * 0.5;
            return thanhTien;
        }

        public override string ToString()
        {
            return base.ToString() + $"\t{this.TinhTrang}";
        }
    }
}
