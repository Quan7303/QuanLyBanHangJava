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
public class NhaCungCap {
    // thuoc tinh
    public String MaNCC, TenNCC, Email, DiaChi, DienThoai;

    public NhaCungCap()
    {
        
    }
    
    public NhaCungCap(String MaNCC, String TenNCC, String Email, String DiaChi, String DienThoai) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.DienThoai = DienThoai;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }
    
    
}
