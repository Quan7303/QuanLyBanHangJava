package giaodien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import ketnoi.ketnoi;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKeForm extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboLoaiTK;
    private JButton btnThongKe, btnThoat;
    private JPanel chartPanelContainer;
    private static ketnoi ketnoi = new ketnoi();
    
    private void setButtonIcon(JButton button, String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
    }

    public ThongKeForm() {
        setTitle("THỐNG KÊ DOANH THU");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("THỐNG KÊ BÁN HÀNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng thống kê
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel dưới
        JPanel southPanel = new JPanel();
        comboLoaiTK = new JComboBox<>(new String[]{
                "Doanh thu theo ngày",
                "Top sản phẩm bán chạy",
                "Doanh thu theo tháng",
                "Doanh thu theo năm",
                "Số lượng hóa đơn theo ngày"
        });

        btnThongKe = new JButton("Thống kê");
        btnThoat = new JButton("Thoát");
        btnThongKe.setBackground(new Color(76,175,80));
        setButtonIcon(btnThoat, "/giaodien/anh/nut_thoat.png", 20, 20);
        setButtonIcon(btnThongKe, "/giaodien/anh/tim_kiem.png",20, 20);

        southPanel.add(comboLoaiTK);
        southPanel.add(btnThongKe);
        southPanel.add(btnThoat);
        add(southPanel, BorderLayout.SOUTH);

        // Panel biểu đồ
        chartPanelContainer = new JPanel(new BorderLayout());
        chartPanelContainer.setPreferredSize(new Dimension(500, 400));
        add(chartPanelContainer, BorderLayout.EAST);

        // Sự kiện
        btnThongKe.addActionListener(e -> {
            loadThongKe();
            showChart();
        });

        btnThoat.addActionListener(e -> {this.setVisible(false); new giaodien().setVisible(true);});
    }


    // ======================= LOAD THỐNG KÊ VÀO TABLE ==========================
    private void loadThongKe() {
        String loai = comboLoaiTK.getSelectedItem().toString();
        String sql = "";

        switch (loai) {

            case "Doanh thu theo ngày":
                sql = """
                      SELECT NgayDH, SUM(SoLuongDat * DGBan) AS TongTien
                      FROM hoadon h JOIN cthoadon c ON h.MaHD = c.MaHD
                      GROUP BY NgayDH ORDER BY NgayDH
                      """;
                tableModel.setColumnIdentifiers(new String[]{"Ngày", "Tổng tiền"});
                break;

            case "Top sản phẩm bán chạy":
                sql = """
                      SELECT TOP 5 MaSP, SUM(SoLuongDat) AS TongBan
                      FROM cthoadon
                      GROUP BY MaSP ORDER BY TongBan DESC 
                      """;
                tableModel.setColumnIdentifiers(new String[]{"Mã SP", "Số lượng bán"});
                break;

            case "Doanh thu theo tháng":
                sql = """
                      SELECT MONTH(NgayDH) AS Thang, SUM(SoLuongDat * DGBan) AS TongTien
                      FROM hoadon h JOIN cthoadon c ON h.MaHD = c.MaHD
                      GROUP BY MONTH(NgayDH)
                      ORDER BY Thang
                      """;
                tableModel.setColumnIdentifiers(new String[]{"Tháng", "Tổng tiền"});
                break;

            case "Doanh thu theo năm":
                sql = """
                      SELECT YEAR(NgayDH) AS Nam, SUM(SoLuongDat * DGBan) AS TongTien
                      FROM hoadon h JOIN cthoadon c ON h.MaHD = c.MaHD
                      GROUP BY YEAR(NgayDH)
                      ORDER BY Nam
                      """;
                tableModel.setColumnIdentifiers(new String[]{"Năm", "Tổng tiền"});
                break;

            case "Số lượng hóa đơn theo ngày":
                sql = """
                      SELECT NgayDH, COUNT(*) AS SoLuongHD
                      FROM hoadon GROUP BY NgayDH ORDER BY NgayDH
                      """;
                tableModel.setColumnIdentifiers(new String[]{"Ngày", "Số lượng hóa đơn"});
                break;
        }

        tableModel.setRowCount(0);

        try (Connection conn = ketnoi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                Object[] row = new Object[meta.getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu thống kê!");
        }
    }


    // ============================ VẼ BIỂU ĐỒ ==================================
    private void showChart() {
        String loai = comboLoaiTK.getSelectedItem().toString();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String category = tableModel.getValueAt(i, 0).toString();
            double value = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            dataset.addValue(value, "Giá trị", category);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                loai,
                "Danh mục",
                "Giá trị",
                dataset
        );

        chartPanelContainer.removeAll();
        chartPanelContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartPanelContainer.validate();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Không thể dùng Nimbus, dùng giao diện mặc định!");
        }
        SwingUtilities.invokeLater(() -> new ThongKeForm().setVisible(true));
    }
}
