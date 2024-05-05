package handlerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import dao.CTHoaDonDichVu_dao;
import dao.CTHoaDonPhim_dao;
import dao.CTHoaDonTong_dao;
import dao.DichVuAnUong_dao;

import dao.EntityManagerFactoryUtil;
import dao.GiaDichVu_dao;
import dao.KhachHang_dao;

import dao.NhanVien_dao;
import dao.Phim_dao;
import dao.PhongChieuPhim_dao;
import dao.TaiKhoan_dao;
import dao.XuatChieu_dao;
import enities.CTHoaDonDichVu;
import enities.CTHoaDonVe;
import enities.DichVuAnUong;

import enities.GiaDichVu;
import enities.HoaDon;



import enities.KhachHang;
import enities.NhanVien;
import enities.Phim;
import enities.PhongChieuPhim;

import enities.TaiKhoan;
import enities.XuatChieu;
import enities.KhachHang;




public class ClientHandler implements Runnable {
	private Socket socketClient;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private EntityManagerFactoryUtil emfUtil;
	private EntityManager em;
	private KhachHang_dao kh_dao;

	private NhanVien_dao nv_dao;
	private TaiKhoan_dao tk_dao;
	private PhongChieuPhim_dao pc_dao;
	private XuatChieu_dao xc_dao;
	private Phim_dao ph_dao;
	private DichVuAnUong_dao dvau_dao;
	private GiaDichVu_dao giaDichVu_dao;
	private CTHoaDonPhim_dao cthdPhim_dao;
	private CTHoaDonDichVu_dao cthdDichVu_dao;
	private CTHoaDonTong_dao hdTong_dao;
	private CountDownLatch latch1 = new CountDownLatch(1);
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private List<KhachHang> listKH = new ArrayList<>();
	private List<NhanVien> listNV = new ArrayList<>();
	private List<TaiKhoan> listTK = new ArrayList<>();
	private List<PhongChieuPhim> listPC = new ArrayList<>();
	private List<XuatChieu> listXC = new ArrayList<>();
	private List<Phim> listPhim = new ArrayList<>();

	public ClientHandler(Socket socketClient)  {
		super();
		this.socketClient = socketClient;
		emfUtil = new EntityManagerFactoryUtil();
		em = emfUtil.getEm();
		kh_dao = new KhachHang_dao(em);

		nv_dao = new NhanVien_dao(em);
		tk_dao = new TaiKhoan_dao(em);
		pc_dao = new PhongChieuPhim_dao(em);
		xc_dao=new XuatChieu_dao(em);
		ph_dao=new Phim_dao(em);
		dvau_dao = new DichVuAnUong_dao(em);
		giaDichVu_dao = new GiaDichVu_dao(em);
		cthdPhim_dao = new CTHoaDonPhim_dao(em);
		cthdDichVu_dao = new CTHoaDonDichVu_dao(em);
		hdTong_dao = new CTHoaDonTong_dao(em);
	}
	

