package giaodien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import com.toedter.calendar.JDateChooser;

import ketnoi.ketnoi;

public class HoaDonForm extends JFrame {
    static ketnoi ketnoi = new ketnoi();
    // --- TextFields (THÔNG TIN HÓA ĐƠN) ---
    private JTextField tfMaHD, tfMaKH, tfMaNV;
    private JDateChooser tfNgayDH, tfNgayNH;
    private JComboBox<String> comboPTTT;


    // --- Buttons ---
    private JButton btnThemHD, btnXoaHD, btnSuaHD, btnThoat;

    // --- Table ---
    private JTable table, tableCTHD;
    private DefaultTableModel tableModel, modelCTHD;



    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public HoaDonForm() {
        setTitle("QUẢN LÝ HÓA ĐƠN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);
        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // --- WEST PANEL (THÔNG TIN HÓA ĐƠN) ---
        tfMaHD = new JTextField();
        tfMaKH = new JTextField();
        tfMaNV = new JTextField();
        tfNgayDH = new JDateChooser();
        tfNgayDH.setDateFormatString("yyyy-MM-dd");
        tfNgayNH = new JDateChooser();
        tfNgayNH.setDateFormatString("yyyy-MM-dd");
        comboPTTT = new JComboBox<>(
                new String[]{"Tien mat", "Chuyen khoan"}
        );

        JPanel westPanel = new JPanel(new GridLayout(7,2,5,25));
        westPanel.setBackground(new Color(248,187,208));

        westPanel.add(new JLabel("MÃ HD:", SwingConstants.CENTER));
        westPanel.add(tfMaHD);
        westPanel.add(new JLabel("MÃ KH:", SwingConstants.CENTER));
        westPanel.add(tfMaKH);
        westPanel.add(new JLabel("MÃ NV:", SwingConstants.CENTER));
        westPanel.add(tfMaNV);
        westPanel.add(new JLabel("NGÀY DH:", SwingConstants.CENTER));
        westPanel.add(tfNgayDH);
        westPanel.add(new JLabel("NGÀY NH:", SwingConstants.CENTER));
        westPanel.add(tfNgayNH);
        westPanel.add(new JLabel("PTTT:", SwingConstants.CENTER));
        westPanel.add(comboPTTT);

        // --- NÚT chức năng ---
        btnThemHD = new JButton("Thêm");
        btnXoaHD = new JButton("Xóa");
        btnSuaHD = new JButton("Sửa");
        btnThoat = new JButton("Thoát");

        setButtonIcon(btnThemHD,"/giaodien/anh/nut_them.png", 20, 20);
        setButtonIcon(btnXoaHD,"/giaodien/anh/nut_xoa.png", 20, 20);
        setButtonIcon(btnSuaHD,"/giaodien/anh/nut_sua.png", 20, 20);
        setButtonIcon(btnThoat,"/giaodien/anh/nut_thoat.png", 20, 20);

        btnThemHD.setBackground(new Color(76,175,80));
        btnSuaHD.setBackground(new Color(33,150,243));
        btnXoaHD.setBackground(new Color(244,67,54));



        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(new Color(255,220,230));
        buttonPanel1.add(btnThemHD);
        buttonPanel1.add(btnSuaHD);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(new Color(255,220,230));
        buttonPanel2.add(btnXoaHD);
        buttonPanel2.add(btnThoat);

        westPanel.add(buttonPanel1);
        westPanel.add(buttonPanel2);
        westPanel.setPreferredSize(new Dimension(400,0));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        add(westPanel, BorderLayout.WEST);



        // --- EAST PANEL (DANH SÁCH SẢN PHẨM CỦA HÓA ĐƠN) ---
        String[] colCTHD = {"MaSP", "SoLuongDat", "DGBan", "ThanhTien"};
        modelCTHD = new DefaultTableModel(colCTHD, 0);
        tableCTHD = new JTable(modelCTHD);

        JScrollPane spCTHD = new JScrollPane(tableCTHD);

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(spCTHD, BorderLayout.CENTER);
        eastPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        eastPanel.setPreferredSize(new Dimension(400, 0));

        // ====== CÁC NÚT CHO CHI TIẾT HÓA ĐƠN ======
        JButton btnThemCT = new JButton("Thêm SP");
        JButton btnSuaCT = new JButton("Sửa SP");
        JButton btnXoaCT = new JButton("Xóa SP");
        JButton btnTinhTien = new JButton("Tính tổng");

        btnThemCT.setBackground(new Color(76,175,80));
//        setButtonIcon(btnThemCT, "/giaodien/anh/nut_them.png", 20, 20);
        btnSuaCT.setBackground(new Color(33,150,243));
//        setButtonIcon(btnSuaCT, "/giaodien/anh/nut_sua.png", 20, 20);
        btnXoaCT.setBackground(new Color(244,67,54));
//        setButtonIcon(btnXoaCT, "/giaodien/anh/nut_xoa.png", 20, 20);
        btnTinhTien.setBackground(new Color(255,220,230));
//        setButtonIcon(btnTinhTien, "/giaodien/anh/may_in.png", 20, 20);

        JPanel pnlCTButton = new JPanel();
        pnlCTButton.add(btnThemCT);
        pnlCTButton.add(btnSuaCT);
        pnlCTButton.add(btnXoaCT);
        pnlCTButton.add(btnTinhTien);

        eastPanel.add(pnlCTButton, BorderLayout.SOUTH);


        add(eastPanel, BorderLayout.EAST);


        // --- CENTER PANEL ---
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255,253,208));
        add(centerPanel, BorderLayout.CENTER);

