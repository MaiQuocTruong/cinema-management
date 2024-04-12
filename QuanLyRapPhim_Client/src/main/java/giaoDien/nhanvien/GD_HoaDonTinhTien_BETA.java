package giaoDien.nhanvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GD_HoaDonTinhTien_BETA extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_HoaDonTinhTien_BETA frame = new GD_HoaDonTinhTien_BETA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_HoaDonTinhTien_BETA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] columnNames = { "Tên NV", "Tên KH", "Ngày Lặp HD", "Tổng Tiền Vé", "Tổng Tiền DV", "Ưu Đãi", "VAT",   "Tổng Tiền"}; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		table.getColumnModel().getColumn(2).setPreferredWidth(200); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
		contentPane.setLayout(null);
																	// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 241, 891, 132); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);
		
		lblNewLabel = new JLabel("Hóa Đơn Tính Tiền ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(368, 38, 183, 37);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Xác Nhận");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(255, 427, 135, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Hủy Bỏ");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(547, 427, 106, 29);
		contentPane.add(btnNewButton_1);
	}

}
