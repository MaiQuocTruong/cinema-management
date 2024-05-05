package giaoDien.nhanvien;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client_dao.ClientCTHoaDonDichVu;
import client_dao.ClientCTHoaDonPhim;
import client_dao.ClientCTHoaDonTong;
import client_dao.ClientKhachHang_dao;
import client_dao.ClientNhanVien_dao;
import enities.CTHoaDonDichVu;
import enities.CTHoaDonVe;
import enities.HoaDon;

import enities.KhachHang;
import enities.NhanVien;
import swing.custom.CustomCellRenderer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ActionEvent;

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

public class GD_HoaDonTinhTien_BETA extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private Buttontest btnXacNhan, btnPrint;
	private ClientKhachHang_dao client_kh;
	private ClientCTHoaDonTong clientHDTong;
	private ClientCTHoaDonDichVu clientHDDichVu;
	private ClientCTHoaDonPhim clienHDPhim;
	private ClientNhanVien_dao clientNhanVien;
	private List<CTHoaDonDichVu> listHoaDonDichVu = new ArrayList<>();
	private List<CTHoaDonVe> listHoaDonVePhim = new ArrayList<>();
	swing.custom.CustomDashedLineSeparator custom;
	private JPanel topPanel_1;

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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public GD_HoaDonTinhTien_BETA(String maHD , double tongTienVe , double tongTienDV , String sdtKH , List<CTHoaDonDichVu> listHoaDonDichVu , List<CTHoaDonVe> listHoaDonVePhim , String maNhanVien , String tenNhanVien) throws UnknownHostException, IOException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 353);
        setUndecorated(true); // Thêm dòng này để setUndecorated cho JFrame
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.listHoaDonDichVu = listHoaDonDichVu;
		this.listHoaDonVePhim = listHoaDonVePhim;
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				Image image = null;
				try {
				    BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/cinema3xjpg.jpg"));
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
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		contentPane.add(topPanel);
		
		String[] columnNames = {"Mã NV", "Tên KH", "Ngày Lặp HD", "Tổng Tiền Vé", "Tổng Tiền DV", "Ưu Đãi", "VAT", "Tổng Tiền"}; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		table = new JTable(tableModel);
		table.setBackground(Color.WHITE); // Đặt màu nền cho bảng
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.setFillsViewportHeight(true);
		table.setOpaque(false);
		contentPane.setLayout(null);
		
		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 704, 132);
		scrollPane.getViewport().setBackground(Color.WHITE); // Đặt màu nền cho scrollPane

		// Đặt màu nền cho tiêu đề của bảng (nếu cần)
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.GRAY);
		
		// Tạo một instance của Renderer cho tiêu đề của bảng
		CustomCellRenderer headerRenderer = new CustomCellRenderer();
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		contentPane.setLayout(null);
		
		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);
		
		lblNewLabel = new JLabel("Hóa Đơn Tính Tiền ");
		lblNewLabel.setBounds(279, 10, 157, 33);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		btnXacNhan = new Buttontest();
		btnXacNhan.setText("Xác Nhận");
		btnXacNhan.setShadowColor(Color.BLACK);
		btnXacNhan.setRippleColor(Color.WHITE);
		btnXacNhan.setForeground(new Color(245, 245, 245));
		btnXacNhan.setBackground(new Color(0, 255, 127));
		btnXacNhan.setBounds(10, 183, 135, 33);
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnXacNhan);
		
		btnPrint = new Buttontest();
		btnPrint.setText("Print");
		btnPrint.setShadowColor(Color.BLACK);
		btnPrint.setRippleColor(Color.WHITE);
		btnPrint.setForeground(new Color(245, 245, 245));
		btnPrint.setBackground(new Color(255, 165, 0));
		btnPrint.setBounds(219, 183, 135, 33);
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				printToPDF();
			}

			private void printToPDF() {
				Document document = new Document();
				try {

					// Sử dụng thời gian hiện tại để tạo tên file duy nhất
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String fileName = "PDF/HoaDonTinhTien_" + dateFormat.format(new Date()) + ".pdf";

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

			private void addContent(Document document, com.itextpdf.text.Font vietnameseFont,
					com.itextpdf.text.Font vietnameseFont_px) throws DocumentException {
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

				Paragraph hoadontinhtien = new Paragraph("HÓA ĐƠN TÍNH TIỀN", vietnameseFont_px);
				hoadontinhtien.setAlignment(Element.ALIGN_CENTER); // Căn giữa đều
				document.add(hoadontinhtien);

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
					} else if (i == 5) {
						headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
					} else if (i == 6) {
						headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
					} else  {
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
						} else if (col == 5) {
							cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
						} else if (col == 6) {
							cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giờ chiếu"
						} else {
							cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Mã Ghế"
						}

						tablePDF.addCell(cell);
					}
				}
				document.add(tablePDF);
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
		
		topPanel_1 = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);

				// Đường dẫn đến hình ảnh
				Image image = null;
				try {
				    BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/ccity.jpg"));
				    image = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				} catch (IOException e) {
				    e.printStackTrace();
				}

				// Vẽ hình ảnh lên panel
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel_1.setBounds(10, 222, 343, 111);
		contentPane.add(topPanel_1);
		topPanel_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
