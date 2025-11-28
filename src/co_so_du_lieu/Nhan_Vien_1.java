package co_so_du_lieu;

import ketnoi.ketnoi;
import QLBH.Model.NhanVien;
import QLBH.Model.PhongBan;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Nhan_Vien_1{
    public boolean insert(NhanVien nv) throws Exception 
     {
        String Sql = "insert into NhanVien(MaNV, HoNV, TenNV, GioiTinh, NgaySinh, DiaChi, DienThoai, NoiSinh, NgayVaoLam, Email, MaPB) values(?,?,?,?,?,?,?,?,?,?,?)";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, nv.getMaNV());
        pm.setString(2, nv.getHoNV());
        pm.setString(3, nv.getTenNV());
        pm.setString(4, nv.getGioiTinh());
        pm.setString(5, nv.getNgaySinh());
        pm.setString(6, nv.getDiaChi());
        pm.setString(7, nv.getDienThoai());
        pm.setString(8, nv.getNoiSinh());
        pm.setString(9, nv.getNgayVaoLam());
        pm.setString(10, nv.getEmail());
        pm.setString(11, nv.getMaPB());
        
        return pm.executeUpdate() > 0;
     }
    
    public boolean Update(NhanVien nv) throws Exception 
    {
        String Sql = "Update NhanVien SET MaNV=?,HoNV=?,TenNV=?,GioiTinh=?,NgaySinh=?,DiaChi=?,DienThoai=?,NoiSinh=?,NgayVaoLam=?,Email=?,MaPB=? where MaNV=?";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, nv.getMaNV());
        pm.setString(2, nv.getHoNV());
        pm.setString(3, nv.getTenNV());
        pm.setString(4, nv.getGioiTinh());
        pm.setString(5, nv.getNgaySinh());
        pm.setString(6, nv.getDiaChi());
        pm.setString(7, nv.getDienThoai());
        pm.setString(8, nv.getNoiSinh());
        pm.setString(9, nv.getNgayVaoLam());
        pm.setString(10, nv.getEmail());
        pm.setString(11, nv.getMaPB());
        pm.setString(12, nv.getMaNV());

        return pm.executeUpdate() > 0;
    }
    
    
    public boolean Delete(String MaNV) throws Exception {
        String sql = "Delete from NhanVien where BINARY_CHECKSUM(MaNV) = BINARY_CHECKSUM (?)";
        ketnoi kn=new ketnoi();
        Connection conn = kn.getConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, MaNV);
        return pm.executeUpdate() > 0;
    }
}

