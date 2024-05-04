//package entites_AnhXa;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "giadichvu")
//public class GiaDichVu {
//	
////	@Id
////	@Column(name = "MaGiaDichVu")
////	private String maGiaDichVu;
//	
//	@Id
//	@ManyToOne
//    @JoinColumn(name = "MaDichVu")
//    private DichVuAnUong DichVuAnUong;
//	
//	@Column(name = "DonGia")
//	private double donGia;
//	
//	@Column (name = "KichThuoc")
//	private String kichThuoc;
//	
//	@Column (name = "TrangThaiSize")
//	private String trangThaiSize;
//
//	public GiaDichVu() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
////	public String getMaGiaDichVu() {
////		return maGiaDichVu;
////	}
////
////	public void setMaGiaDichVu(String maGiaDichVu) {
////		this.maGiaDichVu = maGiaDichVu;
////	}
//
//	public DichVuAnUong getDichVuAnUong() {
//		return DichVuAnUong;
//	}
//
//	public void setDichVuAnUong(DichVuAnUong dichVuAnUong) {
//		DichVuAnUong = dichVuAnUong;
//	}
//
//	public double getDonGia() {
//		return donGia;
//	}
//
//	public void setDonGia(double donGia) {
//		this.donGia = donGia;
//	}
//
//	public String getKichThuoc() {
//		return kichThuoc;
//	}
//
//	public void setKichThuoc(String kichThuoc) {
//		this.kichThuoc = kichThuoc;
//	}
//
//	public String getTrangThaiSize() {
//		return trangThaiSize;
//	}
//
//	public void setTrangThaiSize(String trangThaiSize) {
//		this.trangThaiSize = trangThaiSize;
//	}
//
//	
//
//	
//	
//	
//}
