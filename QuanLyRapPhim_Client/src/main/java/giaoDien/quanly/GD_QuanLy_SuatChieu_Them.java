package giaoDien.quanly;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import client_dao.ClientPhim_dao;
import client_dao.ClientPhongChieu_dao;
import client_dao.ClientXuatChieu_dao;
import enities.Phim;
import enities.PhongChieuPhim;
import enities.XuatChieu;

//import enities.Phim;
//import enities.XuatChieu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GD_QuanLy_SuatChieu_Them extends JFrame {

	private JPanel contentPane;
	private boolean isCalendarVisible = false;
	private DefaultTableModel model;
	private List<String> dsTenPhim;
	private List<String> dsXuatChieu;
	private JDateChooser ngayChieuDate;
	private ClientXuatChieu_dao clientXC;
	private List<XuatChieu> listXC;
	private static int lastPhongChieuId = 0;
	private JTextField txtNgayChieu;
	private JComboBox<String> comboBoxMaPhim;
	private ClientPhongChieu_dao phongchieu_cl;
	private JComboBox comboBoxMaPhongChieu;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GD_QuanLy_SuatChieu.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		GD_QuanLy_SuatChieu_Them run = new GD_QuanLy_SuatChieu_Them();

		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public GD_QuanLy_SuatChieu_Them() throws UnknownHostException, IOException, ClassNotFoundException {

//		initComponents();
		setResizable(false);
		this.setLocationRelativeTo(null);
		;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 997, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				String imagePath = "/imgs/video-camera.png";
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
				Image image = imageIcon.getImage();

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(477, 10, 191, 195);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);

		JPanel pnl_danhSachSuatChieu = new JPanel();
		pnl_danhSachSuatChieu.setBackground(new Color(192, 192, 192));
		pnl_danhSachSuatChieu.setBounds(0, 10, 467, 643);
		contentPane.add(pnl_danhSachSuatChieu);
		pnl_danhSachSuatChieu.setLayout(null);

		JLabel lblDanhSchSut = new JLabel("Danh sách suất chiếu ");
		lblDanhSchSut.setForeground(new Color(0, 50, 100));
		lblDanhSchSut.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDanhSchSut.setBounds(26, 30, 280, 39);
		pnl_danhSachSuatChieu.add(lblDanhSchSut);

		JDateChooser ngayHienThiDateChooser_1 = new JDateChooser();
		ngayHienThiDateChooser_1.setBounds(50, 79, 347, 29);
		pnl_danhSachSuatChieu.add(ngayHienThiDateChooser_1);

		JComboBox cbx_tenPhim_1 = new JComboBox();
		cbx_tenPhim_1.setBounds(50, 134, 347, 27);
		pnl_danhSachSuatChieu.add(cbx_tenPhim_1);

		String[] colName = { "Gio Bat Dau", "Gio Ket Thuc", "Ten Phim" };

		model = new DefaultTableModel(colName, 0);

		JLabel lbl_icon = new JLabel();
		lbl_icon.setBounds(490, 26, 193, 224);

		JLabel lblNewLabel = new JLabel("Thêm xuất chiếu ");
		lblNewLabel.setForeground(new Color(0, 50, 100));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblNewLabel.setBounds(693, 69, 280, 39);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(691, 128, 282, 31);
		contentPane.add(lblNewLabel_1);

		JLabel lblMaPhongChieu = new JLabel("Mã Phòng Chiếu");
		lblMaPhongChieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaPhongChieu.setBounds(508, 285, 137, 25);
		contentPane.add(lblMaPhongChieu);
		comboBoxMaPhongChieu = new JComboBox();
		comboBoxMaPhongChieu.setBounds(655, 288, 318, 25);
		contentPane.add(comboBoxMaPhongChieu);
		loadPhongChieuToComboBox(comboBoxMaPhongChieu);

		JLabel lblMaPhongPhim = new JLabel("Mã Phim");
		lblMaPhongPhim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaPhongPhim.setBounds(508, 239, 137, 25);
		contentPane.add(lblMaPhongPhim);
		comboBoxMaPhim = new JComboBox();
		comboBoxMaPhim.setBounds(655, 242, 318, 25);
		contentPane.add(comboBoxMaPhim);
		loadPhimToComboBox(comboBoxMaPhim);

		JLabel lbl_dinhDang = new JLabel("Định dạng:");
		lbl_dinhDang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_dinhDang.setBounds(508, 496, 137, 25);
		contentPane.add(lbl_dinhDang);
		// tao mang dinh dang
		String[] dinhDang_arr = { "2D", "3D" };

		JComboBox<String> cbx_dinhDang = new JComboBox<>(dinhDang_arr);
		cbx_dinhDang.setBounds(655, 497, 318, 31);
		contentPane.add(cbx_dinhDang);

		// Ngày chiếu
		JLabel lbl_ngayChieu = new JLabel("Ngày chiếu :");
		lbl_ngayChieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_ngayChieu.setBounds(508, 338, 129, 25);
		contentPane.add(lbl_ngayChieu);
