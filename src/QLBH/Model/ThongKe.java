/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLBH.Model;

/**
 *
 * @author THUY TRUC
 */
public class ThongKe {

    // thuoc tinh
    public String MaHD, TenNV, MaSP, TenSP, NgayDH, TenKH;
    public int SoLuongDat; double DGBan;
    
    public ThongKe() {
    }
    
    public ThongKe(String MaHD, String TenNV, String MaSP, String TenSP, String NgayDH, String TenKH, int SoLuongDat, double DGBan) {
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.NgayDH = NgayDH;
        this.TenKH = TenKH;
        this.SoLuongDat = SoLuongDat;
        this.DGBan = DGBan;
    }
    
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getNgayDH() {
        return NgayDH;
    }

    public void setNgayDH(String NgayDH) {
        this.NgayDH = NgayDH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public int getSoLuongDat() {
        return SoLuongDat;
    }

    public void setSoLuongDat(int SoLuongDat) {
        this.SoLuongDat = SoLuongDat;
    }

    public double getDGBan() {
        return DGBan;
    }

    public void setDGBan(double DGBan) {
        this.DGBan = DGBan;
    }
}
