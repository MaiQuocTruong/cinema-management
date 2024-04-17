package enities;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "xuatchieu")
public class XuatChieu implements Serializable {
	@Id
	@Column(name = "MaXuat")
	private String maXuat;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaPhim")
	private Phim phim;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaPhongChieu")
	private PhongChieuPhim phongchieu;
	
	@Column(name = "DinhDang")
	private String dinhDang;
	
	@Column(name = "NgayChieu")
	private LocalDate ngayChieu;
	
	@Column(name = "GioChieu")
	private Time gioChieu;
	
	@Column(name = "GioKetThuc")
	private Time gioKetThuc;
	
	@Column(name = "TrangThai")
	private String trangThai;

	public XuatChieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XuatChieu(String maXuat, Phim phim, PhongChieuPhim phongchieu, String dinhDang, LocalDate ngayChieu,
			Time gioChieu, Time gioKetThuc, String trangThai) {
		super();
		this.maXuat = maXuat;
		this.phim = phim;
		this.phongchieu = phongchieu;
		this.dinhDang = dinhDang;
		this.ngayChieu = ngayChieu;
		this.gioChieu = gioChieu;
		this.gioKetThuc = gioKetThuc;
		this.trangThai = trangThai;
	}

	public String getMaXuat() {
		return maXuat;
	}

	public void setMaXuat(String maXuat) {
		this.maXuat = maXuat;
	}

	public Phim getPhim() {
		return phim;
	}

	public void setPhim(Phim phim) {
		this.phim = phim;
	}

	public PhongChieuPhim getPhongchieu() {
		return phongchieu;
	}

	public void setPhongchieu(PhongChieuPhim phongchieu) {
		this.phongchieu = phongchieu;
	}

	public String getDinhDang() {
		return dinhDang;
	}

	public void setDinhDang(String dinhDang) {
		this.dinhDang = dinhDang;
	}

	public LocalDate getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(LocalDate ngayChieu) {
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

	@Override
	public String toString() {
		return "XuatChieu [maXuat=" + maXuat + ", phim=" + phim + ", phongchieu=" + phongchieu + ", dinhDang="
				+ dinhDang + ", ngayChieu=" + ngayChieu + ", gioChieu=" + gioChieu + ", gioKetThuc=" + gioKetThuc
				+ ", trangThai=" + trangThai + "]";
	}

	
	
}