//        ngayChieuDate = new JDateChooser();
//		ngayChieuDate.setBounds(655, 338, 318, 25);
//		contentPane.add(ngayChieuDate);
		txtNgayChieu = new JTextField();
		txtNgayChieu.setColumns(10);
		txtNgayChieu.setBounds(653, 338, 280, 25);
		contentPane.add(txtNgayChieu);
		ngayChieuDate = new JDateChooser();
		ngayChieuDate.setBounds(655, 338, 318, 25);
		ngayChieuDate.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
		contentPane.add(ngayChieuDate);
		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
		ngayChieuDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = ngayChieuDate.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtNgayChieu.setText(dateFormat.format(selectedDate));
			}
		});

		JLabel lbl_gioBatDau = new JLabel("Giờ bắt đầu:");
		lbl_gioBatDau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_gioBatDau.setBounds(508, 391, 143, 25);
		contentPane.add(lbl_gioBatDau);

		// Tạo mảng giờ cho JComboBox
		// Tạo mảng giờ và phút cho JComboBox
		String[] timeSlots = new String[24 * 60]; // 24 giờ * 4 slot trong mỗi giờ
		int slotIndex = 0;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j++) { // Tăng phút mỗi lần 1 phút
				timeSlots[slotIndex++] = String.format("%02d:%02d", i, j);
			}
		}

		JComboBox<String> cbx_gioBatDau = new JComboBox<>(timeSlots);
		cbx_gioBatDau.setBounds(655, 392, 318, 31);
		contentPane.add(cbx_gioBatDau);

		JLabel lbl_gioKetThuc = new JLabel("Giờ kết thúc:");
		lbl_gioKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_gioKetThuc.setBounds(508, 440, 143, 25);
		contentPane.add(lbl_gioKetThuc);

		JComboBox<String> cbx_gioKetThuc = new JComboBox<>(timeSlots);
		cbx_gioKetThuc.setBounds(655, 441, 318, 31);
		contentPane.add(cbx_gioKetThuc);

		JLabel lbl_trangThai = new JLabel("Trạng Thái:");
		lbl_trangThai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_trangThai.setBounds(508, 552, 129, 25);
		contentPane.add(lbl_trangThai);
		String[] trangThai_arr = { "Đang chiếu", "Chuẩn bị chiếu" };

		JComboBox<String> cbx_trangThai = new JComboBox<String>(trangThai_arr);
		cbx_trangThai.setBounds(655, 554, 320, 29);
		contentPane.add(cbx_trangThai);

		// Load Data
		Socket socket = new Socket("192.168.100.150", 6789);
		clientXC = new ClientXuatChieu_dao(socket);

		JButton btn_Them = new JButton("Thêm");
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Them.setBounds(527, 622, 124, 31);
		contentPane.add(btn_Them);
		btn_Them.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now();
				  
			    
		        // Định dạng ngày tháng năm
		        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		        String currentDate = now.format(dateFormatter);

		        // Định dạng giờ phút giây và millisecond
		        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
		        String currentTimeWithMillis = now.format(timeFormatter);

		        // Tạo ra maNV với kiểu là HD + ngày tháng năm + giờ phút giây và mill giây + 6 chuỗi ngẫu nhiên
		        String maXuatChieu = "XC" + currentDate + "" + currentTimeWithMillis + generateRandomString();
			

				// String dsPhim = comboBoxMaPhim.getSelectedItem() != null ?
				// comboBoxMaPhim.getSelectedItem().toString() : "P001";
				// String maPhim = "P001";
				String dsphim = comboBoxMaPhim.getSelectedItem().toString();
				Phim phim = new Phim();
				phim.setMaPhim(dsphim);

				// String dsPhongChieu = comboBoxMaPhongChieu.getSelectedItem() != null ?
				// comboBoxMaPhim.getSelectedItem().toString() : "X001";
				String dsPhongChieu = comboBoxMaPhongChieu.getSelectedItem().toString();
				// String maPhong = "PC001";
				// Tạo đối tượng Phim và PhongChieuPhim dựa trên mã phim và mã phòng chiếu
				PhongChieuPhim phongchieu = new PhongChieuPhim();
				phongchieu.setMaPhongChieu(dsPhongChieu);

				Date ngayChieutrenGD = ngayChieuDate.getDate();
				
				if (ngayChieutrenGD == null) {
				    // Hiển thị thông báo lỗi trên giao diện người dùng
				    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày tháng năm trước khi tiếp tục.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				} else {
				Instant instant = ngayChieutrenGD.toInstant();
				LocalDate ngayChieu = instant.atZone(ZoneId.systemDefault()).toLocalDate();
				String ngayChieuTrongTable = txtNgayChieu.getText();

				String gioChieuString = cbx_gioBatDau.getSelectedItem().toString();
				Time gioChieu = Time.valueOf(gioChieuString + ":00");

				String gioKetThucString = cbx_gioKetThuc.getSelectedItem().toString();
				Time gioKetThuc = Time.valueOf(gioKetThucString + ":00");

				String dinhDang = cbx_dinhDang.getSelectedItem().toString();

				String trangThai = cbx_trangThai.getSelectedItem().toString();

				// XuatChieu xc = new XuatChieu(maXuatChieu, maPhim, maPhong, dinhDang,
				// ngayChieu, gioChieu, gioKetThuc, trangThai);
				XuatChieu xc = new XuatChieu(maXuatChieu, phim, phongchieu, dinhDang, ngayChieu, gioChieu, gioKetThuc,
						trangThai);
				try {
					clientXC.addXC(xc);
					JOptionPane.showMessageDialog(btn_Them, "Thêm Thành Công!");
					GD_QuanLy_SuatChieu gd_SuatChieu = new GD_QuanLy_SuatChieu();
					gd_SuatChieu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gd_SuatChieu.setLocationRelativeTo(null);
					gd_SuatChieu.setVisible(true);
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});

		JButton btn_xoa = new JButton("Hủy bỏ");
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_xoa.setBounds(840, 622, 124, 31);
		contentPane.add(btn_xoa);
		btn_xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GD_QuanLy_SuatChieu gd_SuatChieu;
				try {
					gd_SuatChieu = new GD_QuanLy_SuatChieu();
					gd_SuatChieu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					gd_SuatChieu.setLocationRelativeTo(null);
					gd_SuatChieu.setVisible(true);
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

	public void loadPhongChieuToComboBox(JComboBox<String> comboBox) {
		try {
			// Tạo một đối tượng ClientPhongChieu_dao và kết nối đến server
			Socket socket = new Socket("192.168.100.150", 6789);
			ClientPhongChieu_dao clientPhongChieuDao = new ClientPhongChieu_dao(socket);

			// Gọi phương thức getListPhongChieu() để lấy danh sách phòng chiếu từ server
			List<PhongChieuPhim> danhSachPhongChieu = clientPhongChieuDao.getListPhongChieu();

			// Xóa tất cả các mục hiện có trong combobox
			comboBox.removeAllItems();

			// Thêm tên của từng phòng chiếu vào combobox
			for (PhongChieuPhim phongChieu : danhSachPhongChieu) {
				comboBox.addItem(phongChieu.getMaPhongChieu()); // Sử dụng phương thức getTenPhongChieu() hoặc getter
																// tương ứng
			}

			// Đóng kết nối tới server
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace(); // Xử lý ngoại lệ nếu có
		}
	}

	public void loadPhimToComboBox(JComboBox<String> comboBox) {
		try {
			// Tạo một đối tượng ClientPhongChieu_dao và kết nối đến server
			Socket socket = new Socket("192.168.100.150", 6789);
			ClientPhim_dao clientPhimDao = new ClientPhim_dao(socket);

			// Gọi phương thức getListPhongChieu() để lấy danh sách phòng chiếu từ server
			List<Phim> danhSachPhim = clientPhimDao.getListPhim();

			// Xóa tất cả các mục hiện có trong combobox
			comboBox.removeAllItems();

			// Thêm tên của từng phòng chiếu vào combobox
			for (Phim phim : danhSachPhim) {
				comboBox.addItem(phim.getMaPhim()); // Sử dụng phương thức getTenPhongChieu() hoặc getter tương ứng
			}

			// Đóng kết nối tới server
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace(); // Xử lý ngoại lệ nếu có
		}
	}
	
	
	public static String generateRandomString() {
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);

        // Sinh ngẫu nhiên một ký tự in hoa
        char uppercaseChar = uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length()));
        sb.append(uppercaseChar);

        // Sinh ngẫu nhiên một ký tự chữ thường
        char lowercaseChar = lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length()));
        sb.append(lowercaseChar);

        // Sinh ngẫu nhiên một số
        char digitChar = digits.charAt(random.nextInt(digits.length()));
        sb.append(digitChar);

        // Sinh các ký tự còn lại ngẫu nhiên
        for (int i = 3; i < 6; i++) {
            String allChars = lowercaseLetters + uppercaseLetters + digits;
            char randomChar = allChars.charAt(random.nextInt(allChars.length()));
            sb.append(randomChar);
        }

        // Đảo lộn chuỗi để tránh các ký tự theo thứ tự nhất định
        String shuffledString = shuffleString(sb.toString());

        return shuffledString;
    }

    // Hàm đảo lộn chuỗi
    private static String shuffleString(String input) {
        char[] charArray = input.toCharArray();
        Random random = new Random();

        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }

        return new String(charArray);
    }

}
