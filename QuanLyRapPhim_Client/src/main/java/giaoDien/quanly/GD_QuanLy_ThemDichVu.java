package giaoDien.quanly;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GD_QuanLy_ThemDichVu extends JFrame {

	private JPanel contentPane;
	private JTextField txt_tenDoAn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		GD_QuanLy_ThemDichVu run = new GD_QuanLy_ThemDichVu();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_QuanLy_ThemDichVu() {
		initComponents();
		setTitle("Thêm Đồ Ăn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 483, 544);
		setResizable(false);
		this.setLocationRelativeTo(null);
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
		cbx_loaiDoAn.setBounds(157, 266, 300, 25);
		contentPane.add(cbx_loaiDoAn);
		
		JComboBox cbx_trangThai = new JComboBox();
		cbx_trangThai.setBounds(157, 322, 300, 25);
		contentPane.add(cbx_trangThai);
		
		JLabel lbl_themDoAnMoi = new JLabel("Thêm đồ ăn mới");
		lbl_themDoAnMoi.setForeground(new Color(0, 66, 132));
		lbl_themDoAnMoi.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_themDoAnMoi.setBounds(170, 53, 200, 44);
		contentPane.add(lbl_themDoAnMoi);
		
		JLabel lbl_vuiLong = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lbl_vuiLong.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lbl_vuiLong.setBounds(170, 93, 261, 25);
		contentPane.add(lbl_vuiLong);
		
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				String imagePath = "/imgs/popcorn1.png";
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
				Image image = imageIcon.getImage();

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(10, 10, 156, 165);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

		pack();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		GD_QuanLy_DichVu gdqldv = new GD_QuanLy_DichVu();
		gdqldv.setLocationRelativeTo(null);
		gdqldv.setVisible(true);
	}
}
