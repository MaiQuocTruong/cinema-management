package enities;

import java.io.Serializable;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class CTHoaDonVe implements Serializable{
	@Id
	@Column(name = "MaHD")
	private String maHD;
	
	@Id
	@Column(name = "MaXuat")
	private String maXuat;
	
	@Id
	@Column(name = "MaVe")
	private String maVe;
	
	@Column(name = "MaGhe")
	private String maGhe;
	
	@Id
	@Column(name = "MaPhongChieu")
	private String maPhongChieu;
	
	@Column(name = "GioChieu")
	private Time gioChieu;
	
	@Id
	@Column(name = "MaPhim")
	private String maPhim;
	
	@Column(name = "TenPhim")
	private String tenPhim;
	
	@Column(name = "DonGiaPhim")
	private double donGiaPhim;
	
	

	public CTHoaDonVe() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CTHoaDonVe(String maHD, String maXuat, String maVe, String maGhe, String maPhongChieu, Time gioChieu,
			String maPhim, String tenPhim, double donGiaPhim) {
		super();
		this.maHD = maHD;
		this.maXuat = maXuat;
		this.maVe = maVe;
		this.maGhe = maGhe;
		this.maPhongChieu = maPhongChieu;
		this.gioChieu = gioChieu;
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.donGiaPhim = donGiaPhim;
	}



	public String getMaHD() {
		return maHD;
	}



	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}



	public String getMaXuat() {
		return maXuat;
	}



	public void setMaXuat(String maXuat) {
		this.maXuat = maXuat;
	}



	public String getMaVe() {
		return maVe;
	}



	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}



	public String getMaGhe() {
		return maGhe;
	}



	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}



	public String getMaPhongChieu() {
		return maPhongChieu;
	}



	public void setMaPhongChieu(String maPhongChieu) {
		this.maPhongChieu = maPhongChieu;
	}



	public Time getGioChieu() {
		return gioChieu;
	}



	public void setGioChieu(Time gioChieu) {
		this.gioChieu = gioChieu;
	}



	public String getMaPhim() {
		return maPhim;
	}



	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}



	public String getTenPhim() {
		return tenPhim;
	}



	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}



	public double getDonGiaPhim() {
		return donGiaPhim;
	}



	public void setDonGiaPhim(double donGiaPhim) {
		this.donGiaPhim = donGiaPhim;
	}



	@Override
	public String toString() {
		return "CTHoaDonVe [maHD=" + maHD + ", maXuat=" + maXuat + ", maVe=" + maVe + ", maGhe=" + maGhe
				+ ", maPhongChieu=" + maPhongChieu + ", gioChieu=" + gioChieu + ", maPhim=" + maPhim + ", tenPhim="
				+ tenPhim + ", donGiaPhim=" + donGiaPhim + "]";
	}

	
	
	
	
}
