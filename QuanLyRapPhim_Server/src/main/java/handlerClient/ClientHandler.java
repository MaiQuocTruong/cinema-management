package handlerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import dao.EntityManagerFactoryUtil;
import dao.KhachHang_dao;

import dao.NhanVien_dao;
import dao.PhongChieuPhim_dao;
import dao.TaiKhoan_dao;
import enities.KhachHang;
import enities.NhanVien;
import enities.PhongChieuPhim;
import enities.TaiKhoan;
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
	
	


	public ClientHandler(Socket socketClient)  {
		super();
		this.socketClient = socketClient;
		emfUtil = new EntityManagerFactoryUtil();
		em = emfUtil.getEm();
		kh_dao = new KhachHang_dao(em);

		nv_dao = new NhanVien_dao(em);
		tk_dao = new TaiKhoan_dao(em);
		pc_dao = new PhongChieuPhim_dao(em);
	
		
	}
	

	public void run()  {
		try {
			in = new ObjectInputStream(socketClient.getInputStream());
			out = new ObjectOutputStream(socketClient.getOutputStream());
			
			while(true) {
				String clientRequest = in.readUTF();
//				System.out.println("Recive from client: " + clientData);
				
				switch (clientRequest) {
				case "GetListCustomer":
					List<KhachHang> listKH = kh_dao.getListCustomer();
					out.writeObject(listKH);
					out.flush();
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
					KhachHang kh_needUpdate = (KhachHang) in.readObject();
					kh_dao.updateKhachHang(kh_needUpdate);
					break;	
				case "DeleteCustomer":
					KhachHang kh_remove = (KhachHang) in.readObject();
					kh_dao.deleteKhachHang(kh_remove.getMaKH());
					break;
				case "GetListEmployee":
					List<NhanVien> listNV = nv_dao.getListNhanVien();
					out.writeObject(listNV);
					out.flush();
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
				case "GetListAccount":
					List<TaiKhoan> listTK = tk_dao.getListTaiKhoan();
					out.writeObject(listTK);
					out.flush();
					break;
				case "FindAccountOnMaNV":
					String manv = in.readUTF();
					TaiKhoan resultFindTK = tk_dao.findEmployeeOnMaNV(manv);
					out.writeObject(resultFindTK);
					out.flush();
					break;
				case "GetListPhongChieu":
					List<PhongChieuPhim> listPC = pc_dao.getListPhongChieu();
					out.writeObject(listPC);
					out.flush();
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
					PhongChieuPhim pc_needUpdate = (PhongChieuPhim) in.readObject();
					pc_dao.updatePhongChieu(pc_needUpdate);
					break;	
				case "DeletePhongChieu":
					PhongChieuPhim pc_needRemove = (PhongChieuPhim) in.readObject();
					pc_dao.deletePhongChieu(pc_needRemove.getMaPhongChieu());
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
