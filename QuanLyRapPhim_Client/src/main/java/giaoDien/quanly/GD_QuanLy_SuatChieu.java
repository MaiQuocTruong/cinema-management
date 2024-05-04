package giaoDien.quanly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import client_dao.ClientPhongChieu_dao;
import client_dao.ClientXuatChieu_dao;
import enities.Phim;
import enities.PhongChieuPhim;
import enities.XuatChieu;
//import enities.Phim;
//import enities.XuatChieu;
import giaoDien.nhanvien.GD_NhanVien;
import runapp.Login;
import testbutton.Buttontest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GD_QuanLy_SuatChieu extends JFrame implements ActionListener {
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
	private JPanel panelPhim, panelDichVu, panelNhanVien, panelTaiKhoan, panelThongKe;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JTextField txtNgayHienThi, txtHoTen, txtmaXC;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private boolean daChonPhim = false; // Biến để kiểm tra xem đã chọn Phim hay chưa
	private JDateChooser ngayHienThiDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private boolean isCalendarVisible = false;
	private JButton btnThem, btnXoa, btnLamMoi, btnSua, btnTracuu, btnTimtheoNgay;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> phongChieuComboBox; // Declare the JComboBox here
	private List<XuatChieu> listXC;
	private ClientXuatChieu_dao clientXC;
	
//	static String quanly;
	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 * 
	 */
	public static void main(String[] args)
			throws UnknownHostException, IOException, ClassNotFoundException, EOFException {
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
		GD_QuanLy_SuatChieu run = new GD_QuanLy_SuatChieu();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public GD_QuanLy_SuatChieu() throws UnknownHostException, IOException, ClassNotFoundException {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Suất Chiếu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn
																									// ảnh của bạn
		lblNvIcon.setBounds(760, 5, 40, 40); // Điều chỉnh tọa độ và kích thước của ảnh
		contentPane.add(lblNvIcon);

		JLabel lblnhanvien = new JLabel("QL:");
		lblnhanvien.setForeground(Color.WHITE);
		lblnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnhanvien.setBounds(801, 0, 39, 50);
		lblnhanvien.setForeground(Color.WHITE);
		contentPane.add(lblnhanvien);

		lbltennv = new JLabel("Mai Quốc Trưởng");
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

		// Thêm toolbar "phim"
		JToolBar qlyPhimToolbar = new JToolBar();
		qlyPhimToolbar.setFloatable(false);
		qlyPhimToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest qlyPhimButton = new Buttontest();
		qlyPhimButton.setText("Phim");
		qlyPhimButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		qlyPhimButton.setForeground(SystemColor.text);
		qlyPhimButton.setRippleColor(new Color(255, 255, 255));
		qlyPhimButton.setBackground(new Color(255, 128, 64));
		qlyPhimButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/video-camera12.png")));
		qlyPhimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible()
						|| panelDichVu.isVisible() || panelPhim.isVisible()) {
					panelPhim.setVisible(false);
					panelDichVu.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelPhim.setVisible(true);
				}
			}
		});
		qlyPhimToolbar.add(qlyPhimButton);
		qlyPhimToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(qlyPhimToolbar);

		panelPhim = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelPhim.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelPhim.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelPhim.setVisible(true); // tắt/ẩn panel
		panelPhim.setBackground(whiteColor);
		contentPane.add(panelPhim);

		JButton btnqlyPhim = new JButton("Quản Lý Phim");
		btnqlyPhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyPhim.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/film-reel.png")));
