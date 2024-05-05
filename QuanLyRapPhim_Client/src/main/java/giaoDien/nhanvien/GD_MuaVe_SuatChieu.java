package giaoDien.nhanvien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import testbutton.Buttontest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import client_dao.ClientXuatChieu_dao;
import enities.KhachHang;
import enities.XuatChieu;
import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class GD_MuaVe_SuatChieu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClock, lbltennv;
	private Timer timer;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private JPanel panelChonVe, panelThongKe, panelKhachHang;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnXacNhan, btnHuyBo, btnLamMoi;
	private JTextField txtNgayHienThi;
	private JDateChooser ngayHienThiDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private boolean isCalendarVisible = false;
	private JComboBox<String> phongChieuComboBox; // Declare the JComboBox here
//	static String quanly;
	private String maPhim;
	private int soLuongPhim;
	private ClientXuatChieu_dao clientXuatChieu_dao;
	private String maNhanVien , tenNhanVien;

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
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
//	public GD_MuaVe_SuatChieu(String maPhim , int soLuongPhim) {
//		this.maPhim = maPhim;
//		this.soLuongPhim = soLuongPhim;
//	}
	
	
	public GD_MuaVe_SuatChieu(String maHD, String maPhim , int soLuongVe , String sdtkh , String maNhanVien , String tenNhanVien) throws UnknownHostException, IOException, ClassNotFoundException {
		initComponents();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé - Suất Chiếu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setVisible(true);
		

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn
																									// ảnh của
		// bạn
		lblNvIcon.setBounds(760, 5, 40, 40); // Điều chỉnh tọa độ và kích thước của ảnh
		contentPane.add(lblNvIcon);

		JLabel lblnhanvien = new JLabel("NV:");
		lblnhanvien.setForeground(Color.WHITE);
		lblnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnhanvien.setBounds(801, 0, 39, 50);
		lblnhanvien.setForeground(Color.WHITE);
		contentPane.add(lblnhanvien);

		lbltennv = new JLabel(tenNhanVien);
		lbltennv.setForeground(Color.WHITE);
		lbltennv.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltennv.setBounds(832, 0, 238, 50);
		lbltennv.setForeground(Color.WHITE);
//		lbltennv.setText(UserInfo.getTenNhanVien());
		contentPane.add(lbltennv);

		// Thêm panel nằm ngang ở trên cùng
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(0, 0, 1168, 50);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		topPanel.setBackground(customColor); // Thay đổi ở đây
		contentPane.add(topPanel);

		// Thêm toolbar "Mua vé"
		JToolBar muaVeToolbar = new JToolBar();
		muaVeToolbar.setFloatable(false);
		muaVeToolbar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest muaVeButton = new Buttontest();
		muaVeButton.setText("Mua Vé");
		muaVeButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		muaVeButton.setForeground(SystemColor.text);
		muaVeButton.setRippleColor(new Color(255, 255, 255));
		muaVeButton.setBackground(new Color(255, 128, 64));
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/tickets1.png")));
		muaVeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelKhachHang.isVisible() || panelChonVe.isVisible() || panelThongKe.isVisible()) {
					panelChonVe.setVisible(false);
					panelThongKe.setVisible(false);
					panelKhachHang.setVisible(false);
				} else {
					panelChonVe.setVisible(true);
				}
			}
		});
		muaVeToolbar.add(muaVeButton);
		muaVeToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(muaVeToolbar);

		panelChonVe = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelChonVe.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelChonVe.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
