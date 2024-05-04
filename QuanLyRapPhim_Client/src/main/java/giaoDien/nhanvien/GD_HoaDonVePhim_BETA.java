package giaoDien.nhanvien;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client_dao.ClientXuatChieu_dao;
import enities.CTHoaDonDichVu;
import enities.CTHoaDonVe;

import enities.KhachHang;
import enities.XuatChieu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import swing.custom.CustomCellRenderer;
import swing.custom.CustomDashedLineSeparator;
import testbutton.Buttontest;

public class GD_HoaDonVePhim_BETA extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField tf_tongsoluongve;
	private JTextField tf_tongTienVe;
	private ClientXuatChieu_dao client_xc;
	private Buttontest btnXacNhan, btnPrint;
	private List<CTHoaDonVe> listHoaDonVePhim = new ArrayList<>();
	private double tongTienVe = 0;
	swing.custom.CustomDashedLineSeparator custom;
	
	
	DecimalFormat currencyFormat = new DecimalFormat("###,###,### VND");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public GD_HoaDonVePhim_BETA(String maHD, Set<String> setMaGhe, String maXC, double totalDichVu, int soLuongVe,
			String sdtKH, List<CTHoaDonDichVu> listHdDichVu , String maNhanVien , String tenNhanVien)
			throws UnknownHostException, IOException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 375);
        setUndecorated(true); // Thêm dòng này để setUndecorated cho JFrame
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);

		String[] columnNames = { "Mã Vé", "Tên Phim", "Phòng Chiếu", "Ngày chiếu", "Giờ chiếu", "Mã Ghế" }; // Thay đổi tên cột
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		table.setBackground(Color.WHITE); // Đặt màu nền cho bảng
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.setFillsViewportHeight(true);
		table.setOpaque(false);
		contentPane.setLayout(null);

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 703, 132);
		scrollPane.getViewport().setBackground(Color.WHITE); // Đặt màu nền cho scrollPane

		// Đặt màu nền cho tiêu đề của bảng (nếu cần)
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.GRAY);
		
		// Tạo một instance của Renderer cho tiêu đề của bảng
		CustomCellRenderer headerRenderer = new CustomCellRenderer();
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Hóa Đơn Vé Phim");
		lblNewLabel.setBounds(279, 10, 147, 33);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);

		btnXacNhan = new Buttontest();
		btnXacNhan.setText("Xác Nhận");
		btnXacNhan.setShadowColor(Color.BLACK);
		btnXacNhan.setRippleColor(Color.WHITE);
		btnXacNhan.setForeground(new Color(245, 245, 245));
		btnXacNhan.setBackground(new Color(0, 255, 127));
		btnXacNhan.setBounds(12, 300, 115, 33);
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnXacNhan);

		JLabel lblNewLabel_1 = new JLabel("Tổng Số Lượng Vé:");
		lblNewLabel_1.setBounds(11, 199, 147, 29);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tổng Tiền Vé:");
		lblNewLabel_2.setBounds(11, 249, 147, 29);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_2);

		tf_tongsoluongve = new JTextField();
		tf_tongsoluongve.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_tongsoluongve.setBounds(165, 199, 175, 29);
		contentPane.add(tf_tongsoluongve);
		tf_tongsoluongve.setColumns(10);

		tf_tongTienVe = new JTextField();
		tf_tongTienVe.setBounds(165, 249, 175, 29);
		contentPane.add(tf_tongTienVe);
		tf_tongTienVe.setColumns(10);
		
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				Image image = null;
				try {
				    BufferedImage img = ImageIO.read(getClass().getResource("/imgs/cinema3x.png"));
				    image = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				} catch (IOException e) {
				    e.printStackTrace();
				}

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(363, 183, 350, 150);
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 150);
		topPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(0, 0, 350, 150);
		panel.add(lblNewLabel_3);

		// Đọc và điều chỉnh kích thước hình ảnh
        ImageIcon icon = new ImageIcon(GD_HoaDonVePhim_BETA.class.getResource("/imgs/cinema3x.png"));
        Image image = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); // Điều chỉnh kích thước hình ảnh mong muốn

        // Tạo biểu tượng mới từ hình ảnh đã điều chỉnh kích thước
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt biểu tượng làm icon cho JLabel
        lblNewLabel_3.setIcon(scaledIcon);
		
		
		Socket socket = new Socket("192.168.2.20", 6789);
		client_xc = new ClientXuatChieu_dao(socket);
		XuatChieu xc = client_xc.getXuatChieuOnID(maXC);

		for (String maGhe : setMaGhe) {
			LocalDateTime now = LocalDateTime.now();

			// Định dạng ngày tháng năm
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			String currentDate = now.format(dateFormatter);

			// Định dạng giờ phút giây và millisecond
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
			String currentTimeWithMillis = now.format(timeFormatter);

			// Tạo ra maHD với kiểu là HD + ngày tháng năm + giờ phút giây và mill giây
			String maVe = "V" + maGhe + xc.getPhim().getMaPhim() + xc.getGioChieu() + currentDate + "" + currentTimeWithMillis;

			listHoaDonVePhim.add(new CTHoaDonVe(maHD, maXC, maVe, maGhe, xc.getPhongchieu().getMaPhongChieu(),
					xc.getGioChieu(), xc.getPhim().getMaPhim(), xc.getPhim().getTenPhim(), xc.getPhim().getGiaTien()));

			Object[] rowdata = { maVe, xc.getPhim().getTenPhim(), xc.getPhongchieu().getTenPhongChieu(),
					xc.getNgayChieu(), xc.getGioChieu(), maGhe };
			tableModel.addRow(rowdata);
		}

		// Thêm dữ liệu vào bảng
		tongTienVe = soLuongVe * xc.getPhim().getGiaTien();
		tf_tongsoluongve.setText(String.valueOf(soLuongVe));
		tf_tongTienVe.setText(String.valueOf(tongTienVe));

		btnPrint = new Buttontest();
		btnPrint.setText("Print");
		btnPrint.setShadowColor(Color.BLACK);
		btnPrint.setRippleColor(Color.WHITE);
		btnPrint.setForeground(new Color(245, 245, 245));
		btnPrint.setBackground(new Color(255, 165, 0));
		btnPrint.setBounds(236, 300, 104, 33);
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnPrint);

		btnXacNhan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					GD_HoaDonTinhTien_BETA gd_hdTong = new GD_HoaDonTinhTien_BETA(maHD, tongTienVe, totalDichVu, sdtKH,
							listHdDichVu, listHoaDonVePhim , maNhanVien , tenNhanVien);
					gd_hdTong.setVisible(true);
					gd_hdTong.setLocationRelativeTo(null);
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

		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printToPDF();
			}
		});

		JLabel lbl_close = new JLabel("x");
		lbl_close.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(704, 0, 26, 22);
		contentPane.add(lbl_close);
	}

	private void printToPDF() {
		Document document = new Document();
		try {

			// Sử dụng thời gian hiện tại để tạo tên file duy nhất
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String fileName = "PDF/HoaDonVePhim_" + dateFormat.format(new Date()) + ".pdf";

			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();

			BaseFont unicodeFont = BaseFont.createFont("Tahoma Regular font.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			com.itextpdf.text.Font vietnameseFont = new com.itextpdf.text.Font(unicodeFont, 12,
					com.itextpdf.text.Font.NORMAL);
			
			BaseFont unicodeFont_px = BaseFont.createFont("FVF Fernando 08.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			com.itextpdf.text.Font vietnameseFont_px = new com.itextpdf.text.Font(unicodeFont_px, 12,
					com.itextpdf.text.Font.NORMAL);
			// Add content to the PDF document
			addContent(document, vietnameseFont, vietnameseFont_px);

			// Close the document
			document.close();

			// Mở file PDF sau khi đã lưu
			Desktop.getDesktop().open(new File(fileName));

			// Display a message indicating successful PDF creation
			JOptionPane.showMessageDialog(contentPane, "ĐÃ XUẤT FILE PDF!", "Success", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addContent(Document document, com.itextpdf.text.Font vietnameseFont, com.itextpdf.text.Font vietnameseFont_px) throws DocumentException {
		// TODO Auto-generated method stub
		PdfPTable infoTable = new PdfPTable(1);
		infoTable.setWidthPercentage(100);

		// Thêm ô ngày lập đơn vào bảng
		PdfPCell dateCell = new PdfPCell(new Paragraph("DATE:" + getCurrentDate(), vietnameseFont));
		dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		dateCell.setBorder(Rectangle.NO_BORDER);
		infoTable.addCell(dateCell);

		// Thêm ô giờ và phút vào bảng
		PdfPCell timeCell = new PdfPCell(new Paragraph("TIME:" + getCurrentTime(), vietnameseFont));
		timeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		timeCell.setBorder(Rectangle.NO_BORDER);
		infoTable.addCell(timeCell);

		// Thêm bảng vào tài liệu
		document.add(infoTable);

		document.add(new Paragraph("*******************************************************************************",
				vietnameseFont));
		Paragraph cinemaParagraph = new Paragraph("CINEMA CITY", vietnameseFont_px);
		cinemaParagraph.setAlignment(Element.ALIGN_CENTER); // Căn giữa đều
		document.add(cinemaParagraph);
		document.add(new Paragraph("*******************************************************************************",
				vietnameseFont));

		custom = new swing.custom.CustomDashedLineSeparator();
		custom.setLineWidth(1);
		custom.setOffset(10);
		custom.setLineColor(BaseColor.BLACK);
		document.add(new Chunk(custom));

		Paragraph hoadonvephim = new Paragraph("HÓA ĐƠN VÉ PHIM", vietnameseFont_px);
		hoadonvephim.setAlignment(Element.ALIGN_CENTER); // Căn giữa đều
		document.add(hoadonvephim);

		document.add(new Paragraph("\n"));
		custom = new CustomDashedLineSeparator();
		custom.setLineWidth(1);
		custom.setOffset(10);
		custom.setLineColor(BaseColor.BLACK);
		document.add(new Chunk(custom));

		// add table in pdf
		PdfPTable tablePDF = new PdfPTable(tableModel.getColumnCount());
		tablePDF.setWidthPercentage(100);
		// Add table headers
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			PdfPCell headerCell = new PdfPCell(new Phrase(tableModel.getColumnName(i), vietnameseFont));
			headerCell.setBorder(Rectangle.BOTTOM);
			headerCell.setBorderWidth(0.5f);
			headerCell.setPaddingBottom(10f); // Adjust the padding as needed
			headerCell.setBorderColorBottom(BaseColor.BLACK);

			// Căn lề cho tiêu đề của từng cột
			if (i == 0) {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Mã Vé"
			} else if (i == 1) {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Tên Phim"
			} else if (i == 2) {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Phòng Chiếu"
			} else if (i == 3) {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Ngày chiếu"
			} else if (i == 4) {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
			} else {
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Mã Ghế"
			}

			tablePDF.addCell(headerCell);
		}
		// Add table data
		for (int row = 0; row < tableModel.getRowCount(); row++) {
			for (int col = 0; col < tableModel.getColumnCount(); col++) {
				PdfPCell cell = new PdfPCell(new Phrase(tableModel.getValueAt(row, col).toString(), vietnameseFont));
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBorderWidth(0.5f);
				cell.setPaddingBottom(10f); // Adjust the padding as needed
				cell.setBorderColorBottom(BaseColor.BLACK);

				// Căn lề cho ô bảng
				if (col == 0) {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Mã Vé"
				} else if (col == 1) {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Tên Phim"
				} else if (col == 2) {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Phòng Chiếu"
				} else if (col == 3) {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Ngày chiếu"
				} else if (col == 4) {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
				} else {
					cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Mã Ghế"
				}

				tablePDF.addCell(cell);
			}
		}
		document.add(tablePDF);
		document.add(new Paragraph("\n"));

		PdfPTable infoTable1 = new PdfPTable(1);
		infoTable1.setWidthPercentage(100);

		PdfPCell cellTongSoLuognVe = new PdfPCell(new Paragraph("Tổng số lượng vé: " + tf_tongsoluongve.getText() + " vé", vietnameseFont));
		cellTongSoLuognVe.setBorder(Rectangle.NO_BORDER);
		infoTable1.addCell(cellTongSoLuognVe);
		infoTable1.completeRow();
		document.add(infoTable1);
		
		// format tiền tệ VND
		double tienTe = Double.parseDouble(tf_tongTienVe.getText());
		String formattedAmount = formatCurrency(tienTe);
		
		PdfPTable infoTable2 = new PdfPTable(1);
		infoTable2.setWidthPercentage(100);
		
		PdfPCell cellTongTienVe = new PdfPCell(new Paragraph("Tổng tiền vé: " + formattedAmount, vietnameseFont));
		cellTongTienVe.setBorder(Rectangle.NO_BORDER);
		infoTable2.addCell(cellTongTienVe);
		infoTable2.completeRow();
		document.add(infoTable2);

		document.add(new Paragraph("\n"));
		custom = new CustomDashedLineSeparator();
		custom.setLineWidth(1);
		custom.setOffset(10);
		custom.setLineColor(BaseColor.BLACK);
		document.add(new Chunk(custom));

		Paragraph camOn = new Paragraph("Cảm ơn quý khách", vietnameseFont);
		camOn.getFont().setStyle(com.itextpdf.text.Font.BOLDITALIC);
		camOn.setAlignment(Element.ALIGN_CENTER); // Căn phải
		document.add(camOn);
	}

	private String formatCurrency(double tienTe) {
		// TODO Auto-generated method stub
	    currencyFormat = new DecimalFormat("###,###,### VND");
	    
	    // Format the amount as currency
	    String formattedAmount1 = currencyFormat.format(tienTe);
	    
	    return formattedAmount1;
	}

	private String getCurrentTime() {
		// TODO Auto-generated method stub
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ssa");
		return timeFormat.format(new Date());
	}

	private String getCurrentDate() {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(new Date());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
