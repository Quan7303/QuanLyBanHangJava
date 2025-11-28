package ketnoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ketnoi {
    private final String serverName = "DESKTOP-RM7VAFI\\KHOA"; // tên server SQL của bạn
    private final String dbName = "master";       // ví dụ: QLNV, QLSV...
    private final String portNumber = "61506";                // cổng SQL Server
    private final String userID = "sa";                      // user đăng nhập SQL
    private final String password = "123456";       // mật khẩu của user sa

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Nạp driver JDBC cho SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // Tạo chuỗi kết nối
        String url = "jdbc:sqlserver://DESKTOP-RM7VAFI\\KHOA:61506;databaseName=Java_QLBanHangMatKinh;encrypt=false";

        // Kết nối tới SQL Server
        Connection conn = DriverManager.getConnection(url, userID, password);
        return conn;
    }
    public static void closeConnection(Connection c){
        try{
            if(c!=null){
                c.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Test thử kết nối
    public static void main(String[] args) {
        try {
            ketnoi db = new ketnoi();
            Connection conn = db.getConnection();
            if (conn != null) {
                System.out.println("Kết nối SQL Server thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
    }
}