//		panelChonVe.setVisible(false); // ẩn panel
		panelChonVe.setVisible(true); // tắt ẩn panel
		panelChonVe.setBackground(whiteColor);
		contentPane.add(panelChonVe);

		JButton btnPhim = new JButton("Chọn Phim");
		btnPhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPhim.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/film-reel.png")));
		btnPhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_Phim gdMuaVePhim;
				try {
					gdMuaVePhim = new GD_MuaVe_Phim(maNhanVien , tenNhanVien);
					gdMuaVePhim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdMuaVePhim.setLocationRelativeTo(null);
					gdMuaVePhim.setVisible(true);
					dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/chair.png")));
//		btnGhe.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				GD_MuaVe_ChonGhe gdmGHE = new GD_MuaVe_ChonGhe();
//				gdmGHE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				gdmGHE.setLocationRelativeTo(null);
//				gdmGHE.setVisible(true);
//				dispose();
//			}
//		});
		JButton btnThucAn = new JButton("Thức Ăn");
		btnThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/popcorn2.png")));
//		btnThucAn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				GD_MuaVe_ThucAn gdmvthan;
//				try {
//					gdmvthan = new GD_MuaVe_ThucAn();
//					gdmvthan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//					gdmvthan.setLocationRelativeTo(null);
//					gdmvthan.setVisible(true);
//					dispose();
//				} catch (UnknownHostException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (ClassNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
		JButton btnSuatChieu = new JButton("Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/clapperboard2.png")));

		panelChonVe.add(btnPhim);
		panelChonVe.add(btnSuatChieu);
		panelChonVe.add(btnGhe);
		panelChonVe.add(btnThucAn);

		// Thêm toolbar "Thống kê"
		JToolBar thongKeToolBar = new JToolBar();
		thongKeToolBar.setFloatable(false);
		thongKeToolBar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest thongKeButton = new Buttontest();
		thongKeButton.setText("Thống Kê");
		thongKeButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		thongKeButton.setForeground(SystemColor.text);
		thongKeButton.setRippleColor(new Color(255, 255, 255));
		thongKeButton.setBackground(new Color(46, 139, 87));
		thongKeButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/bill.png")));
		thongKeToolBar.add(thongKeButton);
		thongKeToolBar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(thongKeToolBar);

		panelThongKe = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelThongKe.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel thống kê
		panelThongKe.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelThongKe.setVisible(false); // Ẩn panel thống kê ban đầu
		panelThongKe.setBackground(whiteColor);
		contentPane.add(panelThongKe);
		
		 // Thêm button thống kê vào panel thống kê
		JButton btnThongKeHoaDon = new JButton("Thống Kê Hóa Đơn");
		btnThongKeHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeHoaDon.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/bill.png")));
		JButton btnThongKeThucAn = new JButton("Thống Kê Thức Ăn"); 
		btnThongKeThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeThucAn.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/popcorn2.png")));
		JButton btnThongKeVePhim = new JButton("Thống Kê Vé Phim");
		btnThongKeVePhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeVePhim.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/tickets2.png")));
		
		panelThongKe.add(btnThongKeHoaDon);
	    panelThongKe.add(btnThongKeThucAn);
	    panelThongKe.add(btnThongKeVePhim);

	    // Thêm sự kiện cho button Hóa Đơn để hiển thị/ẩn panel thống kê
	    thongKeButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (panelKhachHang.isVisible() || panelChonVe.isVisible() || panelThongKe.isVisible()) {
	            	panelChonVe.setVisible(false);
	                panelThongKe.setVisible(false);
	                panelKhachHang.setVisible(false);
	            } else {
	                panelThongKe.setVisible(true);
	            }
	        }
	    });
		
		// Thêm toolbar "Khach Hang"
		JToolBar khachHangToolbar = new JToolBar();
		khachHangToolbar.setFloatable(false);
		khachHangToolbar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest khachHangButton = new Buttontest();
		khachHangButton.setText("Khách Hàng");
		khachHangButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		khachHangButton.setForeground(SystemColor.text);
		khachHangButton.setRippleColor(new Color(255, 255, 255));
		khachHangButton.setBackground(new Color(100, 100, 255));
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/customer1.png")));
		khachHangButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (panelKhachHang.isVisible() || panelChonVe.isVisible() || panelThongKe.isVisible()) {
	            	panelChonVe.setVisible(false);
	                panelThongKe.setVisible(false);
	                panelKhachHang.setVisible(false);
	            } else {
	            	panelKhachHang.setVisible(true);
	            }
	        }
	    });
		khachHangToolbar.add(khachHangButton);
		khachHangToolbar.setBackground(customColor);
		topPanel.add(khachHangToolbar);
		
		panelKhachHang = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelKhachHang.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel thống kê
		panelKhachHang.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelKhachHang.setVisible(false); // Ẩn panel thống kê ban đầu
		panelKhachHang.setBackground(whiteColor);
		contentPane.add(panelKhachHang);
		
		 // Thêm button thống kê vào panel thống kê
		JButton btnQlyKH = new JButton("Quản Lý Khách Hàng");
		btnQlyKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQlyKH.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/khachhang1.png")));
		btnQlyKH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_KhachHang gdkh;
				try {
					gdkh = new GD_KhachHang(maNhanVien , tenNhanVien);
					gdkh.setVisible(true);
					gdkh.setLocationRelativeTo(null);
					dispose();
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panelKhachHang.add(btnQlyKH);

		// Create logout button
		JToolBar logoutToolBar = new JToolBar();
		logoutToolBar.setFloatable(false);
		logoutToolBar.setMargin(new java.awt.Insets(-5, 631, 0, 0));
		testbutton.Buttontest logoutButton = new Buttontest();
		logoutButton.setText("Đăng Xuất");
		logoutButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		logoutButton.setForeground(SystemColor.text);
		logoutButton.setRippleColor(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(226, 110, 110));
		logoutButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblTraCuuSuatChieu = new JLabel("Tra cứu suất chiếu");
		lblTraCuuSuatChieu.setFont(new Font("Open Sans", 1, 16));
		lblTraCuuSuatChieu.setBounds(45, 102, 152, 20);
		contentPane.add(lblTraCuuSuatChieu);

		JPanel pnlTheoTenPhim = new JPanel();
		pnlTheoTenPhim.setBackground(new Color(255, 255, 0));
		pnlTheoTenPhim.setBounds(10, 464, 230, 37);
		pnlTheoTenPhim.setOpaque(false);
		contentPane.add(pnlTheoTenPhim);
		// Add JTextField below JCheckBox
		JTextField txtTenPhim = new JTextField();
		txtTenPhim.setFont(new Font("Open Sans", 0, 16));
		txtTenPhim.setColumns(16); // You can adjust the column count based on your requirement
		pnlTheoTenPhim.add(txtTenPhim);

		JPanel pnlNgayHienThi_1 = new JPanel();
		pnlNgayHienThi_1.setOpaque(false);
		pnlNgayHienThi_1.setBackground(Color.YELLOW);
		pnlNgayHienThi_1.setBounds(10, 187, 191, 37);
		contentPane.add(pnlNgayHienThi_1);

		txtNgayHienThi = new JTextField();
		txtNgayHienThi.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNgayHienThi.setColumns(13);
		pnlNgayHienThi_1.add(txtNgayHienThi);

		// Khởi tạo JDateChooser cho từ ngày
		ngayHienThiDateChooser = new JDateChooser();
		ngayHienThiDateChooser.setBounds(140, 194, 100, 29);
		ngayHienThiDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
		contentPane.add(ngayHienThiDateChooser);

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		ngayHienThiDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = ngayHienThiDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtNgayHienThi.setText(dateFormat.format(selectedDate));
			}
		});

		JPanel pnlNgayHienThi = new JPanel();
		pnlNgayHienThi.setOpaque(false);
		pnlNgayHienThi.setBackground(Color.YELLOW);
		pnlNgayHienThi.setBounds(10, 140, 230, 37);
		pnlNgayHienThi.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlNgayHienThi);

		JLabel lblNgayHienThi = new JLabel("Ngày hiển thị:");
		lblNgayHienThi.setFont(new Font("Open Sans", 0, 16));
		pnlNgayHienThi.add(lblNgayHienThi);

		JPanel pnlPhongChieu = new JPanel();
		pnlPhongChieu.setOpaque(false);
		pnlPhongChieu.setBackground(Color.YELLOW);
		pnlPhongChieu.setBounds(10, 234, 230, 37);
		contentPane.add(pnlPhongChieu);
		pnlPhongChieu.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblPhongChieu = new JLabel("Phòng chiếu:");
		lblPhongChieu.setFont(new Font("Open Sans", 0, 16));
		pnlPhongChieu.add(lblPhongChieu);

		String[] phongChieuList = { "Phòng 1", "Phòng 2", "Phòng 3", "Phòng 4" };
		phongChieuComboBox = new JComboBox<>(phongChieuList);
		pnlPhongChieu.add(phongChieuComboBox);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setOpaque(false);
		pnlTrangThai.setBackground(Color.YELLOW);
		pnlTrangThai.setBounds(10, 281, 230, 37);
		contentPane.add(pnlTrangThai);
		pnlTrangThai.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblTrangThai = new JLabel("Theo trạng thái:");
		lblTrangThai.setFont(new Font("Open Sans", 0, 16));
		pnlTrangThai.add(lblTrangThai);

		JRadioButton rdbtnSapChieu = new JRadioButton("Sắp chiếu");
		rdbtnSapChieu.setFont(new Font("Open Sans", 0, 16));
		rdbtnSapChieu.setBounds(14, 324, 103, 21);
		contentPane.add(rdbtnSapChieu);

		JRadioButton rdbtnDangChieu = new JRadioButton("Đang chiếu");
		rdbtnDangChieu.setFont(new Font("Open Sans", 0, 16));
		rdbtnDangChieu.setBounds(14, 358, 103, 21);
		contentPane.add(rdbtnDangChieu);

		JRadioButton rdbtnDaChieu = new JRadioButton("Đã chiếu");
		rdbtnDaChieu.setFont(new Font("Open Sans", 0, 16));
		rdbtnDaChieu.setBounds(14, 392, 103, 21);
		contentPane.add(rdbtnDaChieu);

		// Thêm chúng vào ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnSapChieu);
		buttonGroup.add(rdbtnDangChieu);
		buttonGroup.add(rdbtnDaChieu);

		JPanel pnlTenPhim = new JPanel();
		pnlTenPhim.setOpaque(false);
		pnlTenPhim.setBackground(Color.YELLOW);
		pnlTenPhim.setBounds(10, 429, 230, 37);
		contentPane.add(pnlTenPhim);
		pnlTenPhim.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblTenPhim = new JLabel("Theo tên phim:");
		lblTenPhim.setFont(new Font("Dialog", Font.PLAIN, 16));
		pnlTenPhim.add(lblTenPhim);
		
		// Khởi tạo các nút
		btnXacNhan = new JButton("Xác nhận");
		btnHuyBo = new JButton("Hủy bỏ");
		btnLamMoi = new JButton("Làm mới");

		// Đặt vị trí cho các nút
		btnXacNhan.setBounds(250, 99, 100, 30);
		btnHuyBo.setBounds(360, 99, 100, 30);
		btnLamMoi.setBounds(470, 99, 100, 30);

		// Thêm các nút vào contentPane
		contentPane.add(btnXacNhan);
		contentPane.add(btnHuyBo);
		contentPane.add(btnLamMoi);

		// Khởi tạo DefaultTableModel với các cột
		String[] columnNames = {"Mã suất chiếu", "Tên phim", "Ngày chiếu", "Giờ chiếu", "Định dạng", "Ngôn ngữ",
				"Phòng chiếu", "Trạng thái" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		table.getColumnModel().getColumn(2).setPreferredWidth(200); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
																	// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		// Thêm dữ liệu vào bảng
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);
		
		
//		System.out.println("Mã Phim Trong Xuất Chiếu " + maPhim + "Số Lượng Vé Phim " + soLuongVe);
		Socket socket = new Socket("192.168.100.4", 6789);
		clientXuatChieu_dao = new ClientXuatChieu_dao(socket);
		List<XuatChieu> listXuatChieu = clientXuatChieu_dao.getListXC();
		
		
		
		for (XuatChieu xuatChieu : listXuatChieu) {
			if(xuatChieu.getPhim().getMaPhim().equalsIgnoreCase(maPhim)) {
				Object [] rowData = {xuatChieu.getMaXuat() , xuatChieu.getPhim().getTenPhim() , xuatChieu.getNgayChieu() , xuatChieu.getGioChieu() , xuatChieu.getDinhDang() , xuatChieu.getPhim().getNgonNgu() , xuatChieu.getPhongchieu().getTenPhongChieu() ,xuatChieu.getTrangThai()};
				tableModel.addRow(rowData);
			}
			
		}
		
		
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					 String maSuatChieu = (String) table.getValueAt(row, 0);
					 
					try {
						 GD_MuaVe_ChonGhe gd_chonGhe = new GD_MuaVe_ChonGhe(maHD , maSuatChieu , soLuongVe , sdtkh , maNhanVien , tenNhanVien);
						 gd_chonGhe.setVisible(true);
						 dispose();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 
				}else {
					 JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 suất chiếu", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				try {
					formWindowClosing(evt);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	private void formWindowClosing(java.awt.event.WindowEvent evt) throws UnknownHostException, IOException, ClassNotFoundException {// GEN-FIRST:event_formWindowClosing
		GD_MuaVe_Phim gdmvphim = new GD_MuaVe_Phim(maNhanVien , tenNhanVien);
		gdmvphim.setLocationRelativeTo(null);
		gdmvphim.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
