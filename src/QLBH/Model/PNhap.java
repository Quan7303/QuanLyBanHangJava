/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLBH.Model;


public class PNhap {
    // thuoc tinh
    public String SoPN, NgayNhap, GhiChu, MaNCC;

    public PNhap(String SoPN, String NgayNhap, String GhiChu, String MaNCC) {
        this.SoPN = SoPN;
        this.NgayNhap = NgayNhap;
        this.GhiChu = GhiChu;
        this.MaNCC = MaNCC;
    }

    public PNhap() {
    }
    
    public String getSoPN() {
        return SoPN;
    }

    public void setSoPN(String SoPN) {
        this.SoPN = SoPN;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }
}
