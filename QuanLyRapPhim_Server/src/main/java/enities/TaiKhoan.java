package enities;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class TaiKhoan implements Serializable {
	@Id
	@Column(name = "MaNhanVien")
	private String maNV;
	
	@Column (name = "MatKhau")
	private String matkhau;
	
	@Column (name = "Email")
	private String email;
	
	@Column (name = "TrangThai")
	private boolean trangThai;

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(String maNV, String matkhau, String email, boolean trangThai) {
		super();
		this.maNV = maNV;
		this.matkhau = matkhau;
		this.email = email;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maNV=" + maNV + ", matkhau=" + matkhau + ", email=" + email + ", trangThai=" + trangThai
				+ "]";
	}
	
	
}
