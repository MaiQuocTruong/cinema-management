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
import enities.KhachHang;

public class ClientHandler implements Runnable {
	private Socket socketClient;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private EntityManagerFactoryUtil emfUtil;
	private EntityManager em;
	private KhachHang_dao kh_dao;
	

	public ClientHandler(Socket socketClient)  {
		super();
		this.socketClient = socketClient;
		emfUtil = new EntityManagerFactoryUtil();
		em = emfUtil.getEm();
		kh_dao = new KhachHang_dao(em);
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
