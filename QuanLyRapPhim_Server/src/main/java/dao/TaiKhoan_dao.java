package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import enities.NhanVien;
import enities.TaiKhoan;

public class TaiKhoan_dao {
	private EntityManager em;

	public TaiKhoan_dao(EntityManager em) {
		super();
		this.em = em;
	}

	public List<TaiKhoan> getListTaiKhoan() {
		List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();

		TypedQuery<TaiKhoan> resultQuery = em.createQuery("Select tk from TaiKhoan tk", TaiKhoan.class);
		listTK = resultQuery.getResultList();
		return listTK;
	}

	public void updateTrangThaiTK(String idNV1) {
		try {
			TaiKhoan tk_sua = em.find(TaiKhoan.class, idNV1);
			System.out.println(tk_sua);
			if (tk_sua != null && em.contains(tk_sua)) {
				tk_sua.setTrangThai(true);
				em.getTransaction().begin();
				em.merge(tk_sua);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}

	// hàm xóa (btn Xóa)
	public void deleteTrangThaiTK(String idNV) {
		try {
			TaiKhoan tk = em.find(TaiKhoan.class, idNV);
			System.out.println(tk);
			if (tk != null && em.contains(tk)) {
				tk.setTrangThai(false);
				em.getTransaction().begin();
				em.merge(tk);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}

	public TaiKhoan findEmployeeOnMaNV(String manv) {
		try {
			return em.createQuery("Select tk from TaiKhoan tk where tk.maNV = :maNV", TaiKhoan.class)
					.setParameter("maNV", manv).getSingleResult();
		} catch (NoResultException e) {
			// Xử lý khi không tìm thấy kết quả
			// Hiển thị thông báo cho người dùng
			return null; // Hoặc bạn có thể trả về một giá trị mặc định hoặc null tùy thuộc vào logic của
							// bạn
		}
	}
	 
//	public Boolean kiemTraMatKhau(String maNVCheck ,String matKhauCanCheck) {
//		try {
//			TaiKhoan nvCanCheck = em.createQuery("Select tk from TaiKhoan tk where tk.maNV = :maNV", TaiKhoan.class)
//					.setParameter("maNV", maNVCheck).getSingleResult();
//			Boolean flag = nvCanCheck.getMatkhau().equals(matKhauCanCheck);
//			return flag;
//		}catch (NoResultException e) {
//			return null;
//		}
//	}
}