//		btnqlyPhim.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				GD_QuanLy_Phim gdqlphim;
//				try {
//					gdqlphim = new GD_QuanLy_Phim();
//					
//				} catch (ClassNotFoundException | IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
		JButton btnSuatChieu = new JButton("Quản Lý Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/clapperboard2.png")));
		JButton btnqlyphongchieu = new JButton("Quản Lý Phòng Chiếu");
		btnqlyphongchieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyphongchieu.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/tickets2.png")));
		btnqlyphongchieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_PhongChieu gdqlpc;
				try {
					gdqlpc = new GD_QuanLy_PhongChieu();
					gdqlpc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlpc.setLocationRelativeTo(null);
					gdqlpc.setVisible(true);
					dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		panelPhim.add(btnqlyPhim);
		panelPhim.add(btnSuatChieu);
		panelPhim.add(btnqlyphongchieu);

		// Thêm toolbar "dịch vụ"
		JToolBar dichVuToolbar = new JToolBar();
		dichVuToolbar.setFloatable(false);
		dichVuToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest dichVuButton = new Buttontest();
		dichVuButton.setText("Dịch Vụ");
		dichVuButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		dichVuButton.setForeground(SystemColor.text);
		dichVuButton.setRippleColor(new Color(255, 255, 255));
		dichVuButton.setBackground(new Color(46, 139, 87));
		dichVuButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/popcorn12.png")));
		dichVuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible()
						|| panelDichVu.isVisible() || panelPhim.isVisible()) {
					panelPhim.setVisible(false);
					panelDichVu.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelDichVu.setVisible(true);
				}
			}
		});
		dichVuToolbar.add(dichVuButton);
		dichVuToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(dichVuToolbar);

		panelDichVu = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelDichVu.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelDichVu.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelDichVu.setVisible(false); // tắt/ẩn panel
		panelDichVu.setBackground(whiteColor);
		contentPane.add(panelDichVu);

		JButton btnqldv = new JButton("Quản Lý Dịch Vụ");
		btnqldv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqldv.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/popcorn2.png")));
		btnqldv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_DichVu gdqldv = new GD_QuanLy_DichVu();
				gdqldv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqldv.setLocationRelativeTo(null);
				gdqldv.setVisible(true);
				dispose();
			}
		});
		panelDichVu.add(btnqldv);

		// Thêm toolbar "nhân viên"
		JToolBar nhanVienToolbar = new JToolBar();
		nhanVienToolbar.setFloatable(false);
		nhanVienToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest nhanVienButton = new Buttontest();
		nhanVienButton.setText("Nhân Viên");
		nhanVienButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		nhanVienButton.setForeground(SystemColor.text);
		nhanVienButton.setRippleColor(new Color(255, 255, 255));
		nhanVienButton.setBackground(new Color(255, 0, 128));
		nhanVienButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/customer1.png")));
		nhanVienButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible()
						|| panelDichVu.isVisible() || panelPhim.isVisible()) {
					panelPhim.setVisible(false);
					panelDichVu.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelNhanVien.setVisible(true);
				}
			}
		});
		nhanVienToolbar.add(nhanVienButton);
		nhanVienToolbar.setBackground(customColor);
		topPanel.add(nhanVienToolbar);

		panelNhanVien = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelNhanVien.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelNhanVien.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelNhanVien.setVisible(false); // tắt/ẩn panel
		panelNhanVien.setBackground(whiteColor);
		contentPane.add(panelNhanVien);

		JButton btnNhanVien = new JButton("Quản Lý Nhân Viên");
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhanVien.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/khachhang1.png")));
		btnNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_NhanVien gdqlnv;
				try {
					gdqlnv = new GD_QuanLy_NhanVien();
					gdqlnv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlnv.setLocationRelativeTo(null);
					gdqlnv.setVisible(true);
					dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		panelNhanVien.add(btnNhanVien);

		// Thêm toolbar "tài khoản"
		JToolBar taiKhoanToolbar = new JToolBar();
		taiKhoanToolbar.setFloatable(false);
		taiKhoanToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest taiKhoanButton = new Buttontest();
		taiKhoanButton.setText("Tài Khoản");
		taiKhoanButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		taiKhoanButton.setForeground(SystemColor.text);
		taiKhoanButton.setRippleColor(new Color(255, 255, 255));
		taiKhoanButton.setBackground(new Color(99, 176, 28));
		taiKhoanButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/employee1.png")));
		taiKhoanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible()
						|| panelDichVu.isVisible() || panelPhim.isVisible()) {
					panelPhim.setVisible(false);
					panelDichVu.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelTaiKhoan.setVisible(true);
				}
			}
		});
		taiKhoanToolbar.add(taiKhoanButton);
		taiKhoanToolbar.setBackground(customColor);
		topPanel.add(taiKhoanToolbar);

		panelTaiKhoan = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelTaiKhoan.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelTaiKhoan.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelTaiKhoan.setVisible(false); // tắt/ẩn panel
		panelTaiKhoan.setBackground(whiteColor);
		contentPane.add(panelTaiKhoan);

		JButton btnTaiKhoan = new JButton("Quản Lý Tài Khoản");
		btnTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaiKhoan.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/av1.png")));
		btnTaiKhoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_TaiKhoan gdqlytk;
				try {
					gdqlytk = new GD_QuanLy_TaiKhoan();
					gdqlytk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlytk.setLocationRelativeTo(null);
					gdqlytk.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		panelTaiKhoan.add(btnTaiKhoan);

		// thêm toolbar "thống kê"
		JToolBar thongKeToolbar = new JToolBar();
		thongKeToolbar.setFloatable(false);
		thongKeToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest thongKeButton = new Buttontest();
		thongKeButton.setText("Thống Kê");
		thongKeButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		thongKeButton.setForeground(SystemColor.text);
		thongKeButton.setRippleColor(new Color(255, 255, 255));
		thongKeButton.setBackground(new Color(100, 100, 255));
		thongKeButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/thongke1.png")));
		thongKeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible()
						|| panelDichVu.isVisible() || panelPhim.isVisible()) {
					panelPhim.setVisible(false);
					panelDichVu.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelThongKe.setVisible(true);
				}
			}
		});
		thongKeToolbar.add(thongKeButton);
		thongKeToolbar.setBackground(customColor);
		topPanel.add(thongKeToolbar);

		panelThongKe = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelThongKe.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelThongKe.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelThongKe.setVisible(false); // tắt/ẩn panel
		panelThongKe.setBackground(whiteColor);
		contentPane.add(panelThongKe);

		JButton btnThongKePhim = new JButton("Thống Kê Phim");
		btnThongKePhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKePhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_ThongKePhim gdqlthongkephim;
				try {
					gdqlthongkephim = new GD_QuanLy_ThongKePhim();
					gdqlthongkephim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlthongkephim.setLocationRelativeTo(null);
					gdqlthongkephim.setVisible(true);
					dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnThongKePhim.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/clapperboard2.png")));
		JButton btnThongKeDichVu = new JButton("Thống Kê Dịch Vụ");
		btnThongKeDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDichVu.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/popcorn2.png")));
		btnThongKeDichVu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_ThongKeDichVu gdqlthongDVu;
				try {
					gdqlthongDVu = new GD_QuanLy_ThongKeDichVu();
					gdqlthongDVu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlthongDVu.setLocationRelativeTo(null);
					gdqlthongDVu.setVisible(true);
					dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		panelThongKe.add(btnThongKePhim);
		panelThongKe.add(btnThongKeDichVu);

		// Create logout button
		JToolBar logoutToolBar = new JToolBar();
		logoutToolBar.setFloatable(false);
		logoutToolBar.setMargin(new java.awt.Insets(-5, 438, 0, 0));
		testbutton.Buttontest logoutButton = new Buttontest();
		logoutButton.setText("Đăng Xuất");
		logoutButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		logoutButton.setForeground(SystemColor.text);
		logoutButton.setRippleColor(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(226, 110, 110));
		logoutButton.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/logout.png")));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Login lg = new Login();
					lg.setVisible(true);
					lg.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblTraCuuSuatChieu = new JLabel("Tra cứu suất chiếu");
		lblTraCuuSuatChieu.setFont(new Font("Open Sans", 1, 16));
		lblTraCuuSuatChieu.setBounds(45, 102, 152, 20);
		contentPane.add(lblTraCuuSuatChieu);

		JPanel pnlNgayHienThi_1 = new JPanel();
		pnlNgayHienThi_1.setOpaque(false);
		pnlNgayHienThi_1.setBackground(Color.YELLOW);
		pnlNgayHienThi_1.setBounds(10, 187, 191, 37);
		contentPane.add(pnlNgayHienThi_1);

		txtNgayHienThi = new JTextField();
		txtNgayHienThi.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNgayHienThi.setColumns(13);
		pnlNgayHienThi_1.add(txtNgayHienThi);

//		// Khởi tạo JDateChooser cho từ ngày
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
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				txtNgayHienThi.setText(dateFormat.format(selectedDate));
			}
		});

		JPanel pnlNgayHienThi = new JPanel();
		pnlNgayHienThi.setOpaque(false);
		pnlNgayHienThi.setBackground(Color.YELLOW);
		pnlNgayHienThi.setBounds(10, 140, 230, 37);
		pnlNgayHienThi.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlNgayHienThi);

