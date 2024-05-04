package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import enities.HoaDon;
import enities.NhanVien;
import jakarta.persistence.TypedQuery;

public class ClientCTHoaDonTong {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientCTHoaDonTong(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<HoaDon> getListHoaDonTong() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListHoaDonTong");
		out.flush();
		
		List<HoaDon> listHoaDonTong = (List<HoaDon>) in.readObject();
		return listHoaDonTong;
	}
	
	
	public void addHoaDonTong(HoaDon hdTong) throws IOException {
		out.writeUTF("AddHoaDonTong");
		out.flush();
		
		out.writeObject(hdTong);
		out.flush();
	}
	
	
}
