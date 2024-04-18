package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import enities.TaiKhoan;

public class ClientTaiKhoan_dao {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientTaiKhoan_dao(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<TaiKhoan> getListTK() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListAccount");
		out.flush();
		
		List<TaiKhoan> listTK = (List<TaiKhoan>) in.readObject();
		return listTK;
	}
	
	public TaiKhoan findTKOnMaNV(String manv) throws IOException, ClassNotFoundException {
		out.writeUTF("FindAccountOnMaNV");
		out.flush();
		
		out.writeUTF(manv);
		out.flush();
		
		TaiKhoan tk = (TaiKhoan) in.readObject();
		return tk;
	}
}