	public void run()  {
		try {
			in = new ObjectInputStream(socketClient.getInputStream());
			out = new ObjectOutputStream(socketClient.getOutputStream());
			
			while(true) {
				String clientRequest = in.readUTF();
				System.out.println("Recive from client: " + clientRequest);
				
				switch (clientRequest) {
				case "GetListCustomer":
					lock.readLock().lock();
					try {
						listKH = kh_dao.getListCustomer();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listKH);
						out.flush();
						lock.readLock().unlock();
					}
					
					break;
				case "AddCustomer":
					KhachHang kh = (KhachHang) in.readObject();
					kh_dao.addKhachHang(kh);
					break;
				case "FindCustomerOnPhoneNumber":
					String phoneNumber = in.readUTF();
					KhachHang resultFindKH = kh_dao.findCustomerOnPhoneNumber(phoneNumber);
					out.writeObject(resultFindKH);
					out.flush();
					break;
				case "UpdateCustomer":
					lock.writeLock().lock();
					try {
						KhachHang kh_needUpdate = (KhachHang) in.readObject();
						kh_dao.updateKhachHang(kh_needUpdate);
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;	
				case "DeleteCustomer":
					lock.writeLock().lock();
					try {
						KhachHang kh_remove = (KhachHang) in.readObject();
						kh_dao.deleteKhachHang(kh_remove.getMaKH());
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;
				case "GetListEmployee":
					
					lock.readLock().lock();
					try {
						listNV = nv_dao.getListNhanVien();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listNV);
						out.flush();
						
						lock.readLock().lock();
					}
					
					break;
					
					
				case "AddEmployee":
					NhanVien nv = (NhanVien) in.readObject();
					nv_dao.addNhanVien(nv);
					break;
				case "FindEmployeeOnPhoneNumber":
					String sdt = in.readUTF();
					NhanVien resultFindNV = nv_dao.findEmployeeOnPhoneNumber(sdt);
					out.writeObject(resultFindNV);
					out.flush();
					break;
				case "SetTrangThaiNV":
					NhanVien nv_trangthai = (NhanVien) in.readObject();
					nv_dao.setTrangThaiNV(nv_trangthai.getMaNV());
					tk_dao.deleteTrangThaiTK(nv_trangthai.getMaNV());
					break;
				case "FindEmployeeOnId":
					String maNV = in.readUTF();
					NhanVien nvCanTim = nv_dao.findEmployeeOnId(maNV);
					out.writeObject(nvCanTim);
					out.flush();
					break;
				case "UpdateEmployee":	
						NhanVien nv_needUpdate = (NhanVien) in.readObject();
						nv_dao.updateNV(nv_needUpdate);
						tk_dao.updateTrangThaiTK(nv_needUpdate.getMaNV());						
						break;
				case "getNameNhanVien":
					String manv_cantimten = in.readUTF();
					out.writeObject(nv_dao.getTenNhanVien(manv_cantimten));
					out.flush();
					break;
				case "GetListAccount":
					
					lock.readLock().lock();
					try {
						listTK = tk_dao.getListTaiKhoan();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listTK);
						out.flush();
						lock.readLock().unlock();
					}
					
					
					break;
				case "FindAccountOnMaNV":
					String manv = in.readUTF();
					TaiKhoan resultFindTK = tk_dao.findEmployeeOnMaNV(manv);
					out.writeObject(resultFindTK);
					out.flush();
					break;
//				case "checkPassword":
//					String maNVCheck = in.readUTF();
//					TaiKhoan tkCanCheck = tk_dao.findEmployeeOnMaNV(maNVCheck);
//					if(tk_dao.kiemTraMatKhau(tkCanCheck.getMaNV(),tkCanCheck.getMatkhau())) {
//						out.writeObject(true);
//						out.flush();
//					}
//					out.writeObject(false);
//					out.flush();
//					break;
				case "GetListPhongChieu":
					lock.readLock().lock();
					try {
						listPC = pc_dao.getListPhongChieu();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listPC);
						out.flush();
						lock.readLock().unlock();
					}
					break;
				case "AddPhongChieu":
					PhongChieuPhim pcp = (PhongChieuPhim) in.readObject();
					pc_dao.addPhongChieu(pcp);
					break;
				case "findPhongChieuOnMaPC":
					String maPC = in.readUTF();
					PhongChieuPhim phimCanTim = pc_dao.findPhongChieuOnMaPC(maPC);
					out.writeObject(phimCanTim);
					out.flush();
					break;
				case "UpdatePhongChieu":
					
					lock.writeLock().lock();	
					try {
						PhongChieuPhim pc_needUpdate = (PhongChieuPhim) in.readObject();
						pc_dao.updatePhongChieu(pc_needUpdate);
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;	
				case "DeletePhongChieu":
					lock.writeLock().lock();	
					try {
						String idXuatChieu = in.readUTF();
						pc_dao.deletePhongChieu(idXuatChieu);
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					break;
				case "GetListXuatChieu":
					lock.readLock().lock();
					try {
						listXC = xc_dao.getListXuatChieu();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listXC);
						out.flush();
						lock.readLock().unlock();
					}
					
					break;
				case "findXuatChieuOnMaXC":
					String maXC = in.readUTF();
					XuatChieu XCCanTim = xc_dao.findXuatChieuOnMaXC(maXC);
					out.writeObject(XCCanTim);
					out.flush();
					break;
				case "GetXuatChieuOnID":
					String idXC = in.readUTF();
					XuatChieu xc_needFind = xc_dao.getXuatChieuOnID(idXC);
					out.writeObject(xc_needFind);
					out.flush();
					break;
				case "DeleteXuatChieu":
					lock.writeLock().lock();	
					try {
						XuatChieu xc_needRemove = (XuatChieu) in.readObject();
						xc_dao.deleteXuatChieu(xc_needRemove.getMaXuat());
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					
					
					break;
				case "AddXuatChieu":
					XuatChieu listxc = (XuatChieu) in.readObject();
					xc_dao.addxuatChieu(listxc);
					break;
				case "UpdateXuatChieu":
					lock.writeLock().lock();	
					try {
						XuatChieu xc_update = (XuatChieu) in.readObject();
						xc_dao.updateXuatChieu(xc_update);
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;
				case "FindXuatChieuOnNgayChieu":
				    try {
				        // Đọc ngày chiếu từ client
				        String ngayChieuStr = in.readUTF();
				        
				        // Chuyển đổi chuỗi ngày chiếu thành LocalDate
				        LocalDate ngayChieu = LocalDate.parse(ngayChieuStr);
				        
				        // Gọi phương thức tìm kiếm với ngày chiếu đã chuyển đổi
				        XuatChieu danhSachXC = xc_dao.findXuatChieuOnNgayChieu(ngayChieu);
				        
				        // Gửi kết quả tìm kiếm về cho client
				        out.writeObject(danhSachXC);
				        out.flush();
				    } catch (IOException e) {
				        // Xử lý ngoại lệ nếu có
				        e.printStackTrace();
				    }
				    break;
//				case "LayDanhSachMaPhongChieu":
//				    try {
//				        List<String> danhSachMaPhongChieu = pc_dao.layDanhSachMaPhongChieu();
//				        out.writeObject(danhSachMaPhongChieu);
//				        out.flush();
//				    } catch (IOException e) {
//				        e.printStackTrace();
//				    }
//				    break;
				case "GetListCinema":
					lock.readLock().lock();
					try {
						 listPhim = ph_dao.getListMovies();
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						out.writeObject(listPhim);
						out.flush();
						lock.readLock().unlock();
					}
					break;
					
				case "AddFilm":
					Phim ph = (Phim) in.readObject();
					ph_dao.addPhimmoi(ph);
					break;
				case "UpdateFilm":
					lock.writeLock().lock();	
					try {
						Phim ph_needUpdate = (Phim) in.readObject();
						ph_dao.updatePhim(ph_needUpdate);
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;
				case "DeleteFilm":
					lock.writeLock().lock();	
					try {
						Phim ph_needremove = (Phim) in.readObject();
						ph_dao.deletePhim(ph_needremove.getMaPhim());;
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock.writeLock().unlock();
					}
					
					break;
					
				case "FindonTen":
					String tenPhim = in.readUTF();
					Phim resultFind = ph_dao.findPhimonTen(tenPhim);
					out.writeObject(resultFind);
					out.flush();
					break;
				case "FindonMa":
					String maPhim = in.readUTF();
					Phim resultFind1 = ph_dao.findPhimonma(maPhim);
					out.writeObject(resultFind1);
					out.flush();
					break;
				case "Find":
					try {
				        // Đọc ngày bắt đầu và ngày kết thúc từ client
				        String tuNgayStr = in.readUTF();
				        String denNgayStr = in.readUTF();

				        // Chuyển đổi chuỗi ngày thành LocalDate
				        LocalDate tuNgay = LocalDate.parse(tuNgayStr);
				        LocalDate denNgay = LocalDate.parse(denNgayStr);

				        // Gọi phương thức tìm kiếm với ngày bắt đầu và ngày kết thúc
				        List<Phim> danhSachPhim = ph_dao.findXuatChieuTrongKhoangNgay(tuNgay, denNgay);

				        // Gửi kết quả tìm kiếm về cho client
				        out.writeObject(danhSachPhim);
				        out.flush();
				    } catch (IOException e) {
				        // Xử lý ngoại lệ nếu có
				        e.printStackTrace();
				    }
				    break;
				case "GetListDichVu" :
					List<DichVuAnUong> listDVAnUong = dvau_dao.getListDichVuAnUong();
					out.writeObject(listDVAnUong);
					out.flush();
					break;
				case "GetListDichVuAnUong":
					List<DichVuAnUong> listDichVuAnUong = dvau_dao.getListDichVuAnUong();
					out.writeObject(listDichVuAnUong);
					out.flush();
					break;
				case "GetListGiaDichVu":
					String idDichVu = in.readUTF();
					List<GiaDichVu> listGiaDichVu = giaDichVu_dao.getListGiaDichVu(idDichVu);
					out.writeObject(listGiaDichVu);
					out.flush();
					break;
				case "GetListHoaDonTong":
					List<HoaDon> listHoaDonTong = hdTong_dao.getListHoaDonTong();
					out.writeObject(listHoaDonTong);
					out.flush();
					break;
				case "AddHoaDonTong":
					HoaDon hoaDonTong = (HoaDon) in.readObject();
					hdTong_dao.addHoaDonTong(hoaDonTong);
					latch1.countDown();
					break;
				case "AddHoaDonDichVu":
					List<CTHoaDonDichVu> listhoaDonDichVu =  (List<CTHoaDonDichVu>) in.readObject();
					for (CTHoaDonDichVu hoaDonDichVu : listhoaDonDichVu) {
						System.out.println(hoaDonDichVu);
						cthdDichVu_dao.addHoaDonDichVu(hoaDonDichVu);
					}
					break;
				case "AddHoaDonPhim":
					List<CTHoaDonVe> listhoaDonVe =  (List<CTHoaDonVe>) in.readObject();
					for (CTHoaDonVe hoaDonVePhim : listhoaDonVe) {
						cthdPhim_dao.addHoaDonPhim(hoaDonVePhim);
					}
					
					break;
				case "ThongKeDoanhThuPhim":
					Map<String, Double> listDoanhThuTheoPhim = cthdPhim_dao.getDoanhThuTheoPhim();
					out.writeObject(listDoanhThuTheoPhim);
					out.flush();
					break;
				case "ThongKeDoanhThuDichVu":
					Map<String, Double> listDoanhThuTheoDoanhThu = cthdDichVu_dao.getDoanhThuDichVu();
					out.writeObject(listDoanhThuTheoDoanhThu);
					out.flush();
					break;
					
				
				default:
					break;
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			emfUtil.closeEnManager();
			emfUtil.closeEnManagerFactory();
		}
		
	}

	
	
}
