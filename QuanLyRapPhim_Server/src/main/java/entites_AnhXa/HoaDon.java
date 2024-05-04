//package entites_AnhXa;
//
//import java.time.LocalDate;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class HoaDon {
//	
//	@Id
//	@Column(name = "MaHD")
//	private String maHD;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "MaKhachHang" , referencedColumnName = "MaKH")
//	private KhachHang kh;
//	
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "MaNhanVien")
//	private NhanVien nv;
//	
//	@Column(name = "UuDai")
//	private double uuDai;
//	
//	@Column(name = "VAT")
//	private double vat;
//	
//	@Column(name = "TongTien")
//	private double tongTien;
//	
//	@Column(name = "NgayLapHD")
//	private LocalDate ngayLapHD;
//
//	
//
//	public HoaDon() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//
//	public HoaDon(String maHD, KhachHang kh, NhanVien nv, double uuDai, double vat, double tongTien,
//			LocalDate ngayLapHD) {
//		super();
//		this.maHD = maHD;
//		this.kh = kh;
//		this.nv = nv;
//		this.uuDai = uuDai;
//		this.vat = vat;
//		this.tongTien = tongTien;
//		this.ngayLapHD = ngayLapHD;
//	}
//
//
//
//	public String getMaHD() {
//		return maHD;
//	}
//
//	public void setMaHD(String maHD) {
//		this.maHD = maHD;
//	}
//
//	public KhachHang getKh() {
//		return kh;
//	}
//
//	public void setKh(KhachHang kh) {
//		this.kh = kh;
//	}
//
//	public NhanVien getNv() {
//		return nv;
//	}
//
//	public void setNv(NhanVien nv) {
//		this.nv = nv;
//	}
//
//	public double getUuDai() {
//		return uuDai;
//	}
//
//	public void setUuDai(double uuDai) {
//		this.uuDai = uuDai;
//	}
//
//	public double getVat() {
//		return vat;
//	}
//
//	public void setVat(double vat) {
//		this.vat = vat;
//	}
//
//	public double getTongTien() {
//		return tongTien;
//	}
//
//	public void setTongTien(double tongTien) {
//		this.tongTien = tongTien;
//	}
//
//	public LocalDate getNgayLapHD() {
//		return ngayLapHD;
//	}
//
//	public void setNgayLapHD(LocalDate ngayLapHD) {
//		this.ngayLapHD = ngayLapHD;
//	}
//
//	@Override
//	public String toString() {
//		return "HoaDonTong [maHD=" + maHD + ", kh=" + kh + ", nv=" + nv + ", uuDai=" + uuDai + ", vat=" + vat
//				+ ", tongTien=" + tongTien + ", ngayLapHD=" + ngayLapHD + "]";
//	}
//
//	
//	
//	
//	
//	
//	
//}
