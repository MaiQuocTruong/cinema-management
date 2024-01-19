package giaoDien.quanly;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

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

public class GD_QuanLy_Phim_Them extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Color customColor = new Color(0, 92, 111);
    private JLabel lblHinhAnh;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    
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
		setBounds(100, 100, 787, 820);
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Phim - Thêm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
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
		topPanel.setBounds(10, 10, 195, 195);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);
		
		JLabel lblThemPhim = new JLabel("Thêm phim mới");
		lblThemPhim.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblThemPhim.setForeground(new Color(0, 0, 160));
		lblThemPhim.setBounds(215, 103, 200, 30);
		contentPane.add(lblThemPhim);
		
		JLabel lblNewLabel = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(215, 143, 228, 13);
		contentPane.add(lblNewLabel);

        // Tạo JButton "Upload File" và xử lý sự kiện khi nút được nhấn
        JButton btnUploadFile = new JButton("Upload File");
        btnUploadFile.setBounds(135, 454, 165, 30);
        contentPane.add(btnUploadFile);

        // Tạo JLabel để hiển thị hình ảnh
        lblHinhAnh = new JLabel();
        lblHinhAnh.setBounds(135, 232, 165, 212);
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Tạo đường viền đen xung quanh
        contentPane.add(lblHinhAnh);
        
        JLabel lblNewLabel_1 = new JLabel("Tên phim");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(47, 498, 74, 30);
        contentPane.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(135, 494, 288, 34);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Thời lượng");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(37, 551, 84, 30);
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Giới hạn tuổi");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1.setBounds(21, 601, 100, 35);
        contentPane.add(lblNewLabel_1_1_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(135, 547, 288, 34);
        contentPane.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(135, 602, 288, 34);
        contentPane.add(textField_2);
        
        JLabel lblNewLabel_1_2 = new JLabel("Diễn viên");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2.setBounds(466, 232, 74, 30);
        contentPane.add(lblNewLabel_1_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(550, 232, 213, 34);
        contentPane.add(textField_3);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Nhà sản xuất");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2_1.setBounds(440, 291, 100, 30);
        contentPane.add(lblNewLabel_1_2_1);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(550, 287, 213, 34);
        contentPane.add(textField_4);
        
        JLabel lblNewLabel_1_2_2 = new JLabel("Quốc gia");
        lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2_2.setBounds(466, 342, 74, 30);
        contentPane.add(lblNewLabel_1_2_2);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(550, 338, 213, 34);
        contentPane.add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(550, 393, 213, 34);
        contentPane.add(textField_6);
        
        JLabel lblNewLabel_1_2_2_1 = new JLabel("Trạng thái");
        lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2_2_1.setBounds(456, 397, 84, 30);
        contentPane.add(lblNewLabel_1_2_2_1);

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
}
