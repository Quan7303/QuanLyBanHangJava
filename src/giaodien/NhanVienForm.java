package giaodien;

import com.toedter.calendar.JDateChooser;
import QLBH.Model.NhanVien;
import co_so_du_lieu.Nhan_Vien_1;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ketnoi.ketnoi;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVienForm extends JFrame {

    private JTable tblNV;
    private DefaultTableModel tbtModel;
    private JDateChooser dateChooserNgaySinh;
    private JDateChooser ngayVaoLam;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    private void Combobox1(JComboBox<String> cboxMaPB) {
        try {
            String sql = "Select MaPB from PhongBan";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            cboxMaPB.removeAllItems();
            while (rs.next()) {
                cboxMaPB.addItem(rs.getString("MaPB"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public NhanVienForm() {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);

        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // --- Cột trái (WEST) ---
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField9 = new JTextField();
        JTextField textField4 = new JTextField();
        dateChooserNgaySinh = new JDateChooser();
        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
        JComboBox<String> gioitinh = new JComboBox<>();

        gioitinh.addItem("Nam");
        gioitinh.addItem("Nữ");

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(6, 2, 0, 40));
        westPanel.setBackground(new Color(248, 187, 208)); // hồng nhạt

        westPanel.add(new JLabel("MÃ NV:", SwingConstants.CENTER));
        westPanel.add(textField1);
        westPanel.add(new JLabel("HỌ:", SwingConstants.CENTER));
        westPanel.add(textField2);
        westPanel.add(new JLabel("Tên:", SwingConstants.CENTER));
        westPanel.add(textField9);
        westPanel.add(new JLabel("GIỚI TÍNH:", SwingConstants.CENTER));
        westPanel.add(gioitinh);
        westPanel.add(new JLabel("NGÀY SINH:", SwingConstants.CENTER));
        westPanel.add(dateChooserNgaySinh);
        westPanel.add(new JLabel("ĐỊA CHỈ:", SwingConstants.CENTER));
        westPanel.add(textField4);

        westPanel.setPreferredSize(new Dimension(400, 0));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(westPanel, BorderLayout.WEST);

        // --- Phần phải (EAST) ---
        JTextField textField5 = new JTextField();
        JTextField textField6 = new JTextField();
        JTextField textField8 = new JTextField();
        ngayVaoLam = new JDateChooser();
        ngayVaoLam.setDateFormatString("yyyy-MM-dd");
        JComboBox<String> MaPB = new JComboBox<>();

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(6, 2, 0, 40));
        eastPanel.setBackground(new Color(248, 187, 208));

        eastPanel.add(new JLabel("ĐIỆN THOẠI:", SwingConstants.CENTER));
        eastPanel.add(textField5);
        eastPanel.add(new JLabel("NƠI SINH:", SwingConstants.CENTER));
        eastPanel.add(textField6);
        eastPanel.add(new JLabel("NGÀY VÀO LÀM:", SwingConstants.CENTER));
        eastPanel.add(ngayVaoLam);
        eastPanel.add(new JLabel("EMAIL:", SwingConstants.CENTER));
        eastPanel.add(textField8);
        eastPanel.add(new JLabel("MÃ PB:", SwingConstants.CENTER));
        eastPanel.add(MaPB);

        // nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonPanel2 = new JPanel(new GridLayout(2, 2));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnSua = new JButton("Sửa");
        JButton btnThoat = new JButton("Thoát");
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Huy");

        // chinh mau cho cac nut
        btnThem.setBackground(new Color(76, 175, 80)); // Xanh lá
        btnSua.setBackground(new Color(33, 150, 243)); // Xanh dương
        btnXoa.setBackground(new Color(244, 67, 54)); // Đỏ
        btnHuy.setBackground(new Color(244, 67, 54)); // Đỏ
        btnLuu.setBackground(new Color(76, 175, 80)); // Xanh lá

        // Them nut vao Panel va chinh mau nen
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnSua);
        buttonPanel2.add(btnLuu);
        buttonPanel2.add(btnHuy);
        buttonPanel2.add(btnThoat);
        buttonPanel.setBackground(new Color(255, 220, 230));
        buttonPanel2.setBackground(new Color(255, 220, 230));

        // them icon cho cac nut
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 20, 20);
        setButtonIcon(btnXoa, "/giaodien/anh/nut_xoa.png", 20, 20);
        setButtonIcon(btnThem, "/giaodien/anh/nut_them.png", 20, 20);
        setButtonIcon(btnSua, "/giaodien/anh/nut_sua.png", 20, 20);
        setButtonIcon(btnLuu, "/giaodien/anh/nut_luu.png", 20, 20);
        setButtonIcon(btnHuy, "/giaodien/anh/nut_huy.png", 20, 20);

        eastPanel.add(buttonPanel);
        eastPanel.add(buttonPanel2);

        eastPanel.setPreferredSize(new Dimension(400, 0));
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(eastPanel, BorderLayout.EAST);
        Combobox1(MaPB);

        // center
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 253, 208)); // vàng kem nhạt
        add(centerPanel, BorderLayout.CENTER);

        // --- Phần bảng (SOUTH) ---
        String[] columnNames = {"Mã NV", "Họ NV", "Tên NV", "Giới Tính",
            "Ngày Sinh", "Địa Chỉ", "Điện Thoại", "Nơi Sinh", "Ngày Vào Làm", "Email", "Mã PB"};
        tbtModel = new DefaultTableModel(columnNames, 0);
        tblNV = new JTable(tbtModel);
        tblNV.setFillsViewportHeight(true);
        tblNV.setRowHeight(25);

        // Cho bảng cuộn
        JScrollPane scrollPane = new JScrollPane(tblNV);

        // Panel phía Nam
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        southPanel.setPreferredSize(new Dimension(0, 130));

        // Gắn panel phía Nam vào giao diện
        add(southPanel, BorderLayout.SOUTH);

        // Gọi hàm load dữ liệu từ SQL
        load();

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField9.setEnabled(false);
        gioitinh.setEnabled(false);
        dateChooserNgaySinh.setEnabled(false);
        textField4.setEnabled(false);
        textField5.setEnabled(false);
        textField6.setEnabled(false);
        ngayVaoLam.setEnabled(false);
        textField8.setEnabled(false);
        MaPB.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        tblNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblNV.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                }
                Object val1 = tbtModel.getValueAt(selectedRow, 0);
                Object val2 = tbtModel.getValueAt(selectedRow, 1);
                Object val3 = tbtModel.getValueAt(selectedRow, 2);
                Object val4 = tbtModel.getValueAt(selectedRow, 4);
                Object val5 = tbtModel.getValueAt(selectedRow, 5);
                Object val6 = tbtModel.getValueAt(selectedRow, 6);
                Object val7 = tbtModel.getValueAt(selectedRow, 7);
                Object val8 = tbtModel.getValueAt(selectedRow, 8);
                Object val9 = tbtModel.getValueAt(selectedRow, 9);
                Object val10 = tbtModel.getValueAt(selectedRow, 3);
                Object val11 = tbtModel.getValueAt(selectedRow, 10);

                textField1.setText(val1 != null ? val1.toString() : "");
                textField2.setText(val2 != null ? val2.toString() : "");
                textField9.setText(val3 != null ? val3.toString() : "");
                gioitinh.setSelectedItem(val10 != null ? val10.toString() : "");
                try {
                    dateChooserNgaySinh.setDate(val4 != null ? sdf.parse(val4.toString()) : null);
                } catch (Exception ex) {
                    dateChooserNgaySinh.setDate(null);
                }
                textField4.setText(val5 != null ? val5.toString() : "");
                textField5.setText(val6 != null ? val6.toString() : "");
                textField6.setText(val7 != null ? val7.toString() : "");
                try {
                    ngayVaoLam.setDate(val8 != null ? sdf.parse(val8.toString()) : null);
                } catch (Exception ex) {
                    ngayVaoLam.setDate(null);
                }
                textField8.setText(val9 != null ? val9.toString() : "");
                MaPB.setSelectedItem(val11 != null ? val11.toString() : "");
            }
        });

        // su kien cac nut
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });

        btnThem.addActionListener(e -> {
            tblNV.setEnabled(false);
            tblNV.clearSelection();
            textField1.setText("");
            textField2.setText("");
            textField9.setText("");
            textField4.setText("");
            textField5.setText("");
            textField6.setText("");
            textField8.setText("");
            dateChooserNgaySinh.setDate(null);
            ngayVaoLam.setDate(null);
            gioitinh.setSelectedIndex(0);
            MaPB.setSelectedIndex(-1);
            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField9.setEnabled(true);
            gioitinh.setEnabled(true);
            dateChooserNgaySinh.setEnabled(true);
            textField4.setEnabled(true);
            textField5.setEnabled(true);
            textField6.setEnabled(true);
            ngayVaoLam.setEnabled(true);
            textField8.setEnabled(true);
            MaPB.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        });
        // nut xoa
        btnXoa.addActionListener(e -> {
            int selectedRow = tblNV.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
                return;
            }

            String ma = tbtModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa nhân viên có mã: " + ma + " ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Nhan_Vien_1 pbDAO = new Nhan_Vien_1();
                    boolean result = pbDAO.Delete(ma);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        load();
                        textField1.setText("");
                        textField2.setText("");
                        textField9.setText("");
                        textField4.setText("");
                        textField5.setText("");
                        textField6.setText("");
                        textField8.setText("");
                        dateChooserNgaySinh.setDate(null);
                        ngayVaoLam.setDate(null);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy !");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
                }
            }
        });
        // nut sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblNV.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng để sửa!");
                    return;
                } else {
                    tblNV.setEnabled(false);
                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField9.setEnabled(true);
                    gioitinh.setEnabled(true);
                    dateChooserNgaySinh.setEnabled(true);
                    textField4.setEnabled(true);
                    textField5.setEnabled(true);
                    textField6.setEnabled(true);
                    ngayVaoLam.setEnabled(true);
                    textField8.setEnabled(true);
                    MaPB.setEnabled(true);
                    btnThem.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnLuu.setEnabled(true);
                    btnHuy.setEnabled(true);
                }
            }
        });

        // nut luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblNV.getSelectedRow();
                String ma = textField1.getText().trim();
                String ho = textField2.getText().trim();
                String ten = textField9.getText().trim();
                String gt = gioitinh.getSelectedItem() != null ? gioitinh.getSelectedItem().toString() : "";
                Date ns = dateChooserNgaySinh.getDate();
                String ngaysinh = (ns != null) ? sdf.format(ns) : "";
                String diachi = textField4.getText().trim();
                String dienthoai = textField5.getText().trim();
                String noisinh = textField6.getText().trim();
                Date nvl = ngayVaoLam.getDate();
                String ngayvaolam = (nvl != null) ? sdf.format(nvl) : "";
                String email = textField8.getText().trim();
                String maPB = MaPB.getSelectedItem() != null ? MaPB.getSelectedItem().toString() : "";

                if (ma.isEmpty() || ten.isEmpty() || gt.isEmpty() || ngaysinh.isEmpty() || diachi.isEmpty()
                        || dienthoai.isEmpty() || noisinh.isEmpty() || ngayvaolam.isEmpty() || maPB.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                NhanVien nv = new NhanVien(ma.toUpperCase(), ho, ten, gt, ngaysinh, diachi, dienthoai, noisinh,
                        ngayvaolam, email, maPB);
                Nhan_Vien_1 nvDAO = new Nhan_Vien_1();

                if (selectedRow == -1) {
                    try {
                        boolean kq = nvDAO.insert(nv);
                        if (kq) {
                            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                            load();
                            textField1.setEnabled(false);
                            textField2.setEnabled(false);
                            textField9.setEnabled(false);
                            gioitinh.setEnabled(false);
                            dateChooserNgaySinh.setEnabled(false);
                            textField4.setEnabled(false);
                            textField5.setEnabled(false);
                            textField6.setEnabled(false);
                            ngayVaoLam.setEnabled(false);
                            textField8.setEnabled(false);
                            MaPB.setEnabled(false);
                            btnLuu.setEnabled(false);
                            btnHuy.setEnabled(false);
                            btnSua.setEnabled(true);
                            btnXoa.setEnabled(true);
                            tblNV.setEnabled(true);
                            tblNV.getSelectionModel().setSelectionInterval(tblNV.getRowCount() - 1,
                                    tblNV.getRowCount() - 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                    }
                } else {
                    try {
                        boolean result = nvDAO.Update(nv);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
                            load();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có mã: " + ma);
                        }

                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField9.setEnabled(false);
                        gioitinh.setEnabled(false);
                        dateChooserNgaySinh.setEnabled(false);
                        textField4.setEnabled(false);
                        textField5.setEnabled(false);
                        textField6.setEnabled(false);
                        ngayVaoLam.setEnabled(false);
                        textField8.setEnabled(false);
                        MaPB.setEnabled(false);
                        btnThem.setEnabled(true);
                        btnXoa.setEnabled(true);
                        btnLuu.setEnabled(false);
                        btnHuy.setEnabled(false);
                        tblNV.setEnabled(true);
                        tblNV.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin: " + ex.getMessage(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblNV.setEnabled(true);
                textField1.setEnabled(false);
                textField2.setEnabled(false);
                textField9.setEnabled(false);
                gioitinh.setEnabled(false);
                dateChooserNgaySinh.setEnabled(false);
                textField4.setEnabled(false);
                textField5.setEnabled(false);
                textField6.setEnabled(false);
                ngayVaoLam.setEnabled(false);
                textField8.setEnabled(false);
                MaPB.setEnabled(false);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                btnLuu.setEnabled(false);
                btnHuy.setEnabled(false);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField9.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField8.setText("");
                dateChooserNgaySinh.setDate(null);
                ngayVaoLam.setDate(null);
                tblNV.clearSelection();
            }
        });
    }

    public void load() {
        try {
            // Xoá dữ liệu cũ trong bảng
            tbtModel.setRowCount(0);

            // Câu truy vấn
            String sql = "SELECT * FROM NhanVien";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Duyệt kết quả và thêm vào bảng
            while (rs.next()) {
                Object[] row = {
                    rs.getString("MaNV"),
                    rs.getString("HoNV"),
                    rs.getString("TenNV"),
                    rs.getString("GioiTinh"),
                    rs.getString("NgaySinh"),
                    rs.getString("DiaChi"),
                    rs.getString("DienThoai"),
                    rs.getString("NoiSinh"),
                    rs.getString("NgayVaoLam"),
                    rs.getString("Email"),
                    rs.getString("MaPB")
                };
                tbtModel.addRow(row);
            }

            // Cập nhật lại bảng
            tbtModel.fireTableDataChanged();
            ketnoi.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ SQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Không thể dùng Nimbus, dùng giao diện mặc định!");
        }
        SwingUtilities.invokeLater(() -> new NhanVienForm().setVisible(true));
    }
}
