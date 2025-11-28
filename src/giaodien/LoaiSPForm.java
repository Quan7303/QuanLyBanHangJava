package giaodien;

import QLBH.Model.LoaiSP;
import co_so_du_lieu.Loai_San_Pham_1;
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

public class LoaiSPForm extends JFrame {

    private JTable tblLSP;
    private DefaultTableModel tbtModel;

    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public LoaiSPForm() {
        setTitle("QUẢN LÝ CÁC LOẠI SẢN PHẨM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ CÁC LOẠI SẢN PHẨM", SwingConstants.CENTER);
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

        JPanel cennterPanel = new JPanel();
        cennterPanel.setLayout(new GridLayout(4, 2, 5, 50));
        cennterPanel.setBackground(new Color(248, 187, 208));

        cennterPanel.add(new JLabel("MÃ LSP:", SwingConstants.CENTER));
        cennterPanel.add(textField1);
        cennterPanel.add(new JLabel("TÊN LSP:", SwingConstants.CENTER));
        cennterPanel.add(textField2);
        cennterPanel.add(new JLabel("GHI CHÚ:", SwingConstants.CENTER));
        cennterPanel.add(textField3);

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
        btnHuy.setBackground(new Color(244, 67, 54)); // Đỏ
        btnLuu.setBackground(new Color(76, 175, 80)); // Xanh lá
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
        String[] columnNames = {"Mã loại SP", "Tên loại SP", "Ghi chú"};
        tbtModel = new DefaultTableModel(columnNames, 0);
        tblLSP = new JTable(tbtModel);
        tblLSP.setFillsViewportHeight(true);
        tblLSP.setRowHeight(25);

        // Cho bảng cuộn
        JScrollPane scrollPane = new JScrollPane(tblLSP);

        // Panel phía Nam
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách loại sản phẩm"));
        southPanel.setPreferredSize(new Dimension(0, 200));

        // Gắn panel phía Nam vào giao diện
        add(southPanel, BorderLayout.SOUTH);

        // Gọi hàm load dữ liệu từ SQL
        load();

        // su kien cac nut
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
        tblLSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblLSP.getSelectedRow();
                Object val1 = tbtModel.getValueAt(selectedRow, 0);
                Object val2 = tbtModel.getValueAt(selectedRow, 1);
                Object val3 = tbtModel.getValueAt(selectedRow, 2);

                textField1.setText(val1 != null ? val1.toString() : "");
                textField2.setText(val2 != null ? val2.toString() : "");
                textField3.setText(val3 != null ? val3.toString() : "");
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblLSP.setEnabled(false);
                tblLSP.clearSelection();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField1.setEnabled(true);
                textField2.setEnabled(true);
                textField3.setEnabled(true);
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                btnLuu.setEnabled(true);
                btnHuy.setEnabled(true);
            }

        });

        // nut xoa
        btnXoa.addActionListener(e -> {
            int selectedRow = tblLSP.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
                return;
            }

            String ma = tbtModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa loại sản phẩm có mã: " + ma + " ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Loai_San_Pham_1 lspDAO = new Loai_San_Pham_1();
                    boolean result = lspDAO.Delete(ma);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        load();
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy!");
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
                if (tblLSP.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng để sửa!");
                    return;
                } else {
                    tblLSP.setEnabled(false);
                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);
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
                int selectedRow = tblLSP.getSelectedRow();
                String ma = textField1.getText().trim();
                String ten = textField2.getText().trim();
                String ghichu = textField3.getText().trim();

                // 2️⃣ Kiểm tra dữ liệu rỗng
                if (ma.isEmpty() || ten.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // 3️⃣ Tạo đối tượng khách hàng
                LoaiSP sp = new LoaiSP(ma.toUpperCase(), ten, ghichu);
                Loai_San_Pham_1 spDAO = new Loai_San_Pham_1();
                if (selectedRow == -1) {
                    try {
                        // Loai_San_Pham_1 spDAO = new Loai_San_Pham_1();
                        boolean kq = spDAO.insert(sp);

                        // 5️⃣ Thông báo kết quả
                        if (kq) {
                            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công!");
                            load();
                            // textField1.setText("");
                            // textField2.setText("");
                            // textField3.setText("");
                            textField1.setEnabled(false);
                            textField2.setEnabled(false);
                            textField3.setEnabled(false);
                            btnLuu.setEnabled(false);
                            btnHuy.setEnabled(false);
                            btnSua.setEnabled(true);
                            btnXoa.setEnabled(true);
                            tblLSP.setEnabled(true);
                            //tblLSP.getSelectionModel().setSelectionInterval(tblLSP.getRowCount() - 1,tblLSP.getRowCount() - 1);

                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thất bại!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                    }
                } else {
                    try {
                        boolean result = spDAO.Update(sp);

                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin loại sản phẩm thành công!");
                            load();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy loại sản phẩm có mã: " + ma);
                        }

                        // textField1.setText("");
                        // textField2.setText("");
                        // textField3.setText("");
                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        btnThem.setEnabled(true);
                        btnXoa.setEnabled(true);
                        btnLuu.setEnabled(false);
                        btnHuy.setEnabled(false);
                        tblLSP.setEnabled(true);
                        tblLSP.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin : " + ex.getMessage(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        });

        btnHuy.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblLSP.setEnabled(true);
                // tblLSP.clearSelection();
                // textField1.setText("");
                // textField2.setText("");
                // textField3.setText("");
                textField1.setEnabled(false);
                textField2.setEnabled(false);
                textField3.setEnabled(false);
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
                tblLSP.clearSelection();
            }
        });
    }

    public void load() {
        try {
            // Xoá dữ liệu cũ trong bảng
            tbtModel.setRowCount(0);

            // Câu truy vấn
            String sql = "SELECT * FROM LoaiSanPham";

            // Kết nối SQL
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Duyệt kết quả và thêm vào bảng
            while (rs.next()) {
                Object[] row = {
                    rs.getString("MaloaiSP"),
                    rs.getString("TenloaiSP"),
                    rs.getString("GhiChu"),};
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
        SwingUtilities.invokeLater(() -> new LoaiSPForm().setVisible(true));
    }
}
