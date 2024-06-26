package giaoDien.quanly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;


import client_dao.ClientPhim_dao;
import enities.NhanVien;
import enities.Phim;
import enities.PhongChieuPhim;
import runapp.Login;
import testbutton.Buttontest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GD_QuanLy_Phim extends JFrame implements ActionListener {
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
	private JTextField txtDenNgay, txtTuNgay;
    private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
    private boolean daChonPhim = false; // Biến để kiểm tra xem đã chọn Phim hay chưa
	private JDateChooser tuNgayDateChooser, denNgayDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private boolean isCalendarVisible = false;
	private JButton btnThem, btnXoa, btnLamMoi, btnSua,btnTracuu,btnTracuungay;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtTenPhim;
	private List<Phim> listphim;
	private ClientPhim_dao clientphim;
	private JCheckBox chkTenPhim;
	//private GD_QuanLy_Phim_Sua sua;
//	static String quanly;
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws UnknownHostException 
	 */
	
	public JTable getTable() {
	    return table;
	}
	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_Phim.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		GD_QuanLy_Phim run = new GD_QuanLy_Phim();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public GD_QuanLy_Phim() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		initComponents();
		

		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Phim");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNvIcon = new JLabel("");
        lblNvIcon.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh của bạn
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
		panelPhim.setVisible(true); // tắt/ẩn panel
		panelPhim.setBackground(whiteColor);
		contentPane.add(panelPhim);

		JButton btnqlyPhim = new JButton("Quản Lý Phim");
		btnqlyPhim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyPhim.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/film-reel.png")));
		JButton btnSuatChieu = new JButton("Quản Lý Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/clapperboard2.png")));
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
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JButton btnqlyphongchieu = new JButton("Quản Lý Phòng Chiếu");
		btnqlyphongchieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyphongchieu.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/tickets2.png")));
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
				} catch (ClassNotFoundException | IOException e1) {
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
		dichVuButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/popcorn12.png")));
		dichVuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() ||panelTaiKhoan.isVisible() || panelNhanVien.isVisible() || panelDichVu.isVisible() || panelPhim.isVisible()) {
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
		btnqldv.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/popcorn2.png")));
		btnqldv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_DichVu gdqldv;
				gdqldv = new GD_QuanLy_DichVu();
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
		nhanVienButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/customer1.png")));
		nhanVienButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelThongKe.isVisible() || panelTaiKhoan.isVisible() || panelNhanVien.isVisible() || panelDichVu.isVisible() || panelPhim.isVisible()) {
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
		btnNhanVien.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/khachhang1.png")));
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
		taiKhoanButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/employee1.png")));
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
		btnTaiKhoan.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/av1.png")));
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
		thongKeButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/thongke1.png")));
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
		btnThongKePhim.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/clapperboard2.png")));
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
		JButton btnThongKeDichVu = new JButton("Thống Kê Dịch Vụ"); 
		btnThongKeDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDichVu.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/popcorn2.png")));
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
        logoutButton.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/logout.png")));
        logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
        
        
        
        
        
        
        JLabel lblTraCuuPhim = new JLabel("Tra cứu phim");
		lblTraCuuPhim.setFont(new Font("Open Sans", 1, 16));
		lblTraCuuPhim.setBounds(14, 106, 108, 20);
		contentPane.add(lblTraCuuPhim);

		JPanel pnlTenPhim = new JPanel();
		pnlTenPhim.setBackground(new Color(255, 255, 0));
		pnlTenPhim.setBounds(14, 140, 130, 30);
		pnlTenPhim.setOpaque(false);
		pnlTenPhim.setLayout(new BoxLayout(pnlTenPhim, BoxLayout.Y_AXIS)); // Use vertical BoxLayout
		contentPane.add(pnlTenPhim);
		 //chkTenPhim = new JCheckBox();
		btnTracuu=new JButton();
		btnTracuu.setFont(new Font("Open Sans", 0, 16)); // NOI18N
		btnTracuu.setSelected(true);
		btnTracuu.setText("Theo tên phim");
		btnTracuu.addActionListener(this);
//		chkTenPhim.setFont(new Font("Open Sans", 0, 16)); // NOI18N
//		chkTenPhim.setSelected(true);
//		chkTenPhim.setText("Theo tên phim");
//		chkTenPhim.addActionListener(this);
		pnlTenPhim.add(btnTracuu);

		JPanel pnlTheoTenPhim = new JPanel();
		pnlTheoTenPhim.setBackground(new Color(255, 255, 0));
		pnlTheoTenPhim.setBounds(10, 170, 172, 37);
		pnlTheoTenPhim.setOpaque(false);
		contentPane.add(pnlTheoTenPhim);
		// Add JTextField below JCheckBox
		 txtTenPhim = new JTextField();
		txtTenPhim.setFont(new Font("Open Sans", 0, 16));
		txtTenPhim.setColumns(12); // You can adjust the column count based on your requirement
		pnlTheoTenPhim.add(txtTenPhim);

		JPanel pnlNgayCongChieu = new JPanel();
		pnlNgayCongChieu.setBackground(new Color(255, 255, 0));
		pnlNgayCongChieu.setBounds(10, 217, 182, 40);
		pnlNgayCongChieu.setOpaque(false);
		contentPane.add(pnlNgayCongChieu);
		btnTracuungay=new JButton();
		btnTracuungay.setFont(new Font("Open Sans", 0, 16)); // NOI18N
		btnTracuungay.setSelected(true);
		btnTracuungay.setText("Theo ngày công chiếu");
//		JCheckBox chkNgayCongChieu = new JCheckBox();
//		chkNgayCongChieu.setFont(new Font("Open Sans", 0, 16));
//		chkNgayCongChieu.setText("Theo ngày công chiếu");
//		chkNgayCongChieu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
////                chkNgayCongChieuActionPerformed(evt);
//			}
//		});
		pnlNgayCongChieu.add(btnTracuungay);

		JPanel pnlTuNgay_1 = new JPanel();
		pnlTuNgay_1.setOpaque(false);
		pnlTuNgay_1.setBackground(Color.YELLOW);
		pnlTuNgay_1.setBounds(10, 313, 134, 37);
		contentPane.add(pnlTuNgay_1);

		txtTuNgay = new JTextField();
		txtTuNgay.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTuNgay.setColumns(9);
		pnlTuNgay_1.add(txtTuNgay);

		// Khởi tạo JDateChooser cho từ ngày
		tuNgayDateChooser = new JDateChooser();
		tuNgayDateChooser.setBounds(85, 320, 100, 29);
		tuNgayDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
		contentPane.add(tuNgayDateChooser);

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		tuNgayDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = tuNgayDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				txtTuNgay.setText(dateFormat.format(selectedDate));
			}
		});

		JPanel pnlTuNgay = new JPanel();
		pnlTuNgay.setOpaque(false);
		pnlTuNgay.setBackground(Color.YELLOW);
		pnlTuNgay.setBounds(10, 266, 182, 37);
		pnlTuNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlTuNgay);

		JLabel lbltungay = new JLabel("Từ ngày:");
		lbltungay.setFont(new Font("Open Sans", 0, 16));
		pnlTuNgay.add(lbltungay);

		JPanel pnlDenNgay = new JPanel();
		pnlDenNgay.setOpaque(false);
		pnlDenNgay.setBackground(Color.YELLOW);
		pnlDenNgay.setBounds(10, 383, 182, 37);
		pnlDenNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(pnlDenNgay);

		JLabel lbldenngay = new JLabel("Đến ngày:");
		lbldenngay.setFont(new Font("Open Sans", 0, 16));
		pnlDenNgay.add(lbldenngay);
		
		// Khởi tạo JDateChooser cho từ ngày
		denNgayDateChooser = new JDateChooser();
		denNgayDateChooser.setBounds(85, 437, 100, 29);
		denNgayDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);

		JPanel pnlDenNgay_1 = new JPanel();
		pnlDenNgay_1.setOpaque(false);
		pnlDenNgay_1.setBackground(Color.YELLOW);
		pnlDenNgay_1.setBounds(10, 430, 134, 37);
		contentPane.add(pnlDenNgay_1);

		txtDenNgay = new JTextField();
		txtDenNgay.setFont(new Font("Open Sans", 0, 16));
		txtDenNgay.setColumns(9);
		pnlDenNgay_1.add(txtDenNgay);
		contentPane.add(denNgayDateChooser);

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		denNgayDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = denNgayDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				txtDenNgay.setText(dateFormat.format(selectedDate));
			}
		});
		
		
		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnLamMoi = new JButton("Làm mới");
		btnSua = new JButton("Sửa");
		
		// Đặt vị trí cho các nút
		btnThem.setBounds(192, 99, 100, 30);
		btnXoa.setBounds(302, 99, 100, 30);
		btnLamMoi.setBounds(412, 99, 100, 30);
		btnSua.setBounds(522, 99, 100, 30);
		btnTracuungay.addActionListener(this);
		// Thêm sự kiện các nút
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLy_Phim_Them gdThemPhim;
				try {
					
					gdThemPhim = new GD_QuanLy_Phim_Them();
					gdThemPhim.setVisible(true);
					gdThemPhim.setLocationRelativeTo(null);
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        int row = table.getSelectedRow();
		        if (row >= 0) {
		            String tenPhim = (String) table.getValueAt(row, 1);
		            String loaiPhim = (String) table.getValueAt(row, 2);
		            String quocGia = (String) table.getValueAt(row, 10);
		            String ngonNgu = (String) table.getValueAt(row, 9);
		            String trangThaiPhim = (String) table.getValueAt(row, 11);
		            int thoiLuong = Integer.parseInt(table.getValueAt(row, 8).toString());
		            double giaTien = Double.parseDouble(table.getValueAt(row, 5).toString());
		            int soLuongVe = Integer.parseInt(table.getValueAt(row, 6).toString());
		            int gioiHanTuoi = Integer.parseInt(table.getValueAt(row, 7).toString());
		            String ngayChieu = (String) table.getValueAt(row, 3);
		            String ngayHetHan = (String) table.getValueAt(row, 4 );
		            Object value=table.getValueAt(row, 12);
		            // Tạo và hiển thị giao diện cập nhật
		            GD_QuanLy_Phim_Sua sua;
					try {
						sua = new GD_QuanLy_Phim_Sua();
						sua.updateFields(tenPhim, loaiPhim, quocGia, ngonNgu, trangThaiPhim,
	                            thoiLuong, giaTien, soLuongVe, gioiHanTuoi, ngayChieu, ngayHetHan,value);
	            sua.setVisible(true);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		        }
				
			}
		});
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTracuungay.addActionListener(this);
		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnLamMoi);
		contentPane.add(btnSua);
		// Khởi tạo DefaultTableModel với các cột
		String[] columnNames = {"Mã phim","Tên phim", "Loại phim","Ngày chiếu","Ngày hết hạn", "Giá tiền","SL vé", "Tuổi","Thời lượng","Ngôn ngữ",
				"Quốc gia", "Trạng thái","Hinh"}; // Thay đổi tên cột tùy ý
		
		tableModel = new DefaultTableModel(columnNames, 0);
		
		//"Quốc gia", "Giá tiền", "SL vé", "Trạng thái"

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		
		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(192, 140, 954, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);
