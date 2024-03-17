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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.DichVuAnUong_dao;
import enities.DichVuAnUong;
import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class GD_MuaVe_ThucAn extends JFrame implements ActionListener {
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
	private DefaultTableModel tableModel1, tableModel2;
	private JButton btnXacNhan, btnHuyBo, btnLamMoi, btnChonKichCo;
	private JTextField txtTen;
	private boolean isCalendarVisible = false;
	private JComboBox<String> loaiDoAnComboBox; // Declare the JComboBox here

	private DichVuAnUong_dao dichVuAnUong_dao = new DichVuAnUong_dao();
	private List<DichVuAnUong> listServices = new ArrayList<DichVuAnUong>();

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
			java.util.logging.Logger.getLogger(GD_MuaVe_ThucAn.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ThucAn.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ThucAn.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ThucAn.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_MuaVe_ThucAn run = new GD_MuaVe_ThucAn();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_MuaVe_ThucAn() {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé - Thức Ăn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn

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
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/tickets1.png")));
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
		btnPhim.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/film-reel.png")));
		btnPhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_Phim gdMuaVe = new GD_MuaVe_Phim();
				gdMuaVe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdMuaVe.setLocationRelativeTo(null);
				gdMuaVe.setVisible(true);
				dispose();
			}
		});
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/chair.png")));
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
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/popcorn2.png")));
		JButton btnSuatChieu = new JButton("Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/clapperboard2.png")));
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

		panelChonVe.add(btnPhim);
		panelChonVe.add(btnSuatChieu);
		panelChonVe.add(btnGhe);
		panelChonVe.add(btnThucAn);

		// Thêm toolbar "Thống kê"
		JToolBar thongKeToolbar = new JToolBar();
		thongKeToolbar.setFloatable(false);
		thongKeToolbar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest thongKeButton = new Buttontest();
		thongKeButton.setText("Thống Kê");
		thongKeButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		thongKeButton.setForeground(SystemColor.text);
		thongKeButton.setRippleColor(new Color(255, 255, 255));
		thongKeButton.setBackground(new Color(46, 139, 87));
		thongKeButton.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/bill.png")));
		thongKeToolbar.add(thongKeButton);
		thongKeToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(thongKeToolbar);

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
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/customer1.png")));
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
				GD_KhachHang gdkh = new GD_KhachHang();
				gdkh.setVisible(true);
				gdkh.setLocationRelativeTo(null);
				dispose();
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
		logoutButton.setIcon(new ImageIcon(GD_MuaVe_ThucAn.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblTraCuuDoAn = new JLabel("Tra cứu đồ ăn");
		lblTraCuuDoAn.setFont(new Font("Open Sans", 1, 16));
		lblTraCuuDoAn.setBounds(68, 102, 113, 20);
		contentPane.add(lblTraCuuDoAn);

		JPanel pnlTheoTen = new JPanel();
		pnlTheoTen.setOpaque(false);
		pnlTheoTen.setBackground(Color.YELLOW);
		pnlTheoTen.setBounds(10, 187, 230, 37);
		contentPane.add(pnlTheoTen);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Open Sans", 0, 16));
		txtTen.setColumns(16);
		pnlTheoTen.add(txtTen);

		JPanel pnlTen = new JPanel();
		pnlTen.setOpaque(false);
		pnlTen.setBackground(Color.YELLOW);
		pnlTen.setBounds(10, 140, 230, 37);
		pnlTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlTen);

		JLabel lblTen = new JLabel("Theo tên:");
		lblTen.setFont(new Font("Open Sans", 0, 16));
		pnlTen.add(lblTen);

		String[] phongChieuList = { "Phòng 1", "Phòng 2", "Phòng 3", "Phòng 4" };

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setOpaque(false);
		pnlTrangThai.setBackground(Color.YELLOW);
		pnlTrangThai.setBounds(10, 245, 230, 37);
		contentPane.add(pnlTrangThai);
		pnlTrangThai.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblTrangThai = new JLabel("Theo trạng thái:");
		lblTrangThai.setFont(new Font("Open Sans", 0, 16));
		pnlTrangThai.add(lblTrangThai);

		JRadioButton rdbtnDangBan = new JRadioButton("Đang được bán");
		rdbtnDangBan.setFont(new Font("Open Sans", 0, 16));
		rdbtnDangBan.setBounds(14, 284, 180, 21);
		contentPane.add(rdbtnDangBan);

		JRadioButton rdbtnNgungBan = new JRadioButton("Đang ngưng bán");
		rdbtnNgungBan.setFont(new Font("Open Sans", 0, 16));
		rdbtnNgungBan.setBounds(14, 314, 180, 21);
		contentPane.add(rdbtnNgungBan);

		// Thêm chúng vào ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnDangBan);
		buttonGroup.add(rdbtnNgungBan);

		JPanel pnlLoaiDoAn = new JPanel();
		pnlLoaiDoAn.setOpaque(false);
		pnlLoaiDoAn.setBackground(Color.YELLOW);
		pnlLoaiDoAn.setBounds(10, 389, 230, 67);
		contentPane.add(pnlLoaiDoAn);
		pnlLoaiDoAn.setLayout(null);

		JLabel lblLoaiDoAn = new JLabel("Loai dich vu:");
		lblLoaiDoAn.setBounds(5, 5, 92, 21);
		lblLoaiDoAn.setFont(new Font("Dialog", Font.PLAIN, 16));
		pnlLoaiDoAn.add(lblLoaiDoAn);

//		String[] loaiDoAn = { "Nước uống", "Đồ ăn nhanh", "Bánh kẹo" };
		loaiDoAnComboBox = new JComboBox<>();
		loaiDoAnComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loaiDoAnComboBox.setBounds(94, 5, 126, 25);
		pnlLoaiDoAn.add(loaiDoAnComboBox);

		// Khởi tạo các nút
		btnXacNhan = new JButton("Xác nhận");
		btnHuyBo = new JButton("Hủy bỏ");
		btnLamMoi = new JButton("Làm mới");
		btnChonKichCo = new JButton("Chọn kích cỡ");

		// Đặt vị trí cho các nút
		btnXacNhan.setBounds(250, 99, 100, 30);
		btnHuyBo.setBounds(360, 99, 100, 30);
		btnLamMoi.setBounds(470, 99, 100, 30);
		btnChonKichCo.setBounds(1050, 99, 100, 30);

		// Thêm các nút vào contentPane
		contentPane.add(btnXacNhan);
		contentPane.add(btnHuyBo);
		contentPane.add(btnLamMoi);
		contentPane.add(btnChonKichCo);

		// Khai báo các cột cho bảng đầu tiên
		String[] columnNames1 = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Trạng thái", "Loại dịch vụ" };

		// Khởi tạo DefaultTableModel cho bảng đầu tiên
		tableModel1 = new DefaultTableModel(columnNames1, 0);

		// Khởi tạo JTable cho bảng đầu tiên
		JTable table1 = new JTable(tableModel1);

		// Đặt chiều rộng cho các cột nếu cần
		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(2).setPreferredWidth(200);

		// Khai báo các cột cho bảng thứ hai
		String[] columnNames2 = { "Kích cỡ", "Đơn giá", "Trạng thái" };

		// Khởi tạo DefaultTableModel cho bảng thứ hai
		DefaultTableModel tableModel2 = new DefaultTableModel(columnNames2, 0);

		// Khởi tạo JTable cho bảng thứ hai
		JTable table2 = new JTable(tableModel2);
		
		// Thêm sự kiện lắng nghe khi nhấn phím enter trên bảng
		table2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		table2.getActionMap().put("Enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int selectedRow = table2.getSelectedRow();
				if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
					if (selectedRow == JOptionPane.OK_OPTION) {
						// Xử lý khi người dùng ấn OK
						String quantityString = JOptionPane.showInputDialog(null, "Nhập số lượng:");
						// Chuyển đổi chuỗi số lượng thành số nguyên
						try {
							int quantity = Integer.parseInt(quantityString);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ!", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi nhấn enter!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Đặt vị trí và kích thước cho bảng thứ nhất
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(250, 140, 600, 469);
		contentPane.add(scrollPane1);

		// Đặt vị trí và kích thước cho bảng thứ hai
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(850, 140, 300, 469);
		contentPane.add(scrollPane2);

		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

//		Load Du Lieu Len Table
		dichVuAnUong_dao.setUp();
		try {
			listServices = dichVuAnUong_dao.getServices();
			int i = 0;
			for (DichVuAnUong dichVuAnUong : listServices) {
				String STT = i + 1 + "";
				String maDichVu = dichVuAnUong.getMaDichVu();
				String tenDichVu = dichVuAnUong.getTenDichVu();
				String trangThai = dichVuAnUong.getTrangThai();
				String loaiDichVu = dichVuAnUong.getLoaiDichVu();

				Object[] rowDataTable1 = { STT, maDichVu, tenDichVu, trangThai, loaiDichVu };
				tableModel1.addRow(rowDataTable1);

				String kichCo = dichVuAnUong.getKichThuoc();
				String donGia = String.valueOf(dichVuAnUong.getDonGia());
				String trangThaiSize = dichVuAnUong.getTrangThaiSize();
				System.out.println(donGia);

				loaiDoAnComboBox.addItem(dichVuAnUong.getLoaiDichVu());

				Object[] rowDataTable2 = { kichCo, donGia, trangThaiSize };
				tableModel2.addRow(rowDataTable2);
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		GD_MuaVe_ChonGhe gdmvcghe = new GD_MuaVe_ChonGhe();
		gdmvcghe.setLocationRelativeTo(null);
		gdmvcghe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
