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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;
import com.toedter.calendar.JDateChooser;

import dao.NhanVien_dao;
import enities.KhachHang;
import enities.NhanVien;
import runapp.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;

public class GD_QuanLy_NhanVien extends JFrame implements ActionListener {
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
	private JButton btnThem, btnXoa, btnSua, btnLamMoi;
	private boolean isCalendarVisible = false;
	private JTextField txtSDT, txtDiaChi, txtEmail, txtNgaySinh, txtTimKiem, txtHoTen;
	private JDateChooser ngaySinhDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private NhanVien_dao nv_dao = new NhanVien_dao();
	private List<NhanVien> listNV;
	private JRadioButton rdbtnNam, rdbtnNu;
	private JComboBox<String> cboxChucVu,cboxTrangThai; // Declare the JComboBox here

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		GD_QuanLy_NhanVien run = new GD_QuanLy_NhanVien();
		run.setVisible(true);
	}

	public GD_QuanLy_NhanVien() {
		this.setLocationRelativeTo(null);
//		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Nhân Viên");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNvIcon = new JLabel("");
        lblNvIcon.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh của bạn
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
		qlyPhimButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/video-camera12.png")));
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
		btnqlyPhim.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/film-reel.png")));
		JButton btnSuatChieu = new JButton("Quản Lý Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/clapperboard2.png")));
		btnSuatChieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_SuatChieu gdqlsc = new GD_QuanLy_SuatChieu();
				gdqlsc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlsc.setLocationRelativeTo(null);
				gdqlsc.setVisible(true);
				dispose();
			}
		});
		JButton btnVe = new JButton("Quản Lý Vé Phim");
		btnVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVe.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/tickets2.png")));

		panelPhim.add(btnqlyPhim);
		panelPhim.add(btnSuatChieu);
		panelPhim.add(btnVe);

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
		dichVuButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/popcorn12.png")));
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
		btnqldv.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/popcorn2.png")));
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
		nhanVienButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/customer1.png")));
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
		panelNhanVien.setVisible(true); // tắt/ẩn panel
		panelNhanVien.setBackground(whiteColor);
		contentPane.add(panelNhanVien);

		JButton btnNhanVien = new JButton("Quản Lý Nhân Viên");
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhanVien.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/khachhang1.png")));
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
		taiKhoanButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/employee1.png")));
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
		btnTaiKhoan.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/av1.png")));
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
		thongKeButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/thongke1.png")));
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

		JButton btnThongKeDThu = new JButton("Thống Kê Doanh Thu");
		btnThongKeDThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDThu.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/thongke.png")));
		JButton btnThongKeVe = new JButton("Thống Kê Vé Bán");
		btnThongKeVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeVe.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/tickets2.png")));
		JButton btnThongKePhim = new JButton("Thống Kê Phim");
		btnThongKePhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKePhim.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/clapperboard2.png")));
		JButton btnThongKeDichVu = new JButton("Thống Kê Dịch Vụ");
		btnThongKeDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDichVu.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/popcorn2.png")));

		panelThongKe.add(btnThongKeDThu);
		panelThongKe.add(btnThongKeVe);
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
		logoutButton.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/imgs/logout.png")));
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

		JLabel lblQLKH = new JLabel("Quản Lý Nhân Viên");
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

		JLabel lbldiachi = new JLabel("Địa chỉ:");
		lbldiachi.setFont(new Font("Dialog", Font.PLAIN, 16));
		lbldiachi.setBounds(17, 346, 130, 21);
		contentPane.add(lbldiachi);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setOpaque(false);
		pnlDiaChi.setBackground(Color.YELLOW);
		pnlDiaChi.setBounds(10, 368, 230, 37);
		contentPane.add(pnlDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtDiaChi.setColumns(16);
		pnlDiaChi.add(txtDiaChi);

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
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblChucVu.setBounds(17, 520, 70, 21);
		contentPane.add(lblChucVu);
		
		cboxChucVu = new JComboBox();
		cboxChucVu.setBounds(97, 522, 100, 22);
		cboxChucVu.addItem("Nhân Viên");
		cboxChucVu.addItem("Quản Lý");
		contentPane.add(cboxChucVu);
		
		JLabel lblTrangThai = new JLabel("Tr.Thái:");
		lblTrangThai.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTrangThai.setBounds(17, 560, 70, 21);
		contentPane.add(lblTrangThai);
		
		cboxTrangThai = new JComboBox<>(new String[]{"Còn làm", "Ngưng làm"});
		cboxTrangThai.setBounds(97, 562, 100, 22);
		contentPane.add(cboxTrangThai);

		JButton btntimkiem = new JButton("");
		btntimkiem.setIcon(new ImageIcon(GD_QuanLy_NhanVien.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(1090, 99, 40, 30);
		contentPane.add(btntimkiem);

		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(this);

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
		String[] columnNames = { "Mã NV", "Tên NV", "Ngày sinh", "SĐT", "Địa chỉ", "Email", "ChucVu", "Giới tính",
				"Trạng thái"}; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(4).setPreferredWidth(50); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh theo ý muốn

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
		loadData();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		try {
			String ngaySinhTrongTable = "";
			String gioiTinhTrongTable = "";
			String trangThaiTrongTable = "";
			nv_dao.setUp();
			listNV = nv_dao.getListNV();
			for (NhanVien nv : listNV) {
				String maNV = nv.getMaNV();
				String tenNV = nv.getTenNV();
				String email = nv.getEmail();
				String diaChi = nv.getDiaChi();
				Date ngaySinh = nv.getNgaySinh();

				ngaySinhTrongTable = ngaySinh + "";
				boolean gioiTinh = nv.isGioiTinh();

				if (gioiTinh) {
					gioiTinhTrongTable = "Nam";
				} else {
					gioiTinhTrongTable = "Nữ";
				}
				
				boolean trangThai = nv.isTrangThai();
				if (trangThai) {
					trangThaiTrongTable = "Còn làm";
				} else {
					trangThaiTrongTable = "Ngưng làm";
				}
//				cboxTrangThai.addItem(trangThaiTrongTable);
				String SDT = nv.getSdt();
				
				String chucVu = nv.getChucVu();
				

				java.lang.Object[] rowData = {maNV, tenNV, ngaySinhTrongTable, SDT, diaChi, email, chucVu,
						gioiTinhTrongTable, trangThaiTrongTable};
				tableModel.addRow(rowData);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
//		GD_NhanVien gdnv = new GD_NhanVien();
//		gdnv.setLocationRelativeTo(null);
//		gdnv.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		java.lang.Object o = e.getSource();
		if(o.equals(btnThem)) {
			try {
				themKhachHang();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	private void themKhachHang() throws Exception {
		nv_dao.setUp();
		int idCust = 0;
		for (NhanVien nhanVien : nv_dao.getListNV()) {
			idCust++;
		}
		
		int idNewCust = idCust + 1;
		String maNV = "NV00" + idNewCust;
		String hoTenNhanVien = txtHoTen.getText();
		Date ngaySinh = ngaySinhDateChooser.getDate();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String email = txtEmail.getText();
		
		boolean gender = false;
		String gioiTinhTrongBang = "";
		if(rdbtnNam.isSelected()) {
			gender = true;
			gioiTinhTrongBang = "Nam";
		}else {
			gender = false;
			gioiTinhTrongBang = "Nữ";
		}
		
		
		
		String chucVu = cboxChucVu.getSelectedItem().toString();
		
		
		boolean statusQuit = false;
		String trangThai = cboxTrangThai.getSelectedItem().toString();
		if(!trangThai.trim().equals("Còn làm")) {
			statusQuit = true;
		}else {
			statusQuit = false;
		}
		
		
		
		NhanVien nv = new NhanVien(maNV, hoTenNhanVien, diaChi, ngaySinh, gender, email, sdt, chucVu, statusQuit);
		java.lang.Object [] rowData = {maNV , hoTenNhanVien , ngaySinh , sdt , diaChi , email , chucVu , gioiTinhTrongBang , trangThai};
		tableModel.addRow(rowData);
		nv_dao.addNV(nv);
		
	}
}
