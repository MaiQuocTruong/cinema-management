package enities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "phongchieuphim")
public class PhongChieuPhim implements Serializable{
	@Id
	@Column(name = "MaPhongChieu")
	private String maPhongChieu;
	
	@Column(name = "TenPhongChieu")
	private String tenPhongChieu;
	
	@Column(name = "SucChua")
	private int sucChua;

	@OneToMany(mappedBy = "phongchieu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<XuatChieu> xuatChieus = new HashSet<>();
	
	
	public Set<XuatChieu> getXuatChieus() {
		return xuatChieus;
	}

	public PhongChieuPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhongChieuPhim(String maPhongChieu, String tenPhongChieu, int sucChua) {
		super();
		this.maPhongChieu = maPhongChieu;
		this.tenPhongChieu = tenPhongChieu;
		this.sucChua = sucChua;
	}

	public String getMaPhongChieu() {
		return maPhongChieu;
	}

	public void setMaPhongChieu(String maPhongChieu) {
		this.maPhongChieu = maPhongChieu;
	}

	public String getTenPhongChieu() {
		return tenPhongChieu;
	}

	public void setTenPhongChieu(String tenPhongChieu) {
		this.tenPhongChieu = tenPhongChieu;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	@Override
	public String toString() {
		return "PhongChieuPhim [maPhongChieu=" + maPhongChieu + ", tenPhongChieu=" + tenPhongChieu + ", sucChua="
				+ sucChua + "]";
	}
	
	
}
