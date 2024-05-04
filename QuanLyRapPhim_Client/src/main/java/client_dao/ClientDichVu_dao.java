package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import enities.DichVuAnUong;

import enities.GiaDichVu;




public class ClientDichVu_dao {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ClientDichVu_dao(Socket socket) throws IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<DichVuAnUong> getListDichVuAnUong() throws IOException, ClassNotFoundException{
		out.writeUTF("GetListDichVuAnUong");
		out.flush();
		List<DichVuAnUong> listDichVuAnUong = (List<DichVuAnUong>) in.readObject();
		return listDichVuAnUong;
	}
	
	
	public List<GiaDichVu> getListGiaDichVu(String idDichVu) throws IOException, ClassNotFoundException{
		out.writeUTF("GetListGiaDichVu");
		out.flush();
		
		out.writeUTF(idDichVu);
		out.flush();
		
		List<GiaDichVu> listGiaDichVu = (List<GiaDichVu>) in.readObject();
		return listGiaDichVu;
	}
	
}
