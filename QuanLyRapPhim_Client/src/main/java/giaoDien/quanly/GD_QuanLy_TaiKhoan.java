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

import client_dao.ClientTaiKhoan_dao;
import enities.KhachHang;
import enities.NhanVien;
import enities.TaiKhoan;
import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;

public class GD_QuanLy_TaiKhoan extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelPhim, panelDichVu, panelNhanVien, panelTaiKhoan, panelThongKe;
	private JLabel lblClock, lbltennv;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnThem, btnXoa, btnSua, btnLamMoi, btntimkiem;
	private boolean isCalendarVisible = false;
	private JTextField txtMatKhau, txtTimKiem, txtEmail;
	private JComboBox<String> cboxTrangThai;
	private List<NhanVien> listNV;
	private List<TaiKhoan> listTK;
	private ClientTaiKhoan_dao clientTK;
	private int idCust = 0;
	boolean isNhanVienDaThem = false;

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_QuanLy_TaiKhoan run = new GD_QuanLy_TaiKhoan();
		run.setVisible(true);
	}

	public GD_QuanLy_TaiKhoan() throws ClassNotFoundException, IOException {
		this.setLocationRelativeTo(null);
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Tài Khoản");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn
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
		qlyPhimButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/video-camera12.png")));
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
		btnqlyPhim.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/film-reel.png")));
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
		btnSuatChieu.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/clapperboard2.png")));
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
		btnqlyphongchieu.setIcon(new ImageIcon(GD_QuanLy.class.getResource("/imgs/tickets2.png")));
		btnqlyphongchieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_PhongChieu gdqlpc = new GD_QuanLy_PhongChieu();
				gdqlpc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlpc.setLocationRelativeTo(null);
				gdqlpc.setVisible(true);
				dispose();
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
		dichVuButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/popcorn12.png")));
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
		btnqldv.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/popcorn2.png")));
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
		nhanVienButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/customer1.png")));
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
		btnNhanVien.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/khachhang1.png")));
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
		taiKhoanButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/employee1.png")));
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
		panelTaiKhoan.setVisible(true); // tắt/ẩn panel
		panelTaiKhoan.setBackground(whiteColor);
		contentPane.add(panelTaiKhoan);

		JButton btnTaiKhoan = new JButton("Quản Lý Tài Khoản");
		btnTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaiKhoan.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/av1.png")));
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
		thongKeButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/thongke1.png")));
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
		btnThongKePhim.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/clapperboard2.png")));
		JButton btnThongKeDichVu = new JButton("Thống Kê Dịch Vụ");
		btnThongKeDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDichVu.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/popcorn2.png")));
		btnThongKeDichVu.addActionListener(new ActionListener() {

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
		logoutButton.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/imgs/logout.png")));
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

		JLabel lblQLTK = new JLabel("Quản Lý Tài Khoản");
		lblQLTK.setFont(new Font("Open Sans", 1, 16));
		lblQLTK.setBounds(43, 102, 170, 20);
		contentPane.add(lblQLTK);

		JLabel lblEmail = new JLabel("Nhập Email:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail.setBounds(17, 139, 130, 21);
		contentPane.add(lblEmail);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setBackground(new Color(255, 255, 0));
		pnlEmail.setBounds(10, 161, 230, 37);
		pnlEmail.setOpaque(false);
		contentPane.add(pnlEmail);

		JLabel lblMatkhau = new JLabel("Nhập mật khẩu:");
		lblMatkhau.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMatkhau.setBounds(17, 208, 130, 21);
		contentPane.add(lblMatkhau);

		JPanel pnlMatKhau = new JPanel();
		pnlMatKhau.setOpaque(false);
		pnlMatKhau.setBackground(Color.YELLOW);
		pnlMatKhau.setBounds(10, 229, 230, 37);
		contentPane.add(pnlMatKhau);

		txtMatKhau = new JTextField();
		txtMatKhau.setEnabled(false);
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtMatKhau.setColumns(16);
		pnlMatKhau.add(txtMatKhau);

		// Thêm chúng vào ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();

		// Add JTextField below JCheckBox
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Open Sans", 0, 16));
		txtEmail.setColumns(16); // You can adjust the column count based on your requirement
		pnlEmail.add(txtEmail);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTimKiem.setColumns(16);
		txtTimKiem.setBounds(871, 99, 214, 30);
		contentPane.add(txtTimKiem);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTrangThai.setBounds(17, 276, 130, 21);
		contentPane.add(lblTrangThai);

		cboxTrangThai = new JComboBox<>(new String[] { "Còn làm", "Ngưng làm" });
		cboxTrangThai.setBounds(17, 313, 214, 30);
		contentPane.add(cboxTrangThai);

		btntimkiem = new JButton("");
		btntimkiem.setIcon(new ImageIcon(GD_QuanLy_TaiKhoan.class.getResource("/Imgs/search.png")));
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
		String[] columnNames = { "Mã nhân viên", "Mật khẩu", "Email", "Trạng thái" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_QuanLy.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

		// load dữ liệu
		Socket socket = new Socket("192.168.100.4", 6789);
		clientTK = new ClientTaiKhoan_dao(socket);
		
		listTK = clientTK.getListTK();
		loadDataToTable(listTK);
		
		//su kien cua cac button
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Kiểm tra xem đã có nhân viên được thêm vào chưa
		        if (!isNhanVienDaThem) {
		            // Nếu chưa có nhân viên được thêm vào, thông báo lỗi
		            JOptionPane.showMessageDialog(null, "Qua quản lý nhân viên để thêm nhân viên đồng thời thêm tài khoản!");
		            return;
		        }
		        // Nếu đã có nhân viên được thêm vào, thực hiện thêm tài khoản
		        // Your code to add account here
		    }
		});
		
		btnXoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Kiểm tra xem đã có nhân viên được thêm vào chưa
		        if (!isNhanVienDaThem) {
		            // Nếu chưa có nhân viên được thêm vào, thông báo lỗi
		            JOptionPane.showMessageDialog(null, "Qua quản lý nhân viên để xóa nhân viên đồng thời xóa tài khoản!");
		            return;
		        }
		        // Nếu đã có nhân viên được thêm vào, thực hiện xóa tài khoản
		        // Your code to delete account here
		    }
		});
		
		btnSua.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Kiểm tra xem đã có nhân viên được thêm vào chưa
		        if (!isNhanVienDaThem) {
		            // Nếu chưa có nhân viên được thêm vào, thông báo lỗi
		            JOptionPane.showMessageDialog(null, "Qua quản lý nhân viên để sửa nhân viên đồng thời sửa tài khoản!!");
		            return;
		        }
		        // Nếu đã có nhân viên được thêm vào, thực hiện sửa tài khoản
		        // Your code to edit account here
		    }
		});
		
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaTrangTF();
				xoaBang();
				try {
					List<TaiKhoan> listTKS = clientTK.getListTK();
					loadDataToTable(listTKS);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		btntimkiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					timKiem();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadDataToTextFlied();
			}
		});
	}

	private void timKiem() throws ClassNotFoundException, IOException{
		// TODO Auto-generated method stub
		xoaBang();
		loadLaiDataSauKhiTimKiem();
	}

	private void loadLaiDataSauKhiTimKiem() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String maNvCanTim = txtTimKiem.getText();
		TaiKhoan tkNeedFind = clientTK.findTKOnMaNV(maNvCanTim);
		if (tkNeedFind == null) {
	        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã " + maNvCanTim);
	        loadDataToTable(listTK);
		} else {
			try {
				String trangThaiTrongTable = "";
				String maNV = tkNeedFind.getMaNV();
				String matKhau = tkNeedFind.getMatkhau();
				String email = tkNeedFind.getEmail();
				boolean trangThai = tkNeedFind.isTrangThai();
				trangThaiTrongTable = trangThai + "";
				if (trangThai) {
					trangThaiTrongTable = "Còn làm";
				} else {
					trangThaiTrongTable = "Ngưng làm";
				}
				java.lang.Object[] rowData = { maNV, matKhau, email, trangThaiTrongTable };
				tableModel.addRow(rowData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private void xoaBang() {
		// TODO Auto-generated method stub
		for (int j = 0; j < table.getRowCount(); j++) {
			tableModel.removeRow(j);
			j--;
		}
	}

	private void xoaTrangTF() {
		// TODO Auto-generated method stub
		txtMatKhau.setText("");
		txtEmail.setText("");
		txtTimKiem.setText("");
		cboxTrangThai.setSelectedIndex(0);
	}

	private void loadDataToTextFlied() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row >= 0) {
			String matKhau = tableModel.getValueAt(row, 1).toString();
			String email = tableModel.getValueAt(row, 2).toString();
			String trangThai = tableModel.getValueAt(row, 3).toString();

			txtMatKhau.setText(matKhau);
			txtEmail.setText(email);
			if (trangThai.trim().equalsIgnoreCase("Còn làm")) {
				cboxTrangThai.setSelectedItem("Còn làm");
			} else {
				cboxTrangThai.setSelectedItem("Ngưng làm");
			}
		}
	}

	private void loadDataToTable(List<TaiKhoan> listTK) {
		// TODO Auto-generated method stub
		try {
			String trangThaiTrongTable = "";
			for (TaiKhoan tk : listTK) {
				String maNV = tk.getMaNV();
				String matKhau = tk.getMatkhau();
				String email = tk.getEmail();
				
				boolean trangThai = tk.isTrangThai();
				trangThaiTrongTable = trangThai + "";
				if (trangThai) {
					trangThaiTrongTable = "Còn làm";
				} else {
					trangThaiTrongTable = "Ngưng làm";
				}
				java.lang.Object[] rowData = { maNV, matKhau, email, trangThaiTrongTable };
				tableModel.addRow(rowData);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		GD_QuanLy gdql = new GD_QuanLy();
		gdql.setLocationRelativeTo(null);
		gdql.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
