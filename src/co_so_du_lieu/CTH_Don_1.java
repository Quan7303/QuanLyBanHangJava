/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.CTHDon;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CTH_Don_1 {
    public boolean insert(CTHDon cthd) throws Exception
    {
        String sql = "insert into CTHoaDon(MaHD, MaSP, SoLuongDat, DGBan) values (?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();

        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, cthd.getMaHD());
        pm.setString(2, cthd.getMaSP());
        pm.setInt(3, cthd.getSoLuongDat());
        pm.setDouble(4, cthd.getDGBan());
        
        
        return pm.executeUpdate() > 0;
    }

     public boolean Delete(String cthd) throws Exception
    {
        String sql = "Delete from CTHoaDon where BINARY_CHECKSUM (MaHD)= BINARY_CHECKSUM(?)";
        
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, cthd);
       
        
        
        return pm.executeUpdate() > 0;
    }
     
    public boolean Update(CTHDon cthd) throws Exception
    {
        String Sql ="Update CTHoaDon SET MaHD=?, MaSP=?, SoLuongDat=?, DGBan=? WHERE MaHD = ?";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, cthd.getMaHD());
        pm.setString(2, cthd.getMaSP());
        pm.setInt(3, cthd.getSoLuongDat());
        pm.setDouble(4, cthd.getDGBan());
        pm.setString(5, cthd.getMaHD());
        
        return pm.executeUpdate() > 0;
    }
}
