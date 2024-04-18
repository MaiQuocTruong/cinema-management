package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import enities.NhanVien;
import enities.PhongChieuPhim;

public class ClientPhongChieu_dao {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientPhongChieu_dao(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	
	public List<PhongChieuPhim> getListPhongChieu() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListPhongChieu");
		out.flush();
		
		List<PhongChieuPhim> listCust = (List<PhongChieuPhim>) in.readObject();
		return listCust;
	}

	public void addPhongChieu(PhongChieuPhim pcp) throws IOException {
		out.writeUTF("AddPhongChieu");
		out.flush();
		
		out.writeObject(pcp);
		out.flush();
		
	}
	
	public PhongChieuPhim findPhongChieuOnMaPC(String maPC) throws IOException, ClassNotFoundException {
		out.writeUTF("findPhongChieuOnMaPC");
		out.flush();
		
		out.writeUTF(maPC);
		out.flush();
		
		PhongChieuPhim pc = (PhongChieuPhim) in.readObject();
		return pc;
	}
	
	
	public void updatePhongChieu(PhongChieuPhim pcp) throws IOException {
		out.writeUTF("UpdatePhongChieu");
		out.flush();
		
		out.writeObject(pcp);
		out.flush();
	}
	
	public void deletePhongChieu(PhongChieuPhim pcp) throws IOException {
		out.writeUTF("DeletePhongChieu");
		out.flush();
		
		out.writeObject(pcp);
		out.flush();
	}
}
