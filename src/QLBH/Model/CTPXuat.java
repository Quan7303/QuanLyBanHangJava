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
public class CTPXuat {

    // thuoc tinh
    public String SoPX, MaSP, TenSP;
    public int SoLuong; double DGBan;

    

    public CTPXuat(String SoPX, String MaSP, String TenSP, int SoLuong, double DGBan) {
        this.SoPX = SoPX;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.DGBan = DGBan;
    }

    public CTPXuat() {
    }
    
    public String getSoPX() {
        return SoPX;
    }

    public void setSoPX(String SoPX) {
        this.SoPX = SoPX;
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

    public double getDGBan() {
        return DGBan;
    }

    public void setDGBan(double DGBan) {
        this.DGBan = DGBan;
    }
    
    
    
}
