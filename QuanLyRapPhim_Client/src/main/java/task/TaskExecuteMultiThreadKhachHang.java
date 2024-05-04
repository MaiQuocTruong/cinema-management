package task;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import enities.KhachHang;

public class TaskExecuteMultiThreadKhachHang extends SwingWorker<KhachHang, KhachHang>{
	private List<KhachHang> listKH;
	private DefaultTableModel tableModel;
	
	
	
	
	public TaskExecuteMultiThreadKhachHang(List<KhachHang> listKH, DefaultTableModel tableModel) {
		super();
		this.listKH = listKH;
		this.tableModel = tableModel;
	}


	@Override
	protected KhachHang doInBackground() throws Exception {
		for (KhachHang khachHang : listKH) {
			publish(khachHang);
		}
		return null;
	}
	
	@Override
	protected void process(List<KhachHang> listKH) {
		try {
			System.out.println(listKH);
			String ngaySinhTrongTable = "";
			String gioiTinhTrongTable = "";

			for (KhachHang kh : listKH) {
				
				String maKH = kh.getMaKH();
				String tenKH = kh.getTenKH();
				String email = kh.getEmail();
				LocalDate ngaySinh = kh.getNgaySinh();

				ngaySinhTrongTable = ngaySinh + "";
				boolean gioiTinh = kh.isGioiTinh();

				if (gioiTinh) {
					gioiTinhTrongTable = "Nam";
				} else {
					gioiTinhTrongTable = "Nu";
				}

				String loaiKH = kh.getLoaiKH();
				String SDT = kh.getSdt();
				int diemHienCo = kh.getDiemHienCo();
				String diemHienCoTrongTable = String.valueOf(diemHienCo);

				String cmnd = kh.getCmnd();
				int diemDaDung = kh.getDiemDaDung();
				String diemDaDungTrongTable = String.valueOf(diemDaDung);

				java.lang.Object[] rowData = { maKH, tenKH, ngaySinhTrongTable, SDT, cmnd, email, loaiKH,
						gioiTinhTrongTable, diemHienCoTrongTable, diemDaDungTrongTable };
				tableModel.addRow(rowData);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
	
	
	protected void done() {
       System.out.println("Finsh");
    }
	
	
	
}
