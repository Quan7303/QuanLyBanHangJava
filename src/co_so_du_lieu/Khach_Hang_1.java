/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Khach_Hang_1 {
    
    public boolean insert(KhachHang kh) throws Exception 
    {
        String Sql = "insert into KhachHang(MaKH, TenKH, DiaChi, NgaySinh, DienThoai) values(?,?,?,?,?)";
        
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, kh.getMaKH());
        pm.setString(2, kh.getTenKH());
        pm.setString(3, kh.getDiaChi());
        pm.setString(4, kh.getNgaySinh());
        pm.setString(5, kh.getDienThoai());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Update(KhachHang kh) throws Exception
    {
        String Sql ="update KhachHang SET MaKH=?,TenKH=?,DiaChi=?,NgaySinh=?,DienThoai=? WHERE MaKH=?";
        
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, kh.getMaKH());
        pm.setString(2, kh.getTenKH());
        pm.setString(3, kh.getDiaChi());
        pm.setString(4, kh.getNgaySinh());
        pm.setString(5, kh.getDienThoai());
        pm.setString(6, kh.getMaKH());
         return pm.executeUpdate() > 0;
    }
    
    public boolean Delete(String kh) throws Exception 
    {
        String sql = "Delete from KhachHang where BINARY_CHECKSUM(MaKH) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, kh);
        return pm.executeUpdate() > 0;
    }
}

