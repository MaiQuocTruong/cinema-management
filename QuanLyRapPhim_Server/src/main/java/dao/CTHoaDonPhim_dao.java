package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enities.CTHoaDonVe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CTHoaDonPhim_dao {
	private EntityManager em;

	public CTHoaDonPhim_dao(EntityManager em) {
		super();
		this.em = em;
	}

	
	public List<CTHoaDonVe> getListHoaDonVePhim(){
		List<CTHoaDonVe> listHDVePhim = new ArrayList<>();
		TypedQuery<CTHoaDonVe> query = em.createQuery("Select hdvp from CTHoaDonVe hdvp", CTHoaDonVe.class);
		listHDVePhim = query.getResultList();
		return listHDVePhim;
	}
	
	public boolean addHoaDonPhim(CTHoaDonVe hdPhim) {
		try {
			em.getTransaction().begin();
			em.persist(hdPhim);
			em.getTransaction().commit();
			return true;
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
		}
		return false;
	}
	
	public Map<String, Double> getDoanhThuTheoPhim() {
        Map<String, Double> totalRevenueMap = new HashMap<>();

        try {
            // Query để lấy tổng doanh thu của mỗi phim
            Query query = em.createQuery("SELECT c.tenPhim, SUM(c.donGiaPhim) FROM CTHoaDonVe c GROUP BY c.tenPhim");
            List<Object[]> resultList = query.getResultList();

            // Lưu tổng doanh thu vào Map
            for (Object[] row : resultList) {
                String tenPhim = (String) row[0];
                Double totalRevenue = (Double) row[1];
                totalRevenueMap.put(tenPhim, totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRevenueMap;
    }
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
}
