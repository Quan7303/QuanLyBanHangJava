/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.PXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Phieu_Xuat_1 {
    public boolean insert(PXuat px) throws Exception
    {
        String sql = "insert into PhieuXuat(SoPX, NgayXuat, GhiChu, MaNV) values (?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, px.getSoPX());
        pm.setString(2, px.getNgayXuat());
        pm.setString(3, px.getGhiChu());
        pm.setString(4, px.getMaNV());
        
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String px1) throws Exception
    {
        String sql = "Delete from PhieuXuat where BINARY_CHECKSUM (SoPX)= BINARY_CHECKSUM(?) ";
        
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, px1);
        
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(PXuat px) throws Exception
    {
        String Sql ="Update PhieuXuat SET SoPX=?, NgayXuat=?, GhiChu=?, MaNV=? WHERE SoPX=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, px.getSoPX());
        pm.setString(2, px.getNgayXuat());
        pm.setString(3, px.getGhiChu());
        pm.setString(4, px.getMaNV());
        pm.setString(5, px.getSoPX());
       
        return pm.executeUpdate() > 0;
    }
}
