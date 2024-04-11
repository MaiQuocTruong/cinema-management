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
import javax.persistence.EntityManager;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;
import com.toedter.calendar.JDateChooser;

import client_dao.ClientKhachHang_dao;
import enities.KhachHang;

import runapp.Login;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class GD_KhachHang extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelChonVe, panelThongKe, panelKhachHang;
	private JLabel lblClock, lbltennv;
	private Timer timer;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnThem, btnXoa, btnSua, btnLamMoi;
	private boolean isCalendarVisible = false;
	private JTextField txtSDT, txtCMND, txtEmail, txtNgaydky, txtTimKiem, txtHoTen;
	private JDateChooser ngaySinhDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private JTextField txtNgaySinh;
	
	private List<KhachHang> listKH;
	private JRadioButton rdbtnNam, rdbtnNu;
	private ClientKhachHang_dao clientKH;
//	static String quanly;

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			
			
		
		
			
			
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		GD_KhachHang run = new GD_KhachHang();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public GD_KhachHang() throws IOException, ClassNotFoundException {
		this.setLocationRelativeTo(null);
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Khách Hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh
																							// của
		// bạn
		lblNvIcon.setBounds(760, 5, 40, 40); // Điều chỉnh tọa độ và kích thước của ảnh
		contentPane.add(lblNvIcon);

		JLabel lblnhanvien = new JLabel("NV:");
		lblnhanvien.setForeground(Color.WHITE);
		lblnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnhanvien.setBounds(801, 0, 39, 50);
		lblnhanvien.setForeground(Color.WHITE);
		contentPane.add(lblnhanvien);

		lbltennv = new JLabel("Trương Đại Lộc");
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
		muaVeButton.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/tickets1.png")));
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
		panelChonVe.setVisible(false); // tắt ẩn panel
		panelChonVe.setBackground(whiteColor);
		contentPane.add(panelChonVe);

		JButton btnPhim = new JButton("Chọn Phim");
		btnPhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPhim.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/film-reel.png")));
		btnPhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_Phim gdmvphim = new GD_MuaVe_Phim();
				gdmvphim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdmvphim.setLocationRelativeTo(null);
				gdmvphim.setVisible(true);
				dispose();
			}
		});
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/chair.png")));
		btnGhe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_ChonGhe gdmGHE = new GD_MuaVe_ChonGhe();
				gdmGHE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdmGHE.setLocationRelativeTo(null);
				gdmGHE.setVisible(true);
				dispose();
			}
		});
		JButton btnThucAn = new JButton("Thức Ăn");
		btnThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThucAn.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/popcorn2.png")));
		btnThucAn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_ThucAn gdmvthan = new GD_MuaVe_ThucAn();
				gdmvthan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdmvthan.setLocationRelativeTo(null);
				gdmvthan.setVisible(true);
				dispose();
			}
		});
		JButton btnSuatChieu = new JButton("Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_SuatChieu gdSChieu = new GD_MuaVe_SuatChieu();
				gdSChieu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdSChieu.setLocationRelativeTo(null);
				gdSChieu.setVisible(true);
				dispose();
			}
		});
		btnSuatChieu.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/clapperboard2.png")));

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
		thongKeButton.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/thongke.png")));
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
		btnThongKeHoaDon.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/bill.png")));
		JButton btnThongKeThucAn = new JButton("Thống Kê Thức Ăn");
		btnThongKeThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeThucAn.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/popcorn2.png")));
		JButton btnThongKeVePhim = new JButton("Thống Kê Vé Phim");
		btnThongKeVePhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeVePhim.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/tickets2.png")));

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
		khachHangButton.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/customer1.png")));
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
		panelKhachHang.setVisible(true); // Ẩn panel thống kê ban đầu
		panelKhachHang.setBackground(whiteColor);
		contentPane.add(panelKhachHang);

		// Thêm button thống kê vào panel thống kê
		JButton btnQlyKH = new JButton("Quản Lý Khách Hàng");
		btnQlyKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQlyKH.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/khachhang1.png")));
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
		logoutButton.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblQLKH = new JLabel("Quản Lý Khách Hàng");
		lblQLKH.setFont(new Font("Open Sans", 1, 16));
		lblQLKH.setBounds(43, 102, 170, 20);
		contentPane.add(lblQLKH);

		JLabel lblHoTen = new JLabel("Nhập họ tên:");
		lblHoTen.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblHoTen.setBounds(17, 139, 130, 21);
		contentPane.add(lblHoTen);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setBackground(new Color(255, 255, 0));
		pnlHoTen.setBounds(10, 161, 230, 37);
		pnlHoTen.setOpaque(false);
		contentPane.add(pnlHoTen);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNgaySinh.setBounds(17, 208, 130, 21);
		contentPane.add(lblNgaySinh);

		JPanel pnlNgaySinh = new JPanel();
		pnlNgaySinh.setOpaque(false);
		pnlNgaySinh.setBackground(Color.YELLOW);
		pnlNgaySinh.setBounds(10, 230, 191, 37);
		contentPane.add(pnlNgaySinh);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtNgaySinh.setColumns(13);
		pnlNgaySinh.add(txtNgaySinh);

		ngaySinhDateChooser = new JDateChooser();
		ngaySinhDateChooser.setBounds(140, 237, 100, 29);
		ngaySinhDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
		contentPane.add(ngaySinhDateChooser);

		ngaySinhDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = ngaySinhDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtNgaySinh.setText(dateFormat.format(selectedDate));
			}
		});

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSDT.setBounds(17, 277, 130, 21);
		contentPane.add(lblSDT);

		JPanel pnlSDT = new JPanel();
		pnlSDT.setOpaque(false);
		pnlSDT.setBackground(Color.YELLOW);
		pnlSDT.setBounds(10, 299, 230, 37);
		contentPane.add(pnlSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSDT.setColumns(16);
		pnlSDT.add(txtSDT);

		JLabel lblcmnd = new JLabel("Số CMND:");
		lblcmnd.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblcmnd.setBounds(17, 346, 130, 21);
		contentPane.add(lblcmnd);

		JPanel pnlCMND = new JPanel();
		pnlCMND.setOpaque(false);
		pnlCMND.setBackground(Color.YELLOW);
		pnlCMND.setBounds(10, 368, 230, 37);
		contentPane.add(pnlCMND);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtCMND.setColumns(16);
		pnlCMND.add(txtCMND);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail.setBounds(17, 415, 130, 21);
		contentPane.add(lblEmail);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setOpaque(false);
		pnlEmail.setBackground(Color.YELLOW);
		pnlEmail.setBounds(10, 437, 230, 37);
		contentPane.add(pnlEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtEmail.setColumns(16);
		pnlEmail.add(txtEmail);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Dialog", Font.PLAIN, 16));
		rdbtnNam.setBounds(17, 485, 103, 21);
		contentPane.add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Dialog", Font.PLAIN, 16));
		rdbtnNu.setBounds(140, 485, 103, 21);
		contentPane.add(rdbtnNu);

		// Thêm chúng vào ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNam);
		buttonGroup.add(rdbtnNu);

		// Add JTextField below JCheckBox
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Open Sans", 0, 16));
		txtHoTen.setColumns(16); // You can adjust the column count based on your requirement
		pnlHoTen.add(txtHoTen);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTimKiem.setColumns(16);
		txtTimKiem.setBounds(871, 99, 214, 30);
		contentPane.add(txtTimKiem);

		JButton btntimkiem = new JButton("");
		btntimkiem.setIcon(new ImageIcon(GD_KhachHang.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(1090, 99, 40, 30);
		contentPane.add(btntimkiem);

		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnSua = new JButton("Sửa");
		btnLamMoi = new JButton("Làm mới");

		// Đặt vị trí cho các nút
		btnThem.setBounds(250, 99, 100, 30);
		btnXoa.setBounds(360, 99, 100, 30);
		btnSua.setBounds(470, 99, 100, 30);
		btnLamMoi.setBounds(580, 99, 100, 30);
		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnSua);
		contentPane.add(btnLamMoi);

		// Khởi tạo DefaultTableModel với các cột
		String[] columnNames = { "Mã KH", "Tên KH", "Ngày sinh", "SĐT", "Số CMND", "Email", "Loại KH", "Giới tính",
				"Điểm hiện có", "Điểm đã dùng" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		table.getColumnModel().getColumn(2).setPreferredWidth(50); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
																	// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		// Thêm dữ liệu vào bảng
//		Object[] rowData = { "KH00001", "Trương Đại Lộc", "06-11-2003", "0123456789", "012345678910", "locshadow@gmail.com",
//				"Nghèo", "Nam", "1000", "600" }; // Thay đổi dữ liệu tùy ý
//		tableModel.addRow(rowData);
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

//		Load Data
		Socket socket = new Socket("192.168.2.20", 6789);
		clientKH = new ClientKhachHang_dao(socket);
		
		listKH = clientKH.getListKH();
		loadDataToTable(listKH);
		
		

//		Add Su Kien
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		
//		KhachHang kh = clientKH.findCustomerOnPhoneNumber("123123");
//		System.out.println(kh);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
  				loadDataToTextFlied();
  			}
			
		});
		
		
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
		GD_NhanVien gdnv = new GD_NhanVien();
		gdnv.setLocationRelativeTo(null);
		gdnv.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		java.lang.Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				themKhachHang();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(o.equals(btnSua)) {
				try {
					updateKhachHang();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else if(o.equals(btnXoa)) {
			try {
				deleteKhachHang();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
	private void loadDataToTable(List<KhachHang> listKH) {
		try {
			String ngaySinhTrongTable = "";
			String gioiTinhTrongTable = "";
		
			for (KhachHang kh : listKH) {
				String maKH = kh.getMaKH();
				String tenKH = kh.getTenKH();
				String email = kh.getEmail();
				LocalDate ngaySinh = kh.getNgaySinh();

				ngaySinhTrongTable = ngaySinh + "";
				boolean gioiTinh = kh.isGioiTinh();

				if (gioiTinh) {
					gioiTinhTrongTable = "Nam";
				} else {
					gioiTinhTrongTable = "Nu";
				}

				String loaiKH = kh.getLoaiKH();
				String SDT = kh.getSdt();
				int diemHienCo = kh.getDiemHienCo();
				String diemHienCoTrongTable = String.valueOf(diemHienCo);

				String cmnd = kh.getCmnd();
				int diemDaDung = kh.getDiemDaDung();
				String diemDaDungTrongTable = String.valueOf(diemDaDung);

				java.lang.Object[] rowData = { maKH, tenKH, ngaySinhTrongTable, SDT, cmnd, email, loaiKH,
						gioiTinhTrongTable, diemHienCoTrongTable, diemDaDungTrongTable };
				tableModel.addRow(rowData);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void xoaTrangTF() {
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtCMND.setText("");
		txtEmail.setText("");
		rdbtnNam.setSelected(false);
		rdbtnNu.setSelected(false);
	}
	
	private void xoaBang() {
  		for (int j = 0; j < table.getRowCount(); j++) {
  			tableModel.removeRow(j);
  			j--;
  		}
  	}

	public void themKhachHang() throws Exception {
		int idCust = 0;
		for (KhachHang khachHang : clientKH.getListKH()) {
			idCust++;
		}
		int newIDKhachHang = idCust + 1;
		String maKH = "KH00" + newIDKhachHang;
		String tenKH = txtHoTen.getText();
		Date ngaySinhTrenGD = ngaySinhDateChooser.getDate();

		Instant instant = ngaySinhTrenGD.toInstant();
		
		LocalDate ngaySinh = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		
		String ngaySinhTrongTable = txtNgaySinh.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String loaiKH = "Thuong";

		boolean gender = true;
		if (rdbtnNu.isSelected()) {
			gender = false;
		} else if (rdbtnNam.isSelected()) {
			gender = true;
		}

		String cmnd = txtCMND.getText();

		String gioiTinhTrongTable = "";
		if (gender) {
			gioiTinhTrongTable = "Nam";
		} else {
			gioiTinhTrongTable = "Nu";
		}

		int diemHienCo = 0;
		int diemDaSuDung = 0;
		String diemHienCoTrongTable = "0";
		String diemDaSuDungTrongTable = "0";

		KhachHang kh = new KhachHang(maKH, tenKH, email, ngaySinh, gender, loaiKH, sdt, diemHienCo, cmnd, diemDaSuDung,0);
		
		clientKH.addKH(kh);
		
		java.lang.Object[] rowData = { maKH, tenKH, ngaySinhTrongTable, sdt, cmnd, email, loaiKH, gioiTinhTrongTable,
				diemHienCoTrongTable, diemDaSuDungTrongTable };

		tableModel.addRow(rowData);
		xoaTrangTF();

	}
	
	private void loadDataToTextFlied() {
		int row = table.getSelectedRow();
		if(row >= 0) {
			String tenKH = (String) table.getValueAt(row, 1);
			String ngaySinh = (String) table.getValueAt(row, 2);
			String sdt = (String) table.getValueAt(row, 3);
			String cmnd = (String) table.getValueAt(row, 4);
			String email = (String) table.getValueAt(row,5);
			String gioiTinh = (String) table.getValueAt(row, 7);
			
			txtHoTen.setText(tenKH);
			txtNgaySinh.setText(ngaySinh);
			txtSDT.setText(sdt);
			txtCMND.setText(cmnd);
			txtEmail.setText(email);
			
			if(gioiTinh.trim().equalsIgnoreCase("Nam")) {
				rdbtnNam.setSelected(true);
			}else {
				rdbtnNu.setSelected(true);
			}
			
		}
	}
	
	private void updateKhachHang() throws ClassNotFoundException, IOException {
		int row = table.getSelectedRow();
		String sdtCanTim = (String) table.getValueAt(row, 3);
		
		String tenKH = txtHoTen.getText();
		System.out.println(tenKH);
		Date ngaySinhTrenGD = ngaySinhDateChooser.getDate();
		
		
		String sdtMoi = txtSDT.getText();
		String cmnd = txtCMND.getText();
		String email = txtEmail.getText();
		
		LocalDate ngaySinh;
		
		// Định nghĩa định dạng của chuỗi ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if(ngaySinhTrenGD == null) {
			String ngaySinhGD = txtNgaySinh.getText();
			ngaySinh = LocalDate.parse(ngaySinhGD, dateFormatter);
		}else {
			Instant instant = ngaySinhTrenGD.toInstant();
			ngaySinh = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		}

		
		System.out.println(ngaySinh);
		
		KhachHang khNeedUpdate = clientKH.findCustomerOnPhoneNumber(sdtCanTim);
		khNeedUpdate.setTenKH(tenKH);
		khNeedUpdate.setNgaySinh(ngaySinh);
		khNeedUpdate.setSdt(sdtMoi);
		khNeedUpdate.setCmnd(cmnd);
		khNeedUpdate.setEmail(email);
		
		System.out.println(khNeedUpdate);
		
		clientKH.updateKhachHang(khNeedUpdate);
		
		
		
		List<KhachHang> listKHUpdate = clientKH.getListKH();
		xoaBang();
		loadDataToTable(listKHUpdate);
		xoaTrangTF();
	}
	
	public void deleteKhachHang() throws ClassNotFoundException, IOException {
		int row = table.getSelectedRow();
		String sdtCanTim = (String) table.getValueAt(row, 3);
		KhachHang kh_needRemove = clientKH.findCustomerOnPhoneNumber(sdtCanTim);
		clientKH.deleteKhachHang(kh_needRemove);
		tableModel.removeRow(row);
	}

}
