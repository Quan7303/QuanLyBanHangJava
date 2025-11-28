package giaodien;

import QLBH.Model.CTPNhap;
import QLBH.Model.CTPXuat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ketnoi.ketnoi;

public class PhieuXuatForm extends JFrame {
    
    private JTable tblPhieu, tblChiTiet, tblCur;
    private DefaultTableModel model1, model2, model3;
    JComboBox<String> MaSP = new JComboBox<>();
    JComboBox<String> MaNV = new JComboBox<>();
    private JTextField txtMaPX, txtNgayXuat, txtGhiChu, txtSoLuong, txtDonGia;
    private JLabel lblTongTien;       
    private JButton btnThem, btnXoa, btnLuu, btnThoat, btnThongKe;   
    private JScrollPane scrollPane1, scrollPane2;
    private DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm:ss");
    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }
    private void Combobox1() {
        try {
            String sql = "Select MaSP from SanPham;";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            MaSP.removeAllItems();
            while (rs.next()) {
                MaSP.addItem(rs.getString("MaSP"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void Combobox2() {
        try {
            String sql = "Select * from NhanVien;";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            MaNV.removeAllItems();
            while (rs.next()) {
                MaNV.addItem(rs.getString("MaNV"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public PhieuXuatForm() {
        setTitle("QUẢN LÝ PHIẾU XUẤT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ PHIẾU XUẤT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);

        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // --- Cột trái (WEST) ---
        txtMaPX = new JTextField();
        txtNgayXuat = new JTextField();
        txtGhiChu = new JTextField();
        txtSoLuong = new JTextField();
        txtDonGia = new JTextField();
        lblTongTien = new JLabel();        
        
        txtMaPX.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                txtMaPX.setText(txtMaPX.getText().trim().toUpperCase());
            }
        });
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(7, 2, 5, 15));
        westPanel.setBackground(new Color(248, 187, 208)); // hồng nhạt

        westPanel.add(new JLabel("MÃ PX:", SwingConstants.CENTER)); westPanel.add(txtMaPX);
        westPanel.add(new JLabel("MÃ NV:", SwingConstants.CENTER)); westPanel.add(MaNV);
        westPanel.add(new JLabel("GHI CHÚ:", SwingConstants.CENTER)); westPanel.add(txtGhiChu);
        westPanel.add(new JLabel("MÃ SP:", SwingConstants.CENTER)); westPanel.add(MaSP);
        westPanel.add(new JLabel("SỐ LƯỢNG:", SwingConstants.CENTER)); westPanel.add(txtSoLuong);
        westPanel.add(new JLabel("ĐƠN GIÁ XUẤT:", SwingConstants.CENTER));westPanel.add(txtDonGia);

        
        
        //nut chức năng
        JPanel buttonPanel = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        JPanel buttonPanel3 = new JPanel();

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Lưu");
        btnThoat = new JButton("Thoát");
        btnThongKe=new JButton("Thống kê");
        
        //chinh mau cho cac nut
        btnThem.setBackground(new Color(76, 175, 80));   // Xanh lá
        btnLuu.setBackground(new Color(33, 150, 243)); // Xanh dương
        btnXoa.setBackground(new Color(244, 67, 54));     // Đỏ
       
        //Them nut vao Panel va chinh mau nen
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel2.add(btnLuu);
        buttonPanel2.add(btnThoat);
        buttonPanel.setBackground(new Color(255, 220, 230));
        buttonPanel2.setBackground(new Color(255, 220, 230));
        
        //them icon cho cac nut
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 20, 20);
        setButtonIcon(btnXoa, "/giaodien/anh/nut_xoa.png", 20, 20);
        setButtonIcon(btnThem, "/giaodien/anh/nut_them.png", 20, 20);
        setButtonIcon(btnLuu, "/giaodien/anh/nut_sua.png", 20, 20);
        setButtonIcon(btnThongKe, "/giaodien/anh/tim_kiem.png", 40, 40);
        
        westPanel.setPreferredSize(new Dimension(400, 0));
        westPanel.add(buttonPanel);
        westPanel.add(buttonPanel2);
        

        
        westPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(westPanel, BorderLayout.WEST);
        

        // --- Phần phải (EAST) ---
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(400, 0));
        eastPanel.setLayout(new BorderLayout()); 
        eastPanel.setBackground(new Color(248, 187, 208));

        JPanel x = new JPanel();
        x.setLayout(new GridLayout(1, 0, 5, 5)); 
        JLabel tt = new JLabel("TỔNG SỐ TIỀN:", SwingConstants.CENTER);
        lblTongTien.setText("0.0"); 
        x.add(tt); x.add(lblTongTien);
        x.setPreferredSize(new Dimension(0, 30));
        eastPanel.add(x, BorderLayout.SOUTH);
        eastPanel.setPreferredSize(new Dimension(400, 0));
        
        String[] columnNames3 = {"Mã sản phẩm","Tên sản phẩm", "Số lượng", "Đơn giá xuất"};
        model3 = new DefaultTableModel(columnNames3, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblCur = new JTable(model3);
        tblCur.setFillsViewportHeight(true);
        tblCur.setRowHeight(25);
        JScrollPane scrollPane3 = new JScrollPane(tblCur);
        eastPanel.add(scrollPane3, BorderLayout.CENTER);

        eastPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        add(eastPanel, BorderLayout.EAST);

        
        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(400, 0));
        centerPanel.setBackground(new Color(255, 253, 208)); // vàng kem nhạt
        add(centerPanel, BorderLayout.CENTER);
        
    
        
        // westPanel.setPreferredSize(new Dimension(400, 0));
        
        // --- Phần bảng (SOUTH) ---
        String[] columnNames1 = {"Số PX", "Mã NV", "Ngày xuất", "Ghi chú"};
        String[] columnNames2 = {"Số PX", "Mã sản phẩm","Số lượng", "Đơn giá nhập"};
        
        model1 = new DefaultTableModel(columnNames1, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model2 = new DefaultTableModel(columnNames2, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tblPhieu = new JTable(model1); 
        tblPhieu.setFillsViewportHeight(true); 
        tblPhieu.setRowHeight(25);
        tblPhieu.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!e.isConsumed()){
                    getCTPhieu(tblPhieu.getValueAt(tblPhieu.getSelectedRow(), 0).toString()); 
                    e.consume();
                }
            }
            
        });
        
        tblChiTiet = new JTable(model2);
        tblChiTiet.setFillsViewportHeight(true);
        tblChiTiet.setRowHeight(25);
        

        
        // Cho bảng cuộn
        scrollPane1 = new JScrollPane(tblPhieu);
        scrollPane2 = new JScrollPane(tblChiTiet);
        // Panel phía Nam
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.add(scrollPane1); southPanel.add(scrollPane2);
        scrollPane1.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu xuất"));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu xuất"));
        
        southPanel.setPreferredSize(new Dimension(0, 150));

        // Gắn panel phía Nam vào giao diện
        add(southPanel, BorderLayout.SOUTH);

        // Gọi hàm load dữ liệu từ SQL
        Combobox1();
        Combobox2();
        load();
        setButton();
        //su kien cac nut
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });
        
        
    }
    public void resetText(){
        txtMaPX.setText(""); MaNV.setSelectedIndex(0); txtGhiChu.setText("");
        MaSP.setSelectedIndex(0); txtSoLuong.setText(""); txtDonGia.setText(""); 
        model3.setRowCount(0); lblTongTien.setText("0.0");
    }
    public boolean checkTextSL(){
        if(txtSoLuong.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try{
            Integer.parseInt(txtSoLuong.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean checkTextDG(){
        if(txtDonGia.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Đơn giá không được để trống", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try{
            Double.valueOf(txtDonGia.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean checkTextMaPhieu(){
        txtMaPX.setText(txtMaPX.getText().trim().toUpperCase()); 
        if(txtMaPX.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất không được để trống", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String soPX = txtMaPX.getText();
        if(checkExistPX(soPX)){
            JOptionPane.showMessageDialog(this, "Phiếu xuất đã tồn tại", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;      
    }
    
    public void load() {
        try {
            // Xoá dữ liệu cũ trong bảng
            model1.setRowCount(0);

            // Câu truy vấn
            String sql1 = "SELECT * FROM PhieuXuat ORDER BY NgayXuat DESC";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            // Duyệt kết quả và thêm vào bảng
            while (rs1.next()) {
                String time = rs1.getTimestamp("NgayXuat").toLocalDateTime().format(fmt1); 
                Object[] row = {
                    rs1.getString("SoPX"),
                    rs1.getString("MaNV"),
                    time, 
                    rs1.getString("GhiChu"),
                    
                };
                model1.addRow(row);
            }

            // Cập nhật lại bảng
            model1.fireTableDataChanged();
            ketnoi.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }  
    public void getCTPhieu(String maPX){
        Connection c = null;
        try{
            String command = "SELECT * FROM CTPhieuXuat WHERE SoPX = ?;";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, maPX);
            ResultSet rs = state.executeQuery();
            model2.setRowCount(0);
            Double amount = 0.0;
            while(rs.next()){
                Object[] row = {rs.getString("SoPX"), rs.getString("MaSP"), 
                rs.getString("SoLuong"), rs.getString("DGBan")};
                int sl = Integer.valueOf(rs.getString("SoLuong"));
                Double dg = Double.valueOf(rs.getString("DGBan"));
                amount += sl * dg;
                model2.addRow(row); 
            }
            scrollPane2.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu xuất"
                    + "                                        Tổng tiền: " + amount.longValue()));
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }

    }
    
    public String getTenSP(String maSP){
        String result = null;
        Connection c = null;
        try{
            String command = "SELECT tenSP FROM SanPham WHERE maSP = ?";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, maSP);
            ResultSet rs = state.executeQuery();
            if(rs.next()){
                result = rs.getString("tenSP");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return result;
    }
    public boolean updateSLTon(String maSP, int sl){
        Connection c = null;
        try{
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            String command = "UPDATE SanPham SET SLTon += ? WHERE MaSP = ?";
            PreparedStatement state = c.prepareStatement(command);
            state.setInt(1, sl);
            state.setString(2, maSP); 
            int rs = state.executeUpdate();
            if(rs > 0){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return false;
    }
    public int getSLTon(String maSP){
        Connection c = null;
        try{
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            String command = "select SLTon FROM SanPham WHERE MaSP = ?";
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, maSP); 
            ResultSet rs = state.executeQuery();
            if(rs.next()){
                return rs.getInt("SLTon");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return 0;
    }
    public boolean checkExistCurrentPX(String maSP){
        for(int i = 0; i < tblCur.getRowCount(); i++){
            if(tblCur.getValueAt(i, 0).toString().equalsIgnoreCase(maSP)) return true;
        }
        return false;      
    }
    public void resetTongTien(){
        Double money = 0.0;
        for(int i = 0; i < tblCur.getRowCount(); i++){
            money += Integer.valueOf(tblCur.getValueAt(i, 2).toString()) * 
                    Double.valueOf(tblCur.getValueAt(i, 3).toString());
        }
        lblTongTien.setText("" + money.longValue()); 
        
    }
    private void showWarning(String text){
        JOptionPane.showMessageDialog(this, text, 
                    "", JOptionPane.WARNING_MESSAGE);
    }
    public void setButton(){
        btnThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkTextSL()) return;
                if(!checkTextDG()) return;
                int soLuong = Integer.valueOf(txtSoLuong.getText());
                String maSP = MaSP.getSelectedItem().toString();
                if(soLuong > getSLTon(maSP)){
                    showWarning("Số lượng tồn không đủ"); 
                    return;
                }
                if(checkExistCurrentPX(maSP)){
                    return;
                }
                model3.addRow(new Object[]{maSP, getTenSP(maSP), txtSoLuong.getText(), txtDonGia.getText()});  
                resetTongTien();
            }
        });
        btnXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblCur.getSelectedRow() != -1){
                    model3.removeRow(tblCur.getSelectedRow());
                    resetTongTien();
                }
            }
        });
        btnLuu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String soPX = txtMaPX.getText();
                if(!checkTextMaPhieu()) return;
                if(model3.getRowCount() == 0){
                    showWarning("Phiếu xuất rỗng!"); 
                    return;
                }
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn lưu không?", 
                "", JOptionPane.YES_NO_OPTION);
                if(choice != 0) return;
                if(!savePhieuXuat()) return;
                for(int i = 0; i < tblCur.getRowCount(); i++){
                    String maSP = tblCur.getValueAt(i, 0).toString();
                    int soLuong = Integer.valueOf(tblCur.getValueAt(i, 2).toString()); 
                    double donGia = Double.valueOf(tblCur.getValueAt(i, 3).toString());
                    CTPXuat x = new CTPXuat(soPX, maSP, "", soLuong, donGia);
                    if(saveCTPhieuXuat(x)){
                        updateSLTon(maSP, -soLuong);
                    }
                    else return;
                }
                load();
                resetText();
            }
        });
    }
    
    public boolean checkExistPX(String soPX){
        Connection c = null;
        try{
            String command = "SELECT 1 FROM PhieuXuat WHERE SoPX = ?";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPX);
            ResultSet rs = state.executeQuery();
            if(rs.next()) return true;
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return false;
    }
   
    public boolean savePhieuXuat(){
        String soPX = txtMaPX.getText(), ghiChu = txtGhiChu.getText(), nv = MaNV.getSelectedItem().toString();  
        
        LocalDateTime ngayNhap = LocalDateTime.now();
        Connection c = null;
        try{
            String command = "INSERT INTO PhieuXuat VALUES(?, ?, ?, ?)";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPX);
            state.setTimestamp(2, Timestamp.valueOf(ngayNhap));
            state.setString(3, ghiChu);
            state.setString(4, nv);
            int rs = state.executeUpdate();
            if(rs > 0) return true;
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return false;
    }
    public boolean saveCTPhieuXuat(CTPXuat x){
        String soPX = x.getSoPX(), maSP = x.getMaSP();
        Integer soLuong = x.getSoLuong();
        Double donGia = x.getDGBan();
        Connection c = null;
        try{
            String command = "INSERT INTO CTPhieuXuat VALUES(?, ?, ?, ?)";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPX);
            state.setString(2, maSP);
            state.setInt(3, soLuong);
            state.setDouble(4, donGia);
            int rs = state.executeUpdate();
            if(rs > 0) return true;
        }
        catch(Exception e){
            // e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Trùng sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(c != null) try{c.close();} catch(SQLException e){}
        }
        return false;
        
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Không thể dùng Nimbus, dùng giao diện mặc định!");
        }
        SwingUtilities.invokeLater(() -> new PhieuXuatForm().setVisible(true));
    }
}
