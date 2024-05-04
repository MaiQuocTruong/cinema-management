package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enities.CTHoaDonVe;
import jakarta.persistence.Query;




public class ClientCTHoaDonPhim {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientCTHoaDonPhim(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<CTHoaDonVe> getListHoaDonPhim() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListHoaDonVePhim");
		out.flush();
		
		List<CTHoaDonVe> listHoaDonVe = (List<CTHoaDonVe>) in.readObject();
		return listHoaDonVe;
	}
	
	
	public void addHoaDonVePhim(List<CTHoaDonVe> listhdVePhim) throws IOException {
		out.writeUTF("AddHoaDonPhim");
		out.flush();
		
		out.writeObject(listhdVePhim);
		out.flush();
	}
	
	public Map<String, Double> getDoanhThuTheoPhim() throws IOException, ClassNotFoundException {
        Map<String, Double> doanhThuTungPhim = new HashMap<>();
        out.writeUTF("ThongKeDoanhThuPhim");
        out.flush();
        
        doanhThuTungPhim = (Map<String, Double>) in.readObject();
        return doanhThuTungPhim;
    }
	
	
	
	
	
}
