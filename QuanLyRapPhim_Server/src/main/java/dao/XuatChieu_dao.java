package dao;

import java.util.ArrayList;
import java.util.List;


import enities.XuatChieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class XuatChieu_dao {
	private EntityManager em;

	public XuatChieu_dao() {
		super();
		// TODO Auto-generated constructor stub
	}

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
}
