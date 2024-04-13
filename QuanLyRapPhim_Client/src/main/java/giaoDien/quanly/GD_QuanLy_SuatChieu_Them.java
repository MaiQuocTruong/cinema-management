package giaoDien.quanly;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import client_dao.ClientKhachHang_dao;

import enities.Phim;
import enities.XuatChieu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GD_QuanLy_SuatChieu_Them extends JFrame {

	private JPanel contentPane;
	private boolean isCalendarVisible = false;
	private DefaultTableModel model;
	private List<String> dsTenPhim;
	private JTextField txt_MaXuat;
	private JDateChooser ngayChieuDate;
//	private ClientXuatChieu_dao client_xc;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public GD_QuanLy_SuatChieu_Them() throws UnknownHostException, IOException {
//		initComponents();
		setResizable(false);
		this.setLocationRelativeTo(null);;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lbl_icon = new JLabel();
		lbl_icon.setBounds(490, 26, 193, 224);
		
		ngayChieuDate = new JDateChooser();
		ngayChieuDate.setBounds(655, 338, 318, 29);
		contentPane.add(ngayChieuDate);
               
        JLabel lblNewLabel = new JLabel("Cập nhật xuất chiếu ");
        lblNewLabel.setForeground(new Color(0, 50, 100));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblNewLabel.setBounds(693, 69, 280, 39);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Vui lòng nhập đầy đủ thông tin");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(691, 128, 282, 31);
        contentPane.add(lblNewLabel_1);
        
        JLabel lbl_dinhDang = new JLabel("Định dạng:");
        lbl_dinhDang.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_dinhDang.setBounds(532, 496, 113, 25);
        contentPane.add(lbl_dinhDang);
        
        //tao mang dinh dang
        String[] dinhDang_arr = {"2D","3D"};
        
        JComboBox<String> cbx_dinhDang = new JComboBox<>(dinhDang_arr);
        cbx_dinhDang.setBounds(655, 497, 318, 31);
        contentPane.add(cbx_dinhDang);
        
        JLabel lbl_ngayChieu = new JLabel("Ngày chiếu :");
        lbl_ngayChieu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_ngayChieu.setBounds(512, 338, 125, 25);
        contentPane.add(lbl_ngayChieu);
		
		JLabel lbl_gioBatDau = new JLabel("Giờ bắt đầu:");
		lbl_gioBatDau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_gioBatDau.setBounds(512, 391, 139, 25);
		contentPane.add(lbl_gioBatDau);
		
		// Tạo mảng giờ cho JComboBox
		// Tạo mảng giờ và phút cho JComboBox
        String[] timeSlots = new String[24 * 60]; // 24 giờ * 4 slot trong mỗi giờ
        int slotIndex = 0;
        for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 60; j++) { // Tăng phút mỗi lần 1 phút
                timeSlots[slotIndex++] = String.format("%02d:%02d", i, j);
            }
        }
		
		JComboBox<String> cbx_gioBatDau = new JComboBox<>(timeSlots);
		cbx_gioBatDau.setBounds(655, 392, 318, 31);
		contentPane.add(cbx_gioBatDau);
		
		JLabel lbl_gioKetThuc = new JLabel("Giờ kết thúc:");
		lbl_gioKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_gioKetThuc.setBounds(512, 440, 139, 25);
		contentPane.add(lbl_gioKetThuc);
		
		JComboBox<String> cbx_gioKetThuc = new JComboBox<>(timeSlots);
		cbx_gioKetThuc.setBounds(655, 441, 318, 31);
		contentPane.add(cbx_gioKetThuc);
		
		JButton btn_capNhat = new JButton("Cập nhật");
		btn_capNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_capNhat.setBounds(527, 622, 124, 31);
		contentPane.add(btn_capNhat);
		
		String[] trangThai_arr = {"Đang chiếu","Chuẩn bị chiếu"};
		
		JComboBox<String> cbx_trangThai = new JComboBox<String>(trangThai_arr);
		cbx_trangThai.setBounds(655, 554, 320, 29);
		contentPane.add(cbx_trangThai);
		
//		Load Data
		Socket socket = new Socket("192.168.2.20", 6789);
//		client_xc = new ClientXuatChieu_dao(socket);
		
		btn_capNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maXuat = txt_MaXuat.getText();
				Date ngayChieu = ngayChieuDate.getDate();
				String gioChieuString = cbx_gioBatDau.getSelectedItem().toString();
				Time gioChieu = Time.valueOf(gioChieuString + ":00");
				String gioKetThucString = cbx_gioKetThuc.getSelectedItem().toString();
				Time gioKetThuc = Time.valueOf(gioKetThucString + ":00"); 
				String dinhDang = cbx_dinhDang.getSelectedItem().toString();
				String trangThai = cbx_trangThai.getSelectedItem().toString();
				
				XuatChieu xc = new XuatChieu(maXuat, dinhDang, ngayChieu,gioChieu,gioKetThuc,trangThai);
			}
		});
		
		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_xoa.setBounds(840, 622, 124, 31);
		contentPane.add(btn_xoa);
		
		
		
		JLabel maXuat_lbl = new JLabel("Mã Xuất :");
		maXuat_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		maXuat_lbl.setBounds(539, 286, 98, 31);
		contentPane.add(maXuat_lbl);
		
		txt_MaXuat = new JTextField();
		txt_MaXuat.setBounds(655, 286, 309, 31);
		contentPane.add(txt_MaXuat);
		txt_MaXuat.setColumns(10);
		
		JLabel lbl_trangThai = new JLabel("Trạng Thái:");
		lbl_trangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_trangThai.setBounds(532, 552, 113, 25);
		contentPane.add(lbl_trangThai);
		
		
		
		
	}
	
	private void initComponents() {
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
		GD_QuanLy gdql = new GD_QuanLy();
		gdql.setLocationRelativeTo(null);
		gdql.setVisible(true);
	}
}
