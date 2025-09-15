using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sach
{
    public abstract class Sach
    {
        public string MaSach { get; protected set; }
        public DateTime NgayNhap { get; protected set; }
        private double DonGia;
        public int SoLuong { get; protected set; }
        public string NhaXuatBan { get; protected set; }

        public Sach(string maSach, DateTime ngayNhap, double donGia, int soLuong, string nhaXuatBan)
        {
            MaSach = maSach;
            NgayNhap = ngayNhap;
            DonGia = donGia;
            SoLuong = soLuong;
            NhaXuatBan = nhaXuatBan;
        }

        public virtual double TinhThanhTien()
        {
            return this.DonGia * this.SoLuong;
        }
        public override string ToString()
        {
            return $"{this.MaSach}\t{this.NgayNhap:dd/MM/yyyy}\t{this.DonGia}\t{this.SoLuong}\t{this.NhaXuatBan}";
        }

        public double getDonGia()
        {
            return this.DonGia;
        }

        public String getNhaXuatBan()
        {
            return this.NhaXuatBan;
        }
    }
}
