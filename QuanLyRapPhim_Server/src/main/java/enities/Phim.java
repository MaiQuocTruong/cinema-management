package enities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "phim")
public class Phim implements Serializable{
	@Id
	@Column(name = "MaPhim")
	private String maPhim;
	@Column(name = "MaXuat")
	private String maXuat;
	@Column(name = "TenPhim")
	private String tenPhim;
	@Column(name = "LoaiPhim")
	private String loaiPhim;
	@Column(name = "NgayChieu")
	private Date ngayChieu;
	@Column(name = "NgayHetHan")
	private Date ngayHetHan;
	@Column(name = "GiaTien")
	private Double giaTien;
	@Column(name = "SoLuongVe")
	private Integer soLuongVe;
	@Column(name = "HinhPhim")
	private String hinhPhim;
	@Column(name = "GioiHanTuoi")
	private Integer gioiHanTuoi;
	@Column(name = "ThoiLuong")
	private Integer thoiLuong;
	@Column(name = "NgonNgu")
	private String ngonNgu;
	@Column(name = "QuocGia")
	private String quocGia;
	@Column(name = "TrangThaiPhim")
	private String trangThaiPhim;

	public Phim() {
		super();
	}

	public Phim(String maPhim) {
		super();
		this.maPhim = maPhim;
	}

	public Phim(String maPhim, String maXuat, String tenPhim, String loaiPhim, Date ngayChieu, Date ngayHetHan,
			Double giaTien, Integer soLuongVe, String hinhPhim, Integer gioiHanTuoi, Integer thoiLuong, String ngonNgu,
			String quocGia, String trangThaiPhim) {
		super();
		this.maPhim = maPhim;
		this.maXuat = maXuat;
		this.tenPhim = tenPhim;
		this.loaiPhim = loaiPhim;
		this.ngayChieu = ngayChieu;
		this.ngayHetHan = ngayHetHan;
		this.giaTien = giaTien;
		this.soLuongVe = soLuongVe;
		this.hinhPhim = hinhPhim;
		this.gioiHanTuoi = gioiHanTuoi;
		this.thoiLuong = thoiLuong;
		this.ngonNgu = ngonNgu;
		this.quocGia = quocGia;
		this.trangThaiPhim = trangThaiPhim;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaXuat() {
		return maXuat;
	}

	public void setMaXuat(String maXuat) {
		this.maXuat = maXuat;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getLoaiPhim() {
		return loaiPhim;
	}

	public void setLoaiPhim(String loaiPhim) {
		this.loaiPhim = loaiPhim;
	}

	public Date getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public Date getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	public Double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(Double giaTien) {
		this.giaTien = giaTien;
	}

	public Integer getSoLuongVe() {
		return soLuongVe;
	}

	public void setSoLuongVe(Integer soLuongVe) {
		this.soLuongVe = soLuongVe;
	}

	public String getHinhPhim() {
		return hinhPhim;
	}

	public void setHinhPhim(String hinhPhim) {
		this.hinhPhim = hinhPhim;
	}

	public Integer getGioiHanTuoi() {
		return gioiHanTuoi;
	}

	public void setGioiHanTuoi(Integer gioiHanTuoi) {
		this.gioiHanTuoi = gioiHanTuoi;
	}

	public Integer getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(Integer thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getNgonNgu() {
		return ngonNgu;
	}

	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public String getTrangThaiPhim() {
		return trangThaiPhim;
	}

	public void setTrangThaiPhim(String trangThaiPhim) {
		this.trangThaiPhim = trangThaiPhim;
	}

	@Override
	public String toString() {
		return "Phim [maPhim=" + maPhim + ", maXuat=" + maXuat + ", tenPhim=" + tenPhim + ", loaiPhim=" + loaiPhim
				+ ", ngayChieu=" + ngayChieu + ", ngayHetHan=" + ngayHetHan + ", giaTien=" + giaTien + ", soLuongVe="
				+ soLuongVe + ", hinhPhim=" + hinhPhim + ", gioiHanTuoi=" + gioiHanTuoi + ", thoiLuong=" + thoiLuong
				+ ", ngonNgu=" + ngonNgu + ", quocGia=" + quocGia + ", trangThaiPhim=" + trangThaiPhim + "]";
	}

	

	
	
	
	
}
