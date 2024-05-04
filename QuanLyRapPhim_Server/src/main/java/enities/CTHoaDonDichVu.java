package enities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CTHoaDonDichVu implements Serializable{
	@Id
	@Column(name = "MaHD")
	private String maHD;
	
	@Id
	@Column(name = "MaDichVu")
	private String maDichVu;
	
	@Column(name = "TenDichVu")
	private String tenDichVu;
	
	@Column(name = "SoLuong")
	private int soLuongDichVu;
	
	@Column(name = "DonGiaDichVu")
	private double dongGiaDichVu;

	
	
	public CTHoaDonDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CTHoaDonDichVu(String maHD, String maDichVu, String tenDichVu, int soLuongDichVu, double dongGiaDichVu) {
		super();
		this.maHD = maHD;
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.soLuongDichVu = soLuongDichVu;
		this.dongGiaDichVu = dongGiaDichVu;
	}



	public String getMaHD() {
		return maHD;
	}



	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}



	public String getMaDichVu() {
		return maDichVu;
	}



	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}



	public String getTenDichVu() {
		return tenDichVu;
	}



	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}



	public int getSoLuongDichVu() {
		return soLuongDichVu;
	}



	public void setSoLuongDichVu(int soLuongDichVu) {
		this.soLuongDichVu = soLuongDichVu;
	}



	public double getDongGiaDichVu() {
		return dongGiaDichVu;
	}



	public void setDongGiaDichVu(double dongGiaDichVu) {
		this.dongGiaDichVu = dongGiaDichVu;
	}



	@Override
	public String toString() {
		return "CTHoaDonDichVu [maHD=" + maHD + ", maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu
				+ ", soLuongDichVu=" + soLuongDichVu + ", dongGiaDichVu=" + dongGiaDichVu + "]";
	}


	
	
}
