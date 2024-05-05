package giaoDien.quanly;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.TableModel;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import client_dao.ClientPhim_dao;
import enities.Phim;

import javax.swing.JComboBox;

public class GD_QuanLy_Phim_Them extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Color customColor = new Color(0, 92, 111);
    private JLabel lblHinhAnh, lblQuocGia, lblThemPhim, lblTenPhim, lblLoaiPhim, lblNgayChieu, lblNgayHetHan, lblTuoi, lblThoiLuong, lblNgonNgu
    ,lblTrangThai, lblTien, lblSoLuongVe;
    private JButton btnUploadFile, btnXacNhan, btnHuyBo;
    private JTextField txtTenPhim, txtLoaiPhim, txtQuocGia, txtNgayChieu, txtNgayHetHan, txtTuoi, txtThoiLuong, txtNgonNgu, txtTrangThai
    ,txtTien, txtSoLuongVe;
    private JComboBox comboBox;
    private boolean isCalendarVisible = false;
	private JDateChooser ngayChieuDateChooser, ngayHetHanDateChooser; // Thêm đối tượng JDateChooser cho từ ngày
	private List<Phim> listphim;
	private ClientPhim_dao clientphim;
	private DefaultTableModel tableModel;
	private JTable table;
	private String imagePath="";
	
	
	
	




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

		GD_QuanLy_Phim_Them run = new GD_QuanLy_Phim_Them();
		run.setVisible(true);
	}
	






	public GD_QuanLy_Phim_Them() throws UnknownHostException, IOException, ClassNotFoundException {
		initComponents();
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

        //Up ảnh
        btnUploadFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                 imagePath = selectedFile.getAbsolutePath();

                // Hiển thị hình ảnh đã chọn lên JLabel
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
                lblHinhAnh.setIcon(new ImageIcon(image));
                
                
            }
        });
        
        
        
        
        //add sự kiện xác nhận
        btnXacNhan.addActionListener(this);
       //btnHuyBo.addActionListener(this);
       btnHuyBo.addActionListener(e -> dispose());
    
    // Khởi tạo DefaultTableModel với các cột
    		String[] columnNames = {"Mã phim","MX","Tên phim", "Loại phim","Ngày chiếu","Ngày hết hạn", "Giá tiền","SL vé", "Tuổi","Thời lượng","Ngôn ngữ",
    				"Quốc gia", "Trạng thái","Hinh"}; // Thay đổi tên cột tùy ý
    		
    		tableModel = new DefaultTableModel(columnNames, 0);
    		
    	

    		// Khởi tạo JTable với DefaultTableModel
    		table = new JTable(tableModel);
    		// Đặt chiều rộng cho cột "Tên phim"
    		
    		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
    		JScrollPane scrollPane = new JScrollPane(table);
    		scrollPane.setBounds(192, 140, 954, 469); // Điều chỉnh tọa độ và kích thước của bảng

    		// Thêm bảng và JScrollPane vào contentPane
    		//contentPane.add(scrollPane);
    		
