package enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dichvuanuong")
public class DichVuAnUong {
	@Id
	@Column(name = "MaDichVu")
	private String maDichVu;
	
	@Column(name = "TenDichVu")
	private String tenDichVu;
	
	@Column (name = "TrangThai")
	private String trangThai;
	
	@Column (name = "LoaiDichVu")
	private String loaiDichVu;
	
	@Column (name =  " SoLuong")
	private int soLuong;
	
	@Column (name = "DonGia")
	private double donGia;
	
	@Column (name = "KichThuoc")
	private String kichThuoc;
	
	@Column (name = "TrangThaiSize")
	private String trangThaiSize;

	public DichVuAnUong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DichVuAnUong(String maDichVu, String tenDichVu, String trangThai, String loaiDichVu, int soLuong,
			double donGia, String kichThuoc, String trangThaiSize) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.trangThai = trangThai;
		this.loaiDichVu = loaiDichVu;
		this.soLuong = soLuong;
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

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getLoaiDichVu() {
		return loaiDichVu;
	}

	public void setLoaiDichVu(String loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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
		return "DichVuAnUong [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", trangThai=" + trangThai
				+ ", loaiDichVu=" + loaiDichVu + ", soLuong=" + soLuong + ", donGia=" + donGia + ", kichThuoc="
				+ kichThuoc + ", trangThaiSize=" + trangThaiSize + "]";
	}
	
	
	
}
