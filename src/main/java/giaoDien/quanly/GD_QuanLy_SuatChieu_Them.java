package giaoDien.quanly;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.Phim_dao;
import enities.Phim;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_QuanLy_SuatChieu_Them extends JFrame {

	private JPanel contentPane;
	private boolean isCalendarVisible = false;
	private DefaultTableModel model;
	private JTable table;
	private Phim_dao phim_dao;
	private List<String> dsTenPhim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QuanLy_SuatChieu_Them frame = new GD_QuanLy_SuatChieu_Them();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_QuanLy_SuatChieu_Them() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		String[] colName = {"Gio Bat Dau","Gio Ket Thuc","Ten Phim"};
		
		model = new DefaultTableModel(colName,0);
		table = new JTable(model);
		
		JScrollPane scrPane_danhSach = new JScrollPane(table);
		scrPane_danhSach.setBounds(10, 189, 447, 426);
		pnl_danhSachSuatChieu.add(scrPane_danhSach);
		
		JLabel lbl_icon = new JLabel();
		lbl_icon.setBounds(490, 26, 193, 224);
		
		
        
        // Load hình ảnh từ file
        ImageIcon imageIcon = new ImageIcon("C:\\loc-projects\\cinema-management\\src\\main\\resources\\imgs\\video-camera.png");
        Image image = imageIcon.getImage(); // Lấy hình ảnh từ ImageIcon
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        // Đặt hình ảnh cho nhãn
        lbl_icon.setIcon(imageIcon);

        // Căn giữa hình ảnh trong nhãn
        lbl_icon.setHorizontalAlignment(JLabel.CENTER);
        lbl_icon.setVerticalAlignment(JLabel.CENTER);
        imageIcon.setImage(scaledImage);
        contentPane.add(lbl_icon);
        
        JLabel lblNewLabel = new JLabel("Cập nhật xuất chiếu ");
        lblNewLabel.setForeground(new Color(0, 50, 100));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblNewLabel.setBounds(693, 69, 280, 39);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Vui lòng nhập đầy đủ thông tin");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(691, 128, 282, 31);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Tên phim:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(512, 295, 115, 25);
        contentPane.add(lblNewLabel_2);
        
        JComboBox cbx_tenPhim = new JComboBox();
        cbx_tenPhim.setBounds(655, 295, 318, 27);
        
        phim_dao = new Phim_dao();
        dsTenPhim = new ArrayList<String>();
        try {
        	phim_dao.setUp();
        	List<Phim> dsph = phim_dao.getServices();
            for(Phim ph : dsph) {
            	cbx_tenPhim.addItem(ph.getTenPhim());
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
       
        
        contentPane.add(cbx_tenPhim);
        
        JLabel lblNewLabel_2_1 = new JLabel("Phòng chiếu:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1.setBounds(512, 348, 139, 25);
        contentPane.add(lblNewLabel_2_1);
        
        JComboBox cbx_phongChieu = new JComboBox();
        cbx_phongChieu.setBounds(655, 347, 318, 31);
        contentPane.add(cbx_phongChieu);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Định dạng:");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2_1_1.setBounds(512, 401, 139, 25);
        contentPane.add(lblNewLabel_2_1_1);
        
        JComboBox cbx_dinhDang = new JComboBox();
        cbx_dinhDang.setBounds(655, 400, 318, 31);
        contentPane.add(cbx_dinhDang);
        
        JLabel lblNewLabel_3 = new JLabel("Ngày chiếu :");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(512, 457, 125, 25);
        contentPane.add(lblNewLabel_3);
		
		JLabel lbl_gioBatDau = new JLabel("Giờ băt đầu:");
		lbl_gioBatDau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_gioBatDau.setBounds(512, 512, 139, 25);
		contentPane.add(lbl_gioBatDau);
		
		JComboBox cbx_gioBatDau = new JComboBox();
		cbx_gioBatDau.setBounds(655, 511, 318, 31);
		contentPane.add(cbx_gioBatDau);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Giờ kết thúc:");
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_2.setBounds(512, 564, 139, 25);
		contentPane.add(lblNewLabel_2_1_1_2);
		
		JComboBox cbx_gioKetThuc = new JComboBox();
		cbx_gioKetThuc.setBounds(655, 563, 318, 31);
		contentPane.add(cbx_gioKetThuc);
		
		JButton btn_capNhat = new JButton("Cập nhật");
		btn_capNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_capNhat.setBounds(527, 622, 124, 31);
		contentPane.add(btn_capNhat);
		
		btn_capNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_xoa.setBounds(840, 622, 124, 31);
		contentPane.add(btn_xoa);
		
		JDateChooser ngayChieuDate = new JDateChooser();
		ngayChieuDate.setBounds(655, 457, 318, 29);
		contentPane.add(ngayChieuDate);
		
		
	}
}
