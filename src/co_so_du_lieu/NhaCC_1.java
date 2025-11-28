/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author THUY TRUC
 */
public class NhaCC_1 {
    public boolean insert(NhaCungCap ncc) throws Exception
    {
        String Sql = "insert into NhaCungCap( MaNCC, TenNCC, DiaChi, DienThoai, Email) values (?,?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, ncc.getMaNCC());
        pm.setString(2, ncc.getTenNCC());
        pm.setString(3, ncc.getDiaChi());
        pm.setString(4, ncc.getDienThoai());
        pm.setString(5, ncc.getEmail());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Update(NhaCungCap ncc) throws Exception
    {
        String Sql = "update NhaCungCap SET MaNCC=?, TenNCC=?, DiaChi=?, DienThoai=?, Email=? WHERE MaNCC=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, ncc.getMaNCC());
        pm.setString(2, ncc.getTenNCC());
        pm.setString(3, ncc.getDiaChi());
        pm.setString(4, ncc.getDienThoai());
        pm.setString(5, ncc.getEmail());
        pm.setString(6, ncc.getMaNCC());
        return pm.executeUpdate() > 0;
    }
    
    public boolean Delete(String ncc) throws Exception
    {
        String sql = "Delete from NhaCungCap where BINARY_CHECKSUM(MaNCC) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ncc);
        return pm.executeUpdate() > 0;
    }
}