//    		Load Data
    		Socket socket = new Socket("192.168.100.4", 6789);
    		clientphim = new ClientPhim_dao(socket);
    		
    		listphim = clientphim.getListPhim();
    		loadDataToTable(listphim);
        
        
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
//	 private void xoaTrangTF(){
// 		txtTenPhim.setText("");
// 		txtLoaiPhim.setText("");
// 		txtNgayChieu.setText("");
// 		txtNgayHetHan.setText("");
// 		txtNgonNgu.setText("");
// 		txtQuocGia.setText("");
// 		txtSoLuongVe.setText("");
// 		txtTien.setText("");
// 		txtTrangThai.setText("");
// 		txtTuoi.setText("");
// 		txtThoiLuong.setText("");
// 		txtTrangThai.setText("");
// 	}	
	
	
	
	//Add Phim mới
		public void AddPhimMoi() throws Exception  {
			LocalDateTime now = LocalDateTime.now();
			  
		    
	        // Định dạng ngày tháng năm
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        String currentDate = now.format(dateFormatter);

	        // Định dạng giờ phút giây và millisecond
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
	        String currentTimeWithMillis = now.format(timeFormatter);

	        // Tạo ra maNV với kiểu là HD + ngày tháng năm + giờ phút giây và mill giây + 6 chuỗi ngẫu nhiên
	        String maPhim = "P" + currentDate + "" + currentTimeWithMillis + generateRandomString();
			
			
			String tenPhim = txtTenPhim.getText();
			String loaiPhim = txtLoaiPhim.getText();
			
			Date ngayChieutrenGD = ngayChieuDateChooser.getDate();
			Instant instant = ngayChieutrenGD.toInstant();
			LocalDate ngayChieu = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			String ngayChieuTrongTable = txtNgayChieu.getText();
			
			
			Date ngayHettrenGD = ngayHetHanDateChooser.getDate();
			Instant instant1 = ngayHettrenGD.toInstant();
			LocalDate ngayHetHan = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
			String ngayHetTrongTable = txtNgayHetHan.getText();
			
			
			
			// Lấy dữ liệu từ ô text và chuyển đổi thành kiểu int
			double giaTien=Double.parseDouble(txtTien.getText());
			int soLuongVe = Integer.parseInt(txtSoLuongVe.getText());
			int gioiHanTuoi =Integer.parseInt(txtTuoi.getText());
			int  thoiLuong=Integer.parseInt(txtThoiLuong.getText());
			
			String  ngonNgu=txtNgonNgu.getText();
			String quocGia =txtQuocGia.getText();
			String trangThaiPhim =txtTrangThai.getText();
			
			String hinhPhim=imagePath; 

			
				
			
			
			Phim ph=new Phim(maPhim, tenPhim, loaiPhim, ngayChieu, ngayHetHan, giaTien, soLuongVe, hinhPhim, gioiHanTuoi, thoiLuong, ngonNgu, quocGia, trangThaiPhim);
			clientphim.addPhim(ph);
			java.lang.Object [] rowData = {maPhim,tenPhim,loaiPhim,ngayChieuTrongTable,
					ngayHetTrongTable,giaTien,soLuongVe,gioiHanTuoi,
					thoiLuong,ngonNgu,quocGia,trangThaiPhim,hinhPhim};
			tableModel.addRow(rowData);
			
			
		}
		
		//Ràng buộc
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
			Date ngayChieutrenGD = ngayChieuDateChooser.getDate();
			
			if (ngayChieutrenGD == null) {
			    // Hiển thị thông báo lỗi trên giao diện người dùng
			    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày tháng năm trước khi tiếp tục.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			} else {
				Instant instant = ngayChieutrenGD.toInstant();
				LocalDate ngayChieu = instant.atZone(ZoneId.systemDefault()).toLocalDate();
				String ngayChieuTrongTable = txtNgayChieu.getText();
			}
			
			
			
			Date ngayHettrenGD = ngayHetHanDateChooser.getDate();
			if (ngayHettrenGD == null) {
			    // Hiển thị thông báo lỗi trên giao diện người dùng
			    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày tháng năm trước khi tiếp tục.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			} else {
			Instant instant1 = ngayHettrenGD.toInstant();
			LocalDate ngayHetHan = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
			String ngayHetTrongTable = txtNgayHetHan.getText();
			}
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
			try {
				if(checkEmpty()) {
				AddPhimMoi();
				// Hiển thị thông báo thành công
			    JOptionPane.showMessageDialog(this, "Thêm phim thành công!");
				GD_QuanLy_Phim gdqlphim = new GD_QuanLy_Phim();
				gdqlphim.setVisible(true);
				gdqlphim.setLocationRelativeTo(null);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Có lỗi");
				}
		        
		        // Đóng giao diện thêm
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else if(o.equals(btnHuyBo)){
			//xoaTrangTF();
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
