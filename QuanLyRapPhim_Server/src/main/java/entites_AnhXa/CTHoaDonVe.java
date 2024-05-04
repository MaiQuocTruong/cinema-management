//package entites_AnhXa;
//
//import java.io.Serializable;
//import java.sql.Time;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumns;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class CTHoaDonVe implements Serializable{
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaHD")
//	private HoaDon hoadon;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaXuat")
//	private XuatChieu xuatChieu;
//	
//	@Id
//	@Column(name = "MaVe")
//	private String maVe;
//	
//	@Column(name = "MaGhe")
//	private String maGhe;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaPhongChieu")
//	private PhongChieuPhim phongChieu;
//	
//	@Column(name = "GioChieu")
//	private Time gioChieu;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaPhim")
//	private Phim phim;
//	
//	@Column(name = "TenPhim")
//	private String tenPhim;
//	
//	@Column(name = "DonGiaPhim")
//	private double donGiaPhim;
//
//	public CTHoaDonVe() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public CTHoaDonVe(HoaDon hoadon, XuatChieu xuatChieu, String maVe, String maGhe, PhongChieuPhim phongChieu,
//			Time gioChieu, Phim phim, String tenPhim, double donGiaPhim) {
//		super();
//		this.hoadon = hoadon;
//		this.xuatChieu = xuatChieu;
//		this.maVe = maVe;
//		this.maGhe = maGhe;
//		this.phongChieu = phongChieu;
//		this.gioChieu = gioChieu;
//		this.phim = phim;
//		this.tenPhim = tenPhim;
//		this.donGiaPhim = donGiaPhim;
//	}
//
//	public HoaDon getHoadon() {
//		return hoadon;
//	}
//
//	public void setHoadon(HoaDon hoadon) {
//		this.hoadon = hoadon;
//	}
//
//	public XuatChieu getXuatChieu() {
//		return xuatChieu;
//	}
//
//	public void setXuatChieu(XuatChieu xuatChieu) {
//		this.xuatChieu = xuatChieu;
//	}
//
//	public String getMaVe() {
//		return maVe;
//	}
//
//	public void setMaVe(String maVe) {
//		this.maVe = maVe;
//	}
//
//	public String getMaGhe() {
//		return maGhe;
//	}
//
//	public void setMaGhe(String maGhe) {
//		this.maGhe = maGhe;
//	}
//
//	public PhongChieuPhim getPhongChieu() {
//		return phongChieu;
//	}
//
//	public void setPhongChieu(PhongChieuPhim phongChieu) {
//		this.phongChieu = phongChieu;
//	}
//
//	public Time getGioChieu() {
//		return gioChieu;
//	}
//
//	public void setGioChieu(Time gioChieu) {
//		this.gioChieu = gioChieu;
//	}
//
//	public Phim getPhim() {
//		return phim;
//	}
//
//	public void setPhim(Phim phim) {
//		this.phim = phim;
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
//	public double getDonGiaPhim() {
//		return donGiaPhim;
//	}
//
//	public void setDonGiaPhim(double donGiaPhim) {
//		this.donGiaPhim = donGiaPhim;
//	}
//
//	@Override
//	public String toString() {
//		return "CTHoaDonVe [hoadon=" + hoadon + ", xuatChieu=" + xuatChieu + ", maVe=" + maVe + ", maGhe=" + maGhe
//				+ ", phongChieu=" + phongChieu + ", gioChieu=" + gioChieu + ", phim=" + phim + ", tenPhim=" + tenPhim
//				+ ", donGiaPhim=" + donGiaPhim + "]";
//	}
//
//	
//	
//}
