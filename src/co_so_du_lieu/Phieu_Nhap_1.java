/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;
import ketnoi.ketnoi;
import QLBH.Model.PNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Phieu_Nhap_1 {
   public boolean insert(PNhap pn) throws Exception
    {
        String sql = "insert into PhieuNhap(SoPN, NgayNhap, GhiChu, MaNCC) values (?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, pn.getSoPN());
        pm.setString(2, pn.getNgayNhap());
        pm.setString(3, pn.getGhiChu());
        pm.setString(4, pn.getMaNCC());
        
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String pn) throws Exception
    {
        String sql = "Delete from PhieuNhap where SoPN = ? ";
        
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, pn);
        
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(PNhap pn) throws Exception
    {
        String Sql ="Update PhieuNhap SET SoPN=?, NgayNhap=?, GhiChu=?, MaNCC=? WHERE SoPN=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pn.getSoPN());
        pm.setString(2, pn.getNgayNhap());
        pm.setString(3, pn.getGhiChu());
        pm.setString(4, pn.getMaNCC());
        pm.setString(5, pn.getSoPN());
       
        return pm.executeUpdate() > 0;
    }
}

