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
public class CTPNhap {
    // thuoc tinh
    public String SoPN, MaSP, TenSP;
    public int SoLuong; double DGNhap;

    public CTPNhap(String SoPN, String MaSP, String TenSP, int SoLuong, double DGNhap) {
        this.SoPN = SoPN;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.DGNhap = DGNhap;
    }

    public CTPNhap() {
    }
    
    public String getSoPN() {
        return SoPN;
    }

    public void setSoPN(String SoPN) {
        this.SoPN = SoPN;
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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDGNhap() {
        return DGNhap;
    }

    public void setDGNhap(double DGNhap) {
        this.DGNhap = DGNhap;
    }
}