//		String maNhanVien = "NV001";
		LocalDate ngayLapHD = LocalDate.now();
		
		double uuDai = 0;
		
		
		Socket socket = new Socket("192.168.147.1", 6789);
		client_kh = new ClientKhachHang_dao(socket);
		
		Socket socket2 = new Socket("192.168.147.1", 6789);
		clientHDTong = new ClientCTHoaDonTong(socket2);
		KhachHang kh = client_kh.findCustomerOnPhoneNumber(sdtKH);
		
		Socket socket3 = new Socket("192.168.147.1", 6789);
		clienHDPhim = new ClientCTHoaDonPhim(socket3);
		
		Socket socket4 = new Socket("192.168.147.1", 6789);
		clientHDDichVu = new ClientCTHoaDonDichVu(socket4);
		
		Socket socket5 = new Socket("192.168.147.1", 6789);
		clientNhanVien = new ClientNhanVien_dao(socket5);
		
		if(kh.getLoaiKH().equalsIgnoreCase("VIP")) {
			uuDai = 0.1;
		}
		
		double vat = 0.08;
		
		double tongTien = tongTienVe + tongTienDV;
		double tongTienTrongHD = tongTien - (tongTien * uuDai ) + (tongTien * vat);
		
		
		Object [] rowdata = {maNhanVien , kh.getTenKH() , ngayLapHD ,tongTienVe , tongTienDV ,uuDai ,vat ,tongTienTrongHD};
		tableModel.addRow(rowdata);
		
		
		NhanVien nv = clientNhanVien.findEmployeeOnId(maNhanVien);
		HoaDon hoaDonTong = new HoaDon(maHD, kh, nv, uuDai, vat, tongTienTrongHD, ngayLapHD);
		
		
		
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CountDownLatch latch1 = new CountDownLatch(1);
				CountDownLatch latch2 = new CountDownLatch(1);
				Lock lock = new ReentrantLock();
				try {
					lock.lock();
					clientHDTong.addHoaDonTong(hoaDonTong);
					System.out.println(hoaDonTong);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					 latch1.countDown();
					 lock.unlock();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					lock.lock(); 
					latch1.await();
					clientHDDichVu.addHoaDonDichVu(listHoaDonDichVu);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					 lock.unlock();
				}
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					lock.lock();   
					latch1.await();
					clienHDPhim.addHoaDonVePhim(listHoaDonVePhim);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					 lock.unlock();
				}
				
				
				try {
					latch1.await();
					GD_MuaVe_Phim gd_muaVe = new GD_MuaVe_Phim(maNhanVien , tenNhanVien);
					gd_muaVe.setVisible(true);
					gd_muaVe.setLocationRelativeTo(null);
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
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
	}
}
