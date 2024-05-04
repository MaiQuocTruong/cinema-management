package client_dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enities.CTHoaDonDichVu;


public class ClientCTHoaDonDichVu {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientCTHoaDonDichVu(Socket socket) throws UnknownHostException, IOException {
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public List<CTHoaDonDichVu> getListHoaDonDichVu() throws UnknownHostException, IOException, ClassNotFoundException{
		out.writeUTF("GetListHoaDonVeDichVu");
		out.flush();
		
		List<CTHoaDonDichVu> listHoaDonDichVu = (List<CTHoaDonDichVu>) in.readObject();
		return listHoaDonDichVu;
	}
	
	
	public void addHoaDonDichVu(List<CTHoaDonDichVu> listHDDichVu) throws IOException {
		out.writeUTF("AddHoaDonDichVu");
		out.flush();
		
		out.writeObject(listHDDichVu);
		out.flush();
	}
	
	public Map<String, Double> getDoanhThuTheoDichVu() throws IOException, ClassNotFoundException {
        Map<String, Double> doanhThuTungDichVu = new HashMap<>();
        out.writeUTF("ThongKeDoanhThuDichVu");
        out.flush();
        
        doanhThuTungDichVu = (Map<String, Double>) in.readObject();
        return doanhThuTungDichVu;
    }
	
	
	
}
