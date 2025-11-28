/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.PhongBan;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Phong_Ban_1 {
    
    public boolean insert(PhongBan pb) throws Exception 
    {
        String Sql = "insert into PhongBan(MaPB, TenPB, GhiChu) values(?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMaPB());
        pm.setString(2, pb.getTenPB());
        pm.setString(3, pb.getGhiChu());
        return pm.executeUpdate() > 0;
    }
     
    public boolean Delete(String pb) throws Exception 
    {
        String sql = "Delete from PhongBan where BINARY_CHECKSUM(MaPB) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, pb);
        return pm.executeUpdate() > 0;
    }
      
    public boolean Update(PhongBan pb) throws Exception {
        String Sql = "Update PhongBan SET MaPB=?,TenPB=?,GhiChu=? WHERE MaPB=?";
        ketnoi kn=new ketnoi();
        Connection conn =kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMaPB());
        pm.setString(2, pb.getTenPB());
        pm.setString(3, pb.getGhiChu());
        pm.setString(4, pb.getMaPB());

        return pm.executeUpdate() > 0;
    }
}

