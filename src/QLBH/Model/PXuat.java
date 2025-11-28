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
public class PXuat {
    // thuoc tinh
    public String SoPX, NgayXuat, GhiChu, MaNV ;

    public PXuat() {
    }

    public PXuat(String SoPX, String NgayXuat, String GhiChu, String MaNV) {
        this.SoPX = SoPX;
        this.NgayXuat = NgayXuat;
        this.GhiChu = GhiChu;
        this.MaNV = MaNV;
    }
 
    public String getSoPX() {
        return SoPX;
    }

    public void setSoPX(String SoPX) {
        this.SoPX = SoPX;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String NgayXuat) {
        this.NgayXuat = NgayXuat;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
}
