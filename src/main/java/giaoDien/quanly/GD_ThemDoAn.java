package giaoDien.quanly;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GD_ThemDoAn extends JFrame {

	private JPanel contentPane;
	private JTextField txt_tenDoAn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ThemDoAn frame = new GD_ThemDoAn();
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
	public GD_ThemDoAn() {
		super("Thêm Đồ Ăn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(226, 226, 226));
		panel.setBounds(0, 443, 467, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnLu.setBounds(124, 11, 89, 35);
		panel.add(btnLu);
		
		JButton btnNewButton = new JButton("Hủy");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnNewButton.setBounds(241, 11, 89, 35);
		panel.add(btnNewButton);
		
		JLabel lbl_tenDoAn = new JLabel("Tên đồ ăn:");
		lbl_tenDoAn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lbl_tenDoAn.setBounds(43, 207, 105, 25);
		contentPane.add(lbl_tenDoAn);
		
		JLabel lbl_loaiDoAn = new JLabel("Loại đồ ăn:");
		lbl_loaiDoAn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lbl_loaiDoAn.setBounds(43, 262, 105, 25);
		contentPane.add(lbl_loaiDoAn);
		
		JLabel lbl_trangThai = new JLabel("Trạng thái:");
		lbl_trangThai.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lbl_trangThai.setBounds(43, 318, 105, 25);
		contentPane.add(lbl_trangThai);
		
		txt_tenDoAn = new JTextField();
		txt_tenDoAn.setBounds(157, 210, 300, 25);
		contentPane.add(txt_tenDoAn);
		txt_tenDoAn.setColumns(10);
		
		JComboBox cbx_loaiDoAn = new JComboBox();
		cbx_loaiDoAn.setBounds(158, 266, 299, 25);
		contentPane.add(cbx_loaiDoAn);
		
		JComboBox cbx_trangThai = new JComboBox();
		cbx_trangThai.setBounds(158, 322, 299, 25);
		contentPane.add(cbx_trangThai);
		
		JLabel lbl_themDoAnMoi = new JLabel("Thêm đồ ăn mới");
		lbl_themDoAnMoi.setForeground(new Color(0, 66, 132));
		lbl_themDoAnMoi.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_themDoAnMoi.setBounds(170, 53, 200, 44);
		contentPane.add(lbl_themDoAnMoi);
		
		JLabel lbl_vuiLong = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lbl_vuiLong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lbl_vuiLong.setBounds(167, 95, 261, 25);
		contentPane.add(lbl_vuiLong);
		
		JLabel lbl_icon = new JLabel("");
		ImageIcon originalIcon = new ImageIcon("C:\\loc-projects\\cinema-management\\src\\main\\resources\\imgs\\popcorn1.png"); // Thay đổi "icon.png" thành đường dẫn của biểu tượng của bạn
        
        // Lấy biểu tượng và chỉnh kích thước trực tiếp
        Image img = originalIcon.getImage();
        Image scaledImage = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
        // Tạo một ImageIcon mới từ biểu tượng đã được chỉnh kích thước
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lbl_icon.setIcon(scaledIcon);
		lbl_icon.setBounds(21, 28, 150, 144);
		lbl_icon.setHorizontalAlignment(JLabel.CENTER);
		lbl_icon.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(lbl_icon);
	}
}
