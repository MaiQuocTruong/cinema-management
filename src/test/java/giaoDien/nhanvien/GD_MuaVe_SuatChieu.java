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
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class GD_MuaVe_SuatChieu extends JFrame implements ActionListener {
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
	private JButton btnXacNhan, btnHuyBo, btnLamMoi;
	private JTextField txtTuNgay;
	private JDateChooser tuNgayDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private boolean isCalendarVisible = false;
	private JComboBox<String> phongChieuComboBox; // Declare the JComboBox here
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
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_MuaVe_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_MuaVe_SuatChieu run = new GD_MuaVe_SuatChieu();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GD_MuaVe_SuatChieu() {
		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Mua Vé - Suất Chiếu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/avt.png"))); // Thay đổi đường dẫn
																									// ảnh của
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
		muaVeButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/tickets1.png")));
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
		btnPhim.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/film-reel.png")));
		btnPhim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_MuaVe_Phim gdMuaVePhim = new GD_MuaVe_Phim();
				gdMuaVePhim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdMuaVePhim.setLocationRelativeTo(null);
				gdMuaVePhim.setVisible(true);
				dispose();
			}
		});
		JButton btnGhe = new JButton("Chọn Ghế");
		btnGhe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGhe.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/chair.png")));
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
		btnThucAn.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/popcorn2.png")));
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
		btnSuatChieu.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/clapperboard2.png")));

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
		hoaDonButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/bill.png")));
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
		khachHangButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/customer1.png")));
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
		logoutButton.setIcon(new ImageIcon(GD_MuaVe_SuatChieu.class.getResource("/imgs/logout.png")));
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);

		JLabel lblNewLabel = new JLabel("Tra cứu suất chiếu");
		lblNewLabel.setFont(new Font("Open Sans", 1, 16));
		lblNewLabel.setBounds(45, 102, 152, 20);
		contentPane.add(lblNewLabel);

		JPanel panel12 = new JPanel();
		panel12.setBackground(new Color(255, 255, 0));
		panel12.setBounds(10, 464, 230, 37);
		panel12.setOpaque(false);
		contentPane.add(panel12);
		// Add JTextField below JCheckBox
		JTextField textField = new JTextField();
		textField.setFont(new Font("Open Sans", 0, 16));
		textField.setColumns(16); // You can adjust the column count based on your requirement
		panel12.add(textField);

		JPanel panel12_1 = new JPanel();
		panel12_1.setOpaque(false);
		panel12_1.setBackground(Color.YELLOW);
		panel12_1.setBounds(10, 187, 191, 37);
		contentPane.add(panel12_1);

		txtTuNgay = new JTextField();
		txtTuNgay.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTuNgay.setColumns(13);
		panel12_1.add(txtTuNgay);

		// Khởi tạo JDateChooser cho từ ngày
		tuNgayDateChooser = new JDateChooser();
		tuNgayDateChooser.setBounds(140, 194, 100, 29);
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
		panel12_2.setBounds(10, 140, 230, 37);
		panel12_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel12_2);

		JLabel lbltungay = new JLabel("Ngày hiển thị:");
		lbltungay.setFont(new Font("Open Sans", 0, 16));
		panel12_2.add(lbltungay);

		JPanel panel12_2_1 = new JPanel();
		panel12_2_1.setOpaque(false);
		panel12_2_1.setBackground(Color.YELLOW);
		panel12_2_1.setBounds(10, 234, 230, 37);
		contentPane.add(panel12_2_1);
		panel12_2_1.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lbltungay_1 = new JLabel("Phòng chiếu:");
		lbltungay_1.setFont(new Font("Open Sans", 0, 16));
		panel12_2_1.add(lbltungay_1);

		String[] phongChieuList = { "Phòng 1", "Phòng 2", "Phòng 3", "Phòng 4" };
		phongChieuComboBox = new JComboBox<>(phongChieuList);
		panel12_2_1.add(phongChieuComboBox);

		JPanel panel12_2_2 = new JPanel();
		panel12_2_2.setOpaque(false);
		panel12_2_2.setBackground(Color.YELLOW);
		panel12_2_2.setBounds(10, 281, 230, 37);
		contentPane.add(panel12_2_2);
		panel12_2_2.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lbltungay_2 = new JLabel("Theo trạng thái:");
		lbltungay_2.setFont(new Font("Open Sans", 0, 16));
		panel12_2_2.add(lbltungay_2);

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

		JPanel panel12_2_2_1 = new JPanel();
		panel12_2_2_1.setOpaque(false);
		panel12_2_2_1.setBackground(Color.YELLOW);
		panel12_2_2_1.setBounds(10, 429, 230, 37);
		contentPane.add(panel12_2_2_1);
		panel12_2_2_1.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lbltungay_2_1 = new JLabel("Theo tên phim:");
		lbltungay_2_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel12_2_2_1.add(lbltungay_2_1);

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
		String[] columnNames = { "STT", "Mã suất chiếu", "Tên phim", "Ngày chiếu", "Giờ chiếu", "Định dạng", "Ngôn ngữ",
				"Phòng chiếu", "Trạng thái" }; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		// Đặt chiều rộng cho cột "Tên phim"
		table.getColumnModel().getColumn(2).setPreferredWidth(200); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
																	// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 140, 900, 469); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		// Thêm dữ liệu vào bảng
		Object[] rowData = { "1", "SC00001", "Màn Sương Phủ Máu", "10-12-2019", "08:30 - 09:15", "3D", "Tiếng Anh",
				"Phòng 1", "Đã Chiếu" }; // Thay đổi dữ liệu tùy ý
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
		GD_MuaVe_Phim gdmvphim = new GD_MuaVe_Phim();
		gdmvphim.setLocationRelativeTo(null);
		gdmvphim.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