//		Load Data
		Socket socket = new Socket("192.168.100.4", 6789);
		clientphim = new ClientPhim_dao(socket);
		
		
		
		listphim = clientphim.getListPhim();
		loadDataToTable(listphim);
		
		
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_QuanLy_Phim.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);
		
		
		


}
	public void timKiem() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		xoaBang();
		loadLaiDataSauKhiTimKiem();
		
	}         
			
	public void loadLaiDataSauKhiTimKiem() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String tenPhimCanTim = txtTenPhim.getText();
		Phim phNeedFind = clientphim.findFilmonTen(tenPhimCanTim);
		
		try {
			String ngayChieuTrongTable = "";
			String ngayHetTrongTable = "";
		
			
				String maPhim = phNeedFind.getMaPhim();
				
				String tenPhim =phNeedFind.getTenPhim();
				String loaiPhim =phNeedFind.getLoaiPhim();
				LocalDate ngayChieu = phNeedFind.getNgayChieu();
				ngayChieuTrongTable=ngayChieu+"";
				
				LocalDate ngayHetHan = phNeedFind.getNgayHetHan();
				ngayHetTrongTable=ngayHetHan+"";
				
				double giaTien=phNeedFind.getGiaTien();
				String giaTienTrongTable = String.valueOf(giaTien);
				
				int soLuongVe = phNeedFind.getSoLuongVe();
				String soLuongVeTrongTable = String.valueOf(soLuongVe);
				
				String hinhPhim =phNeedFind.getHinhPhim();
				
				int gioiHanTuoi = phNeedFind.getGioiHanTuoi();
				String gioiHanTuoiTrongTable = String.valueOf(gioiHanTuoi);
				
				int  thoiLuong= phNeedFind.getThoiLuong();
				String thoiLuongTrongTable = String.valueOf(thoiLuong);
				
				String  ngonNgu=phNeedFind.getNgonNgu();
				String quocGia =phNeedFind.getQuocGia();
				String trangThaiPhim =phNeedFind.getTrangThaiPhim();
				
				

				java.lang.Object[] rowData = {maPhim,tenPhim,loaiPhim,ngayChieuTrongTable,
				ngayHetTrongTable,giaTienTrongTable,soLuongVeTrongTable,gioiHanTuoiTrongTable,
						thoiLuongTrongTable,ngonNgu,quocGia,trangThaiPhim,hinhPhim};
				
				
				tableModel.addRow(rowData);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	

	
	private void loadDataToTable(List<Phim> listPhim) {
			
		try {
			String ngayChieuTrongTable = "";
			String ngayHetTrongTable = "";
		
			for (Phim ph : listPhim) {
				String maPhim = ph.getMaPhim();
				
				String tenPhim =ph.getTenPhim();
				String loaiPhim =ph.getLoaiPhim();
				LocalDate ngayChieu = ph.getNgayChieu();
				ngayChieuTrongTable=ngayChieu+"";
				
				LocalDate ngayHetHan = ph.getNgayHetHan();
				ngayHetTrongTable=ngayHetHan+"";
				
				double giaTien=ph.getGiaTien();
				String giaTienTrongTable = String.valueOf(giaTien);
				
				int soLuongVe = ph.getSoLuongVe();
				String soLuongVeTrongTable = String.valueOf(soLuongVe);
				
				String hinhPhim =ph.getHinhPhim();
				
				int gioiHanTuoi = ph.getGioiHanTuoi();
				String gioiHanTuoiTrongTable = String.valueOf(gioiHanTuoi);
				
				int  thoiLuong= ph.getThoiLuong();
				String thoiLuongTrongTable = String.valueOf(thoiLuong);
				
				String  ngonNgu=ph.getNgonNgu();
				String quocGia =ph.getQuocGia();
				String trangThaiPhim =ph.getTrangThaiPhim();
				
				

				java.lang.Object[] rowData = {maPhim,tenPhim,loaiPhim,ngayChieuTrongTable,
				ngayHetTrongTable,giaTienTrongTable,soLuongVeTrongTable,gioiHanTuoiTrongTable,
						thoiLuongTrongTable,ngonNgu,quocGia,trangThaiPhim,hinhPhim};
				
				
				tableModel.addRow(rowData);
			}
		} catch (Exception e1) {
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
		GD_QuanLy gdql = new GD_QuanLy();
		gdql.setLocationRelativeTo(null);
		gdql.setVisible(true);
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
	    loadDataToTable(listphim);

	    // Cập nhật bảng để hiển thị dữ liệu mới
	    table.setModel(tableModel);
	}
	
	private void lamMoi()  {
		txtTenPhim.setText("");
		xoaBang();
		updateTable();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		java.lang.Object o = e.getSource();
		 if (o.equals(btnTracuu)) {
			try {
				timKiem();
				JOptionPane.showMessageDialog(this, "Tim thay!");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		} else if (o.equals(btnLamMoi)) {
			try {
				lamMoi();;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			else if (o.equals(btnTracuungay)) {
				try {
					timKiem1();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}else if(o.equals(btnXoa)){
		
		try {
			int row=table.getSelectedRow();
			
			String maCanXoa=(String) table.getValueAt(row, 0);
			Phim ph_xoa=clientphim.findFilmonma(maCanXoa);
			clientphim.deletephim(ph_xoa);
			tableModel.removeRow(row);
			JOptionPane.showMessageDialog(btnXoa, "Xoa Thanh Cong!");
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		
		
	}
	
	}
	
	
public void timKiemPhimTheoKhoangNgay() throws ClassNotFoundException, IOException {
	    
		try {
			
		
		String tuNgayStr = txtTuNgay.getText();
	    String denNgayStr = txtDenNgay.getText();

	    // Chuyển đổi chuỗi ngày sang LocalDate
	    LocalDate tuNgay;
	    LocalDate denNgay;
	        tuNgay = LocalDate.parse(tuNgayStr);
	        denNgay = LocalDate.parse(denNgayStr);
	   

	    // Gọi hàm tìm kiếm phim theo khoảng ngày (giả sử là clientphim.findXuatChieuTrongKhoangNgay(tuNgay, denNgay))
	    List<Phim> danhSachPhim =  clientphim.findXuatChieuOnNgayChieu(tuNgay, denNgay);

	    System.out.println(danhSachPhim);
        // Hiển thị danh sách phim tìm được
        for (Phim phim : danhSachPhim) {
            String maPhim = phim.getMaPhim();
            String tenPhim = phim.getTenPhim();
            String loaiPhim = phim.getLoaiPhim();
            LocalDate ngayChieu = phim.getNgayChieu();
            String ngayChieuTrongTable = ngayChieu.toString();

            LocalDate ngayHetHan = phim.getNgayHetHan();
            String ngayHetTrongTable = ngayHetHan.toString();

            double giaTien = phim.getGiaTien();
            String giaTienTrongTable = String.valueOf(giaTien);

            int soLuongVe = phim.getSoLuongVe();
            String soLuongVeTrongTable = String.valueOf(soLuongVe);

            String hinhPhim = phim.getHinhPhim();

            int gioiHanTuoi = phim.getGioiHanTuoi();
            String gioiHanTuoiTrongTable = String.valueOf(gioiHanTuoi);

            int thoiLuong = phim.getThoiLuong();
            String thoiLuongTrongTable = String.valueOf(thoiLuong);

            String ngonNgu = phim.getNgonNgu();
            String quocGia = phim.getQuocGia();
            String trangThaiPhim = phim.getTrangThaiPhim();

            java.lang.Object[] rowData = { maPhim, tenPhim, loaiPhim, ngayChieuTrongTable,
                ngayHetTrongTable, giaTienTrongTable, soLuongVeTrongTable, gioiHanTuoiTrongTable,
                thoiLuongTrongTable, ngonNgu, quocGia, trangThaiPhim, hinhPhim };

            // Thêm một dòng dữ liệu mới vào table model
            tableModel.addRow(rowData);
            JOptionPane.showMessageDialog(null, "Tìm Thấy");
        }  
	} catch (Exception e2) {
		e2.printStackTrace();
	
	}
	}
	public void timKiem1() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		xoaBang();
		timKiemPhimTheoKhoangNgay();
		
	}


	
	
	
}
