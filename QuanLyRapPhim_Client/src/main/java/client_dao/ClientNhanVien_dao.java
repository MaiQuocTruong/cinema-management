package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import enities.NhanVien;

public class ClientNhanVien_dao {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientNhanVien_dao(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<NhanVien> getListNV() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListEmployee");
		out.flush();
		
		List<NhanVien> listNV = (List<NhanVien>) in.readObject();
		return listNV;
	}

	public void addNV(NhanVien nv) throws IOException {
		out.writeUTF("AddEmployee");
		out.flush();
		
		out.writeObject(nv);
		out.flush();
	}
	
	public NhanVien findEmployeeOnPhoneNumber(String sdt) throws IOException, ClassNotFoundException {
		out.writeUTF("FindEmployeeOnPhoneNumber");
		out.flush();
		
		out.writeUTF(sdt);
		out.flush();
		
		NhanVien nv = (NhanVien) in.readObject();
		return nv;
	}
	
//	public void deleteNV(NhanVien nv) throws IOException {
//		out.writeUTF("DeleteEmployee");
//		out.flush();
//		
//		out.writeObject(nv);
//		out.flush();
//	}
	
	public void updateNV(NhanVien nv) throws IOException {
		out.writeUTF("UpdateEmployee");
		out.flush();
		
		out.writeObject(nv);
		out.flush();
	}
	
	public void setTrangThaiNV(NhanVien nv) throws IOException {
		out.writeUTF("SetTrangThaiNV");
		out.flush();
		
		out.writeObject(nv);
		out.flush();
	}
}