package giaoDien.quanly;

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
import java.net.URL;
import java.net.UnknownHostException;
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


import enities.DichVuAnUong;
import enities.GiaDichVu;
import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class GD_QuanLy_DichVu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelPhim, panelDichVu, panelNhanVien, panelTaiKhoan, panelThongKe;
	private JLabel lbltennv, lblNvIcon;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JTable table1, table2;
	private DefaultTableModel tableModel1, tableModel2;
	private JButton btnThem, btnXoa, btnLamMoi, btnChonKichCo, btnSua;
	private JTextField txtTen;
	private boolean isCalendarVisible = false;
	private JComboBox<String> loaiDoAnComboBox; // Declare the JComboBox here

	private List<GiaDichVu> listGia = new ArrayList<GiaDichVu>();

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
			java.util.logging.Logger.getLogger(GD_QuanLy_DichVu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_DichVu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_DichVu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_DichVu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_QuanLy_DichVu run = new GD_QuanLy_DichVu();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_QuanLy_DichVu() {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Dịch Vụ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		lblNvIcon = new JLabel("");
        lblNvIcon.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh của bạn
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
		qlyPhimButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/video-camera12.png")));
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
		panelPhim.setVisible(false); // tắt/ẩn panel
		panelPhim.setBackground(whiteColor);
		contentPane.add(panelPhim);

		JButton btnqlyPhim = new JButton("Quản Lý Phim");
		btnqlyPhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyPhim.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/film-reel.png")));
		btnqlyPhim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_Phim gdqlphim = new GD_QuanLy_Phim();
				gdqlphim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlphim.setLocationRelativeTo(null);
				gdqlphim.setVisible(true);
				dispose();
			}
		});
		JButton btnSuatChieu = new JButton("Quản Lý Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/clapperboard2.png")));
		btnSuatChieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_SuatChieu gdqlsc;
				try {
					gdqlsc = new GD_QuanLy_SuatChieu();
					gdqlsc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdqlsc.setLocationRelativeTo(null);
					gdqlsc.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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
		dichVuButton.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/popcorn12.png")));
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
		panelDichVu.setVisible(true); // tắt/ẩn panel
		panelDichVu.setBackground(whiteColor);
		contentPane.add(panelDichVu);

		JButton btnDoAn = new JButton("Quản Lý Dịch Vụ");
		btnDoAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDoAn.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/popcorn2.png")));
		panelDichVu.add(btnDoAn);

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
		nhanVienButton.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/customer1.png")));
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
		btnNhanVien.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/khachhang1.png")));
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
		taiKhoanButton.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/employee1.png")));
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
		btnTaiKhoan.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/av1.png")));
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
		thongKeButton.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/thongke1.png")));
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
				GD_QuanLy_ThongKePhim gdqlthongkephim = new GD_QuanLy_ThongKePhim();
				gdqlthongkephim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlthongkephim.setLocationRelativeTo(null);
				gdqlthongkephim.setVisible(true);
				dispose();
			
			}
			});
		btnThongKePhim.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/clapperboard2.png")));
		JButton btnThongKeThucAn = new JButton("Thống Kê Dịch Vụ");
		btnThongKeThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeThucAn.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/popcorn2.png")));
		btnThongKeThucAn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_ThongKeDichVu gdqlthongDVu = new GD_QuanLy_ThongKeDichVu();
				gdqlthongDVu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlthongDVu.setLocationRelativeTo(null);
				gdqlthongDVu.setVisible(true);
				dispose();
			
			}
			});

		panelThongKe.add(btnThongKePhim);
		panelThongKe.add(btnThongKeThucAn);

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
		logoutButton.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/logout.png")));
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
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnLamMoi = new JButton("Làm mới");
		btnSua = new JButton("Sửa");
		btnChonKichCo = new JButton("Chọn kích cỡ");

		// Đặt vị trí cho các nút
		btnThem.setBounds(250, 99, 100, 30);
		btnXoa.setBounds(360, 99, 100, 30);
		btnLamMoi.setBounds(470, 99, 100, 30);
		btnSua.setBounds(580, 99, 100, 30);
		btnChonKichCo.setBounds(1050, 99, 100, 30);
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_ThemDichVu gdThemDV = new GD_QuanLy_ThemDichVu();
				gdThemDV.setVisible(true);
				gdThemDV.setLocationRelativeTo(null);
				dispose();
			}
		});

		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnLamMoi);
		contentPane.add(btnSua);
		contentPane.add(btnChonKichCo);

		// Khai báo các cột cho bảng đầu tiên
		String[] columnNames1 = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Trạng thái", "Loại dịch vụ" };

		// Khởi tạo DefaultTableModel cho bảng đầu tiên
		tableModel1 = new DefaultTableModel(columnNames1, 0);

		// Khởi tạo JTable cho bảng đầu tiên
		table1 = new JTable(tableModel1);

		// Đặt chiều rộng cho các cột nếu cần
		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(2).setPreferredWidth(200);
	
		// Khai báo các cột cho bảng thứ hai
		String[] columnNames2 = { "Kích cỡ", "Đơn giá", "Trạng thái" };

		// Khởi tạo DefaultTableModel cho bảng thứ hai
		tableModel2 = new DefaultTableModel(columnNames2, 0);

		// Khởi tạo JTable cho bảng thứ hai
		table2 = new JTable(tableModel2);

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
		background.setIcon(new ImageIcon(GD_QuanLy_DichVu.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

//		Load Du Lieu Len Table
	

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

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
