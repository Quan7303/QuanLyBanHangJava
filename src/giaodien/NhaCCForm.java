package giaodien;

import QLBH.Model.NhaCungCap;
import co_so_du_lieu.NhaCC_1;
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

public class NhaCCForm extends JFrame {

    private JTable tblNCC;
    private DefaultTableModel tbtModel;

    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public NhaCCForm() {
        setTitle("QUẢN LÝ NHÀ CUNG CẤP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP", SwingConstants.CENTER);
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
        JTextField textField5 = new JTextField();

        JPanel cennterPanel = new JPanel();
        cennterPanel.setLayout(new GridLayout(6, 2, 5, 30));
        cennterPanel.setBackground(new Color(248, 187, 208));

        cennterPanel.add(new JLabel("MÃ NCC:", SwingConstants.CENTER));
        cennterPanel.add(textField1);
        cennterPanel.add(new JLabel("TÊN NCC:", SwingConstants.CENTER));
        cennterPanel.add(textField2);
        cennterPanel.add(new JLabel("ĐỊA CHỈ:", SwingConstants.CENTER));
        cennterPanel.add(textField3);
        cennterPanel.add(new JLabel("SỐ ĐIỆN THOẠI:", SwingConstants.CENTER));
        cennterPanel.add(textField4);
        cennterPanel.add(new JLabel("EMAIL:", SwingConstants.CENTER));
        cennterPanel.add(textField5);

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

        // --- Phần bảng (SOUTH) ---
        String[] columnNames = { "Mã NCC", "Tên NCC", "Email", "Địa chỉ", "Điện thoại" };
        tbtModel = new DefaultTableModel(columnNames, 0);
        tblNCC = new JTable(tbtModel);
        tblNCC.setFillsViewportHeight(true);
        tblNCC.setRowHeight(25);

        // Cho bảng cuộn
        JScrollPane scrollPane = new JScrollPane(tblNCC);

        // Panel phía Nam
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
        southPanel.setPreferredSize(new Dimension(0, 200));

        // Gắn panel phía Nam vào giao diện
        add(southPanel, BorderLayout.SOUTH);

        // Gọi hàm load dữ liệu từ SQL
        load();

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        textField4.setEnabled(false);
        textField5.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        tblNCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblNCC.getSelectedRow();
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
                textField4.setText(val4 != null ? val4.toString() : "");
                textField5.setText(val5 != null ? val5.toString() : "");
            }
        });

        // su kien cac nut
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });

        btnThem.addActionListener(e -> {
            tblNCC.setEnabled(false);
            tblNCC.clearSelection();
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            textField4.setEnabled(true);
            textField5.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        });
        // nut xoa
        btnXoa.addActionListener(e -> {
            int selectedRow = tblNCC.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
                return;
            }

            String ma = tbtModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa nhà cung cấp có mã: " + ma + " ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    NhaCC_1 nccDAO = new NhaCC_1();
                    boolean result = nccDAO.Delete(ma);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        load();
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
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
                if (tblNCC.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng để sửa!");
                    return;
                } else {
                    tblNCC.setEnabled(false);
                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);
                    textField4.setEnabled(true);
                    textField5.setEnabled(true);
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
                int selectedRow = tblNCC.getSelectedRow();
                String ma = textField1.getText().trim();
                String ten = textField2.getText().trim();
                String email = textField3.getText().trim();
                String diachi = textField4.getText().trim();
                String dienthoai = textField5.getText().trim();

                if (ma.isEmpty() || ten.isEmpty() || diachi.isEmpty() || dienthoai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                NhaCungCap ncc = new NhaCungCap(ma.toUpperCase(), ten, email, diachi, dienthoai);
                NhaCC_1 nccDAO = new NhaCC_1();

                if (selectedRow == -1) {
                    try {
                        boolean kq = nccDAO.insert(ncc);
                        if (kq) {
                            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
                            load();
                            textField1.setEnabled(false);
                            textField2.setEnabled(false);
                            textField3.setEnabled(false);
                            textField4.setEnabled(false);
                            textField5.setEnabled(false);
                            btnLuu.setEnabled(false);
                            btnHuy.setEnabled(false);
                            btnSua.setEnabled(true);
                            btnXoa.setEnabled(true);
                            tblNCC.setEnabled(true);
                            tblNCC.getSelectionModel().setSelectionInterval(tblNCC.getRowCount() - 1,
                                    tblNCC.getRowCount() - 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                    }
                } else {
                    try {
                        boolean result = nccDAO.Update(ncc);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhà cung cấp thành công!");
                            load();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp có mã: " + ma);
                        }

                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        textField4.setEnabled(false);
                        textField5.setEnabled(false);
                        btnThem.setEnabled(true);
                        btnXoa.setEnabled(true);
                        btnLuu.setEnabled(false);
                        btnHuy.setEnabled(false);
                        tblNCC.setEnabled(true);
                        tblNCC.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin nhà cung cấp: " + ex.getMessage(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblNCC.setEnabled(true);
                textField1.setEnabled(false);
                textField2.setEnabled(false);
                textField3.setEnabled(false);
                textField4.setEnabled(false);
                textField5.setEnabled(false);
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
                textField5.setText("");
                tblNCC.clearSelection();
            }
        });
    }

    public void load() {
        try {
            // Xoá dữ liệu cũ trong bảng
            tbtModel.setRowCount(0);

            // Câu truy vấn
            String sql = "SELECT * FROM NhaCungCap";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Duyệt kết quả và thêm vào bảng
            while (rs.next()) {
                Object[] row = {
                        rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("Email"),
                        rs.getString("DiaChi"),
                        rs.getString("DienThoai")
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
        SwingUtilities.invokeLater(() -> new NhaCCForm().setVisible(true));
    }
}
