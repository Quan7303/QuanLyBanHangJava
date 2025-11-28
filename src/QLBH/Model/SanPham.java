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
public class SanPham {

    // thuoc tinh
    public String MaSP, TenSP, DonViTinh, MaloaiSP;
    public int SLTon;
    
    public SanPham()
    {
        
    }
    
    public SanPham(String MaSP, String TenSP, String DonViTinh, String MaloaiSP, int SLTon) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.DonViTinh = DonViTinh;
        this.MaloaiSP = MaloaiSP;
        this.SLTon = SLTon;
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

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public int getSLTon() {
        return SLTon;
    }
    
    public void setSLTon(int SLTon) {
        this.SLTon = SLTon;
    }
    
    public String getMaloaiSP() {
        return MaloaiSP;
    }

    public void setMaloaiSP(String MaloaiSP) {
        this.MaloaiSP = MaloaiSP;
    }
}
