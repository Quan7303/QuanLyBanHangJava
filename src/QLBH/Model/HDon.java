/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLBH.Model;


public class HDon {
    // thuoc tinh
    public String MaHD, MaKH, MaNV, NgayDH, NgayNH, PTTT;

    public HDon(String MaHD, String MaKH, String MaNV, String NgayDH, String NgayNH, String PTTT) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.NgayDH = NgayDH;
        this.NgayNH = NgayNH;
        this.PTTT = PTTT;
    }

    public HDon() {
    }
    
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayDH() {
        return NgayDH;
    }

    public void setNgayDH(String NgayDH) {
        this.NgayDH = NgayDH;
    }

    public String getNgayNH() {
        return NgayNH;
    }

    public void setNgayNH(String NgayNH) {
        this.NgayNH = NgayNH;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    
    
    
}
