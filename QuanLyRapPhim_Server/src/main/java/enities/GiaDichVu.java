package enities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "giadichvu")
public class GiaDichVu {
	@Id
	@Column(name = "MaDichVu")
	private String maDichVu;
	
	@Column(name = "DonGia")
	private double donGia;
	
	@Column (name = "KichThuoc")
	private String kichThuoc;
	
	@Column (name = "TrangThaiSize")
	private String trangThaiSize;

	public GiaDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GiaDichVu(String maDichVu, double donGia, String kichThuoc, String trangThaiSize) {
		super();
		this.maDichVu = maDichVu;
		this.donGia = donGia;
		this.kichThuoc = kichThuoc;
		this.trangThaiSize = trangThaiSize;
	}
	

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public String getTrangThaiSize() {
		return trangThaiSize;
	}

	public void setTrangThaiSize(String trangThaiSize) {
		this.trangThaiSize = trangThaiSize;
	}

	@Override
	public String toString() {
		return "GiaDichVu [maDichVu=" + maDichVu + ", donGia=" + donGia + ", kichThuoc=" + kichThuoc
				+ ", trangThaiSize=" + trangThaiSize + "]";
	}
	
	
}
