/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.HDon;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class H_Don_1 {
    public boolean insert(HDon hd) throws Exception
    {
        String sql = "insert into HoaDon(MaHD, MaKH, MaNV, NgayDH, NgayNH, PTTT) values (?,?,?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, hd.getMaHD());
        pm.setString(2, hd.getMaKH());
        pm.setString(3, hd.getMaNV());
        pm.setString(4, hd.getNgayDH());
        pm.setString(5, hd.getNgayNH());
        pm.setString(6, hd.getPTTT());
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String hd) throws Exception
    {
        String sql = "Delete from HoaDon where BINARY_CHECKSUM (MaHD)= BINARY_CHECKSUM(?) ";
        
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, hd);
        
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(HDon hd) throws Exception
    {
        String Sql ="Update HoaDon SET MaHD=?, MaKH=?, MaNV=?, NgayDH=?, NgayNH=?, PTTT=? WHERE MaHD=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, hd.getMaHD());
        pm.setString(2, hd.getMaKH());
        pm.setString(3, hd.getMaNV());
        pm.setString(4, hd.getNgayDH());
        pm.setString(5, hd.getNgayNH());
        pm.setString(6, hd.getPTTT());
        pm.setString(7, hd.getMaHD());
       
        return pm.executeUpdate() > 0;
    }
}
