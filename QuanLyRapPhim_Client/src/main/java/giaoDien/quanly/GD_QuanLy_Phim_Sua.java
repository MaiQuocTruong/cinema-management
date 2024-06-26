package giaoDien.quanly;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import client_dao.ClientPhim_dao;
import enities.NhanVien;
import enities.Phim;

public class GD_QuanLy_Phim_Sua extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Color customColor = new Color(0, 92, 111);
	private JLabel lblHinhAnh, lblQuocGia, lblThemPhim, lblTenPhim, lblLoaiPhim, lblNgayChieu, lblNgayHetHan, lblTuoi,
			lblThoiLuong, lblNgonNgu, lblTrangThai, lblTien, lblSoLuongVe;
	private JButton btnUploadFile, btnXacNhan, btnHuyBo;
	private JTextField txtTenPhim, txtLoaiPhim, txtQuocGia, txtNgayChieu, txtNgayHetHan, txtTuoi, txtThoiLuong,
			txtNgonNgu, txtTrangThai, txtTien, txtSoLuongVe;
	private boolean isCalendarVisible = false;
	private JDateChooser ngayChieuDateChooser, ngayHetHanDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private List<Phim> listphim;
	private ClientPhim_dao clientphim;
	private DefaultTableModel tableModel;
	private JTable table;
	private String imagePath="";
	private JComboBox comboBox ;

//// Các phương thức và thành phần khác của giao diện
	
	public void updateFields(String tenPhim, String loaiPhim, String quocGia, String ngonNgu, String trangThaiPhim,
            int thoiLuong, double giaTien, int soLuongVe, int gioiHanTuoi, String ngayChieu, String ngayHetHan,Object value) {
// Hiển thị dữ liệu từ các tham số lên các JTextField
txtTenPhim.setText(tenPhim);
txtLoaiPhim.setText(loaiPhim);
txtQuocGia.setText(quocGia);
txtNgonNgu.setText(ngonNgu);
txtTrangThai.setText(trangThaiPhim);
txtThoiLuong.setText(Integer.toString(thoiLuong));
txtTien.setText(Double.toString(giaTien));
txtSoLuongVe.setText(Integer.toString(soLuongVe));
txtTuoi.setText(Integer.toString(gioiHanTuoi));
txtNgayChieu.setText(ngayChieu);
txtNgayHetHan.setText(ngayHetHan);

if(value!=null) {
	imagePath=value.toString();
	ImageIcon imageIcon = new ImageIcon(imagePath);
	Image image = imageIcon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(),
			Image.SCALE_SMOOTH);
	lblHinhAnh.setIcon(new ImageIcon(image));
}else {
	System.out.println("Lỗi!");
}


	}



// Các phương thức và thành phần khác của giao diện

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
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

		GD_QuanLy_Phim_Sua run = new GD_QuanLy_Phim_Sua();
		run.setVisible(true);
	}

	public GD_QuanLy_Phim_Sua() throws UnknownHostException, IOException, ClassNotFoundException {
		initComponents();
		setBounds(100, 100, 787, 820);
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Phim - Sửa");
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

		lblThemPhim = new JLabel("Cập nhật phim");
		lblThemPhim.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblThemPhim.setForeground(new Color(0, 0, 160));
		lblThemPhim.setBounds(192, 105, 200, 30);
		contentPane.add(lblThemPhim);

		JLabel lblNewLabel = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(192, 145, 228, 20);
		contentPane.add(lblNewLabel);

		// Tạo JButton "Upload File" và xử lý sự kiện khi nút được nhấn
		btnUploadFile = new JButton("Upload File");
		btnUploadFile.setBounds(192, 397, 165, 30);
		contentPane.add(btnUploadFile);

		// Tạo JLabel để hiển thị hình ảnh
		lblHinhAnh = new JLabel();
		lblHinhAnh.setBounds(192, 175, 165, 212);
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

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
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

		// Thêm sự kiện cho JDateChooser để cập nhật giá trị vào textfield khi người
		// dùng chọn ngày
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
				 imagePath = selectedFile.getAbsolutePath();

				// Hiển thị hình ảnh đã chọn lên JLabel
				ImageIcon imageIcon = new ImageIcon(imagePath);
				Image image = imageIcon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(),
						Image.SCALE_SMOOTH);
				lblHinhAnh.setIcon(new ImageIcon(image));
			}
		});
		
//        JLabel lblMaSC = new JLabel("Mã suất chiếu");
//        lblMaSC.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        lblMaSC.setBounds(23, 446, 100, 30);
//        contentPane.add(lblMaSC);
//        
//         comboBox = new JComboBox();
//        comboBox.setBounds(135, 446, 288, 34);
//        contentPane.add(comboBox);
        
        
        //add sự kiện xác nhận
        btnXacNhan.addActionListener(this);
       //btnHuyBo.addActionListener(this);
       btnHuyBo.addActionListener(e -> dispose());
    

