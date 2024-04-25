package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import enities.NhanVien;
import enities.Phim;

public class ClientPhim_dao {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientPhim_dao(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<Phim> getListPhim() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListCinema");
		out.flush();
		
		List<Phim> listphim = (List<Phim>) in.readObject();
		return listphim;
	}
	
	public void addPhim(Phim ph) throws IOException {
		out.writeUTF("AddFilm");
		out.flush();
		
		out.writeObject(ph);
		out.flush();
	}
	
	public void updatePhim(Phim ph) throws IOException {
		out.writeUTF("UpdateFilm");
		out.flush();
		
		out.writeObject(ph);
		out.flush();
	}
	public Phim findFilmonma(String maPhim) throws IOException, ClassNotFoundException {
		out.writeUTF("FindonMa");
		out.flush();
		
		out.writeUTF(maPhim);
		out.flush();
		
		Phim ph = (Phim) in.readObject();
		return ph;
	}
	public void deletephim(Phim ph) throws IOException {
		out.writeUTF("DeleteFilm");
		out.flush();
		out.writeObject(ph);
		out.flush();
	}

	public Phim findFilmonTen(String tenPhim) throws IOException, ClassNotFoundException {
		out.writeUTF("FindonTen");
		out.flush();
		
		out.writeUTF(tenPhim);
		out.flush();
		
		Phim ph = (Phim) in.readObject();
		return ph;
	}
	
	
	public List<Phim> findFilmonDateRange( java.util.Date fromDate, java.util.Date toDate) throws IOException, ClassNotFoundException {
	    out.writeUTF("FindonDateRange");
	    out.flush();
	    
	    out.writeObject(fromDate);
	    out.writeObject(toDate);
	    out.flush();
	    
	    List<Phim> danhSachPhim = (List<Phim>) in.readObject();
	    return danhSachPhim;
	}


}
