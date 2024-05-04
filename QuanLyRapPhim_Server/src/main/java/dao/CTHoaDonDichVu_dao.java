package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enities.CTHoaDonDichVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CTHoaDonDichVu_dao {
	private EntityManager em;

	public CTHoaDonDichVu_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<CTHoaDonDichVu> getListHoaDonDichVu(){
		List<CTHoaDonDichVu> listHoaDonDichVu = new ArrayList<>();
		TypedQuery<CTHoaDonDichVu> query = em.createQuery("Select hddv from CTHoaDonDichVu hddv", CTHoaDonDichVu.class);
		listHoaDonDichVu = query.getResultList();
		return listHoaDonDichVu;
	}
	
	
	public boolean addHoaDonDichVu(CTHoaDonDichVu hdDichVu) {
		System.out.println(hdDichVu);
		try {
			em.getTransaction().begin();
			em.persist(hdDichVu);
			em.getTransaction().commit();
			return true;
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
		}
		
		return false;
		
	}
	
	
	public Map<String, Double> getDoanhThuDichVu() {
        Map<String, Double> totalRevenueMap = new HashMap<>();

        try {
            // Query để lấy tổng doanh thu của mỗi phim
            Query query = em.createQuery("SELECT c.tenDichVu, SUM(c.dongGiaDichVu * c.soLuongDichVu) FROM CTHoaDonDichVu c GROUP BY c.tenDichVu");
            List<Object[]> resultList = query.getResultList();

            // Lưu tổng doanh thu vào Map
            for (Object[] row : resultList) {
                String tenDichVu = (String) row[0];
                Double totalRevenue = (Double) row[1];
                totalRevenueMap.put(tenDichVu, totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRevenueMap;
    }
	
	
	
	
}
