//package entites_AnhXa;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//
//@Entity
//public class Phim implements Serializable{
//	@Id
//	@Column(name = "MaPhim")
//	private String maPhim;
//	
//	@Column(name = "TenPhim")
//	private String tenPhim;
//	
//	@Column(name = "LoaiPhim")
//	private String loaiPhim;
//	
//	@Column(name = "NgayChieu")
//	private LocalDate ngayChieu;
//	
//	@Column(name = "NgayHetHan")
//	private LocalDate ngayHetHan;
//	
//	@Column(name = "GiaTien")
//	private double giaTien;
//	
//	@Column(name = "SoLuongVe")
//	private int soLuongVe;
//	
//	@Column(name = "HinhPhim")
//	private String hinhPhim;
//	
//	@Column(name = "GioiHanTuoi")
//	private int gioiHanTuoi;
//	
//	@Column(name = "ThoiLuong")
//	private int thoiLuong;
//	
//	@Column(name = "NgonNgu")
//	private String ngonNgu;
//	
//	@Column(name = "QuocGia")
//	private String quocGia;
//	
//	@Column(name = "TrangThaiPhim")
//	private String trangThaiPhim;
//
//	@OneToMany(mappedBy = "phim", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<XuatChieu> xuatChieus = new HashSet<>();
//	
//	public Phim() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Phim(String maPhim, String tenPhim, String loaiPhim, LocalDate ngayChieu, LocalDate ngayHetHan,
//			double giaTien, int soLuongVe, String hinhPhim, int gioiHanTuoi, int thoiLuong, String ngonNgu,
//			String quocGia, String trangThaiPhim) {
//		super();
//		this.maPhim = maPhim;
//		this.tenPhim = tenPhim;
//		this.loaiPhim = loaiPhim;
//		this.ngayChieu = ngayChieu;
//		this.ngayHetHan = ngayHetHan;
//		this.giaTien = giaTien;
//		this.soLuongVe = soLuongVe;
//		this.hinhPhim = hinhPhim;
//		this.gioiHanTuoi = gioiHanTuoi;
//		this.thoiLuong = thoiLuong;
//		this.ngonNgu = ngonNgu;
//		this.quocGia = quocGia;
//		this.trangThaiPhim = trangThaiPhim;
//	}
//
//	public String getMaPhim() {
//		return maPhim;
//	}
//
//	public void setMaPhim(String maPhim) {
//		this.maPhim = maPhim;
//	}
//
//	public String getTenPhim() {
//		return tenPhim;
//	}
//
//	public void setTenPhim(String tenPhim) {
//		this.tenPhim = tenPhim;
//	}
//
//	public String getLoaiPhim() {
//		return loaiPhim;
//	}
//
//	public void setLoaiPhim(String loaiPhim) {
//		this.loaiPhim = loaiPhim;
//	}
//
//	public LocalDate getNgayChieu() {
//		return ngayChieu;
//	}
//
//	public void setNgayChieu(LocalDate ngayChieu) {
//		this.ngayChieu = ngayChieu;
//	}
//
//	public LocalDate getNgayHetHan() {
//		return ngayHetHan;
//	}
//
//	public void setNgayHetHan(LocalDate ngayHetHan) {
//		this.ngayHetHan = ngayHetHan;
//	}
//
//	public double getGiaTien() {
//		return giaTien;
//	}
//
//	public void setGiaTien(double giaTien) {
//		this.giaTien = giaTien;
//	}
//
//	public int getSoLuongVe() {
//		return soLuongVe;
//	}
//
//	public void setSoLuongVe(int soLuongVe) {
//		this.soLuongVe = soLuongVe;
//	}
//
//	public String getHinhPhim() {
//		return hinhPhim;
//	}
//
//	public void setHinhPhim(String hinhPhim) {
//		this.hinhPhim = hinhPhim;
//	}
//
//	public int getGioiHanTuoi() {
//		return gioiHanTuoi;
//	}
//
//	public void setGioiHanTuoi(int gioiHanTuoi) {
//		this.gioiHanTuoi = gioiHanTuoi;
//	}
//
//	public int getThoiLuong() {
//		return thoiLuong;
//	}
//
//	public void setThoiLuong(int thoiLuong) {
//		this.thoiLuong = thoiLuong;
//	}
//
//	public String getNgonNgu() {
//		return ngonNgu;
//	}
//
//	public void setNgonNgu(String ngonNgu) {
//		this.ngonNgu = ngonNgu;
//	}
//
//	public String getQuocGia() {
//		return quocGia;
//	}
//
//	public void setQuocGia(String quocGia) {
//		this.quocGia = quocGia;
//	}
//
//	public String getTrangThaiPhim() {
//		return trangThaiPhim;
//	}
//
//	public Set<XuatChieu> getXuatChieus() {
//		return xuatChieus;
//	}
//
//	public void setXuatChieus(Set<XuatChieu> xuatChieus) {
//		this.xuatChieus = xuatChieus;
//	}
//
//	public void setTrangThaiPhim(String trangThaiPhim) {
//		this.trangThaiPhim = trangThaiPhim;
//	}
//
//	@Override
//	public String toString() {
//		return "Phim [maPhim=" + maPhim + ", tenPhim=" + tenPhim + ", loaiPhim=" + loaiPhim + ", ngayChieu=" + ngayChieu
//				+ ", ngayHetHan=" + ngayHetHan + ", giaTien=" + giaTien + ", soLuongVe=" + soLuongVe + ", hinhPhim="
//				+ hinhPhim + ", gioiHanTuoi=" + gioiHanTuoi + ", thoiLuong=" + thoiLuong + ", ngonNgu=" + ngonNgu
//				+ ", quocGia=" + quocGia + ", trangThaiPhim=" + trangThaiPhim + "]";
//	}
//
//	
//
//	
//	
//	
//}
