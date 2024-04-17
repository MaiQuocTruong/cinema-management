package dao;

import java.util.ArrayList;
import java.util.List;

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
}
