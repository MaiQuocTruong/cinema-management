package task;

import java.time.LocalDate;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import enities.NhanVien;
import enities.TaiKhoan;

public class TaskExecuteMultiThreadTaiKhoan extends SwingWorker<TaiKhoan, TaiKhoan>{
	private List<TaiKhoan> listTK;
	private DefaultTableModel tableModel;
	
	
	
	
	public TaskExecuteMultiThreadTaiKhoan(List<TaiKhoan> listTK, DefaultTableModel tableModel) {
		super();
		this.listTK = listTK;
		this.tableModel = tableModel;
	}


	@Override
	protected TaiKhoan doInBackground() throws Exception {
		for (TaiKhoan tk : listTK) {
			publish(tk);
		}
		return null;
	}
	
	@Override
	protected void process(List<TaiKhoan> listTK) {
		try {
			String trangThaiTrongTable = "";
			for (TaiKhoan tk : listTK) {
				String maNV = tk.getMaNV();
				String matKhau = tk.getMatkhau();
				String email = tk.getEmail();
				
				boolean trangThai = tk.isTrangThai();
				trangThaiTrongTable = trangThai + "";
				if (trangThai) {
					trangThaiTrongTable = "Còn làm";
				} else {
					trangThaiTrongTable = "Ngưng làm";
				}
				java.lang.Object[] rowData = { maNV, matKhau, email, trangThaiTrongTable };
				tableModel.addRow(rowData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
	
	
	protected void done() {
       System.out.println("Finsh");
    }
	
}
