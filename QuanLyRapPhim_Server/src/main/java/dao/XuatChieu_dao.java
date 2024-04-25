package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import enities.PhongChieuPhim;
import enities.XuatChieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class XuatChieu_dao {
	private EntityManager em;

//	public XuatChieu_dao() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public XuatChieu_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<XuatChieu> getListXuatChieu(){
		List<XuatChieu> listXuatChieu = new ArrayList<XuatChieu>();
		
		TypedQuery<XuatChieu> resultQuery = em.createQuery("Select xc from XuatChieu xc" , XuatChieu.class);
		listXuatChieu = resultQuery.getResultList();
		return listXuatChieu;
	}
	
	public XuatChieu findXuatChieuOnMaXC(String maXC) {
	    return em.createQuery("Select xc from XuatChieu xc where xc.maXuat = :maXuat", XuatChieu.class)
	             .setParameter("maXuat", maXC)
	             .getSingleResult();
}
	
	public void deleteXuatChieu(String maXuat) {
		try {
//			// Kiểm tra xem đối tượng có trong persistence context hay không
			XuatChieu xcDelete = em.find(XuatChieu.class, maXuat);
			 if (xcDelete != null && em.contains(xcDelete)) {
				 	em.getTransaction().begin();
	                em.remove(xcDelete); // Xóa đối tượng nếu nó được quản lý
	                em.getTransaction().commit();
	          }
		}catch (Exception e) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
		}
	}
	
	public void addxuatChieu(XuatChieu xc) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(xc);
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
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
	public XuatChieu findXuatChieuOnNgayChieu(LocalDate ngayChieu) {
	    return em.createQuery("SELECT xc FROM XuatChieu xc WHERE xc.ngayChieu = :ngayChieu", XuatChieu.class)
	             .setParameter("ngayChieu", ngayChieu)
	             .getSingleResult();
	}

	
}
