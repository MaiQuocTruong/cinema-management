package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enities.NhanVien;
import enities.Phim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class Phim_dao {
	private EntityManager em;

	public Phim_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	
	public List<Phim> getListMovies(){
		List<Phim> listMovies = new ArrayList<Phim>();
			
		try {
			em.getTransaction().begin();
			em.clear();
			TypedQuery<Phim> resultQuery = em.createQuery("Select p from Phim p" , Phim.class);
			listMovies = resultQuery.getResultList();
			em.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return listMovies;
	}
	
	
	
	public void addPhimmoi(Phim ph) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(ph);
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	
	public Phim findPhimonTen(String tenPhim) {
		try {
	       Phim p = em.createQuery("SELECT p FROM Phim p WHERE p.tenPhim = :tenPhim", Phim.class)
	                .setParameter("tenPhim", tenPhim)
	                .getSingleResult();
	        
	        return p; // Trả về Phim nếu tìm thấy
	    } catch (NoResultException e) {
	        return null; // Trả về null nếu không tìm thấy
	    }
	}
	
	public Phim findPhimonma(String maPhim) {
		
		 return em.createQuery("SELECT p FROM Phim p WHERE p.maPhim = :maPhim", Phim.class)
	                .setParameter("maPhim", maPhim)
	                .getSingleResult();
	        
	    
	}
	
	    
	    

	public void updatePhim(Phim ph_update) {
		try {
			em.getTransaction().begin();
			em.merge(ph_update);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			
			e.printStackTrace();
		}
	}
	
	public void deletePhim(String maPhim) {
		try {
//			// Kiểm tra xem đối tượng có trong persistence context hay không
			 Phim phimDelete = em.find(Phim.class, maPhim);
			 if (phimDelete != null && em.contains(phimDelete)) {
				 	em.getTransaction().begin();
	                em.remove(phimDelete); // Xóa đối tượng nếu nó được quản lý
	                em.getTransaction().commit();
	          }
		}catch (Exception e) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
		}
	}
	
	public List<Phim> findXuatChieuTrongKhoangNgay(LocalDate tuNgay, LocalDate denNgay) {
	    return em.createQuery("SELECT p FROM Phim p WHERE p.ngayChieu BETWEEN :tuNgay AND :denNgay", Phim.class)
	             .setParameter("tuNgay", tuNgay)
	             .setParameter("denNgay", denNgay)
	             .getResultList();
	}
	
	
}
