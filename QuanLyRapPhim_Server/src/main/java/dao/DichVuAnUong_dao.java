package dao;

import java.util.List;

import enities.DichVuAnUong;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DichVuAnUong_dao {
	private EntityManager em;

	public DichVuAnUong_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<DichVuAnUong> getListDichVuAnUong(){
		TypedQuery<DichVuAnUong> query = em.createQuery("Select dvau from DichVuAnUong dvau", DichVuAnUong.class);
		List<DichVuAnUong> listDichVuAnUong = query.getResultList();
		return listDichVuAnUong;
	}
}
