package giaoDien;

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

import Test.Login;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class GD_MuaVe extends JFrame implements ActionListener {
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
	private JPanel panelChonVe;
	Color customColor = new Color(0, 92, 111);
	Color whiteColor = Color.WHITE;
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnThem, btnCapNhat, btnXoa, btnLamMoi;
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
			java.util.logging.Logger.getLogger(GD_MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		GD_MuaVe run = new GD_MuaVe();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_MuaVe() {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé");
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
				
						JPanel panel12_1_1 = new JPanel();
						panel12_1_1.setOpaque(false);
						panel12_1_1.setBackground(Color.YELLOW);
						panel12_1_1.setBounds(10, 430, 191, 37);
						contentPane.add(panel12_1_1);
						
								txtDenNgay = new JTextField();
								txtDenNgay.setFont(new Font("Open Sans", 0, 16));
								txtDenNgay.setColumns(13);
								panel12_1_1.add(txtDenNgay);
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
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn ảnh của
																						// bạn
		lblNvIcon.setBounds(760, 5, 40, 40); // Điều chỉnh tọa độ và kích thước của ảnh
		contentPane.add(lblNvIcon);

		JLabel lblnhanvien = new JLabel("NV:");
		lblnhanvien.setForeground(Color.WHITE);
		lblnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnhanvien.setBounds(801, 0, 39, 50);
		lblnhanvien.setForeground(Color.WHITE);
		contentPane.add(lblnhanvien);

		lbltennv = new JLabel("Mai Quoc Truong");
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
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/tickets1.png")));
		muaVeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnPhim.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/film-reel.png")));
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/chair.png")));
		JButton btnThucAn = new JButton("Thức Ăn");
		btnThucAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/popcorn2.png")));
		JButton btnSuatChieu = new JButton("Suất Chiếu");
		btnSuatChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/clapperboard2.png")));

		panelChonVe.add(btnPhim);
		panelChonVe.add(btnSuatChieu);
		panelChonVe.add(btnGhe);
		panelChonVe.add(btnThucAn);

		// Thêm toolbar "Hóa đơn"
		JToolBar hoaDonToolbar = new JToolBar();
		hoaDonToolbar.setFloatable(false);
		hoaDonToolbar.setMargin(new java.awt.Insets(-5, -5, 0, 0));
		testbutton.Buttontest hoaDonButton = new Buttontest();
		hoaDonButton.setText("Hóa Đơn");
		hoaDonButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		hoaDonButton.setForeground(SystemColor.text);
		hoaDonButton.setRippleColor(new Color(255, 255, 255));
		hoaDonButton.setBackground(new Color(46, 139, 87));
		hoaDonButton.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/bill.png")));
		hoaDonToolbar.add(hoaDonButton);
		hoaDonToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(hoaDonToolbar);

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
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/bill.png")));
		khachHangToolbar.add(khachHangButton);
		khachHangToolbar.setBackground(customColor);
		topPanel.add(khachHangToolbar);

		// Create logout button
		JToolBar logoutToolBar = new JToolBar();
		logoutToolBar.setFloatable(false);
		logoutToolBar.setMargin(new java.awt.Insets(-5, 636, 0, 0));
		testbutton.Buttontest logoutButton = new Buttontest();
		logoutButton.setText("Đăng Xuất");
		logoutButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		logoutButton.setForeground(SystemColor.text);
		logoutButton.setRippleColor(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(226, 110, 110));
		logoutButton.setIcon(new ImageIcon(GD_MuaVe.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblNewLabel = new JLabel("Tra cứu phim");
		lblNewLabel.setFont(new Font("Open Sans", 1, 16));
		lblNewLabel.setBounds(70, 102, 108, 20);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(51, 140, 130, 30);
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use vertical BoxLayout
		contentPane.add(panel);
		JCheckBox chkTenPhim = new JCheckBox();
		chkTenPhim.setFont(new Font("Open Sans", 0, 16)); // NOI18N
		chkTenPhim.setSelected(true);
		chkTenPhim.setText("Theo tên phim");
		chkTenPhim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//                chkTenPhimActionPerformed(evt);
			}
		});
		panel.add(chkTenPhim);

		JPanel panel12 = new JPanel();
		panel12.setBackground(new Color(255, 255, 0));
		panel12.setBounds(10, 170, 230, 37);
		panel12.setOpaque(false);
		contentPane.add(panel12);
		// Add JTextField below JCheckBox
		JTextField textField = new JTextField();
		textField.setFont(new Font("Open Sans", 0, 16));
		textField.setColumns(16); // You can adjust the column count based on your requirement
		panel12.add(textField);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 0));
		panel3.setBounds(32, 217, 182, 40);
		panel3.setOpaque(false);
		contentPane.add(panel3);
		JCheckBox chkNgayCongChieu = new JCheckBox();
		chkNgayCongChieu.setFont(new Font("Open Sans", 0, 16));
		chkNgayCongChieu.setText("Theo ngày công chiếu");
		chkNgayCongChieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//                chkNgayCongChieuActionPerformed(evt);
			}
		});
		panel3.add(chkNgayCongChieu);

		JPanel panel12_1 = new JPanel();
		panel12_1.setOpaque(false);
		panel12_1.setBackground(Color.YELLOW);
		panel12_1.setBounds(10, 313, 191, 37);
		contentPane.add(panel12_1);

		txtTuNgay = new JTextField();
		txtTuNgay.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTuNgay.setColumns(13);
		panel12_1.add(txtTuNgay);

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

		JPanel panel12_2 = new JPanel();
		panel12_2.setOpaque(false);
		panel12_2.setBackground(Color.YELLOW);
		panel12_2.setBounds(10, 266, 222, 37);
		panel12_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel12_2);

		JLabel lbltungay = new JLabel("Từ ngày:");
		lbltungay.setFont(new Font("Open Sans", 0, 16));
		panel12_2.add(lbltungay);

		JPanel panel12_3 = new JPanel();
		panel12_3.setOpaque(false);
		panel12_3.setBackground(Color.YELLOW);
		panel12_3.setBounds(10, 383, 230, 37);
		panel12_3.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel12_3);

		JLabel lbldenngay = new JLabel("Đến ngày:");
		lbldenngay.setFont(new Font("Open Sans", 0, 16));
		panel12_3.add(lbldenngay);

		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		btnCapNhat = new JButton("Cập nhật");
		btnXoa = new JButton("Xóa");
		btnLamMoi = new JButton("Làm mới");

		// Đặt vị trí cho các nút
		btnThem.setBounds(250, 99, 100, 30);
		btnCapNhat.setBounds(360, 99, 100, 30);
		btnXoa.setBounds(470, 99, 100, 30);
		btnLamMoi.setBounds(580, 99, 100, 30);

		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnCapNhat);
		contentPane.add(btnXoa);
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
				"Tiếng Nhật", "Nhật Bản", "Ngừng Chiếu" }; // Thay đổi dữ liệu tùy ý
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
		gdnv.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
