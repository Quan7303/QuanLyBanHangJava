/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;
import ketnoi.ketnoi;
import QLBH.Model.LoaiSP;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Loai_San_Pham_1 {
    public boolean insert(LoaiSP lsp) throws Exception 
    {
        String Sql = "insert into LoaiSanPham(MaloaiSP, TenloaiSP, GhiChu) values(?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, lsp.getMaloaiSP());
        pm.setString(2, lsp.getTenloaiSP());
        pm.setString(3, lsp.getGhiChu());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Update(LoaiSP lsp) throws Exception 
    {
        String Sql = "Update LoaiSanPham SET MaloaiSP=?,TenloaiSP=?,GhiChu=? where MaloaiSP=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, lsp.getMaloaiSP());
        pm.setString(2, lsp.getTenloaiSP());
        pm.setString(3, lsp.getGhiChu());
        pm.setString(4, lsp.getMaloaiSP());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Delete(String lsp) throws Exception 
    {
        String sql = "Delete from LoaiSanPham where BINARY_CHECKSUM(MaloaiSP) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, lsp);
        return pm.executeUpdate() > 0;
    }
}