//		JLabel lblNgayHienThi = new JLabel("Ngày hiển thị:");
//		lblNgayHienThi.setFont(new Font("Open Sans", 0, 16));
//		pnlNgayHienThi.add(lblNgayHienThi);
		btnTimtheoNgay = new JButton("Theo Ngày");
		btnTimtheoNgay.setFont(new Font("Open Sans", 0, 16));
		pnlNgayHienThi.add(btnTimtheoNgay);
		btnTimtheoNgay.addActionListener(this);
		
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

		JLabel lblTenPhim = new JLabel("Tra cứu theo:");
		lblTenPhim.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPane.add(lblTenPhim);

		JPanel pnltrucuu = new JPanel();
		pnltrucuu.setOpaque(false);
		pnltrucuu.setBackground(Color.YELLOW);
		pnltrucuu.setBounds(10, 429, 230, 37);
		contentPane.add(pnltrucuu);
		pnltrucuu.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnTracuu = new JButton();
		btnTracuu.setFont(new Font("Open Sans", 0, 16)); // NOI18N
		btnTracuu.setSelected(true);
		btnTracuu.setText("Theo mã XC");
		btnTracuu.addActionListener(this);
		pnltrucuu.add(btnTracuu);

		JPanel pnlTheoTenPhim = new JPanel();
		pnlTheoTenPhim.setBackground(new Color(255, 255, 0));
		pnlTheoTenPhim.setBounds(10, 464, 230, 37);
		pnlTheoTenPhim.setOpaque(false);
		contentPane.add(pnlTheoTenPhim);
		// Add JTextField below JCheckBox
		txtmaXC = new JTextField();
		txtmaXC.setFont(new Font("Open Sans", 0, 16));
		txtmaXC.setColumns(16); // You can adjust the column count based on your requirement
		pnlTheoTenPhim.add(txtmaXC);

		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		// Thêm sự kiện các nút
		btnThem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						GD_QuanLy_SuatChieu_Them gdThemSuatChieu;
						try {
							Socket socket = new Socket("192.168.2.10", 6789);
							clientXC = new ClientXuatChieu_dao(socket);
							try {
								gdThemSuatChieu = new GD_QuanLy_SuatChieu_Them();
								gdThemSuatChieu.setVisible(true);
								gdThemSuatChieu.setLocationRelativeTo(null);
								dispose();
								
								listXC=clientXC.getListXC();
								loadDataToTable(listXC);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// Lấy mã phòng chiếu được chọn từ bảng
					int selectedRow = table.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn một dữ liệu ở bảng để sửa.");
						return;
					}
					String maXuat = (String) table.getValueAt(selectedRow, 0);
					String maPhim=(String) table.getValueAt(selectedRow, 1);
		            String maPhongChieu=(String) table.getValueAt(selectedRow, 2);
					String trangThai = (String) table.getValueAt(selectedRow, 7);
					String dinhDang = (String) table.getValueAt(selectedRow, 6);
					LocalDate ngayChieu = (LocalDate) table.getValueAt(selectedRow, 3);
					Time gioChieu = (Time) table.getValueAt(selectedRow, 4);
					Time gioKetthuc = (Time) table.getValueAt(selectedRow, 5);
					
					// Hiển thị giao diện sửa phòng chiếu
					GD_QuanLy_SuatChieu_Sua gdSua = new GD_QuanLy_SuatChieu_Sua();
					// Truyền mã phòng chiếu tới giao diện sửa
					gdSua.addMaPhimToList(maPhim);
					gdSua.addMaPhongChieuToList(maPhongChieu);
					gdSua.setMaXuat(maXuat,maPhim,maPhongChieu, trangThai,dinhDang,ngayChieu.toString(),gioChieu.toString(),gioKetthuc.toString());
					gdSua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdSua.setLocationRelativeTo(null);
					gdSua.setVisible(true);
					dispose();

				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		btnXoa = new JButton("Xóa");
		btnLamMoi = new JButton("Làm mới");

		// Đặt vị trí cho các nút
		btnThem.setBounds(250, 99, 100, 30);
		btnXoa.setBounds(360, 99, 100, 30);
		btnLamMoi.setBounds(470, 99, 100, 30);
		btnSua.setBounds(580, 99, 100, 30);

		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnLamMoi);
		contentPane.add(btnSua);

		// Khởi tạo DefaultTableModel với các cột
		String[] columnNames = { "Mã suất chiếu", "Mã Phim", "Mã PC", "Ngày chiếu", "Giờ chiếu", "Giờ kết thúc",
				"Định dạng", "Trạng thái" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		// table.getColumnModel().getColumn(2).setPreferredWidth(200); // Đặt giá trị
		// 300 làm ví dụ, bạn có thể điều chỉnh
		// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 300); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		// Thêm dữ liệu vào bảng
		//Load Data
		Socket socket = new Socket("192.168.2.10", 6789);
		clientXC= new ClientXuatChieu_dao(socket);
		listXC = clientXC.getListXC();
		loadDataToTable(listXC);

		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_QuanLy_SuatChieu.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);

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

	private void loadDataToTable(List<XuatChieu> listXC) {
		try {
			String ngayChieuTrongTable = "";

			for (XuatChieu xc : listXC) {

				String maXuat = xc.getMaXuat();
				LocalDate ngayChieu = xc.getNgayChieu();
				ngayChieuTrongTable = ngayChieu + "";
				String maPhim = xc.getPhim().getMaPhim();

				String maPhongChieu = xc.getPhongchieu().getMaPhongChieu();

				// String maPhim = ph.getMaPhim();
				Time GioChieu = xc.getGioChieu();
				Time GioKetThuc = xc.getGioKetThuc();
				String dinhDang = xc.getDinhDang();
				String trangThai = xc.getTrangThai();

				java.lang.Object[] rowData = { maXuat, maPhim, maPhongChieu, ngayChieu, GioChieu, GioKetThuc, dinhDang,
						trangThai };

				tableModel.addRow(rowData);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnXoa)) {
			try {
				int row = table.getSelectedRow();
				String maCanXoa = (String) table.getValueAt(row, 0);
				XuatChieu nv_needXoa = clientXC.findPhongChieuOnMaXuat(maCanXoa);
				clientXC.deleteXuatChieu(nv_needXoa);
				
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(this, " Xóa Thành Công!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTracuu)) {
			try {
				timKiem();
				JOptionPane.showMessageDialog(this, " Tìm thấy");
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		}else if(o.equals(btnTimtheoNgay)) {
			
			try {
				timKiem1();
				JOptionPane.showMessageDialog(this, " Tìm thấy");
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			}
				

	}

	public void timKiem() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		xoaBang();
		loadLaiDataSauKhiTimKiem();

	}
	
	public void timKiem1() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		xoaBang();
		loadLaiDataSauKhiTimKiem1();

	}
	public void loadLaiDataSauKhiTimKiem() throws ClassNotFoundException, IOException {

		// Xóa dữ liệu cũ trước khi load lại dữ liệu mới
		tableModel.setRowCount(0);

		// String maXCCanTim = txtmaXC.getText();
		String maXCCanTim = txtmaXC.getText();

		XuatChieu phNeedFind = clientXC.findPhongChieuOnMaXuat(maXCCanTim);
		
		if (phNeedFind != null) {
			String maXuat = phNeedFind.getMaXuat();
			LocalDate ngayChieu = phNeedFind.getNgayChieu();
			String ngayChieuTrongTable = ngayChieu + "";
			String maPhim = phNeedFind.getPhim().getMaPhim();
			String maPhongChieu = phNeedFind.getPhongchieu().getMaPhongChieu();
			Time gioChieu = phNeedFind.getGioChieu();
			Time gioKetThuc = phNeedFind.getGioKetThuc();
			String dinhDang = phNeedFind.getDinhDang();
			String trangThai = phNeedFind.getTrangThai();

			Object[] rowData = { maXuat, maPhim, maPhongChieu, ngayChieuTrongTable, gioChieu, gioKetThuc, dinhDang,
					trangThai };

			tableModel.addRow(rowData);

		} else {
			JOptionPane.showMessageDialog(this, "Không Tìm thấy");
		}
		

	}
	
	
	public void loadLaiDataSauKhiTimKiem1() {
	    try {
	        tableModel.setRowCount(0);
	        String ngayChieuStr = txtNgayHienThi.getText();
	        LocalDate ngayChieu = LocalDate.parse(ngayChieuStr);
	        XuatChieu phNeedFind = clientXC.findXuatChieuOnNgayChieu(ngayChieu);
	        if (phNeedFind != null) {
	            String maXuat = phNeedFind.getMaXuat();
	            LocalDate ngayChieuResult = phNeedFind.getNgayChieu();
	            String maPhim = phNeedFind.getPhim().getMaPhim();
	            String maPhongChieu = phNeedFind.getPhongchieu().getMaPhongChieu();
	            Time gioChieu = phNeedFind.getGioChieu();
	            Time gioKetThuc = phNeedFind.getGioKetThuc();
	            String dinhDang = phNeedFind.getDinhDang();
	            String trangThai = phNeedFind.getTrangThai();
	            Object[] rowData = { maXuat, maPhim, maPhongChieu, ngayChieuResult, gioChieu, gioKetThuc, dinhDang,
	                    trangThai };
	            tableModel.addRow(rowData);
	        } else {
	            JOptionPane.showMessageDialog(this, "Không Tìm thấy");
	        }
	    } catch (ClassNotFoundException | IOException e) {
	        e.printStackTrace();
	    }
	}


	private void xoaBang() {
		for (int j = 0; j < table.getRowCount(); j++) {
			tableModel.removeRow(j);
			j--;
		}

	}

	private void updateTable() {
		xoaBang();

		// Load lại dữ liệu từ cơ sở dữ liệu hoặc từ mô hình dữ liệu mới
		loadDataToTable(listXC);

		// Cập nhật bảng để hiển thị dữ liệu mới
		table.setModel(tableModel);
	}

	private void lamMoi() {
		txtmaXC.setText("");
		xoaBang();
		updateTable();
	}
	
	

}
