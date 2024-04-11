package enities;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class NhanVien {
	@Id
	@Column(name = "MaNhanVien")
	private String maNV;
	
	@Column (name = "TenNhanVien")
	private String tenNV;
	
	@Column (name = "DiaChi")
	private String diaChi;
	
	@Column (name = "NgaySinh")
	private Date ngaySinh;
	
	@Column (name = "GioiTinh")
	private boolean gioiTinh;
	
	@Column (name = "Email")
	private String email;
	
	@Column (name = "SDT")
	private String sdt;
	
	@Column (name = "ChucVu")
	private String chucVu;
	
	@Column (name = "TrangThai")
	private boolean trangThai;

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNV, String tenNV, String diaChi, Date ngaySinh, boolean gioiTinh, String email, String sdt,
			String chucVu, boolean trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.sdt = sdt;
		this.chucVu = chucVu;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", email=" + email + ", sdt=" + sdt + ", chucVu=" + chucVu + ", trangThai="
				+ trangThai + "]";
	}
	
}
