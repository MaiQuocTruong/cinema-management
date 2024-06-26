package giaoDien.nhanvien;

import testbutton.Buttontest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import client_dao.ClientXuatChieu_dao;
import enities.KhachHang;
import enities.XuatChieu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GD_MuaVe_ChonGhe extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelGhe;
	private JLabel lblClock, lbltennv;
	private Timer timer;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private JPanel panelChonVe, panelThongKe, panelKhachHang;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	Color blackColor = Color.BLACK;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private DefaultTableModel tableModel;
	private JButton btnXacNhan, btnHuyBo, btnLamMoi;
	private boolean isCalendarVisible = false;
	private boolean clickSelect = true;
	private ClientXuatChieu_dao client_xc;
	private Set<String> maGhe = new HashSet<>();

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
			java.util.logging.Logger.getLogger(GD_MuaVe_ChonGhe.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ChonGhe.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ChonGhe.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_ChonGhe.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public GD_MuaVe_ChonGhe(String maHD ,String maXC , int soLuongVe , String sdtkh , String maNhanVien , String tenNhanVien) throws UnknownHostException, IOException {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé - Chọn Ghế");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setVisible(true);
		
		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh
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
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/tickets1.png")));
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
		btnPhim.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/film-reel.png")));
		btnPhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_Phim gdmvphim;
				try {
					gdmvphim = new GD_MuaVe_Phim(maNhanVien , tenNhanVien);
					gdmvphim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gdmvphim.setLocationRelativeTo(null);
					gdmvphim.setVisible(true);
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
		btnGhe.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/chair.png")));
		JButton btnThucAn = new JButton("Thức Ăn");
		btnThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/popcorn2.png")));
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
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/clapperboard2.png")));

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
		thongKeButton.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/bill.png")));
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
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/customer1.png")));
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
		logoutButton.setIcon(new ImageIcon(GD_MuaVe_ChonGhe.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		panelGhe = new JPanel();
		panelGhe.setBackground(Color.WHITE);
		panelGhe.setBounds(10, 160, 60, 60);
		panelGhe.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe.setLayout(new BorderLayout());
		// Thêm sự kiện khi nhấp chuột vào panelGhe
		
		JLabel labelA01 = new JLabel("A01");
		labelA01.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA01.setForeground(Color.black); // Màu chữ trắng
		labelA01.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA01.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe.add(labelA01, BorderLayout.CENTER);
		
		panelGhe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA01.getText());
					panelGhe.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe.setBackground(Color.WHITE);
					maGhe.remove(labelA01.getText());
				}
				
				System.out.println(maGhe);
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe);

		JPanel panelGhe1 = new JPanel();
		panelGhe1.setBackground(Color.WHITE);
		panelGhe1.setBounds(90, 160, 60, 60);
		panelGhe1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe1.setLayout(new BorderLayout());
		
		JLabel labelA02 = new JLabel("A02");
		labelA02.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA02.setForeground(Color.black); // Màu chữ trắng
		labelA02.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA02.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe1.add(labelA02, BorderLayout.CENTER);
		panelGhe1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA02.getText());
					panelGhe1.setBackground(new Color(0, 128, 255));
				} else {
					
					panelGhe1.setBackground(Color.WHITE);
					maGhe.remove(labelA02.getText());
				}
				System.out.println(maGhe);
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe1);

		JPanel panelGhe2 = new JPanel();
		panelGhe2.setBackground(Color.WHITE);
		panelGhe2.setBounds(160, 160, 60, 60);
		panelGhe2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe2.setLayout(new BorderLayout());
		
		JLabel labelA03 = new JLabel("A03");
		labelA03.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA03.setForeground(Color.black); // Màu chữ trắng
		labelA03.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA03.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe2.add(labelA03, BorderLayout.CENTER);
		panelGhe2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA03.getText());
					panelGhe2.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe2.setBackground(Color.WHITE);
					maGhe.remove(labelA03.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe2);

		JPanel panelGhe3 = new JPanel();
		panelGhe3.setBackground(Color.WHITE);
		panelGhe3.setBounds(230, 160, 60, 60);
		panelGhe3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe3.setLayout(new BorderLayout());
		
		JLabel labelA04 = new JLabel("A04");
		labelA04.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA04.setForeground(Color.black); // Màu chữ trắng
		labelA04.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA04.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe3.add(labelA04, BorderLayout.CENTER);
		panelGhe3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA04.getText());
					panelGhe3.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe3.setBackground(Color.WHITE);
					maGhe.remove(labelA04.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe3);

		JPanel panelGhe4 = new JPanel();
		panelGhe4.setBackground(Color.WHITE);
		panelGhe4.setBounds(300, 160, 60, 60);
		panelGhe4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe4.setLayout(new BorderLayout());
		
		JLabel labelA05 = new JLabel("A05");
		labelA05.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA05.setForeground(Color.black); // Màu chữ trắng
		labelA05.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA05.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe4.add(labelA05, BorderLayout.CENTER);
		panelGhe4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					panelGhe4.setBackground(new Color(0, 128, 255));
					maGhe.add(labelA05.getText());
				} else {
					panelGhe4.setBackground(Color.WHITE);
					maGhe.remove(labelA05.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe4);

		JPanel panelGhe5 = new JPanel();
		panelGhe5.setBackground(Color.WHITE);
		panelGhe5.setBounds(370, 160, 60, 60);
		panelGhe5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe5.setLayout(new BorderLayout());
		
		JLabel labelA06 = new JLabel("A06");
		labelA06.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA06.setForeground(Color.black); // Màu chữ trắng
		labelA06.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA06.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe5.add(labelA06, BorderLayout.CENTER);
		panelGhe5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA06.getText());
					panelGhe5.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe5.setBackground(Color.WHITE);
					maGhe.remove(labelA06.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe5);

		JPanel panelGhe6 = new JPanel();
		panelGhe6.setBackground(Color.WHITE);
		panelGhe6.setBounds(440, 160, 60, 60);
		panelGhe6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe6.setLayout(new BorderLayout());
		
		JLabel labelA07 = new JLabel("A07");
		labelA07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA07.setForeground(Color.black); // Màu chữ trắng
		labelA07.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA07.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe6.add(labelA07, BorderLayout.CENTER);
		
		panelGhe6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA07.getText());
					panelGhe6.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe6.setBackground(Color.WHITE);
					maGhe.remove(labelA07.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe6);

		JPanel panelGhe7 = new JPanel();
		panelGhe7.setBackground(Color.WHITE);
		panelGhe7.setBounds(510, 160, 60, 60);
		panelGhe7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe7.setLayout(new BorderLayout());
		
		JLabel labelA08 = new JLabel("A08");
		labelA08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA08.setForeground(Color.black); // Màu chữ trắng
		labelA08.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA08.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe7.add(labelA08, BorderLayout.CENTER);
		panelGhe7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA08.getText());
					panelGhe7.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe7.setBackground(Color.WHITE);
					maGhe.remove(labelA08.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe7);

		JPanel panelGhe8 = new JPanel();
		panelGhe8.setBackground(Color.WHITE);
		panelGhe8.setBounds(580, 160, 60, 60);
		panelGhe8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe8.setLayout(new BorderLayout());
		
		JLabel labelA09 = new JLabel("A09");
		labelA09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA09.setForeground(Color.black); // Màu chữ trắng
		labelA09.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA09.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe8.add(labelA09, BorderLayout.CENTER);
		panelGhe8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA09.getText());
					panelGhe8.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe8.setBackground(Color.WHITE);
					maGhe.remove(labelA09.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe8);

		JPanel panelGhe9 = new JPanel();
		panelGhe9.setBackground(Color.WHITE);
		panelGhe9.setBounds(650, 160, 60, 60);
		panelGhe9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panelGhe9.setLayout(new BorderLayout());
		JLabel labelA10 = new JLabel("A10");
		labelA10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA10.setForeground(Color.black); // Màu chữ trắng
		labelA10.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA10.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe9.add(labelA10, BorderLayout.CENTER);
		panelGhe9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA10.getText());
					panelGhe9.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe9.setBackground(Color.WHITE);
					maGhe.remove(labelA10.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe9);

		JPanel panelGhe10 = new JPanel();
		panelGhe10.setBackground(Color.WHITE);
		panelGhe10.setBounds(720, 160, 60, 60);
		panelGhe10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe10.setLayout(new BorderLayout());
		
		JLabel labelA11 = new JLabel("A11");
		labelA11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA11.setForeground(Color.black); // Màu chữ trắng
		labelA11.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA11.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe10.add(labelA11, BorderLayout.CENTER);
		panelGhe10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA11.getText());
					panelGhe10.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe10.setBackground(Color.WHITE);
					maGhe.remove(labelA11.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe10);

		JPanel panelGhe11 = new JPanel();
		panelGhe11.setBackground(Color.WHITE);
		panelGhe11.setBounds(790, 160, 60, 60);
		panelGhe11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe11.setLayout(new BorderLayout());
		
		JLabel labelA12 = new JLabel("A12");
		labelA12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA12.setForeground(Color.black); // Màu chữ trắng
		labelA12.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA12.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe11.add(labelA12, BorderLayout.CENTER);
		panelGhe11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA12.getText());
					panelGhe11.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe11.setBackground(Color.WHITE);
					maGhe.remove(labelA02.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe11);

		JPanel panelGhe12 = new JPanel();
		panelGhe12.setBackground(Color.WHITE);
		panelGhe12.setBounds(860, 160, 60, 60);
		panelGhe12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe12.setLayout(new BorderLayout());
		
		JLabel labelA13 = new JLabel("A13");
		labelA13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA13.setForeground(Color.black); // Màu chữ trắng
		labelA13.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA13.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe12.add(labelA13, BorderLayout.CENTER);
		panelGhe12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA13.getText());
					panelGhe12.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe12.setBackground(Color.WHITE);
					maGhe.remove(labelA13.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe12);

		JPanel panelGhe13 = new JPanel();
		panelGhe13.setBackground(Color.WHITE);
		panelGhe13.setBounds(930, 160, 60, 60);
		panelGhe13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe13.setLayout(new BorderLayout());
		
		JLabel labelA14 = new JLabel("A14");
		labelA14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA14.setForeground(Color.black); // Màu chữ trắng
		labelA14.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA14.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe13.add(labelA14, BorderLayout.CENTER);
		panelGhe13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					panelGhe13.setBackground(new Color(0, 128, 255));
					maGhe.add(labelA14.getText());
				} else {
					panelGhe13.setBackground(Color.WHITE);
					maGhe.remove(labelA14.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe13);

		JPanel panelGhe14 = new JPanel();
		panelGhe14.setBackground(Color.WHITE);
		panelGhe14.setBounds(1000, 160, 60, 60);
		panelGhe14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe14.setLayout(new BorderLayout());
		
		JLabel labelA15 = new JLabel("A15");
		labelA15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA15.setForeground(Color.black); // Màu chữ trắng
		labelA15.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA15.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe14.add(labelA15, BorderLayout.CENTER);
		
		panelGhe14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA15.getText());
					panelGhe14.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe14.setBackground(Color.WHITE);
					maGhe.remove(labelA15.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe14);

		JPanel panelGhe15 = new JPanel();
		panelGhe15.setBackground(Color.WHITE);
		panelGhe15.setBounds(1080, 160, 60, 60);
		panelGhe15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGhe15.setLayout(new BorderLayout());
		
		JLabel labelA16 = new JLabel("A16");
		labelA16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelA16.setForeground(Color.black); // Màu chữ trắng
		labelA16.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelA16.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGhe15.add(labelA16, BorderLayout.CENTER);
		panelGhe15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelA16.getText());
					panelGhe15.setBackground(new Color(0, 128, 255));
				} else {
					panelGhe15.setBackground(Color.WHITE);
					maGhe.remove(labelA16.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGhe15);

		JPanel panelGheB01 = new JPanel();
		panelGheB01.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB01.setBackground(Color.WHITE);
		panelGheB01.setBounds(10, 230, 60, 60);
		panelGheB01.setLayout(new BorderLayout());
		
		JLabel labelB01 = new JLabel("B01");
		labelB01.setVerticalAlignment(SwingConstants.CENTER);
		labelB01.setHorizontalAlignment(SwingConstants.CENTER);
		labelB01.setForeground(Color.BLACK);
		labelB01.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheB01.add(labelB01, BorderLayout.CENTER);
		panelGheB01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB01.getText());
					panelGheB01.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB01.setBackground(Color.WHITE);
					maGhe.remove(labelB01.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB01);

		JPanel panelGheB02 = new JPanel();
		panelGheB02.setBackground(Color.WHITE);
		panelGheB02.setBounds(90, 230, 60, 60);
		panelGheB02.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB02.setLayout(new BorderLayout());
		
		JLabel labelB02 = new JLabel("B02");
		labelB02.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB02.setForeground(Color.black); // Màu chữ trắng
		labelB02.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB02.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB02.add(labelB02, BorderLayout.CENTER);
		panelGheB02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB02.getText());
					panelGheB02.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB02.setBackground(Color.WHITE);
					maGhe.remove(labelB02.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB02);

		JPanel panelGheB03 = new JPanel();
		panelGheB03.setBackground(Color.WHITE);
		panelGheB03.setBounds(160, 230, 60, 60);
		panelGheB03.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB03.setLayout(new BorderLayout());
		
		JLabel labelB03 = new JLabel("B03");
		labelB03.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB03.setForeground(Color.black); // Màu chữ trắng
		labelB03.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB03.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB03.add(labelB03, BorderLayout.CENTER);
		panelGheB03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB03.getText());
					panelGheB03.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB03.setBackground(Color.WHITE);
					maGhe.remove(labelB03.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB03);

		JPanel panelGheB04 = new JPanel();
		panelGheB04.setBackground(Color.WHITE);
		panelGheB04.setBounds(230, 230, 60, 60);
		panelGheB04.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB04.setLayout(new BorderLayout());
		
		JLabel labelB04 = new JLabel("B04");
		labelB04.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB04.setForeground(Color.black); // Màu chữ trắng
		labelB04.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB04.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB04.add(labelB04, BorderLayout.CENTER);
		panelGheB04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB04.getText());
					panelGheB04.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB04.setBackground(Color.WHITE);
					maGhe.remove(labelB04.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB04);

		JPanel panelGheB05 = new JPanel();
		panelGheB05.setBackground(Color.WHITE);
		panelGheB05.setBounds(300, 230, 60, 60);
		panelGheB05.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB05.setLayout(new BorderLayout());
		
		JLabel labelB05 = new JLabel("B05");
		labelB05.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB05.setForeground(Color.black); // Màu chữ trắng
		labelB05.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB05.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB05.add(labelB05, BorderLayout.CENTER);
		panelGheB05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB05.getText());
					panelGheB05.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB05.setBackground(Color.WHITE);
					maGhe.remove(labelB05.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB05);

		JPanel panelGheB06 = new JPanel();
		panelGheB06.setBackground(Color.WHITE);
		panelGheB06.setBounds(370, 230, 60, 60);
		panelGheB06.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB06.setLayout(new BorderLayout());
		
		JLabel labelb06 = new JLabel("B06");
		labelb06.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelb06.setForeground(Color.black); // Màu chữ trắng
		labelb06.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelb06.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB06.add(labelb06, BorderLayout.CENTER);
		panelGheB06.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelb06.getText());
					panelGheB06.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB06.setBackground(Color.WHITE);
					maGhe.remove(labelb06.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB06);

		JPanel panelGheB07 = new JPanel();
		panelGheB07.setBackground(Color.WHITE);
		panelGheB07.setBounds(440, 230, 60, 60);
		panelGheB07.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB07.setLayout(new BorderLayout());
		
		JLabel labelB07 = new JLabel("B07");
		labelB07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB07.setForeground(Color.black); // Màu chữ trắng
		labelB07.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB07.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB07.add(labelB07, BorderLayout.CENTER);
		panelGheB07.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB07.getText());
					panelGheB07.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB07.setBackground(Color.WHITE);
					maGhe.remove(labelB07.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB07);

		JPanel panelGheB08 = new JPanel();
		panelGheB08.setBackground(Color.WHITE);
		panelGheB08.setBounds(510, 230, 60, 60);
		panelGheB08.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB08.setLayout(new BorderLayout());
		
		JLabel labelB08 = new JLabel("B08");
		labelB08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB08.setForeground(Color.black); // Màu chữ trắng
		labelB08.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB08.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB08.add(labelB08, BorderLayout.CENTER);
		panelGheB08.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB08.getText());
					panelGheB08.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB08.setBackground(Color.WHITE);
					maGhe.remove(labelB08.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB08);

		JPanel panelGheB09 = new JPanel();
		panelGheB09.setBackground(Color.WHITE);
		panelGheB09.setBounds(580, 230, 60, 60);
		panelGheB09.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB09.setLayout(new BorderLayout());
		
		JLabel labelB09 = new JLabel("B09");
		labelB09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB09.setForeground(Color.black); // Màu chữ trắng
		labelB09.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB09.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB09.add(labelB09, BorderLayout.CENTER);
		panelGheB09.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB09.getText());
					panelGheB09.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB09.setBackground(Color.WHITE);
					maGhe.remove(labelB09.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB09);

		JPanel panelGheB10 = new JPanel();
		panelGheB10.setBackground(Color.WHITE);
		panelGheB10.setBounds(650, 230, 60, 60);
		panelGheB10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB10.setLayout(new BorderLayout());
		
		JLabel labelB10 = new JLabel("B10");
		labelB10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB10.setForeground(Color.black); // Màu chữ trắng
		labelB10.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB10.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB10.add(labelB10, BorderLayout.CENTER);
		panelGheB10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB10.getText());
					panelGheB10.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB10.setBackground(Color.WHITE);
					maGhe.remove(labelB10.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB10);

		JPanel panelGheB11 = new JPanel();
		panelGheB11.setBackground(Color.WHITE);
		panelGheB11.setBounds(720, 230, 60, 60);
		panelGheB11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB11.setLayout(new BorderLayout());
		
		JLabel labelB11 = new JLabel("B11");
		labelB11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB11.setForeground(Color.black); // Màu chữ trắng
		labelB11.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB11.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB11.add(labelB11, BorderLayout.CENTER);
		panelGheB11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB11.getText());
					panelGheB11.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB11.setBackground(Color.WHITE);
					maGhe.remove(labelB11.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB11);

		JPanel panelGheB12 = new JPanel();
		panelGheB12.setBackground(Color.WHITE);
		panelGheB12.setBounds(790, 230, 60, 60);
		panelGheB12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB12.setLayout(new BorderLayout());
		
		JLabel labelB12 = new JLabel("B12");
		labelB12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB12.setForeground(Color.black); // Màu chữ trắng
		labelB12.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB12.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB12.add(labelB12, BorderLayout.CENTER);
		panelGheB12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB12.getText());
					panelGheB12.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB12.setBackground(Color.WHITE);
					maGhe.remove(labelB12.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB12);

		JPanel panelGheB13 = new JPanel();
		panelGheB13.setBackground(Color.WHITE);
		panelGheB13.setBounds(860, 230, 60, 60);
		panelGheB13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB13.setLayout(new BorderLayout());
		
		JLabel labelB13 = new JLabel("B13");
		labelB13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB13.setForeground(Color.black); // Màu chữ trắng
		labelB13.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB13.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB13.add(labelB13, BorderLayout.CENTER);
		panelGheB13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB13.getText());
					panelGheB13.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB13.setBackground(Color.WHITE);
					maGhe.remove(labelB13.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB13);

		JPanel panelGheB14 = new JPanel();
		panelGheB14.setBackground(Color.WHITE);
		panelGheB14.setBounds(930, 230, 60, 60);
		panelGheB14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB14.setLayout(new BorderLayout());
		
		JLabel labelB14 = new JLabel("B14");
		labelB14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB14.setForeground(Color.black); // Màu chữ trắng
		labelB14.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB14.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB14.add(labelB14, BorderLayout.CENTER);
		panelGheB14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB14.getText());
					panelGheB14.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB14.setBackground(Color.WHITE);
					maGhe.remove(labelB14.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB14);

		JPanel panelGheB15 = new JPanel();
		panelGheB15.setBackground(Color.WHITE);
		panelGheB15.setBounds(1000, 230, 60, 60);
		panelGheB15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB15.setLayout(new BorderLayout());
		
		JLabel labelB15 = new JLabel("B15");
		labelB15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelB15.setForeground(Color.black); // Màu chữ trắng
		labelB15.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelB15.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheB15.add(labelB15, BorderLayout.CENTER);
		panelGheB15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB15.getText());
					panelGheB15.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB15.setBackground(Color.WHITE);
					maGhe.remove(labelB15.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB15);

		JPanel panelGheB16 = new JPanel();
		panelGheB16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheB16.setBackground(Color.WHITE);
		panelGheB16.setBounds(1080, 230, 60, 60);
		panelGheB16.setLayout(new BorderLayout());
		
		JLabel labelB16 = new JLabel("B16");
		labelB16.setVerticalAlignment(SwingConstants.CENTER);
		labelB16.setHorizontalAlignment(SwingConstants.CENTER);
		labelB16.setForeground(Color.BLACK);
		labelB16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheB16.add(labelB16, BorderLayout.CENTER);
		panelGheB16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelB16.getText());
					panelGheB16.setBackground(new Color(0, 128, 255));
				} else {
					panelGheB16.setBackground(Color.WHITE);
					maGhe.remove(labelB16.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheB16);

		JPanel panelGheC01 = new JPanel();
		panelGheC01.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC01.setBackground(Color.WHITE);
		panelGheC01.setBounds(10, 300, 60, 60);
		panelGheC01.setLayout(new BorderLayout());
		
		JLabel labelC01 = new JLabel("C01");
		labelC01.setVerticalAlignment(SwingConstants.CENTER);
		labelC01.setHorizontalAlignment(SwingConstants.CENTER);
		labelC01.setForeground(Color.BLACK);
		labelC01.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheC01.add(labelC01, BorderLayout.CENTER);
		panelGheC01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC01.getText());
					panelGheC01.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC01.setBackground(Color.WHITE);
					maGhe.remove(labelC01.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC01);

		JPanel panelGheC02 = new JPanel();
		panelGheC02.setBackground(Color.WHITE);
		panelGheC02.setBounds(90, 300, 60, 60);
		panelGheC02.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC02.setLayout(new BorderLayout());
		
		JLabel labelc02 = new JLabel("C02");
		labelc02.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelc02.setForeground(Color.black); // Màu chữ trắng
		labelc02.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelc02.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC02.add(labelc02, BorderLayout.CENTER);
		panelGheC02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelc02.getText());
					panelGheC02.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC02.setBackground(Color.WHITE);
					maGhe.remove(labelc02.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC02);

		JPanel panelGheC03 = new JPanel();
		panelGheC03.setBackground(Color.WHITE);
		panelGheC03.setBounds(160, 300, 60, 60);
		panelGheC03.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC03.setLayout(new BorderLayout());
		
		JLabel labelC03 = new JLabel("C03");
		labelC03.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC03.setForeground(Color.black); // Màu chữ trắng
		labelC03.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC03.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC03.add(labelC03, BorderLayout.CENTER);
		panelGheC03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC03.getText());
					panelGheC03.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC03.setBackground(Color.WHITE);
					maGhe.remove(labelC03.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC03);

		JPanel panelGheC04 = new JPanel();
		panelGheC04.setBackground(Color.WHITE);
		panelGheC04.setBounds(230, 300, 60, 60);
		panelGheC04.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC04.setLayout(new BorderLayout());
		
		JLabel labelC04 = new JLabel("C04");
		labelC04.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC04.setForeground(Color.black); // Màu chữ trắng
		labelC04.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC04.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC04.add(labelC04, BorderLayout.CENTER);
		panelGheC04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC04.getText());
					panelGheC04.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC04.setBackground(Color.WHITE);
					maGhe.remove(labelC04.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC04);

		JPanel panelGheC05 = new JPanel();
		panelGheC05.setBackground(Color.WHITE);
		panelGheC05.setBounds(300, 300, 60, 60);
		panelGheC05.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC05.setLayout(new BorderLayout());
		
		JLabel labelC05 = new JLabel("C05");
		labelC05.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC05.setForeground(Color.black); // Màu chữ trắng
		labelC05.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC05.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC05.add(labelC05, BorderLayout.CENTER);
		panelGheC05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC05.getText());
					panelGheC05.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC05.setBackground(Color.WHITE);
					maGhe.remove(labelC05.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC05);

		JPanel panelGheC06 = new JPanel();
		panelGheC06.setBackground(Color.WHITE);
		panelGheC06.setBounds(370, 300, 60, 60);
		panelGheC06.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC06.setLayout(new BorderLayout());
		
		JLabel labelC06 = new JLabel("C06");
		labelC06.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC06.setForeground(Color.black); // Màu chữ trắng
		labelC06.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC06.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC06.add(labelC06, BorderLayout.CENTER);
		panelGheC06.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC06.getText());
					panelGheC06.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC06.setBackground(Color.WHITE);
					maGhe.remove(labelC06.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC06);

		JPanel panelGheC07 = new JPanel();
		panelGheC07.setBackground(Color.WHITE);
		panelGheC07.setBounds(440, 300, 60, 60);
		panelGheC07.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC07.setLayout(new BorderLayout());
		
		JLabel labelC07 = new JLabel("C07");
		labelC07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC07.setForeground(Color.black); // Màu chữ trắng
		labelC07.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC07.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC07.add(labelC07, BorderLayout.CENTER);
		panelGheC07.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC07.getText());
					panelGheC07.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC07.setBackground(Color.WHITE);
					maGhe.remove(labelC07.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC07);

		JPanel panelGheC08 = new JPanel();
		panelGheC08.setBackground(Color.WHITE);
		panelGheC08.setBounds(510, 300, 60, 60);
		panelGheC08.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC08.setLayout(new BorderLayout());
		
		JLabel labelC08 = new JLabel("C08");
		labelC08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC08.setForeground(Color.black); // Màu chữ trắng
		labelC08.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC08.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC08.add(labelC08, BorderLayout.CENTER);
		panelGheC08.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC08.getText());
					panelGheC08.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC08.setBackground(Color.WHITE);
					maGhe.remove(labelC08.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC08);

		JPanel panelGheC09 = new JPanel();
		panelGheC09.setBackground(Color.WHITE);
		panelGheC09.setBounds(580, 300, 60, 60);
		panelGheC09.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC09.setLayout(new BorderLayout());
		
		JLabel labelC09 = new JLabel("C09");
		labelC09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC09.setForeground(Color.black); // Màu chữ trắng
		labelC09.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC09.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC09.add(labelC09, BorderLayout.CENTER);
		panelGheC09.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC09.getText());
					panelGheC09.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC09.setBackground(Color.WHITE);
					maGhe.remove(labelC09.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC09);

		JPanel panelGheC10 = new JPanel();
		panelGheC10.setBackground(Color.WHITE);
		panelGheC10.setBounds(650, 300, 60, 60);
		panelGheC10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC10.setLayout(new BorderLayout());
		
		JLabel labelC10 = new JLabel("C10");
		labelC10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC10.setForeground(Color.black); // Màu chữ trắng
		labelC10.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC10.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC10.add(labelC10, BorderLayout.CENTER);
		panelGheC10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC10.getText());
					panelGheC10.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC10.setBackground(Color.WHITE);
					maGhe.remove(labelC10.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC10);

		JPanel panelGheC11 = new JPanel();
		panelGheC11.setBackground(Color.WHITE);
		panelGheC11.setBounds(720, 300, 60, 60);
		panelGheC11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC11.setLayout(new BorderLayout());
		
		JLabel labelC11 = new JLabel("C11");
		labelC11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC11.setForeground(Color.black); // Màu chữ trắng
		labelC11.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC11.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC11.add(labelC11, BorderLayout.CENTER);
		panelGheC11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC11.getText());
					panelGheC11.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC11.setBackground(Color.WHITE);
					maGhe.remove(labelC11.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC11);

		JPanel panelGheC12 = new JPanel();
		panelGheC12.setBackground(Color.WHITE);
		panelGheC12.setBounds(790, 300, 60, 60);
		panelGheC12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC12.setLayout(new BorderLayout());
		
		JLabel labelC12 = new JLabel("C12");
		labelC12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC12.setForeground(Color.black); // Màu chữ trắng
		labelC12.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC12.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC12.add(labelC12, BorderLayout.CENTER);
		panelGheC12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC12.getText());
					panelGheC12.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC12.setBackground(Color.WHITE);
					maGhe.remove(labelC12.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC12);

		JPanel panelGheC13 = new JPanel();
		panelGheC13.setBackground(Color.WHITE);
		panelGheC13.setBounds(860, 300, 60, 60);
		panelGheC13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC13.setLayout(new BorderLayout());
		
		JLabel labelC13 = new JLabel("C13");
		labelC13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC13.setForeground(Color.black); // Màu chữ trắng
		labelC13.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC13.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC13.add(labelC13, BorderLayout.CENTER);
		panelGheC13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC13.getText());
					panelGheC13.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC13.setBackground(Color.WHITE);
					maGhe.remove(labelC13.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC13);

		JPanel panelGheC14 = new JPanel();
		panelGheC14.setBackground(Color.WHITE);
		panelGheC14.setBounds(930, 300, 60, 60);
		panelGheC14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC14.setLayout(new BorderLayout());
		
		JLabel labelC14 = new JLabel("C14");
		labelC14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC14.setForeground(Color.black); // Màu chữ trắng
		labelC14.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC14.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC14.add(labelC14, BorderLayout.CENTER);
		panelGheC14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC14.getText());
					panelGheC14.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC14.setBackground(Color.WHITE);
					maGhe.remove(labelC14.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC14);

		JPanel panelGheC15 = new JPanel();
		panelGheC15.setBackground(Color.WHITE);
		panelGheC15.setBounds(1000, 300, 60, 60);
		panelGheC15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC15.setLayout(new BorderLayout());
		
		JLabel labelC15 = new JLabel("C15");
		labelC15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelC15.setForeground(Color.black); // Màu chữ trắng
		labelC15.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelC15.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheC15.add(labelC15, BorderLayout.CENTER);
		panelGheC15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC15.getText());
					panelGheC15.setBackground(new Color(0, 128, 255));
					
				} else {
					panelGheC15.setBackground(Color.WHITE);
					maGhe.remove(labelC15.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC15);

		JPanel panelGheC16 = new JPanel();
		panelGheC16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheC16.setBackground(Color.WHITE);
		panelGheC16.setBounds(1080, 300, 60, 60);
		panelGheC16.setLayout(new BorderLayout());
		
		JLabel labelC16 = new JLabel("C16");
		labelC16.setVerticalAlignment(SwingConstants.CENTER);
		labelC16.setHorizontalAlignment(SwingConstants.CENTER);
		labelC16.setForeground(Color.BLACK);
		labelC16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheC16.add(labelC16, BorderLayout.CENTER);
		panelGheC16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelC16.getText());
					panelGheC16.setBackground(new Color(0, 128, 255));
				} else {
					panelGheC16.setBackground(Color.WHITE);
					maGhe.remove(labelC16.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheC16);

		JPanel panelGheD01 = new JPanel();
		panelGheD01.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD01.setBackground(Color.WHITE);
		panelGheD01.setBounds(10, 370, 60, 60);
		panelGheD01.setLayout(new BorderLayout());
		
		JLabel labelD01 = new JLabel("D01");
		labelD01.setVerticalAlignment(SwingConstants.CENTER);
		labelD01.setHorizontalAlignment(SwingConstants.CENTER);
		labelD01.setForeground(Color.BLACK);
		labelD01.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheD01.add(labelD01, BorderLayout.CENTER);
		panelGheD01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD01.getText());
					panelGheD01.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD01.setBackground(Color.WHITE);
					maGhe.remove(labelD01.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD01);

		JPanel panelGheD02 = new JPanel();
		panelGheD02.setBackground(Color.WHITE);
		panelGheD02.setBounds(90, 370, 60, 60);
		panelGheD02.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD02.setLayout(new BorderLayout());
		
		JLabel labelD02 = new JLabel("D02");
		labelD02.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD02.setForeground(Color.black); // Màu chữ trắng
		labelD02.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD02.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD02.add(labelD02, BorderLayout.CENTER);
		panelGheD02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD02.getText());
					panelGheD02.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD02.setBackground(Color.WHITE);
					maGhe.remove(labelD02.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD02);

		JPanel panelGheD03 = new JPanel();
		panelGheD03.setBackground(Color.WHITE);
		panelGheD03.setBounds(160, 370, 60, 60);
		panelGheD03.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD03.setLayout(new BorderLayout());
		
		JLabel labelD03 = new JLabel("D03");
		labelD03.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD03.setForeground(Color.black); // Màu chữ trắng
		labelD03.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD03.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD03.add(labelD03, BorderLayout.CENTER);
		panelGheD03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD03.getText());
					panelGheD03.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD03.setBackground(Color.WHITE);
					maGhe.remove(labelD03.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD03);

		JPanel panelGheD04 = new JPanel();
		panelGheD04.setBackground(Color.WHITE);
		panelGheD04.setBounds(230, 370, 60, 60);
		panelGheD04.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD04.setLayout(new BorderLayout());
		
		JLabel labelD04 = new JLabel("D04");
		labelD04.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD04.setForeground(Color.black); // Màu chữ trắng
		labelD04.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD04.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD04.add(labelD04, BorderLayout.CENTER);
		panelGheD04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD04.getText());
					panelGheD04.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD04.setBackground(Color.WHITE);
					maGhe.remove(labelD04.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD04);

		JPanel panelGheD05 = new JPanel();
		panelGheD05.setBackground(Color.WHITE);
		panelGheD05.setBounds(300, 370, 60, 60);
		panelGheD05.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD05.setLayout(new BorderLayout());
		
		JLabel labelD05 = new JLabel("D05");
		labelD05.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD05.setForeground(Color.black); // Màu chữ trắng
		labelD05.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD05.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD05.add(labelD05, BorderLayout.CENTER);
		panelGheD05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD05.getText());
					panelGheD05.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD05.setBackground(Color.WHITE);
					maGhe.remove(labelD05.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD05);

		JPanel panelGheD06 = new JPanel();
		panelGheD06.setBackground(Color.WHITE);
		panelGheD06.setBounds(370, 370, 60, 60);
		panelGheD06.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD06.setLayout(new BorderLayout());
		
		JLabel labelD06 = new JLabel("D06");
		labelD06.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD06.setForeground(Color.black); // Màu chữ trắng
		labelD06.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD06.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD06.add(labelD06, BorderLayout.CENTER);
		panelGheD06.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD06.getText());
					panelGheD06.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD06.setBackground(Color.WHITE);
					maGhe.remove(labelD06.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD06);

		JPanel panelGheD07 = new JPanel();
		panelGheD07.setBackground(Color.WHITE);
		panelGheD07.setBounds(440, 370, 60, 60);
		panelGheD07.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD07.setLayout(new BorderLayout());
		
		JLabel labelD07 = new JLabel("D07");
		labelD07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD07.setForeground(Color.black); // Màu chữ trắng
		labelD07.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD07.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD07.add(labelD07, BorderLayout.CENTER);
		panelGheD07.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD07.getText());
					panelGheD07.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD07.setBackground(Color.WHITE);
					maGhe.remove(labelD07.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD07);

		JPanel panelGheD08 = new JPanel();
		panelGheD08.setBackground(Color.WHITE);
		panelGheD08.setBounds(510, 370, 60, 60);
		panelGheD08.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD08.setLayout(new BorderLayout());
		
		JLabel labelD08 = new JLabel("D08");
		labelD08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD08.setForeground(Color.black); // Màu chữ trắng
		labelD08.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD08.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD08.add(labelD08, BorderLayout.CENTER);
		panelGheD08.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD08.getText());
					panelGheD08.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD08.setBackground(Color.WHITE);
					maGhe.remove(labelD08.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD08);

		JPanel panelGheD09 = new JPanel();
		panelGheD09.setBackground(Color.WHITE);
		panelGheD09.setBounds(580, 370, 60, 60);
		panelGheD09.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD09.setLayout(new BorderLayout());
		
		JLabel labelD09 = new JLabel("D09");
		labelD09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD09.setForeground(Color.black); // Màu chữ trắng
		labelD09.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD09.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD09.add(labelD09, BorderLayout.CENTER);
		panelGheD09.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD09.getText());
					panelGheD09.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD09.setBackground(Color.WHITE);
					maGhe.remove(labelD09.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD09);

		JPanel panelGheD10 = new JPanel();
		panelGheD10.setBackground(Color.WHITE);
		panelGheD10.setBounds(650, 370, 60, 60);
		panelGheD10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD10.setLayout(new BorderLayout());
		
		JLabel labelD10 = new JLabel("D10");
		labelD10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD10.setForeground(Color.black); // Màu chữ trắng
		labelD10.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD10.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD10.add(labelD10, BorderLayout.CENTER);
		panelGheD10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD10.getText());
					panelGheD10.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD10.setBackground(Color.WHITE);
					maGhe.remove(labelD10.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD10);

		JPanel panelGheD11 = new JPanel();
		panelGheD11.setBackground(Color.WHITE);
		panelGheD11.setBounds(720, 370, 60, 60);
		panelGheD11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD11.setLayout(new BorderLayout());
		
		JLabel labelD11 = new JLabel("D11");
		labelD11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD11.setForeground(Color.black); // Màu chữ trắng
		labelD11.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD11.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD11.add(labelD11, BorderLayout.CENTER);
		panelGheD11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD11.getText());
					panelGheD11.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD11.setBackground(Color.WHITE);
					maGhe.remove(labelD11.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD11);

		JPanel panelGheD12 = new JPanel();
		panelGheD12.setBackground(Color.WHITE);
		panelGheD12.setBounds(790, 370, 60, 60);
		panelGheD12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD12.setLayout(new BorderLayout());
		
		JLabel labelD12 = new JLabel("D12");
		labelD12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD12.setForeground(Color.black); // Màu chữ trắng
		labelD12.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD12.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD12.add(labelD12, BorderLayout.CENTER);
		panelGheD12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD12.getText());
					panelGheD12.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD12.setBackground(Color.WHITE);
					maGhe.remove(labelD12.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD12);

		JPanel panelGheD13 = new JPanel();
		panelGheD13.setBackground(Color.WHITE);
		panelGheD13.setBounds(860, 370, 60, 60);
		panelGheD13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD13.setLayout(new BorderLayout());
		
		JLabel labelD13 = new JLabel("D13");
		labelD13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD13.setForeground(Color.black); // Màu chữ trắng
		labelD13.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD13.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD13.add(labelD13, BorderLayout.CENTER);
		panelGheD13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD13.getText());
					panelGheD13.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD13.setBackground(Color.WHITE);
					maGhe.remove(labelD13.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD13);

		JPanel panelGheD14 = new JPanel();
		panelGheD14.setBackground(Color.WHITE);
		panelGheD14.setBounds(930, 370, 60, 60);
		panelGheD14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD14.setLayout(new BorderLayout());
		
		JLabel labelD14 = new JLabel("D14");
		labelD14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD14.setForeground(Color.black); // Màu chữ trắng
		labelD14.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD14.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD14.add(labelD14, BorderLayout.CENTER);
		panelGheD14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD14.getText());
					panelGheD14.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD14.setBackground(Color.WHITE);
					maGhe.remove(labelD14.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD14);

		JPanel panelGheD15 = new JPanel();
		panelGheD15.setBackground(Color.WHITE);
		panelGheD15.setBounds(1000, 370, 60, 60);
		panelGheD15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD15.setLayout(new BorderLayout());
		
		JLabel labelD15 = new JLabel("D15");
		labelD15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelD15.setForeground(Color.black); // Màu chữ trắng
		labelD15.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelD15.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheD15.add(labelD15, BorderLayout.CENTER);
		panelGheD15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD15.getText());
					panelGheD15.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD15.setBackground(Color.WHITE);
					maGhe.remove(labelD15.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD15);

		JPanel panelGheD16 = new JPanel();
		panelGheD16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheD16.setBackground(Color.WHITE);
		panelGheD16.setBounds(1080, 370, 60, 60);
		panelGheD16.setLayout(new BorderLayout());
		
		JLabel labelD16 = new JLabel("D16");
		labelD16.setVerticalAlignment(SwingConstants.CENTER);
		labelD16.setHorizontalAlignment(SwingConstants.CENTER);
		labelD16.setForeground(Color.BLACK);
		labelD16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheD16.add(labelD16, BorderLayout.CENTER);
		panelGheD16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelD16.getText());
					panelGheD16.setBackground(new Color(0, 128, 255));
				} else {
					panelGheD16.setBackground(Color.WHITE);
					maGhe.remove(labelD16.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheD16);

		JPanel panelGheE01 = new JPanel();
		panelGheE01.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE01.setBackground(Color.WHITE);
		panelGheE01.setBounds(10, 440, 60, 60);
		panelGheE01.setLayout(new BorderLayout());
		
		JLabel labelE01 = new JLabel("E01");
		labelE01.setVerticalAlignment(SwingConstants.CENTER);
		labelE01.setHorizontalAlignment(SwingConstants.CENTER);
		labelE01.setForeground(Color.BLACK);
		labelE01.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheE01.add(labelE01, BorderLayout.CENTER);
		panelGheE01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE01.getText());
					panelGheE01.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE01.setBackground(Color.WHITE);
					maGhe.remove(labelE01.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE01);

		JPanel panelGheE02 = new JPanel();
		panelGheE02.setBackground(Color.WHITE);
		panelGheE02.setBounds(90, 440, 60, 60);
		panelGheE02.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE02.setLayout(new BorderLayout());
		
		JLabel labelE02 = new JLabel("E02");
		labelE02.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE02.setForeground(Color.black); // Màu chữ trắng
		labelE02.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE02.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE02.add(labelE02, BorderLayout.CENTER);
		panelGheE02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE02.getText());
					panelGheE02.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE02.setBackground(Color.WHITE);
					maGhe.remove(labelE02.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE02);

		JPanel panelGheE03 = new JPanel();
		panelGheE03.setBackground(Color.WHITE);
		panelGheE03.setBounds(160, 440, 60, 60);
		panelGheE03.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE03.setLayout(new BorderLayout());
		
		JLabel labelE03 = new JLabel("E03");
		labelE03.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE03.setForeground(Color.black); // Màu chữ trắng
		labelE03.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE03.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE03.add(labelE03, BorderLayout.CENTER);
		panelGheE03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE03.getText());
					panelGheE03.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE03.setBackground(Color.WHITE);
					maGhe.remove(labelE03.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE03);

		JPanel panelGheE04 = new JPanel();
		panelGheE04.setBackground(Color.WHITE);
		panelGheE04.setBounds(230, 440, 60, 60);
		panelGheE04.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE04.setLayout(new BorderLayout());
		
		JLabel labelE04 = new JLabel("E04");
		labelE04.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE04.setForeground(Color.black); // Màu chữ trắng
		labelE04.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE04.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE04.add(labelE04, BorderLayout.CENTER);
		panelGheE04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE04.getText());
					panelGheE04.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE04.setBackground(Color.WHITE);
					maGhe.remove(labelE04.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE04);

		JPanel panelGheE05 = new JPanel();
		panelGheE05.setBackground(Color.WHITE);
		panelGheE05.setBounds(300, 440, 60, 60);
		panelGheE05.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE05.setLayout(new BorderLayout());
		
		JLabel labelE05 = new JLabel("E05");
		labelE05.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE05.setForeground(Color.black); // Màu chữ trắng
		labelE05.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE05.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE05.add(labelE05, BorderLayout.CENTER);
		panelGheE05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE05.getText());
					panelGheE05.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE05.setBackground(Color.WHITE);
					maGhe.remove(labelE05.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE05);

		JPanel panelGheE06 = new JPanel();
		panelGheE06.setBackground(Color.WHITE);
		panelGheE06.setBounds(370, 440, 60, 60);
		panelGheE06.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE06.setLayout(new BorderLayout());
		
		JLabel labelE06 = new JLabel("E06");
		labelE06.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE06.setForeground(Color.black); // Màu chữ trắng
		labelE06.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE06.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE06.add(labelE06, BorderLayout.CENTER);
		panelGheE06.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE06.getText());
					panelGheE06.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE06.setBackground(Color.WHITE);
					maGhe.remove(labelE06.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE06);

		JPanel panelGheE07 = new JPanel();
		panelGheE07.setBackground(Color.WHITE);
		panelGheE07.setBounds(440, 440, 60, 60);
		panelGheE07.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE07.setLayout(new BorderLayout());
		
		JLabel labelE07 = new JLabel("E07");
		labelE07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE07.setForeground(Color.black); // Màu chữ trắng
		labelE07.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE07.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE07.add(labelE07, BorderLayout.CENTER);
		panelGheE07.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE07.getText());
					panelGheE07.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE07.setBackground(Color.WHITE);
					maGhe.remove(labelE07.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE07);

		JPanel panelGheE08 = new JPanel();
		panelGheE08.setBackground(Color.WHITE);
		panelGheE08.setBounds(510, 440, 60, 60);
		panelGheE08.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE08.setLayout(new BorderLayout());
		
		JLabel labelE08 = new JLabel("E08");
		labelE08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE08.setForeground(Color.black); // Màu chữ trắng
		labelE08.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE08.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE08.add(labelE08, BorderLayout.CENTER);
		panelGheE08.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE08.getText());
					panelGheE08.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE08.setBackground(Color.WHITE);
					maGhe.remove(labelE08.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE08);

		JPanel panelGheE09 = new JPanel();
		panelGheE09.setBackground(Color.WHITE);
		panelGheE09.setBounds(580, 440, 60, 60);
		panelGheE09.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE09.setLayout(new BorderLayout());
		
		JLabel labelE09 = new JLabel("E09");
		labelE09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE09.setForeground(Color.black); // Màu chữ trắng
		labelE09.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE09.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE09.add(labelE09, BorderLayout.CENTER);
		panelGheE09.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE09.getText());
					panelGheE09.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE09.setBackground(Color.WHITE);
					maGhe.remove(labelE09.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE09);

		JPanel panelGheE10 = new JPanel();
		panelGheE10.setBackground(Color.WHITE);
		panelGheE10.setBounds(650, 440, 60, 60);
		panelGheE10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE10.setLayout(new BorderLayout());
		
		JLabel labelE10 = new JLabel("E10");
		labelE10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE10.setForeground(Color.black); // Màu chữ trắng
		labelE10.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE10.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE10.add(labelE10, BorderLayout.CENTER);
		panelGheE10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE10.getText());
					panelGheE10.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE10.setBackground(Color.WHITE);
					maGhe.remove(labelE10.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE10);

		JPanel panelGheE11 = new JPanel();
		panelGheE11.setBackground(Color.WHITE);
		panelGheE11.setBounds(720, 440, 60, 60);
		panelGheE11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE11.setLayout(new BorderLayout());
		
		JLabel labelE11 = new JLabel("E11");
		labelE11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE11.setForeground(Color.black); // Màu chữ trắng
		labelE11.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE11.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE11.add(labelE11, BorderLayout.CENTER);
		panelGheE11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE11.getText());
					panelGheE11.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE11.setBackground(Color.WHITE);
					maGhe.remove(labelE11.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE11);

		JPanel panelGheE12 = new JPanel();
		panelGheE12.setBackground(Color.WHITE);
		panelGheE12.setBounds(790, 440, 60, 60);
		panelGheE12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE12.setLayout(new BorderLayout());
		
		JLabel labelE12 = new JLabel("E12");
		labelE12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE12.setForeground(Color.black); // Màu chữ trắng
		labelE12.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE12.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE12.add(labelE12, BorderLayout.CENTER);
		panelGheE12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE12.getText());
					panelGheE12.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE12.setBackground(Color.WHITE);
					maGhe.remove(labelE12.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE12);

		JPanel panelGheE13 = new JPanel();
		panelGheE13.setBackground(Color.WHITE);
		panelGheE13.setBounds(860, 440, 60, 60);
		panelGheE13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE13.setLayout(new BorderLayout());
		
		JLabel labelE13 = new JLabel("E13");
		labelE13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE13.setForeground(Color.black); // Màu chữ trắng
		labelE13.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE13.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE13.add(labelE13, BorderLayout.CENTER);
		panelGheE13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE13.getText());
					panelGheE13.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE13.setBackground(Color.WHITE);
					maGhe.remove(labelE13.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE13);

		JPanel panelGheE14 = new JPanel();
		panelGheE14.setBackground(Color.WHITE);
		panelGheE14.setBounds(930, 440, 60, 60);
		panelGheE14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE14.setLayout(new BorderLayout());
		
		JLabel labelE14 = new JLabel("E14");
		labelE14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE14.setForeground(Color.black); // Màu chữ trắng
		labelE14.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE14.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE14.add(labelE14, BorderLayout.CENTER);
		panelGheE14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE14.getText());
					panelGheE14.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE14.setBackground(Color.WHITE);
					maGhe.remove(labelE14.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE14);

		JPanel panelGheE15 = new JPanel();
		panelGheE15.setBackground(Color.WHITE);
		panelGheE15.setBounds(1000, 440, 60, 60);
		panelGheE15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE15.setLayout(new BorderLayout());
		
		JLabel labelE15 = new JLabel("E15");
		labelE15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelE15.setForeground(Color.black); // Màu chữ trắng
		labelE15.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		labelE15.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
		panelGheE15.add(labelE15, BorderLayout.CENTER);
		panelGheE15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE15.getText());
					panelGheE15.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE15.setBackground(Color.WHITE);
					maGhe.remove(labelE15.getText());
				}
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE15);

		JPanel panelGheE16 = new JPanel();
		panelGheE16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGheE16.setBackground(Color.WHITE);
		panelGheE16.setBounds(1080, 440, 60, 60);
		panelGheE16.setLayout(new BorderLayout());
		
		JLabel labelE16 = new JLabel("E16");
		labelE16.setVerticalAlignment(SwingConstants.CENTER);
		labelE16.setHorizontalAlignment(SwingConstants.CENTER);
		labelE16.setForeground(Color.BLACK);
		labelE16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelGheE16.add(labelE16, BorderLayout.CENTER);
		panelGheE16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Chuyển đổi màu giữa (0, 128, 255) và màu trắng
				if (clickSelect) {
					maGhe.add(labelE16.getText());
					panelGheE16.setBackground(new Color(0, 128, 255));
				} else {
					panelGheE16.setBackground(Color.WHITE);
					maGhe.remove(labelE16.getText());
				}
				System.out.println(maGhe);
				clickSelect = !clickSelect; // Đảo ngược trạng thái màu
			}
		});
		contentPane.add(panelGheE16);

		JPanel pnlGhThuong = new JPanel();
		pnlGhThuong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGhThuong.setBackground(Color.WHITE);
		pnlGhThuong.setBounds(10, 570, 30, 30);
		contentPane.add(pnlGhThuong);
		pnlGhThuong.setLayout(new BorderLayout());

		JLabel lblGheThuong = new JLabel("Ghế thường");
		lblGheThuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGheThuong.setBounds(45, 570, 100, 30);
		contentPane.add(lblGheThuong);

		JPanel pnlGhVip = new JPanel();
		pnlGhVip.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGhVip.setBackground(new Color(255, 255, 0));
		pnlGhVip.setBounds(160, 570, 30, 30);
		contentPane.add(pnlGhVip);
		pnlGhVip.setLayout(new BorderLayout());

		JLabel lblGhVip = new JLabel("Ghế VIP");
		lblGhVip.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhVip.setBounds(200, 570, 100, 30);
		contentPane.add(lblGhVip);

		JPanel pnlGhBan = new JPanel();
		pnlGhBan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGhBan.setBackground(new Color(255, 0, 0));
		pnlGhBan.setBounds(300, 570, 30, 30);
		contentPane.add(pnlGhBan);
		pnlGhBan.setLayout(new BorderLayout());

		JLabel lblGhBan = new JLabel("Ghế bận");
		lblGhBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhBan.setBounds(340, 570, 90, 30);
		contentPane.add(lblGhBan);

		JPanel pnlGheChon = new JPanel();
		pnlGheChon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGheChon.setBackground(new Color(0, 128, 255));
		pnlGheChon.setBounds(440, 570, 30, 30);
		contentPane.add(pnlGheChon);
		pnlGheChon.setLayout(new BorderLayout());

		JLabel lblGheChon = new JLabel("Ghế đang chọn");
		lblGheChon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGheChon.setBounds(480, 570, 110, 30);
		contentPane.add(lblGheChon);

		// Khởi tạo các nút
		btnXacNhan = new JButton("Xác nhận");
		btnHuyBo = new JButton("Hủy bỏ");
		btnLamMoi = new JButton("Làm mới");

		// Đặt vị trí cho các nút
		btnXacNhan.setBounds(780, 570, 100, 30);
		btnHuyBo.setBounds(910, 570, 100, 30);
		btnLamMoi.setBounds(1040, 570, 100, 30);

		// Thêm các nút vào contentPane
		contentPane.add(btnXacNhan);
		contentPane.add(btnHuyBo);
		contentPane.add(btnLamMoi);

		JLabel lblManHinh = new JLabel("MÀN HÌNH CHIẾU PHIM");
		lblManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblManHinh.setOpaque(true); // Thiết lập cho JLabel có thể vẽ đè lên màu nền
		lblManHinh.setBackground(blackColor); // Đặt màu nền của JLabel là đen
		lblManHinh.setForeground(whiteColor); // Đặt màu chữ của JLabel là trắng
		lblManHinh.setFont(new Font("Dialog", Font.BOLD, 18));
		lblManHinh.setBounds(221, 102, 710, 30);
		contentPane.add(lblManHinh);

		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(GD_NhanVien.class.getResource("/imgs/bggalaxy1.png")));
		background.setBounds(0, 0, 1162, 613);
		contentPane.add(background);

		System.out.println("Mã Xuất Chiếu " + maXC + "Số Lượng" + soLuongVe);
		
		
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(maGhe.size() < soLuongVe) {
					JOptionPane.showMessageDialog(null, "Số Lượng Ghế Không Hợp Lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else if(maGhe.size() > soLuongVe) {
					JOptionPane.showMessageDialog(null, "Số Lượng Ghế Không Hợp Lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						Socket socket = new Socket("192.168.147.1", 6789);
						client_xc = new ClientXuatChieu_dao(socket);
				
						GD_MuaVe_ThucAn gd_dichvu = new GD_MuaVe_ThucAn(maHD , maGhe , maXC , soLuongVe , sdtkh , maNhanVien , tenNhanVien);
						gd_dichvu.setVisible(true);
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
//		GD_MuaVe_SuatChieu gdsc = new GD_MuaVe_SuatChieu();
//		gdsc.setLocationRelativeTo(null);
//		gdsc.setVisible(true);
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
