/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.CTPXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class CTH_Xuat_1 {
    public boolean insert(CTPXuat ctpx) throws Exception
    {
        String sql = "insert into CTPhieuXuat(SoPX, MaSP, SoLuong, DGBan) values (?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ctpx.getSoPX());
        pm.setString(2, ctpx.getMaSP());
        pm.setInt(3, ctpx.getSoLuong());
        pm.setDouble(4, ctpx.getDGBan());
        
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String ctpx1) throws Exception
    {
        String sql = "Delete from CTPhieuXuat where BINARY_CHECKSUM (SoPX)= BINARY_CHECKSUM(?)";
        
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ctpx1);
       
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(CTPXuat ctpx) throws Exception
    {
        String Sql ="Update CTPhieuXuat SET SoPX=?, MaSP=?, SoLuong=?, DGBan=? WHERE SoPX = ?";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, ctpx.getSoPX());
        pm.setString(2, ctpx.getMaSP());
        pm.setInt(3, ctpx.getSoLuong());
        pm.setDouble(4, ctpx.getDGBan());
        pm.setString(5, ctpx.getSoPX());
        
        return pm.executeUpdate() > 0;
    }
}
