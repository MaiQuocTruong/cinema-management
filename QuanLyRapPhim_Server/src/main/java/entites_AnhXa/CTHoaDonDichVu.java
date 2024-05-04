//package entites_AnhXa;
//
//import java.io.Serializable;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class CTHoaDonDichVu implements Serializable{
//	@Id
//	@Column(name = "MaHoaDonDichVu")
//	private String maHDDichVu;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaHD")
//	private HoaDon hoadon;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "MaDichVu")
//	private DichVuAnUong dichVu;
//	
//	@Column(name = "TenDichVu")
//	private String tenDichVu;
//	
//	@Column(name = "SoLuong")
//	private int soLuongDichVu;
//	
//	@Column(name = "DonGiaDichVu")
//	private double dongGiaDichVu;
//
//	
//	
//	
//	public CTHoaDonDichVu() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//
//	public CTHoaDonDichVu(HoaDon hoadon, DichVuAnUong dichVu, String tenDichVu, int soLuongDichVu,
//			double dongGiaDichVu) {
//		super();
//		this.hoadon = hoadon;
//		this.dichVu = dichVu;
//		this.tenDichVu = tenDichVu;
//		this.soLuongDichVu = soLuongDichVu;
//		this.dongGiaDichVu = dongGiaDichVu;
//	}
//
//
//	public HoaDon getHoadon() {
//		return hoadon;
//	}
//
//
//	public void setHoadon(HoaDon hoadon) {
//		this.hoadon = hoadon;
//	}
//
//
//	public DichVuAnUong getDichVu() {
//		return dichVu;
//	}
//
//
//	public void setDichVu(DichVuAnUong dichVu) {
//		this.dichVu = dichVu;
//	}
//
//
//	public String getTenDichVu() {
//		return tenDichVu;
//	}
//
//
//	public void setTenDichVu(String tenDichVu) {
//		this.tenDichVu = tenDichVu;
//	}
//
//
//	public int getSoLuongDichVu() {
//		return soLuongDichVu;
//	}
//
//
//	public void setSoLuongDichVu(int soLuongDichVu) {
//		this.soLuongDichVu = soLuongDichVu;
//	}
//
//
//	public double getDongGiaDichVu() {
//		return dongGiaDichVu;
//	}
//
//
//	public void setDongGiaDichVu(double dongGiaDichVu) {
//		this.dongGiaDichVu = dongGiaDichVu;
//	}
//
//
//	@Override
//	public String toString() {
//		return "CTHoaDonDichVu [hoadon=" + hoadon + ", dichVu=" + dichVu + ", tenDichVu=" + tenDichVu
//				+ ", soLuongDichVu=" + soLuongDichVu + ", dongGiaDichVu=" + dongGiaDichVu + "]";
//	}
//
//
//	
//
//
//
//	
//	
//}
