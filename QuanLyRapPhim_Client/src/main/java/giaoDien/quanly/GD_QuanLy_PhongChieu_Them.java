package giaoDien.quanly;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client_dao.ClientPhongChieu_dao;
import enities.PhongChieuPhim;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GD_QuanLy_PhongChieu_Them extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenPC;
	private JTextField txtSucChua;
	private JTextField txtPhongChieu;
	private JButton btnXacNhan;
	private JButton  btnHuyBo;
	private ClientPhongChieu_dao clientPC_dao;
	private List<PhongChieuPhim> dsPhongChieu;
    private static int lastPhongChieuId = 0; // Biến toàn cục để lưu trữ số cuối cùng của mã phòng chiếu
	private String maPhongChieu; // Thêm trường mã phòng chiếu

	/**
	 * Launch the application.
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		GD_QuanLy_PhongChieu_Them run = new GD_QuanLy_PhongChieu_Them();
		run.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public GD_QuanLy_PhongChieu_Them() throws UnknownHostException, IOException, ClassNotFoundException {
		setTitle("Thêm/Sửa phòng chiếu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAnh = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				String imagePath = "/imgs/video-camera-png-35733.png";
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
				Image image = imageIcon.getImage();

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panelAnh.setOpaque(false);
		panelAnh.setBounds(10, 10, 195, 195);
		panelAnh.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(panelAnh);
		
		JLabel lblThemSuaPC = new JLabel("Thêm phòng chiếu");
		lblThemSuaPC.setForeground(new Color(0, 0, 160));
		lblThemSuaPC.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThemSuaPC.setBounds(282, 10, 195, 20);
		contentPane.add(lblThemSuaPC);
		
		JLabel lblNhapThongTin = new JLabel("Vui lòng nhập đầy đủ thông tin");
		lblNhapThongTin.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNhapThongTin.setBounds(289, 29, 178, 20);
		contentPane.add(lblNhapThongTin);
		
		JLabel lblTenPC = new JLabel("Tên phòng chiếu:");
		lblTenPC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenPC.setBounds(215, 59, 111, 26);
		contentPane.add(lblTenPC);
		
		txtTenPC = new JTextField();
		txtTenPC.setBounds(327, 59, 200, 26);
		contentPane.add(txtTenPC);
		txtTenPC.setColumns(10);
		
		JLabel lblSucChua = new JLabel("Sức chứa:");
		lblSucChua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSucChua.setBounds(259, 98, 69, 26);
		contentPane.add(lblSucChua);
		
		txtSucChua = new JTextField();
		txtSucChua.setColumns(10);
		txtSucChua.setBounds(327, 98, 200, 26);
		contentPane.add(txtSucChua);
		
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBackground(Color.CYAN);
		btnXacNhan.setBounds(215, 174, 111, 31);
		contentPane.add(btnXacNhan);
		
		JLabel lblMaPhongChieu = new JLabel("Mã PC:");
		lblMaPhongChieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaPhongChieu.setBounds(280, 137, 46, 27);
		contentPane.add(lblMaPhongChieu);
		
        String maPC = "PC" + String.format("%03d", lastPhongChieuId);
		txtPhongChieu = new JTextField(); 
		txtPhongChieu.setEnabled(false);
		txtPhongChieu.setColumns(10);
		txtPhongChieu.setBounds(327, 137, 200, 26);
		contentPane.add(txtPhongChieu);
		
		btnHuyBo = new JButton("Hủy bỏ");
		btnHuyBo.setBackground(new Color(240, 240, 240));
		btnHuyBo.setBounds(416, 174, 111, 31);
		contentPane.add(btnHuyBo);
		
		
		btnXacNhan.addActionListener(this);
		btnHuyBo.addActionListener(this);
		
		Socket socket = new Socket("192.168.2.20",6789);
		clientPC_dao = new ClientPhongChieu_dao(socket);
		
		dsPhongChieu = clientPC_dao.getListPhongChieu();
		for (PhongChieuPhim phongChieuPhim : dsPhongChieu) {
            int id = Integer.parseInt(phongChieuPhim.getMaPhongChieu().substring(2));
            if (id > lastPhongChieuId) {
                lastPhongChieuId = id;
            }
        }
	}
	
	public void setMaPhongChieu(String maPhongChieu) {
		this.maPhongChieu = maPhongChieu;
		// Hiển thị mã phòng chiếu lên giao diện
		txtPhongChieu.setText(maPhongChieu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXacNhan)) {
			LocalDateTime now = LocalDateTime.now();
			  
		    
	        // Định dạng ngày tháng năm
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        String currentDate = now.format(dateFormatter);

	        // Định dạng giờ phút giây và millisecond
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
	        String currentTimeWithMillis = now.format(timeFormatter);

	        // Tạo ra maNV với kiểu là HD + ngày tháng năm + giờ phút giây và mill giây + 6 chuỗi ngẫu nhiên
	        String maPC = "PC" + currentDate + "" + currentTimeWithMillis + generateRandomString();
			
			String tenpc = txtTenPC.getText();
			String succhua = txtSucChua.getText();

			
			
            if (tenpc.isEmpty() || succhua.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
			
			PhongChieuPhim pcp = new PhongChieuPhim(maPC, tenpc,Integer.parseInt(succhua));
			try {
				 clientPC_dao.addPhongChieu(pcp);
				 JOptionPane.showMessageDialog(this, "Thêm thành công");
				 GD_QuanLy_PhongChieu gd_PhongChieu = new GD_QuanLy_PhongChieu();
				 gd_PhongChieu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 gd_PhongChieu.setLocationRelativeTo(null);
				 gd_PhongChieu.setVisible(true);
				 dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(o.equals(btnHuyBo)) {
			GD_QuanLy_PhongChieu gd_PhongChieu;
			try {
				gd_PhongChieu = new GD_QuanLy_PhongChieu();
				gd_PhongChieu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gd_PhongChieu.setLocationRelativeTo(null);
				gd_PhongChieu.setVisible(true);
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
