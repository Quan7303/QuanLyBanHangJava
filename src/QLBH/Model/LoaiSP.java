/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLBH.Model;


public class LoaiSP {
    // thuoc tinh
    public String MaloaiSP, TenloaiSP, GhiChu;

    public LoaiSP()
    {
        
    }
    
    public LoaiSP(String MaloaiSP, String TenloaiSP, String GhiChu) {
        this.MaloaiSP = MaloaiSP;
        this.TenloaiSP = TenloaiSP;
        this.GhiChu = GhiChu;
    }

    public String getMaloaiSP() {
        return MaloaiSP;
    }

    public void setMaloaiSP(String MaloaiSP) {
        this.MaloaiSP = MaloaiSP;
    }

    public String getTenloaiSP() {
        return TenloaiSP;
    }

    public void setTenloaiSP(String TenloaiSP) {
        this.TenloaiSP = TenloaiSP;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
