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
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GD_MuaVe_Phim extends JFrame implements ActionListener {
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
	private JTextField txtTuNgay;
	private JTextField txtDenNgay;
	private JDateChooser tuNgayDateChooser, denNgayDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private boolean isCalendarVisible = false;

//	static String quanly;
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
			java.util.logging.Logger.getLogger(GD_MuaVe_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		GD_MuaVe_Phim run = new GD_MuaVe_Phim();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_MuaVe_Phim() {
//		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé - Phim");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Khởi tạo JDateChooser cho từ ngày
		denNgayDateChooser = new JDateChooser();
		denNgayDateChooser.setBounds(140, 437, 100, 29);
		denNgayDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);

		JPanel pnlDenNgay_1 = new JPanel();
		pnlDenNgay_1.setOpaque(false);
		pnlDenNgay_1.setBackground(Color.YELLOW);
		pnlDenNgay_1.setBounds(10, 430, 191, 37);
		contentPane.add(pnlDenNgay_1);

		txtDenNgay = new JTextField();
		txtDenNgay.setFont(new Font("Open Sans", 0, 16));
		txtDenNgay.setColumns(13);
		pnlDenNgay_1.add(txtDenNgay);
		contentPane.add(denNgayDateChooser);

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		denNgayDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = denNgayDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtDenNgay.setText(dateFormat.format(selectedDate));
			}
		});

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh
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
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/tickets1.png")));
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
		btnPhim.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/film-reel.png")));
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/chair.png")));
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
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/popcorn2.png")));
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
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/clapperboard2.png")));

		panelChonVe.add(btnPhim);
		panelChonVe.add(btnSuatChieu);
		panelChonVe.add(btnGhe);
		panelChonVe.add(btnThucAn);

		// Thêm toolbar "Thống kê"
		JToolBar hoaDonToolbar = new JToolBar();
		hoaDonToolbar.setFloatable(false);
		hoaDonToolbar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest hoaDonButton = new Buttontest();
		hoaDonButton.setText("Thống Kê");
		hoaDonButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		hoaDonButton.setForeground(SystemColor.text);
		hoaDonButton.setRippleColor(new Color(255, 255, 255));
		hoaDonButton.setBackground(new Color(46, 139, 87));
		hoaDonButton.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/thongke.png")));
		hoaDonToolbar.add(hoaDonButton);
		hoaDonToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(hoaDonToolbar);

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
	    hoaDonButton.addActionListener(new ActionListener() {
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
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/customer1.png")));
	    // Thêm sự kiện cho button Hóa Đơn để hiển thị/ẩn panel thống kê
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
		btnQlyKH.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/khachhang1.png")));
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
		logoutButton.setIcon(new ImageIcon(GD_MuaVe_Phim.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblTraCuuPhim = new JLabel("Tra cứu phim");
		lblTraCuuPhim.setFont(new Font("Open Sans", 1, 16));
		lblTraCuuPhim.setBounds(70, 102, 108, 20);
		contentPane.add(lblTraCuuPhim);

		JPanel pnlTenPhim = new JPanel();
		pnlTenPhim.setBackground(new Color(255, 255, 0));
		pnlTenPhim.setBounds(51, 140, 130, 30);
		pnlTenPhim.setOpaque(false);
		pnlTenPhim.setLayout(new BoxLayout(pnlTenPhim, BoxLayout.Y_AXIS)); // Use vertical BoxLayout
		contentPane.add(pnlTenPhim);
		JCheckBox chkTenPhim = new JCheckBox();
		chkTenPhim.setFont(new Font("Open Sans", 0, 16)); // NOI18N
		chkTenPhim.setSelected(true);
		chkTenPhim.setText("Theo tên phim");
		chkTenPhim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			}
		});
		pnlTenPhim.add(chkTenPhim);

		JPanel pnlTheoTenPhim = new JPanel();
		pnlTheoTenPhim.setBackground(new Color(255, 255, 0));
		pnlTheoTenPhim.setBounds(10, 170, 230, 37);
		pnlTheoTenPhim.setOpaque(false);
		contentPane.add(pnlTheoTenPhim);
		// Add JTextField below JCheckBox
		JTextField txtTenPhim = new JTextField();
		txtTenPhim.setFont(new Font("Open Sans", 0, 16));
		txtTenPhim.setColumns(16); // You can adjust the column count based on your requirement
		pnlTheoTenPhim.add(txtTenPhim);

		JPanel pnlNgayCongChieu = new JPanel();
		pnlNgayCongChieu.setBackground(new Color(255, 255, 0));
		pnlNgayCongChieu.setBounds(32, 217, 182, 40);
		pnlNgayCongChieu.setOpaque(false);
		contentPane.add(pnlNgayCongChieu);
		JCheckBox chkNgayCongChieu = new JCheckBox();
		chkNgayCongChieu.setFont(new Font("Open Sans", 0, 16));
		chkNgayCongChieu.setText("Theo ngày công chiếu");
		chkNgayCongChieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//                chkNgayCongChieuActionPerformed(evt);
			}
		});
		pnlNgayCongChieu.add(chkNgayCongChieu);

		JPanel pnlTuNgay_1 = new JPanel();
		pnlTuNgay_1.setOpaque(false);
		pnlTuNgay_1.setBackground(Color.YELLOW);
		pnlTuNgay_1.setBounds(10, 313, 191, 37);
		contentPane.add(pnlTuNgay_1);

		txtTuNgay = new JTextField();
		txtTuNgay.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTuNgay.setColumns(13);
		pnlTuNgay_1.add(txtTuNgay);

		// Khởi tạo JDateChooser cho từ ngày
		tuNgayDateChooser = new JDateChooser();
		tuNgayDateChooser.setBounds(140, 320, 100, 29);
		tuNgayDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
		contentPane.add(tuNgayDateChooser);

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		tuNgayDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = tuNgayDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtTuNgay.setText(dateFormat.format(selectedDate));
			}
		});

		JPanel pnlTuNgay = new JPanel();
		pnlTuNgay.setOpaque(false);
		pnlTuNgay.setBackground(Color.YELLOW);
		pnlTuNgay.setBounds(10, 266, 222, 37);
		pnlTuNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlTuNgay);

		JLabel lbltungay = new JLabel("Từ ngày:");
		lbltungay.setFont(new Font("Open Sans", 0, 16));
		pnlTuNgay.add(lbltungay);

		JPanel pnlDenNgay = new JPanel();
		pnlDenNgay.setOpaque(false);
		pnlDenNgay.setBackground(Color.YELLOW);
		pnlDenNgay.setBounds(10, 383, 230, 37);
		pnlDenNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlDenNgay);

		JLabel lbldenngay = new JLabel("Đến ngày:");
		lbldenngay.setFont(new Font("Open Sans", 0, 16));
		pnlDenNgay.add(lbldenngay);
		
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
		String[] columnNames = { "STT", "Mã phim", "Tên phim", "Thời lượng", "Giới hạn tuổi", "Ngày chiếu", "Ngôn ngữ",
				"Quốc gia", "Trạng thái" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		table.getColumnModel().getColumn(2).setPreferredWidth(230); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
																	// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		// Thêm dữ liệu vào bảng
		Object[] rowData = { "1", "PH00001", "Thám Tử Conan: Kẻ hành pháp Zero", "120", "13", "01-01-2018",
				"Tiếng Nhật", "Nhật Bản", "Đang Chiếu" }; // Thay đổi dữ liệu tùy ý
		tableModel.addRow(rowData);
		
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

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
	}
}