        // --- SOUTH PANEL (BẢNG HÓA ĐƠN) ---
        String[] columnNames = {"MaHD","MaKH","MaNV","NgayDH","NgayNH","PTTT"};
        tableModel = new DefaultTableModel(columnNames,0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        southPanel.setPreferredSize(new Dimension(0,140));
        add(southPanel, BorderLayout.SOUTH);



        // --- Load dữ liệu ban đầu ---
        loadTableData();

        // --- SỰ KIỆN NÚT ---
        btnThoat.addActionListener(e -> {this.setVisible(false); new giaodien().setVisible(true);});
        btnThemHD.addActionListener(e -> addBill());
        btnXoaHD.addActionListener(e -> deleteBill());
        btnSuaHD.addActionListener(e -> updateBill());

        btnThemCT.addActionListener(e -> addCTHD());
        btnSuaCT.addActionListener(e -> updateCTHD());
        btnXoaCT.addActionListener(e -> deleteCTHD());
        btnTinhTien.addActionListener(e -> calculateTotalCTHD());


        // --- Chọn dòng bảng điền vào TextField ---
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                tfMaHD.setText(tableModel.getValueAt(row,0).toString());
                tfMaKH.setText(tableModel.getValueAt(row,1).toString());
                tfMaNV.setText(tableModel.getValueAt(row,2).toString());
                tfNgayDH.setDate((java.util.Date)tableModel.getValueAt(row,3));
                tfNgayNH.setDate((java.util.Date)tableModel.getValueAt(row,4));
                comboPTTT.setSelectedItem(tableModel.getValueAt(row,5).toString());

                // --- Load sản phẩm của hóa đơn ---
                String maHD = tableModel.getValueAt(row, 0).toString();
                loadCTHD(maHD, modelCTHD);
            }
        });
    }

    // --- HÀM LOAD DỮ LIỆU ---
    private void loadTableData(){
        tableModel.setRowCount(0);
        String sql = "SELECT MaHD, MaKH, MaNV, NgayDH, NgayNH, PTTT FROM hoadon";
        try(Connection conn = ketnoi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                Object[] row = {
                        rs.getString("MaHD"),
                        rs.getString("MaKH"),
                        rs.getString("MaNV"),
                        rs.getDate("NgayDH"),
                        rs.getDate("NgayNH"),
                        rs.getString("PTTT")
                };
                tableModel.addRow(row);
            }

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi tải dữ liệu hóa đơn!");
        }
    }

    // --- HÀM THÊM HÓA ĐƠN ---
    private void addBill() {
        String error = validateBill();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Lỗi nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "INSERT INTO hoadon(MaHD, MaKH, MaNV, NgayDH, NgayNH, PTTT) VALUES(?,?,?,?,?,?)";
        try (Connection conn = ketnoi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, tfMaHD.getText());
            ps.setString(2, tfMaKH.getText());
            ps.setString(3, tfMaNV.getText());

            java.sql.Date ngayDH = new java.sql.Date(tfNgayDH.getDate().getTime());
            java.sql.Date ngayNH = new java.sql.Date(tfNgayNH.getDate().getTime());

            ps.setDate(4, ngayDH);
            ps.setDate(5, ngayNH);

            ps.setString(6, comboPTTT.getSelectedItem().toString());

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
                loadTableData();
            }
            clearInfo();

        } catch (SQLException ex) {
            String msg = ex.getMessage().toLowerCase();
            // TH1: lỗi trùng mã hóa đơn
            if (msg.contains("duplicate") || msg.contains("primary")) {
                JOptionPane.showMessageDialog(this,
                        "Mã hóa đơn đã tồn tại, không thể thêm!",
                        "Lỗi trùng mã hóa đơn",
                        JOptionPane.ERROR_MESSAGE);
            }
            // TH2: lỗi FK nhân viên
            else if (msg.contains("ma_nv") || msg.contains("nhanvien") || msg.contains("fk_hoadon_nhanvien")) {
                JOptionPane.showMessageDialog(this,
                        "Mã nhân viên không tồn tại trong hệ thống!",
                        "Lỗi khóa ngoại nhân viên",
                        JOptionPane.ERROR_MESSAGE);
            }
            // TH3: lỗi FK khách hàng
            else if (msg.contains("ma_kh") || msg.contains("khachhang") || msg.contains("fk_hoadon_khachhang")) {
                JOptionPane.showMessageDialog(this,
                        "Mã khách hàng không tồn tại trong hệ thống!",
                        "Lỗi khóa ngoại khách hàng",
                        JOptionPane.ERROR_MESSAGE);
            }
            // Lỗi khác
            else {
                JOptionPane.showMessageDialog(this,
                        "Lỗi SQL: " + ex.getMessage(),
                        "Lỗi hệ thống",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi khi thêm hóa đơn!");
        }
    }
    // --- HÀM XÓA HÓA ĐƠN ---
    private void deleteBill(){
        String maHD = tfMaHD.getText();
        if(maHD.isEmpty()){
            JOptionPane.showMessageDialog(this,"Nhập mã hóa đơn cần xóa!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa hóa đơn "+maHD+"?","Xác nhận",JOptionPane.YES_NO_OPTION);
        if(confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM hoadon WHERE MaHD=?";
        try(Connection conn = ketnoi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,maHD);
            int result = ps.executeUpdate();
            if(result>0){
                JOptionPane.showMessageDialog(this,"Xóa hóa đơn thành công!");
                loadTableData();
            }

            clearInfo();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi xóa hóa đơn!");
        }
    }

    // --- HÀM SỬA HÓA ĐƠN ---
    private void updateBill(){
        String error = validateBill();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "UPDATE hoadon SET MaKH=?, MaNV=?, NgayDH=?, NgayNH=?, PTTT=? WHERE MaHD=?";
        try(Connection conn = ketnoi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, tfMaKH.getText());
            ps.setString(2, tfMaNV.getText());

            java.sql.Date ngayDH = new java.sql.Date(tfNgayDH.getDate().getTime());
            java.sql.Date ngayNH = new java.sql.Date(tfNgayNH.getDate().getTime());

            ps.setDate(3, ngayDH);
            ps.setDate(4, ngayNH);

            ps.setString(5, comboPTTT.getSelectedItem().toString());
            ps.setString(6, tfMaHD.getText());

            int result = ps.executeUpdate();
            if(result>0){
                JOptionPane.showMessageDialog(this,"Cập nhật hóa đơn thành công!");
                loadTableData();
                clearInfo();
            }else {
                JOptionPane.showMessageDialog(this,
                        "Không tìm thấy mã hóa đơn để cập nhập",
                        "Không tồn tại",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException ex) {
            String msg = ex.getMessage().toLowerCase();

            // TH1: lỗi trùng mã hóa đơn
            if (msg.contains("duplicate") || msg.contains("primary")) {
                JOptionPane.showMessageDialog(this,
                        "Mã hóa đơn đã tồn tại, không thể thêm!",
                        "Lỗi trùng mã hóa đơn",
                        JOptionPane.ERROR_MESSAGE);
            }

            // TH2: lỗi FK nhân viên
            else if (msg.contains("ma_nv") || msg.contains("nhanvien") || msg.contains("fk_hoadon_nhanvien")) {
                JOptionPane.showMessageDialog(this,
                        "Mã nhân viên không tồn tại trong hệ thống!",
                        "Lỗi khóa ngoại nhân viên",
                        JOptionPane.ERROR_MESSAGE);
            }

            // TH3: lỗi FK khách hàng
            else if (msg.contains("ma_kh") || msg.contains("khachhang") || msg.contains("fk_hoadon_khachhang")) {
                JOptionPane.showMessageDialog(this,
                        "Mã khách hàng không tồn tại trong hệ thống!",
                        "Lỗi khóa ngoại khách hàng",
                        JOptionPane.ERROR_MESSAGE);
            }

            // Lỗi khác
            else {
                JOptionPane.showMessageDialog(this,
                        "Lỗi SQL: " + ex.getMessage(),
                        "Lỗi hệ thống",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi update bill", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCTHD(String maHD, DefaultTableModel modelCTHD){
        modelCTHD.setRowCount(0);

        String sql = """
        SELECT MaSP, SoLuongDat, DGBan, (SoLuongDat * DGBan) AS ThanhTien
        FROM cthoadon
        WHERE MaHD = ?
    """;

        try (Connection conn = ketnoi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHD);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                        rs.getString("MaSP"),
                        rs.getInt("SoLuongDat"),
                        rs.getDouble("DGBan"),
                        rs.getDouble("ThanhTien")
                };
                modelCTHD.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCTHD() {
        // Tạo panel nhập liệu
        JPanel panel = new JPanel(new GridLayout(4,2,5,5));
        JTextField tfMaSPInput = new JTextField();
        JTextField tfSoLuongInput = new JTextField();
        JTextField tfDonGiaInput = new JTextField();
        JTextField tfThanhTien = new JTextField();
        tfThanhTien.setEditable(false);

        panel.add(new JLabel("Mã SP:"));
        panel.add(tfMaSPInput);
        panel.add(new JLabel("Số lượng:"));
        panel.add(tfSoLuongInput);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(tfDonGiaInput);
        panel.add(new JLabel("Thành tiền:"));
        panel.add(tfThanhTien);

        // Tự tính thành tiền khi nhập số lượng hoặc đơn giá
        DocumentListener listener = new DocumentListener() {
            private void update() {
                try {
                    int sl = Integer.parseInt(tfSoLuongInput.getText());
                    double dg = Double.parseDouble(tfDonGiaInput.getText());
                    tfThanhTien.setText(String.valueOf(sl * dg));
                } catch(Exception e) {
                    tfThanhTien.setText("0");
                }
            }
            public void insertUpdate(DocumentEvent e) {update();}
            public void removeUpdate(DocumentEvent e) {update();}
            public void changedUpdate(DocumentEvent e) {update();}
        };
        tfSoLuongInput.getDocument().addDocumentListener(listener);
        tfDonGiaInput.getDocument().addDocumentListener(listener);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Nhập thông tin sản phẩm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.OK_OPTION) {
            String maSP = tfMaSPInput.getText();

            String slText = tfSoLuongInput.getText().trim();
            String dgText = tfDonGiaInput.getText().trim();
            if(slText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng!");
            }
            if(dgText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nập đơn giá!");
            }

            int sl = 0; double dg = 0;
            try{
                sl = Integer.parseInt(slText);
                if(sl <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                    return;
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ!");
                return;
            }

            try{
                dg = Integer.parseInt(dgText);
                if(dg <= 0) {
                    JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0!");
                    return;
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Đơn giá phải là số nguyên");
                return;
            }

            // Kiểm tra mã sản phẩm tồn tại
            try (Connection conn = ketnoi.getConnection();
                 PreparedStatement psCheck = conn.prepareStatement("SELECT COUNT(*) FROM sanpham WHERE MaSP=?")) {
                psCheck.setString(1, maSP);
                ResultSet rs = psCheck.executeQuery();
                rs.next();
                if(rs.getInt(1) == 0) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại!");
                    return;
                }

                // Thêm sản phẩm vào bảng CTHD
                try (PreparedStatement psInsert = conn.prepareStatement(
                        "INSERT INTO cthoadon (MaHD, MaSP, SoLuongDat, DGBan) VALUES (?,?,?,?)")) {
                    psInsert.setString(1, tfMaHD.getText());
                    psInsert.setString(2, maSP);
                    psInsert.setInt(3, sl);
                    psInsert.setDouble(4, dg);
                    psInsert.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                    loadCTHD(tfMaHD.getText(), modelCTHD);
                }

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
    }

    private void updateCTHD() {
        int row = tableCTHD.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn sửa!");
            return;
        }

        // Lấy thông tin hiện tại
        String maSP = modelCTHD.getValueAt(row, 0).toString();
        int soLuong = Integer.parseInt(modelCTHD.getValueAt(row, 1).toString());
        double donGia = Double.parseDouble(modelCTHD.getValueAt(row, 2).toString());

        // Tạo panel dialog
        JPanel panel = new JPanel(new GridLayout(3,2,5,5));
        JTextField tfSoLuongInput = new JTextField(String.valueOf(soLuong));
        JTextField tfDonGiaInput = new JTextField(String.valueOf(donGia));
        JTextField tfThanhTien = new JTextField(String.valueOf(soLuong * donGia));
        tfThanhTien.setEditable(false);

        panel.add(new JLabel("Số lượng:"));
        panel.add(tfSoLuongInput);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(tfDonGiaInput);
        panel.add(new JLabel("Thành tiền:"));
        panel.add(tfThanhTien);

        // DocumentListener tự tính thành tiền
        DocumentListener listener = new DocumentListener() {
            private void update() {
                try {
                    int sl = Integer.parseInt(tfSoLuongInput.getText());
                    double dg = Double.parseDouble(tfDonGiaInput.getText());
                    tfThanhTien.setText(String.valueOf(sl * dg));
                } catch(Exception e) {
                    tfThanhTien.setText("0");
                }
            }
            public void insertUpdate(DocumentEvent e){ update(); }
            public void removeUpdate(DocumentEvent e){ update(); }
            public void changedUpdate(DocumentEvent e){ update(); }
        };
        tfSoLuongInput.getDocument().addDocumentListener(listener);
        tfDonGiaInput.getDocument().addDocumentListener(listener);
        String maSanPham = modelCTHD.getValueAt(row, 0).toString();
        int result = JOptionPane.showConfirmDialog(this, panel, "Sửa sản phẩm " + maSanPham, JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String slText = tfSoLuongInput.getText().trim();
            String dgText = tfDonGiaInput.getText().trim();

            if (slText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng!");
                return;
            }
            if (dgText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá!");
                return;
            }

            int sl;
            double dg;

            try {
                sl = Integer.parseInt(slText);
                if (sl <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                return;
            }

            try {
                dg = Double.parseDouble(dgText);
                if (dg <= 0) {
                    JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải là số thực hợp lệ!");
                return;
            }
            try(Connection conn = ketnoi.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE cthoadon SET SoLuongDat=?, DGBan=? WHERE MaHD=? AND MaSP=?")) {

                ps.setInt(1, Integer.parseInt(tfSoLuongInput.getText()));
                ps.setDouble(2, Double.parseDouble(tfDonGiaInput.getText()));
                ps.setString(3, tfMaHD.getText());
                ps.setString(4, maSP);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!");
                loadCTHD(tfMaHD.getText(), modelCTHD);

            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }
    }

    private void deleteCTHD() {
        int row = tableCTHD.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa!");
            return;
        }

        String maSP = modelCTHD.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa sản phẩm " + maSP + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(confirm != JOptionPane.YES_OPTION) return;

        try(Connection conn = ketnoi.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM cthoadon WHERE MaHD=? AND MaSP=?")) {

            ps.setString(1, tfMaHD.getText());
            ps.setString(2, maSP);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
            loadCTHD(tfMaHD.getText(), modelCTHD);


        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void calculateTotalCTHD() {
        // Tính tổng tiền từ bảng chi tiết hóa đơn
        double tongTien = 0;
        for (int i = 0; i < modelCTHD.getRowCount(); i++) {
            int sl = Integer.parseInt(modelCTHD.getValueAt(i, 1).toString());
            double dg = Double.parseDouble(modelCTHD.getValueAt(i, 2).toString());
            tongTien += sl * dg;
        }

        double thue = tongTien * 0.005; // 5% thuế
        double tienPhaiTra = tongTien + thue;

        // Tạo panel hiển thị thông tin
        JPanel panel = new JPanel(new GridLayout(3,2,5,5));
        JTextField tfTongTien = new JTextField(String.format("%.0f", tongTien));
        tfTongTien.setEditable(false);
        JTextField tfThue = new JTextField(String.format("%.2f", thue));
        tfThue.setEditable(false);
        JTextField tfTienPhaiTra = new JTextField(String.format("%.0f", tienPhaiTra));
        tfTienPhaiTra.setEditable(false);

        panel.add(new JLabel("Tổng tiền:"));
        panel.add(tfTongTien);
        panel.add(new JLabel("Thuế 0.5%:"));
        panel.add(tfThue);
        panel.add(new JLabel("Tiền phải trả:"));
        panel.add(tfTienPhaiTra);

        // Hiển thị dialog
        JOptionPane.showMessageDialog(this, panel, "Tổng tiền hóa đơn", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearInfo(){
        this.tfMaHD.setText("");
        this.tfMaKH.setText("");
        this.tfMaNV.setText("");
        this.tfNgayDH.setDate(null);
        this.tfNgayNH.setDate(null);
    }

    private String validateBill(){
        if(tfMaHD.getText().trim().isEmpty()){
            return "Mã hóa đơn không được để trống!";
        }
        if(tfMaKH.getText().trim().isEmpty()){
            return "Mã khách hàng không được để trống!";
        }
        if(tfMaNV.getText().trim().isEmpty()){
            return "Mã nhân viên không được để trống!";
        }
        if (tfNgayDH.getDate() == null)
            return "Ngày đặt hàng không hợp lệ hoặc chưa chọn!";

        if (tfNgayNH.getDate() == null)
            return "Ngày nhận hàng không hợp lệ hoặc chưa chọn!";

        if (tfNgayNH.getDate().before(tfNgayDH.getDate()))
            return "Ngày nhận hàng không thể nhỏ hơn Ngày đặt hàng!";
        return null;
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new HoaDonForm().setVisible(true));
    }
}
