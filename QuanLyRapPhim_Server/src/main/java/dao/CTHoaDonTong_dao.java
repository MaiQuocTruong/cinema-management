package dao;

import java.util.ArrayList;
import java.util.List;

import enities.HoaDon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CTHoaDonTong_dao {
	private EntityManager em;

	public CTHoaDonTong_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<HoaDon> getListHoaDonTong(){
		List<HoaDon> listHoaDonTong = new ArrayList<>();
		TypedQuery<HoaDon> query = em.createQuery("Select hdt from HoaDon hdt", HoaDon.class);
		listHoaDonTong = query.getResultList();
		return listHoaDonTong;
	}
	
	public boolean addHoaDonTong(HoaDon hdTong) {
		try {
			em.getTransaction().begin();
			em.persist(hdTong);
			em.getTransaction().commit();
			return true;
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
		}
		
		return false;
		
	}
	
	
	
}
