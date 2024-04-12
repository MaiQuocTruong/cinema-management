package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
	
	public List<TaiKhoan> getListTaiKhoan(){
		List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
		
		TypedQuery<TaiKhoan> resultQuery = em.createQuery("Select tk from TaiKhoan tk" , TaiKhoan.class);
		listTK = resultQuery.getResultList();
		return listTK;
	}
	
	public void updateTrangThaiTK(String idNV1) {
		try {
			TaiKhoan tk_sua = em.find(TaiKhoan.class, idNV1);
			System.out.println(tk_sua);
			if(tk_sua != null && em.contains(tk_sua)) {
				tk_sua.setTrangThai(true);
				em.getTransaction().begin();
				em.merge(tk_sua);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
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
			if(tk != null && em.contains(tk)) {
				tk.setTrangThai(true);
				em.getTransaction().begin();
				em.merge(tk);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
}
