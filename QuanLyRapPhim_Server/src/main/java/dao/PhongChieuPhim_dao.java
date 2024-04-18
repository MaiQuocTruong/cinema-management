package dao;

import java.util.ArrayList;
import java.util.List;

import enities.NhanVien;
import enities.PhongChieuPhim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PhongChieuPhim_dao {
	private EntityManager em;

	public PhongChieuPhim_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<PhongChieuPhim> getListPhongChieu(){
		List<PhongChieuPhim> listPhongChieu = new ArrayList<PhongChieuPhim>();
		
		TypedQuery<PhongChieuPhim> resultQuery = em.createQuery("Select pc from PhongChieuPhim pc" , PhongChieuPhim.class);
		listPhongChieu = resultQuery.getResultList();
		return listPhongChieu;
	}
	
	public void addPhongChieu(PhongChieuPhim pcp) {
		try {
			em.getTransaction().begin();
			em.persist(pcp);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	
	public PhongChieuPhim findPhongChieuOnMaPC(String maPC) {
	    return em.createQuery("Select pc from PhongChieuPhim pc where pc.maPhongChieu = :maPhongChieu", PhongChieuPhim.class)
	             .setParameter("maPhongChieu", maPC)
	             .getSingleResult();
	}
	
	public void updatePhongChieu(PhongChieuPhim pcp_update) {
		try {
			em.getTransaction().begin();
			em.merge(pcp_update);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			
			e.printStackTrace();
		}
	}
	
	public void deletePhongChieu(String maPhongChieu) {
		try {
//			// Kiểm tra xem đối tượng có trong persistence context hay không
			 PhongChieuPhim phongChieuDelete = em.find(PhongChieuPhim.class, maPhongChieu);
			 if (phongChieuDelete != null && em.contains(phongChieuDelete)) {
				 	em.getTransaction().begin();
	                em.remove(phongChieuDelete); // Xóa đối tượng nếu nó được quản lý
	                em.getTransaction().commit();
	          }
		}catch (Exception e) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
		}
	}
}
