package task;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import enities.KhachHang;
import enities.NhanVien;

public class TaskExecuteMultiThreadNhanVien extends SwingWorker<NhanVien, NhanVien>{
	private List<NhanVien> listNV;
	private DefaultTableModel tableModel;
	
	
	
	
	public TaskExecuteMultiThreadNhanVien(List<NhanVien> listNV, DefaultTableModel tableModel) {
		super();
		this.listNV = listNV;
		this.tableModel = tableModel;
	}


	@Override
	protected NhanVien doInBackground() throws Exception {
		for (NhanVien nv : listNV) {
			publish(nv);
		}
		return null;
	}
	
	@Override
	protected void process(List<NhanVien> listNV) {
		// TODO Auto-generated method stub
				try {
					String ngaySinhTrongTable = "";
					String gioiTinhTrongTable = "";
					String trangThaiTrongTable = "";
					for (NhanVien nv : listNV) {
						String maNV = nv.getMaNV();
						String tenNV = nv.getTenNV();
						String sdt = nv.getSdt();
						LocalDate ngaySinh = nv.getNgaySinh();
						ngaySinhTrongTable = ngaySinh + "";
						String diaChi = nv.getDiaChi();
						String email = nv.getEmail();

						boolean gioiTinh = nv.isGioiTinh();
						if (gioiTinh) {
							gioiTinhTrongTable = "Nam";
						} else {
							gioiTinhTrongTable = "Nu";
						}
						String chucVu = nv.getChucVu();
						
						boolean trangThai = nv.isTrangThai();
						trangThaiTrongTable = trangThai + "";
						if (trangThai) {
							trangThaiTrongTable = "Còn làm";
						} else {
							trangThaiTrongTable = "Ngưng làm";
						}
						java.lang.Object[] rowData = { maNV, tenNV, ngaySinhTrongTable, sdt, diaChi, email, chucVu,
								gioiTinhTrongTable, trangThaiTrongTable };
						tableModel.addRow(rowData);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
    	
    }
	
	
	protected void done() {
       System.out.println("Finsh");
    }
	
	
	
}
