package giaoDien.quanly;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class GD_QuanLy_Phim_Them extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Color customColor = new Color(0, 92, 111);
    private JLabel lblHinhAnh, lblQuocGia, lblThemPhim, lblTenPhim, lblLoaiPhim, lblNgayChieu, lblNgayHetHan, lblTuoi, lblThoiLuong, lblNgonNgu
    ,lblTrangThai, lblTien, lblSoLuongVe;
    private JButton btnUploadFile, btnXacNhan, btnHuyBo;
    private JTextField txtTenPhim, txtLoaiPhim, txtQuocGia, txtNgayChieu, txtNgayHetHan, txtTuoi, txtThoiLuong, txtNgonNgu, txtTrangThai
    ,txtTien, txtSoLuongVe;
    private boolean isCalendarVisible = false;
	private JDateChooser ngayChieuDateChooser, ngayHetHanDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
    
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		GD_QuanLy_Phim_Them run = new GD_QuanLy_Phim_Them();
		run.setVisible(true);
	}

	public GD_QuanLy_Phim_Them() {
//		initComponents();
		setBounds(100, 100, 787, 820);
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Phim - Thêm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Thêm panel nằm ngang ở trên cùng
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				String imagePath = "/imgs/negative-film.png";
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
				Image image = imageIcon.getImage();

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(10, 10, 172, 195);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);
		
		lblThemPhim = new JLabel("Thêm phim mới");
		lblThemPhim.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblThemPhim.setForeground(new Color(0, 0, 160));
		lblThemPhim.setBounds(192, 103, 200, 30);
		contentPane.add(lblThemPhim);
		
		JLabel lblNewLabel = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(192, 143, 228, 20);
		contentPane.add(lblNewLabel);

        // Tạo JButton "Upload File" và xử lý sự kiện khi nút được nhấn
        btnUploadFile = new JButton("Upload File");
        btnUploadFile.setBounds(192, 447, 165, 30);
        contentPane.add(btnUploadFile);

        // Tạo JLabel để hiển thị hình ảnh
        lblHinhAnh = new JLabel();
        lblHinhAnh.setBounds(192, 234, 165, 194);
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Tạo đường viền đen xung quanh
        contentPane.add(lblHinhAnh);
        
        lblTenPhim = new JLabel("Tên phim");
        lblTenPhim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTenPhim.setBounds(49, 501, 74, 30);
        contentPane.add(lblTenPhim);
        
        txtTenPhim = new JTextField();
        txtTenPhim.setBounds(135, 499, 288, 34);
        contentPane.add(txtTenPhim);
        txtTenPhim.setColumns(10);
        
        lblLoaiPhim = new JLabel("Loại phim");
        lblLoaiPhim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLoaiPhim.setBounds(47, 554, 74, 30);
        contentPane.add(lblLoaiPhim);
        
        lblQuocGia = new JLabel("Quốc gia");
        lblQuocGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuocGia.setBounds(52, 604, 74, 35);
        contentPane.add(lblQuocGia);
        
        txtLoaiPhim = new JTextField();
        txtLoaiPhim.setColumns(10);
        txtLoaiPhim.setBounds(135, 552, 288, 34);
        contentPane.add(txtLoaiPhim);
        
        txtQuocGia = new JTextField();
        txtQuocGia.setColumns(10);
        txtQuocGia.setBounds(135, 605, 288, 34);
        contentPane.add(txtQuocGia);
        
        lblNgayChieu = new JLabel("Ngày chiếu");
        lblNgayChieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNgayChieu.setBounds(457, 234, 90, 30);
        contentPane.add(lblNgayChieu);
        
        txtNgayChieu = new JTextField();
        txtNgayChieu.setColumns(10);
        txtNgayChieu.setBounds(550, 232, 166, 34);
        contentPane.add(txtNgayChieu);
        
        lblNgayHetHan = new JLabel("Ngày hết hạn");
        lblNgayHetHan.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNgayHetHan.setBounds(440, 287, 100, 30);
        contentPane.add(lblNgayHetHan);
        
        txtNgayHetHan = new JTextField();
        txtNgayHetHan.setColumns(10);
        txtNgayHetHan.setBounds(550, 285, 166, 34);
        contentPane.add(txtNgayHetHan);
        
        lblTuoi = new JLabel("Giới hạn tuổi");
        lblTuoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTuoi.setBounds(442, 340, 90, 30);
        contentPane.add(lblTuoi);
        
        txtTuoi = new JTextField();
        txtTuoi.setColumns(10);
        txtTuoi.setBounds(550, 338, 213, 34);
        contentPane.add(txtTuoi);
        
        txtThoiLuong = new JTextField();
        txtThoiLuong.setColumns(10);
        txtThoiLuong.setBounds(550, 499, 213, 34);
        contentPane.add(txtThoiLuong);
        
        lblThoiLuong = new JLabel("Thời lượng");
        lblThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblThoiLuong.setBounds(456, 501, 84, 30);
        contentPane.add(lblThoiLuong);
        
        lblNgonNgu = new JLabel("Ngôn ngữ");
        lblNgonNgu.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNgonNgu.setBounds(464, 554, 84, 30);
        contentPane.add(lblNgonNgu);
        
        txtNgonNgu = new JTextField();
        txtNgonNgu.setColumns(10);
        txtNgonNgu.setBounds(550, 552, 213, 34);
        contentPane.add(txtNgonNgu);
        
        lblTrangThai = new JLabel("Trạng thái");
        lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTrangThai.setBounds(463, 607, 84, 30);
        contentPane.add(lblTrangThai);
        
        txtTrangThai = new JTextField();
        txtTrangThai.setColumns(10);
        txtTrangThai.setBounds(550, 605, 213, 34);
        contentPane.add(txtTrangThai);
        
        txtTien = new JTextField();
        txtTien.setColumns(10);
        txtTien.setBounds(550, 393, 213, 34);
        contentPane.add(txtTien);
        
        lblTien = new JLabel("Giá tiền");
        lblTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTien.setBounds(479, 395, 60, 30);
        contentPane.add(lblTien);
        
        txtSoLuongVe = new JTextField();
        txtSoLuongVe.setColumns(10);
        txtSoLuongVe.setBounds(550, 446, 213, 34);
        contentPane.add(txtSoLuongVe);
        
        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setBounds(192, 665, 165, 30);
        contentPane.add(btnXacNhan);
        
        btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.setBounds(425, 665, 165, 30);
        contentPane.add(btnHuyBo);
        
        lblSoLuongVe = new JLabel("Số lượng vé");
        lblSoLuongVe.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSoLuongVe.setBounds(448, 449, 90, 30);
        contentPane.add(lblSoLuongVe);
        
        ngayChieuDateChooser = new JDateChooser();
        ngayChieuDateChooser.setBounds(663, 232, 100, 33);
        ngayChieuDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
        contentPane.add(ngayChieuDateChooser);
        
		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người dùng chọn ngày
        ngayChieuDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = ngayChieuDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtNgayChieu.setText(dateFormat.format(selectedDate));
			}
		});
        
        ngayHetHanDateChooser = new JDateChooser();
        ngayHetHanDateChooser.setBounds(663, 285, 100, 33);
        ngayHetHanDateChooser.getDateEditor().getUiComponent().setVisible(isCalendarVisible);
        contentPane.add(ngayHetHanDateChooser);
        
		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người dùng chọn ngày
        ngayHetHanDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = ngayHetHanDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				txtNgayHetHan.setText(dateFormat.format(selectedDate));
			}
		});

        btnUploadFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();

                // Hiển thị hình ảnh đã chọn lên JLabel
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
                lblHinhAnh.setIcon(new ImageIcon(image));
            }
        });
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				try {
					formWindowClosing(evt);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	private void formWindowClosing(java.awt.event.WindowEvent evt) throws UnknownHostException, ClassNotFoundException, IOException {// GEN-FIRST:event_formWindowClosing
		GD_QuanLy_Phim gdqlphim = new GD_QuanLy_Phim();
		gdqlphim.setLocationRelativeTo(null);
		gdqlphim.setVisible(true);
	}
}
