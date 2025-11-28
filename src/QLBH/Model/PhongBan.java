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
public class PhongBan {
    // thuoc tinh
    public String MaPB, TenPB, GhiChu;

    public PhongBan()
    {
        
    }
    
    public PhongBan(String MaPB, String TenPB, String GhiChu) {
        this.MaPB = MaPB;
        this.TenPB = TenPB;
        this.GhiChu = GhiChu;
    }

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String MaPB) {
        this.MaPB = MaPB;
    }

    public String getTenPB() {
        return TenPB;
    }

    public void setTenPB(String TenPB) {
        this.TenPB = TenPB;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
