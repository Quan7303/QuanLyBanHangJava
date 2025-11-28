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
public class CTHDon {
    // thuoc tinh
    public String MaHD, MaSP, TenSP;
    public int SoLuongDat; double DGBan;

    public CTHDon(String MaHD, String MaSP, String TenSP, int SoLuongDat, double DGBan) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuongDat = SoLuongDat;
        this.DGBan = DGBan;
    }

    public CTHDon() {
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
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
