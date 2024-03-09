package enities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KhachHang {
	@Id
	@Column(name = "MaKH")
	private String maKH;
	
	@Column (name = "TenKH")
	private String tenKH;
	
	@Column (name = "Email")
	private String email;
	
	@Column (name = "NgaySinh")
	private Date ngaySinh;
	
	@Column (name = "GioiTinh")
	private boolean gioiTinh;
	
	@Column (name = "LoaiKhachHang")
	private String loaiKH;
	
	@Column (name = "SDT")
	private String sdt;
	
	@Column (name = "DiemHienCo")
	private int diemHienCo;
	
	@Column (name = "CMND")
	private String cmnd;
	
	@Column (name = "DiemDaSuDung")
	private int diemDaDung;
	
	@Column (name = "TongChiTieu")
	private double tongChiTieu;

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public KhachHang(String maKH, String tenKH, String email, Date ngaySinh, boolean gioiTinh, String loaiKH,
			String sdt, int diemHienCo, String cmnd, int diemDaDung, double tongChiTieu) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.loaiKH = loaiKH;
		this.sdt = sdt;
		this.diemHienCo = diemHienCo;
		this.cmnd = cmnd;
		this.diemDaDung = diemDaDung;
		this.tongChiTieu = tongChiTieu;
	}




	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getDiemHienCo() {
		return diemHienCo;
	}

	public void setDiemHienCo(int diemHienCo) {
		this.diemHienCo = diemHienCo;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public int getDiemDaDung() {
		return diemDaDung;
	}

	public void setDiemDaDung(int diemDaDung) {
		this.diemDaDung = diemDaDung;
	}

	public double getTongChiTieu() {
		return tongChiTieu;
	}

	public void setTongChiTieu(double tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}




	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", email=" + email + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", loaiKH=" + loaiKH + ", sdt=" + sdt + ", diemHienCo=" + diemHienCo
				+ ", cmnd=" + cmnd + ", diemDaDung=" + diemDaDung + ", tongChiTieu=" + tongChiTieu + "]";
	}
	
	
	
	
}
