package giaoDien.quanly;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;


import enities.Phim;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_QuanLy_SuatChieu_Them extends JFrame {

	private JPanel contentPane;
	private boolean isCalendarVisible = false;
	private DefaultTableModel model;
	private JTable table;
	private List<String> dsTenPhim;

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
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_QuanLy_SuatChieu_Them run = new GD_QuanLy_SuatChieu_Them();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_QuanLy_SuatChieu_Them() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				String imagePath = "/imgs/video-camera.png";
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
				Image image = imageIcon.getImage();

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(477, 10, 191, 195);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);
		
		JPanel pnl_danhSachSuatChieu = new JPanel();
		pnl_danhSachSuatChieu.setBackground(new Color(192, 192, 192));
		pnl_danhSachSuatChieu.setBounds(0, 10, 467, 643);
		contentPane.add(pnl_danhSachSuatChieu);
		pnl_danhSachSuatChieu.setLayout(null);
		
		JLabel lblDanhSchSut = new JLabel("Danh sách suất chiếu ");
		lblDanhSchSut.setForeground(new Color(0, 50, 100));
		lblDanhSchSut.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDanhSchSut.setBounds(26, 30, 280, 39);
		pnl_danhSachSuatChieu.add(lblDanhSchSut);
		
		JDateChooser ngayHienThiDateChooser_1 = new JDateChooser();
		ngayHienThiDateChooser_1.setBounds(50, 79, 347, 29);
		pnl_danhSachSuatChieu.add(ngayHienThiDateChooser_1);
		
		JComboBox cbx_tenPhim_1 = new JComboBox();
		cbx_tenPhim_1.setBounds(50, 134, 347, 27);
		pnl_danhSachSuatChieu.add(cbx_tenPhim_1);
		
		String[] colName = {"Gio Bat Dau","Gio Ket Thuc","Ten Phim"};
		
		model = new DefaultTableModel(colName,0);
		table = new JTable(model);
		
		JScrollPane scrPane_danhSach = new JScrollPane(table);
		scrPane_danhSach.setBounds(10, 189, 447, 426);
		pnl_danhSachSuatChieu.add(scrPane_danhSach);
		
		JLabel lbl_icon = new JLabel();
		lbl_icon.setBounds(490, 26, 193, 224);
               
        JLabel lblNewLabel = new JLabel("Cập nhật xuất chiếu ");
        lblNewLabel.setForeground(new Color(0, 50, 100));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblNewLabel.setBounds(693, 69, 280, 39);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Vui lòng nhập đầy đủ thông tin");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(691, 128, 282, 31);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Tên phim:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(512, 295, 115, 25);
        contentPane.add(lblNewLabel_2);
        
        JComboBox cbx_tenPhim = new JComboBox();
        cbx_tenPhim.setBounds(655, 295, 318, 27);
        
      
    
       
       
        
        contentPane.add(cbx_tenPhim);
        
        JLabel lblNewLabel_2_1 = new JLabel("Phòng chiếu:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1.setBounds(512, 348, 139, 25);
        contentPane.add(lblNewLabel_2_1);
        
        JComboBox cbx_phongChieu = new JComboBox();
        cbx_phongChieu.setBounds(655, 347, 318, 31);
        contentPane.add(cbx_phongChieu);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Định dạng:");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1_1.setBounds(512, 401, 139, 25);
        contentPane.add(lblNewLabel_2_1_1);
        
        JComboBox cbx_dinhDang = new JComboBox();
        cbx_dinhDang.setBounds(655, 400, 318, 31);
        contentPane.add(cbx_dinhDang);
        
        JLabel lblNewLabel_3 = new JLabel("Ngày chiếu :");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(512, 457, 125, 25);
        contentPane.add(lblNewLabel_3);
		
		JLabel lbl_gioBatDau = new JLabel("Giờ băt đầu:");
		lbl_gioBatDau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_gioBatDau.setBounds(512, 512, 139, 25);
		contentPane.add(lbl_gioBatDau);
		
		JComboBox cbx_gioBatDau = new JComboBox();
		cbx_gioBatDau.setBounds(655, 511, 318, 31);
		contentPane.add(cbx_gioBatDau);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Giờ kết thúc:");
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_2.setBounds(512, 564, 139, 25);
		contentPane.add(lblNewLabel_2_1_1_2);
		
		JComboBox cbx_gioKetThuc = new JComboBox();
		cbx_gioKetThuc.setBounds(655, 563, 318, 31);
		contentPane.add(cbx_gioKetThuc);
		
		JButton btn_capNhat = new JButton("Cập nhật");
		btn_capNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_capNhat.setBounds(527, 622, 124, 31);
		contentPane.add(btn_capNhat);
		
		btn_capNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_xoa.setBounds(840, 622, 124, 31);
		contentPane.add(btn_xoa);
		
		JDateChooser ngayChieuDate = new JDateChooser();
		ngayChieuDate.setBounds(655, 457, 318, 29);
		contentPane.add(ngayChieuDate);
		
		
	}
}
