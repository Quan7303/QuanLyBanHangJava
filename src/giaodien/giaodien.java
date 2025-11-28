package giaodien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class giaodien extends JFrame {

    private JButton btnPB, btnNV, btnKH, btnLSP, btnSP, btnNCC, btnPN, btnPX, btnHD, btnTK, btnThoat;
    private JLabel lblTitle;
    private JPanel pnlMain;

    // Hàm thu nhỏ icon
    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public giaodien() {
        setTitle("MÀN HÌNH CHÍNH");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        lblTitle = new JLabel("MÀN HÌNH CHÍNH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.RED);
        add(lblTitle, BorderLayout.NORTH);

        // Panel chính
        pnlMain = new JPanel();
        pnlMain.setLayout(new GridLayout(4, 3, 50, 50));
        pnlMain.setBackground(new Color(230, 230, 250)); // tím nhạt

        // ===== TẠO CÁC NÚT =====
        btnPB = new JButton("QL Phòng Ban");
        btnPB.setBackground(new Color(186, 225, 255));
        btnPB.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnPB, new Color(186, 225, 255));

        btnNV = new JButton("QL Nhân Viên");
        btnNV.setBackground(new Color(255, 223, 186));
        btnNV.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnNV, new Color(255, 223, 186));

        btnKH = new JButton("QL Khách Hàng");
        btnKH.setBackground(new Color(255, 255, 186));
        btnKH.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnKH, new Color(255, 255, 186));

        btnLSP = new JButton("QL Loại SP");
        btnLSP.setBackground(new Color(186, 255, 201));
        btnLSP.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnLSP, new Color(186, 255, 201));

        btnSP = new JButton("QL Sản Phẩm");
        btnSP.setBackground(new Color(255, 179, 186));
        btnSP.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnSP, new Color(255, 179, 186));

        btnNCC = new JButton("QL Nhà CC");
        btnNCC.setBackground(new Color(204, 204, 255));
        btnNCC.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnNCC, new Color(204, 204, 255));

        btnPN = new JButton("QL Phiếu Nhập");
        btnPN.setBackground(new Color(255, 204, 229));
        btnPN.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnPN, new Color(255, 204, 229));

        btnPX = new JButton("QL Phiếu Xuất");
        btnPX.setBackground(new Color(204, 255, 229));
        btnPX.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnPX, new Color(204, 255, 229));

        btnHD = new JButton("QL Hóa Đơn");
        btnHD.setBackground(new Color(255, 230, 204));
        btnHD.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnHD, new Color(255, 230, 204));

        btnTK = new JButton("Thống Kê");
        btnTK.setBackground(new Color(255, 204, 204));
        btnTK.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnTK, new Color(255, 204, 204));

        btnThoat = new JButton("Thoát");
        btnThoat.setBackground(new Color(200, 200, 200));
        btnThoat.setFont(new Font("Tahoma", Font.BOLD, 16));
        addHover(btnThoat, new Color(200, 200, 200));

        // Thêm icon
        setButtonIcon(btnPB, "/giaodien/anh/ngoi_nha.png", 50, 50);
        setButtonIcon(btnNV, "/giaodien/anh/nguoi.png", 50, 50);
        setButtonIcon(btnKH, "/giaodien/anh/nguoi_mua_hang.png", 50, 50);
        setButtonIcon(btnLSP, "/giaodien/anh/hang_hoa.png", 50, 50);
        setButtonIcon(btnSP, "/giaodien/anh/xe_mua_hang.png", 50, 50);
        setButtonIcon(btnNCC, "/giaodien/anh/cong_dong.png", 50, 50);
        setButtonIcon(btnPN, "/giaodien/anh/laptop.png", 60, 60);
        setButtonIcon(btnPX, "/giaodien/anh/laptop.png", 60, 60);
        setButtonIcon(btnHD, "/giaodien/anh/may_in.png", 50, 50);
        setButtonIcon(btnTK, "/giaodien/anh/tim_kiem.png", 50, 50);
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 50, 50);

        // Thêm nút vào panel
        pnlMain.add(btnPB);
        pnlMain.add(btnNV);
        pnlMain.add(btnKH);
        pnlMain.add(btnLSP);
        pnlMain.add(btnSP);
        pnlMain.add(btnNCC);
        pnlMain.add(btnPN);
        pnlMain.add(btnPX);
        pnlMain.add(btnHD);
        pnlMain.add(btnTK);
        pnlMain.add(btnThoat);

        add(pnlMain, BorderLayout.CENTER);

        // ====== SỰ KIỆN NÚT ======
        btnThoat.addActionListener(e -> System.exit(0));
        btnPB.addActionListener(e -> { this.setVisible(false); new PhongBanForm().setVisible(true); });
        btnNV.addActionListener(e -> { this.setVisible(false); new NhanVienForm().setVisible(true); });
        btnNCC.addActionListener(e -> { this.setVisible(false); new NhaCCForm().setVisible(true); });
        btnLSP.addActionListener(e -> { this.setVisible(false); new LoaiSPForm().setVisible(true); });
        btnSP.addActionListener(e -> { this.setVisible(false); new SanPhamForm().setVisible(true); });
        btnKH.addActionListener(e -> { this.setVisible(false); new KhachHangForm().setVisible(true); });
        btnPN.addActionListener(e -> { this.setVisible(false); new PhieuNhapForm().setVisible(true); });
        btnPX.addActionListener(e -> { this.setVisible(false); new PhieuXuatForm().setVisible(true); });
        btnHD.addActionListener(e -> { this.setVisible(false); new HoaDonForm().setVisible(true); });
        btnTK.addActionListener(e -> { this.setVisible(false); new ThongKeForm().setVisible(true); });
    }

    // ===== Hàm phụ: thêm hiệu ứng hover cho từng nút =====
    private void addHover(JButton btn, Color baseColor) {
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(baseColor.darker());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(baseColor);
            }
        });
    }

    // ===== Hàm main =====
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Không thể dùng Nimbus, dùng giao diện mặc định!");
        }

        SwingUtilities.invokeLater(() -> new giaodien().setVisible(true));
    }
}