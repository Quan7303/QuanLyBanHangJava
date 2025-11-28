package giaodien;

import QLBH.Model.CTPNhap;
import QLBH.Model.PNhap;
import giaodien.giaodien;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ketnoi.ketnoi;

public class PhieuNhapForm extends JFrame {
    private JTable tblPhieu, tblChiTiet, tblCur;
    private DefaultTableModel model1, model2, model3;
    JComboBox<String> MaSP = new JComboBox<>();
    JComboBox<String> MaNCC = new JComboBox<>();
    private JTextField txtMaPN, txtNgayNhap, txtGhiChu, txtSoLuong, txtDonGia;
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
            String sql = "Select * from NhaCungCap;";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            MaNCC.removeAllItems();
            while (rs.next()) {
                MaNCC.addItem(rs.getString("MaNCC"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public PhieuNhapForm() {
        setTitle("QUẢN LÝ PHIẾU NHẬP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ PHIẾU NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);

        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // --- Cột trái (WEST) ---
        txtMaPN = new JTextField();
        txtNgayNhap = new JTextField();
        txtGhiChu = new JTextField();
        txtSoLuong = new JTextField();
        txtDonGia = new JTextField();
        lblTongTien = new JLabel();        
        
        txtMaPN.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                txtMaPN.setText(txtMaPN.getText().trim().toUpperCase());
            }
        });
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(7, 2, 5, 15));
        westPanel.setBackground(new Color(248, 187, 208)); // hồng nhạt

        westPanel.add(new JLabel("MÃ PN:", SwingConstants.CENTER)); westPanel.add(txtMaPN);
        westPanel.add(new JLabel("MÃ NCC:", SwingConstants.CENTER)); westPanel.add(MaNCC);
        westPanel.add(new JLabel("GHI CHÚ:", SwingConstants.CENTER)); westPanel.add(txtGhiChu);
        westPanel.add(new JLabel("MÃ SP:", SwingConstants.CENTER)); westPanel.add(MaSP);
        westPanel.add(new JLabel("SỐ LƯỢNG:", SwingConstants.CENTER)); westPanel.add(txtSoLuong);
        westPanel.add(new JLabel("ĐƠN GIÁ NHẬP:", SwingConstants.CENTER));westPanel.add(txtDonGia);

        
        
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
        
        String[] columnNames3 = {"Mã sản phẩm","Tên sản phẩm", "Số lượng", "Đơn giá nhập"};
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
        String[] columnNames1 = {"Số PN", "Mã NCC", "Ngày nhập", "Ghi chú"};
        String[] columnNames2 = {"Số PN", "Mã sản phẩm","Số lượng", "Đơn giá nhập"};
        
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
        scrollPane1.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu nhập"));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu nhập"));
        
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
        txtMaPN.setText(""); MaNCC.setSelectedIndex(0); txtGhiChu.setText("");
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
        txtMaPN.setText(txtMaPN.getText().toUpperCase()); 
        if(txtMaPN.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Mã phiếu nhập không được để trống", 
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String soPN = txtMaPN.getText();
        if(checkExistPN(soPN)){
            JOptionPane.showMessageDialog(this, "Phiếu nhập đã tồn tại", 
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
            String sql1 = "SELECT * FROM PhieuNhap ORDER BY NgayNhap DESC";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            // Duyệt kết quả và thêm vào bảng
            while (rs1.next()) {
                String time = rs1.getTimestamp("NgayNhap").toLocalDateTime().format(fmt1); 
                Object[] row = {
                    rs1.getString("SoPN"),
                    rs1.getString("MaNCC"),
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
    public void getCTPhieu(String maPN){
        Connection c = null;
        try{
            String command = "SELECT * FROM CTPhieuNhap WHERE SoPN = ?;";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, maPN);
            ResultSet rs = state.executeQuery();
            model2.setRowCount(0);
            Double amount = 0.0;
            while(rs.next()){
                Object[] row = {rs.getString("SoPN"), rs.getString("MaSP"), 
                rs.getString("SoLuong"), rs.getString("DGNhap")};
                int sl = Integer.valueOf(rs.getString("SoLuong"));
                Double dg = Double.valueOf(rs.getString("DGNhap"));
                amount += sl * dg;
                model2.addRow(row); 
            }
            scrollPane2.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu nhập"
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
    public boolean checkExistCurrentPN(String maSP){
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
                String maSP = MaSP.getSelectedItem().toString();
                if(checkExistCurrentPN(maSP)){
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
                String soPN = txtMaPN.getText();
                if(!checkTextMaPhieu()) return;
                if(model3.getRowCount() == 0){
                    showWarning("Phiếu nhập rỗng!"); 
                    return;
                }
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn lưu không?", 
                "", JOptionPane.YES_NO_OPTION);
                if(choice != 0) return;
                if(!savePhieuNhap()) return;
                for(int i = 0; i < tblCur.getRowCount(); i++){
                    String maSP = tblCur.getValueAt(i, 0).toString();
                    int soLuong = Integer.valueOf(tblCur.getValueAt(i, 2).toString()); 
                    double donGia = Double.valueOf(tblCur.getValueAt(i, 3).toString());
                    CTPNhap x = new CTPNhap(soPN, maSP, "", soLuong, donGia);
                    if(saveCTPhieuNhap(x)){
                        updateSLTon(maSP, soLuong);
                    }
                    else return;
                }
                load();
                resetText();
            }
        });
    }
    
    public boolean checkExistPN(String soPN){
        Connection c = null;
        try{
            String command = "SELECT 1 FROM PhieuNhap WHERE SoPN = ?";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPN);
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
   
    public boolean savePhieuNhap(){
        String soPN = txtMaPN.getText(), ghiChu = txtGhiChu.getText(), ncc = MaNCC.getSelectedItem().toString();  
        
        LocalDateTime ngayNhap = LocalDateTime.now();
        Connection c = null;
        try{
            String command = "INSERT INTO PhieuNhap VALUES(?, ?, ?, ?)";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPN);
            state.setTimestamp(2, Timestamp.valueOf(ngayNhap));
            state.setString(3, ghiChu);
            state.setString(4, ncc);
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
    public boolean saveCTPhieuNhap(CTPNhap x){
        String soPN = x.getSoPN(), maSP = x.getMaSP();
        Integer soLuong = x.getSoLuong();
        Double donGia = x.getDGNhap();
        Connection c = null;
        try{
            String command = "INSERT INTO CTPhieuNhap VALUES(?, ?, ?, ?)";
            ketnoi kn = new ketnoi();
            c = kn.getConnection();
            PreparedStatement state = c.prepareStatement(command);
            state.setString(1, soPN);
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
        SwingUtilities.invokeLater(() -> new PhieuNhapForm().setVisible(true));
    }
}
