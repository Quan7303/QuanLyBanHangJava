package giaodien;

import QLBH.Model.SanPham;
import co_so_du_lieu.San_Pham_1;
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

public class SanPhamForm extends JFrame {

    private JTable tblSP;
    private DefaultTableModel tbtModel;
    JComboBox<String> MaloaiSP = new JComboBox<>();

    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    private void Combobox1(JComboBox<String> cboxMaloaiSP) {
        try {
            String sql = "Select MaloaiSP from LoaiSanPham";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            cboxMaloaiSP.removeAllItems();
            while (rs.next()) {
                cboxMaloaiSP.addItem(rs.getString("MaloaiSP"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public SanPhamForm() {

        setTitle("QUẢN LÝ SẢN PHẨM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);

        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // Phan dong va tay
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();

        westPanel.setPreferredSize(new Dimension(100, 0));
        eastPanel.setPreferredSize(new Dimension(100, 0));

        westPanel.setBackground(new Color(255, 253, 208));
        eastPanel.setBackground(new Color(255, 253, 208));

        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        // --- Phần phải (EAST) ---
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();

        JPanel cennterPanel = new JPanel();
        cennterPanel.setLayout(new GridLayout(6, 2, 5, 30));
        cennterPanel.setBackground(new Color(248, 187, 208));

        cennterPanel.add(new JLabel("MÃ SP:", SwingConstants.CENTER));
        cennterPanel.add(textField1);
        cennterPanel.add(new JLabel("TÊN SP:", SwingConstants.CENTER));
        cennterPanel.add(textField2);
        cennterPanel.add(new JLabel("ĐƠN VỊ TÍNH:", SwingConstants.CENTER));
        cennterPanel.add(textField3);
        cennterPanel.add(new JLabel("MÃ LOẠI SP", SwingConstants.CENTER));
        cennterPanel.add(MaloaiSP);
        cennterPanel.add(new JLabel("SỐ LƯỢNG TỒN:", SwingConstants.CENTER));
        cennterPanel.add(textField4);

        // nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
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
        btnHuy.setBackground(new Color(244, 67, 54));
        btnLuu.setBackground(new Color(76, 175, 80));

        // Them nut vao Panel va chinh mau nen
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnSua);
        buttonPanel2.add(btnLuu);
        buttonPanel2.add(btnHuy);
        buttonPanel2.add(btnThoat);
        buttonPanel.setBackground(new Color(248, 187, 208));
        buttonPanel2.setBackground(new Color(248, 187, 208));

        // them icon cho cac nut
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 20, 20);
        setButtonIcon(btnXoa, "/giaodien/anh/nut_xoa.png", 20, 20);
        setButtonIcon(btnThem, "/giaodien/anh/nut_them.png", 20, 20);
        setButtonIcon(btnSua, "/giaodien/anh/nut_sua.png", 20, 20);
        setButtonIcon(btnLuu, "/giaodien/anh/nut_luu.png", 20, 20);
        setButtonIcon(btnHuy, "/giaodien/anh/nut_huy.png", 20, 20);

        cennterPanel.add(buttonPanel);
        cennterPanel.add(buttonPanel2);

        cennterPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(cennterPanel, BorderLayout.CENTER);
        Combobox1(MaloaiSP);

        // --- Phần bảng (SOUTH) ---
        String[] columnNames = { "Mã SP", "Tên loại SP", "Đơn vị tính", "Mã loại SP", "Số lượng tồn" };
        tbtModel = new DefaultTableModel(columnNames, 0);
        tblSP = new JTable(tbtModel);
        tblSP.setFillsViewportHeight(true);
        tblSP.setRowHeight(25);

        // Cho bảng cuộn
        JScrollPane scrollPane = new JScrollPane(tblSP);

        // Panel phía Nam
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách loại sản phẩm"));
        southPanel.setPreferredSize(new Dimension(0, 200));

        // Gắn panel phía Nam vào giao diện
        add(southPanel, BorderLayout.SOUTH);

        // Gọi hàm load dữ liệu từ SQL
        load();

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        textField4.setEnabled(false);
        MaloaiSP.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        tblSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblSP.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                }
                Object val1 = tbtModel.getValueAt(selectedRow, 0);
                Object val2 = tbtModel.getValueAt(selectedRow, 1);
                Object val3 = tbtModel.getValueAt(selectedRow, 2);
                Object val4 = tbtModel.getValueAt(selectedRow, 3);
                Object val5 = tbtModel.getValueAt(selectedRow, 4);

                textField1.setText(val1 != null ? val1.toString() : "");
                textField2.setText(val2 != null ? val2.toString() : "");
                textField3.setText(val3 != null ? val3.toString() : "");
                textField4.setText(val5 != null ? val5.toString() : "");
                if (val4 != null) {
                    MaloaiSP.setSelectedItem(val4.toString());
                }
            }
        });

        // su kien cac nut
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });

        btnThem.addActionListener(e -> {
            tblSP.setEnabled(false);
            tblSP.clearSelection();
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            MaloaiSP.setSelectedIndex(-1);
            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            textField4.setEnabled(true);
            MaloaiSP.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        });

        btnXoa.addActionListener(e -> {
            int selectedRow = tblSP.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
                return;
            }

            String ma = tbtModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa sản phẩm có mã: " + ma + " ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    San_Pham_1 spDAO = new San_Pham_1();
                    boolean result = spDAO.Delete(ma);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        load();
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblSP.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng để sửa!");
                    return;
                } else {
                    tblSP.setEnabled(false);
                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);
                    textField4.setEnabled(true);
                    MaloaiSP.setEnabled(true);
                    btnThem.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnLuu.setEnabled(true);
                    btnHuy.setEnabled(true);
                }
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblSP.getSelectedRow();
                String ma = textField1.getText().trim();
                String ten = textField2.getText().trim();
                String donvitinh = textField3.getText().trim();
                Object selectedMaloai = MaloaiSP.getSelectedItem();
                String slton = textField4.getText().trim();

                if (selectedMaloai == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Mã loại sản phẩm!");
                    return;
                }

                String maloai = selectedMaloai.toString();

                if (ma.isEmpty() || ten.isEmpty() || donvitinh.isEmpty() || slton.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                int sltonn;
                try {
                    sltonn = Integer.parseInt(slton);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số nguyên hợp lệ!");
                    return;
                }

                SanPham sp = new SanPham(ma.toUpperCase(), ten, donvitinh, maloai, sltonn);
                San_Pham_1 spDAO = new San_Pham_1();

                if (selectedRow == -1) {
                    try {
                        boolean kq = spDAO.insert(sp);
                        if (kq) {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                            load();
                            textField1.setEnabled(false);
                            textField2.setEnabled(false);
                            textField3.setEnabled(false);
                            textField4.setEnabled(false);
                            MaloaiSP.setEnabled(false);
                            btnLuu.setEnabled(false);
                            btnHuy.setEnabled(false);
                            btnSua.setEnabled(true);
                            btnXoa.setEnabled(true);
                            tblSP.setEnabled(true);
                            tblSP.getSelectionModel().setSelectionInterval(tblSP.getRowCount() - 1,
                                    tblSP.getRowCount() - 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                    }
                } else {
                    try {
                        boolean result = spDAO.Update(sp);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin sản phẩm thành công!");
                            load();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có mã: " + ma);
                        }

                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        textField4.setEnabled(false);
                        MaloaiSP.setEnabled(false);
                        btnThem.setEnabled(true);
                        btnXoa.setEnabled(true);
                        btnLuu.setEnabled(false);
                        btnHuy.setEnabled(false);
                        tblSP.setEnabled(true);
                        tblSP.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin sản phẩm: " + ex.getMessage(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblSP.setEnabled(true);
                textField1.setEnabled(false);
                textField2.setEnabled(false);
                textField3.setEnabled(false);
                textField4.setEnabled(false);
                MaloaiSP.setEnabled(false);
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
                textField3.setText("");
                textField4.setText("");
                tblSP.clearSelection();
            }
        });
    }

    public void load() {
        try {
            // Xoá dữ liệu cũ trong bảng
            tbtModel.setRowCount(0);

            // Câu truy vấn
            String sql = "SELECT * FROM SanPham";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Duyệt kết quả và thêm vào bảng
            while (rs.next()) {
                Object[] row = {
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getString("DonViTinh"),
                        rs.getString("MaloaiSP"),
                        rs.getString("SLTon")
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
        SwingUtilities.invokeLater(() -> new SanPhamForm().setVisible(true));
    }
}
