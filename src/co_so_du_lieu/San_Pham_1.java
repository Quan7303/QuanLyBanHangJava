/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class San_Pham_1 {
    
    public boolean insert(SanPham sp) throws Exception
    {
        String Sql ="insert into SanPham(MaSP, TenSP, SLTon, DonViTinh, MaloaiSP) values (?,?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, sp.getMaSP());
        pm.setString(2, sp.getTenSP());
        pm.setInt(3, sp.getSLTon());
        pm.setString(4, sp.getDonViTinh());
        pm.setString(5, sp.getMaloaiSP());
        
        return pm.executeUpdate() > 0;
    }
    
    public boolean Update(SanPham sp) throws Exception
    {
        String Sql ="Update SanPham SET MaSP=?, TenSP=?, SLTon=?, DonViTinh=?, MaloaiSP=? WHERE MaSP=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, sp.getMaSP());
        pm.setString(2, sp.getTenSP());
        pm.setInt(3, sp.getSLTon());
        pm.setString(4, sp.getDonViTinh());
        pm.setString(5, sp.getMaloaiSP());
        pm.setString(6, sp.getMaSP());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Delete(String sp) throws Exception
    {
        String sql = "Delete from SanPham where BINARY_CHECKSUM(MaSP) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, sp);
        
        return pm.executeUpdate() > 0;
    }
}