//    		Load Data
    		Socket socket = new Socket("192.168.100.4", 6789);
    		clientphim = new ClientPhim_dao(socket);
    		

    	
        
        
	}

	private void updatePhim() throws ClassNotFoundException, IOException {
	    // Retrieve the name of the film to be updated (assuming it's unique)
	    String tenPhim = txtTenPhim.getText();
	    
	    // Find the film object by its name
	    Phim phimToUpdate = clientphim.findFilmonTen(tenPhim);
	    
	    // Check if the film object exists
	    if (phimToUpdate != null) {
	        // Update the fields of the found film object
	        phimToUpdate.setLoaiPhim(txtLoaiPhim.getText());
	        phimToUpdate.setQuocGia(txtQuocGia.getText());
	        phimToUpdate.setNgonNgu(txtNgonNgu.getText());
	        phimToUpdate.setTrangThaiPhim(txtTrangThai.getText());
	        phimToUpdate.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
	        phimToUpdate.setGiaTien(Double.parseDouble(txtTien.getText()));
	        phimToUpdate.setSoLuongVe(Integer.parseInt(txtSoLuongVe.getText()));
	        phimToUpdate.setGioiHanTuoi(Integer.parseInt(txtTuoi.getText()));
	       //phimToUpdate.setNgayChieu(txtNgayChieu.getText());
        //phimToUpdate.setNgayHetHan(txtNgayHetHan.getText());
	        phimToUpdate.setHinhPhim(imagePath);
	        // Call the method to update the film in the data source
	        clientphim.updatePhim(phimToUpdate);
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy phim có tên: " + tenPhim);
	    }
	}

	


	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				try {
					formWindowClosing(evt);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
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
	
	private void formWindowClosing(java.awt.event.WindowEvent evt) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {// GEN-FIRST:event_formWindowClosing
		GD_QuanLy_Phim gdqlphim = new GD_QuanLy_Phim();
		gdqlphim.setLocationRelativeTo(null);
		gdqlphim.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		java.lang.Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			
			if (checkEmpty()) {
				try {
					updatePhim();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, " cập nhật thành công!");
				GD_QuanLy_Phim gdqlphim;
				try {
					gdqlphim = new GD_QuanLy_Phim();
					gdqlphim.setVisible(true);
					gdqlphim.setLocationRelativeTo(null);
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(this, "lỗi!");
			}
		
			
		
		
		}
	
		}
	
	private Boolean checkEmpty() {
		String tenPhim = txtTenPhim.getText();
		String loaiPhim = txtLoaiPhim.getText();
		String  ngonNgu=txtNgonNgu.getText();
		String quocGia =txtQuocGia.getText();
		String trangThaiPhim =txtTrangThai.getText();
		String hinhPhim=imagePath;
		double giaTien = 0; // Giá trị mặc định là 0
		int gioiHanTuoi=0;
		int soLuongVe=0;
		int  thoiLuong=0;
		
	    // Kiểm tra xem chuỗi giá tiền có rỗng không trước khi chuyển đổi
	    if (!txtTien.getText().isEmpty() || !txtTuoi.getText().isEmpty() || !txtSoLuongVe.getText().isEmpty() || !txtThoiLuong.getText().isEmpty()) {
	        try {
	            giaTien = Double.parseDouble(txtTien.getText());
	            gioiHanTuoi =Integer.parseInt(txtTuoi.getText());
	            soLuongVe = Integer.parseInt(txtSoLuongVe.getText());
	            thoiLuong=Integer.parseInt(txtThoiLuong.getText());
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ nếu giá trị không hợp lệ
	            e.printStackTrace();
	            // Hiển thị thông báo hoặc thực hiện xử lý khác tùy ý
	        }
	    }
		
		if (tenPhim.isEmpty() || loaiPhim.isEmpty() || quocGia.isEmpty() || ngonNgu.isEmpty() || trangThaiPhim.isEmpty() 
				|| hinhPhim.isEmpty() || giaTien==0 || gioiHanTuoi==0 || soLuongVe==0 || thoiLuong==0 ) {
			return showErrorTx(null,"Không được để trống");
		}
		return true;
	}
	private Boolean showErrorTx(JTextField tx, String errorInfo) {
		JOptionPane.showMessageDialog(tx, errorInfo);
		tx.requestFocus();
		return false;
	}
	
	// Trong lớp GD_QuanLy_Phim_Sua:

//	public void layDuLieuSua() {
//	    // Retrieve updated data from text fields
////	    String tenPhim = txtTenPhim.getText();
////	    String loaiPhim = txtLoaiPhim.getText();
////	    String quocGia = txtQuocGia.getText();
////	    String ngonNgu = txtNgonNgu.getText();
////	    String trangThaiPhim = txtTrangThai.getText();
////	    int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
////	    double giaTien = Double.parseDouble(txtTien.getText());
////	    int soLuongVe = Integer.parseInt(txtSoLuongVe.getText());
////	    int gioiHanTuoi = Integer.parseInt(txtTuoi.getText());
////	    String ngayChieu = txtNgayChieu.getText();
////	    String ngayHetHan = txtNgayHetHan.getText();
//	    
//	    try {
//	        // Call the method to update data in your data source
//	    		if (checkEmpty()) {
//	    			updatePhim();
//	    			JOptionPane.showMessageDialog(this, "Dữ liệu đã được cập nhật thành công!");
//	    			// Close the current window
//	    	        this.dispose();
//				}else {
//					JOptionPane.showMessageDialog(this, "lỗi");
//				}
//	    		
//	    	
//	        
//	        
//	        // Display success message
//	        
//	        
//	        
//	    } catch (Exception e) {
//	        // Handle any errors or exceptions that may occur during the update process
//	        e.printStackTrace();
//	        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật dữ liệu!");
//	    }
//	}



}
