package giaodien;

import com.toedter.calendar.JDateChooser;
import ketnoi.ketnoi;
import co_so_du_lieu.Khach_Hang_1;
import QLBH.Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KhachHangForm extends JFrame {
    private JTable tblKH;
    private DefaultTableModel tbtModel;
    private JDateChooser dateChooserNgaySinh;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public KhachHangForm() {
        setTitle("QUẢN LÝ KHÁCH HÀNG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setResizable(true);
        this.setLocationRelativeTo(null);
        setBackground(new Color(255, 220, 230));
        setLayout(new BorderLayout());

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.RED);

        JPanel northPanel = new JPanel();
        northPanel.add(lblTitle);
        northPanel.setBackground(new Color(255, 253, 208));
        add(northPanel, BorderLayout.NORTH);

        // Phần hai bên
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(100, 0));
        eastPanel.setPreferredSize(new Dimension(100, 0));
        westPanel.setBackground(new Color(255, 253, 208));
        eastPanel.setBackground(new Color(255, 253, 208));
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        // --- Phần trung tâm ---
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        dateChooserNgaySinh = new JDateChooser();
        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
        JTextField textField5 = new JTextField();

        JPanel cennterPanel = new JPanel();
        cennterPanel.setLayout(new GridLayout(6, 2, 5, 40));
        cennterPanel.setBackground(new Color(248, 187, 208));

        cennterPanel.add(new JLabel("MÃ KH:", SwingConstants.CENTER));
        cennterPanel.add(textField1);
        cennterPanel.add(new JLabel("TÊN KH:", SwingConstants.CENTER));
        cennterPanel.add(textField2);
        cennterPanel.add(new JLabel("ĐỊA CHỈ:", SwingConstants.CENTER));
        cennterPanel.add(textField3);
        cennterPanel.add(new JLabel("NGÀY SINH:", SwingConstants.CENTER));
        cennterPanel.add(dateChooserNgaySinh);
        cennterPanel.add(new JLabel("SỐ ĐIỆN THOẠI:", SwingConstants.CENTER));
        cennterPanel.add(textField5);

        // Nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnSua = new JButton("Sửa");
        JButton btnThoat = new JButton("Thoát");
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Huy");

        // Màu nút
        btnThem.setBackground(new Color(76, 175, 80));
        btnSua.setBackground(new Color(33, 150, 243));
        btnXoa.setBackground(new Color(244, 67, 54));
        btnHuy.setBackground(new Color(244, 67, 54));
        btnLuu.setBackground(new Color(76, 175, 80));

        // Thêm icon
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 20, 20);
        setButtonIcon(btnXoa, "/giaodien/anh/nut_xoa.png", 20, 20);
        setButtonIcon(btnThem, "/giaodien/anh/nut_them.png", 20, 20);
        setButtonIcon(btnSua, "/giaodien/anh/nut_sua.png", 20, 20);
        setButtonIcon(btnLuu, "/giaodien/anh/nut_luu.png", 20, 20);
        setButtonIcon(btnHuy, "/giaodien/anh/nut_huy.png", 20, 20);

        // Thêm nút vào panel
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnSua);
        buttonPanel2.add(btnLuu);
        buttonPanel2.add(btnHuy);
        buttonPanel2.add(btnThoat);
        buttonPanel.setBackground(new Color(248, 187, 208));
        buttonPanel2.setBackground(new Color(248, 187, 208));

        cennterPanel.add(buttonPanel);
        cennterPanel.add(buttonPanel2);
        cennterPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(cennterPanel, BorderLayout.CENTER);

        // --- Bảng phía dưới ---
        String[] columnNames = { "Mã KH", "Tên KH", "Địa Chỉ", "Ngày Sinh", "Điện Thoại" };
        tbtModel = new DefaultTableModel(columnNames, 0);
        tblKH = new JTable(tbtModel);
        tblKH.setFillsViewportHeight(true);
        tblKH.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tblKH);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        southPanel.setPreferredSize(new Dimension(0, 150));
        add(southPanel, BorderLayout.SOUTH);

        load();

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        dateChooserNgaySinh.setEnabled(false);
        textField5.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        tblKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblKH.getSelectedRow();
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
                try {
                    dateChooserNgaySinh.setDate(val4 != null ? sdf.parse(val4.toString()) : null);
                } catch (Exception ex) {
                    dateChooserNgaySinh.setDate(null);
                }
                textField5.setText(val5 != null ? val5.toString() : "");
            }
        });

        // ====== Sự kiện ======
        btnThoat.addActionListener(e -> {
            this.setVisible(false);
            new giaodien().setVisible(true);
        });

        // Nút Thêm
        btnThem.addActionListener(e -> {
            tblKH.setEnabled(false);
            tblKH.clearSelection();
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            dateChooserNgaySinh.setDate(null);
            textField5.setText("");
            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            dateChooserNgaySinh.setEnabled(true);
            textField5.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        });

        // Nút Xóa
        btnXoa.addActionListener(e -> {
            int selectedRow = tblKH.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
                return;
            }

            String ma = tbtModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa khách hàng có mã: " + ma + " ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Khach_Hang_1 khDAO = new Khach_Hang_1();
                    boolean result = khDAO.Delete(ma);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        load();
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        dateChooserNgaySinh.setDate(null);
                        textField5.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy !");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
                }
            }
        });

        // Nút Sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblKH.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng để sửa!");
                    return;
                } else {
                    tblKH.setEnabled(false);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);
                    dateChooserNgaySinh.setEnabled(true);
                    textField5.setEnabled(true);
                    btnThem.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnLuu.setEnabled(true);
                    btnHuy.setEnabled(true);
                }
            }
        });

        // Nút Lưu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblKH.getSelectedRow();
                String ma = textField1.getText().trim();
                String ten = textField2.getText().trim();
                String diaChi = textField3.getText().trim();
                Date ns = dateChooserNgaySinh.getDate();
                String ngaySinh = (ns != null) ? sdf.format(ns) : "";
                String dienThoai = textField5.getText().trim();

                if (ma.isEmpty() || ten.isEmpty() || diaChi.isEmpty() || ngaySinh.isEmpty() || dienThoai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                KhachHang kh = new KhachHang(ma.toUpperCase(), ten, diaChi, ngaySinh, dienThoai);
                Khach_Hang_1 khDAO = new Khach_Hang_1();

                if (selectedRow == -1) {
                    try {
                        boolean kq = khDAO.insert(kh);
                        if (kq) {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                            load();
                            textField1.setEnabled(false);
                            textField2.setEnabled(false);
                            textField3.setEnabled(false);
                            dateChooserNgaySinh.setEnabled(false);
                            textField5.setEnabled(false);
                            btnLuu.setEnabled(false);
                            btnHuy.setEnabled(false);
                            btnSua.setEnabled(true);
                            btnXoa.setEnabled(true);
                            tblKH.setEnabled(true);
                            tblKH.getSelectionModel().setSelectionInterval(tblKH.getRowCount() - 1,
                                    tblKH.getRowCount() - 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                    }
                } else {
                    try {
                        boolean result = khDAO.Update(kh);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin khách hàng thành công!");
                            load();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có mã: " + ma);
                        }

                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        dateChooserNgaySinh.setEnabled(false);
                        textField5.setEnabled(false);
                        btnThem.setEnabled(true);
                        btnXoa.setEnabled(true);
                        btnLuu.setEnabled(false);
                        btnHuy.setEnabled(false);
                        tblKH.setEnabled(true);
                        tblKH.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
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
                tblKH.setEnabled(true);
                textField1.setEnabled(false);
                textField2.setEnabled(false);
                textField3.setEnabled(false);
                dateChooserNgaySinh.setEnabled(false);
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
                dateChooserNgaySinh.setDate(null);
                textField5.setText("");
                tblKH.clearSelection();
            }
        });

    }

    public void load() {
        try {
            tbtModel.setRowCount(0);
            String sql = "SELECT * FROM KhachHang";
            ketnoi kn = new ketnoi();
            Connection conn = kn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getString("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("DiaChi"),
                        rs.getString("NgaySinh"),
                        rs.getString("DienThoai")
                };
                tbtModel.addRow(row);
            }

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
        SwingUtilities.invokeLater(() -> new KhachHangForm().setVisible(true));
    }
}