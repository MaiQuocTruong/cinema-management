package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import enities.KhachHang;

public class ClientKhachHang_dao {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientKhachHang_dao(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	
	public List<KhachHang> getListKH() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListCustomer");
		out.flush();
		
		List<KhachHang> listCust = (List<KhachHang>) in.readObject();
		return listCust;
	}

	public void addKH(KhachHang kh) throws IOException {
		out.writeUTF("AddCustomer");
		out.flush();
		
		out.writeObject(kh);
		out.flush();
		
	}
	
}
