/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.CTPNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CTH_Nhap_1 {
     public boolean insert(CTPNhap ctpn) throws Exception
    {
        String sql = "insert into CTPhieuNhap(SoPN, MaSP, SoLuong, DGNhap) values (?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ctpn.getSoPN());
        pm.setString(2, ctpn.getMaSP());
        pm.setInt(3, ctpn.getSoLuong());
        pm.setDouble(4, ctpn.getDGNhap());
        
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String ctpn) throws Exception
    {
        String sql = "Delete from CTPhieuNhap where SoPN = ?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ctpn);
       
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(CTPNhap ctpn) throws Exception
    {
        String Sql ="Update CTPhieuNhap SET SoPN=?, MaSP=?, SoLuong=?, DGNhap=? WHERE SoPN = ?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, ctpn.getSoPN());
        pm.setString(2, ctpn.getMaSP());
        pm.setInt(3, ctpn.getSoLuong());
        pm.setDouble(4, ctpn.getDGNhap());
        pm.setString(5, ctpn.getSoPN());
        
        return pm.executeUpdate() > 0;
    }
}
