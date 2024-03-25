package enities;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xuatchieu")
public class XuatChieu {
	@Id
	@Column(name = "MaXuat")
	private String maXuat;
	@Column(name = "DinhDang")
	private String dinhDang;
	@Column(name = "NgayChieu")
	private Date ngayChieu;
	@Column(name = "GioChieu")
	private Time gioChieu;
	@Column(name = "GioKetThuc")
	private Time gioKetThuc;
	@Column(name = "TrangThai")
	private String trangThai;
	
	public XuatChieu() {
		super();
	}

	public XuatChieu(String maXuat) {
		super();
		this.maXuat = maXuat;
	}

	public XuatChieu(String maXuat, String dinhDang, Date ngayChieu, Time gioChieu, Time gioKetThuc, String trangThai) {
		super();
		this.maXuat = maXuat;
		this.dinhDang = dinhDang;
		this.ngayChieu = ngayChieu;
		this.gioChieu = gioChieu;
		this.gioKetThuc = gioKetThuc;
		this.trangThai = trangThai;
	}

	public String getDinhDang() {
		return dinhDang;
	}

	public void setDinhDang(String dinhDang) {
		this.dinhDang = dinhDang;
	}

	public Date getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public Time getGioChieu() {
		return gioChieu;
	}

	public void setGioChieu(Time gioChieu) {
		this.gioChieu = gioChieu;
	}

	public Time getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(Time gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaXuat() {
		return maXuat;
	}
	
	
	
	
}
