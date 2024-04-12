package dao;

import java.util.ArrayList;
import java.util.List;

import enities.KhachHang;
import enities.XuatChieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class XuatChieu_dao {
	private EntityManager em;

	public XuatChieu_dao(EntityManager em) {
		super();
		this.em = em;
	}

	public List<XuatChieu> getListXuatChieu() {
		List<XuatChieu> listXC = new ArrayList<XuatChieu>();

		TypedQuery<XuatChieu> resultQuery = em.createQuery("Select xc from XuatChieu xc", XuatChieu.class);
		listXC = resultQuery.getResultList();
		return listXC;
	}
	
	public void addXuatChieu(XuatChieu xc) {
		try {
			em.getTransaction().begin();
			em.persist(xc);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	
	public XuatChieu findXuatChieuOnMaXC(String maXC) {
		return em.createQuery("Select xc from XuatChieu xc where xc.maXuat = :maXuat", XuatChieu.class)
			   .setParameter("maXuat",maXC)
			   .getSingleResult();
	}
	
	public void updateXuatChieu(XuatChieu xc_update) {
		try {
			em.getTransaction().begin();
			em.merge(xc_update);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			
			e.printStackTrace();
		}
	}
	
	public void deleteXuatChieu(String maXC) {
		try {
//			// Kiểm tra xem đối tượng có trong persistence context hay không
			 XuatChieu XuatChieuCanXoa = em.find(XuatChieu.class, maXC);
			 if (XuatChieuCanXoa != null && em.contains(XuatChieuCanXoa)) {
				 	em.getTransaction().begin();
	                em.remove(XuatChieuCanXoa); // Xóa đối tượng nếu nó được quản lý
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
