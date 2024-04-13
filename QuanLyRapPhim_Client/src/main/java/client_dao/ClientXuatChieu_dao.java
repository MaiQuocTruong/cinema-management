package client_dao;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import enities.XuatChieu;


public class ClientXuatChieu_dao implements Serializable {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientXuatChieu_dao(Socket socket) throws UnknownHostException, IOException{
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	
	public List<XuatChieu> getListXC() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListXuatChieu");
		out.flush();
		
		List<XuatChieu> listXC = (List<XuatChieu>) in.readObject();
		return listXC;
	}
	
	public void addXC(XuatChieu xc) throws IOException {
		out.writeUTF("AddXuatChieu");
		out.flush();
		
		out.writeObject(xc);
		out.flush();
		
	}
	
	public XuatChieu findXuatChieuOnMaXC(String maXC) throws IOException, ClassNotFoundException {
		out.writeUTF("findXuatChieuOnMaXC");
		out.flush();
		
		out.writeUTF(maXC);
		out.flush();
		
		XuatChieu xc = (XuatChieu) in.readObject();
		return xc;
	}
	
//	public void updateXuatChieu(XuatChieu xc) throws IOException {
//		out.writeUTF("UpdateXuatChieu");
//		out.flush();
//		
//		out.writeObject(xc);
//		out.flush();
//	}
	
	public void deleteXuatChieu(XuatChieu xc) throws IOException {
		out.writeUTF("DeleteXuatChieu");
		out.flush();
		
		out.writeObject(xc);
		out.flush();
	}
}
