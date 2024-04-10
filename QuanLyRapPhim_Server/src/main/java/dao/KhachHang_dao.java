package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.KhachHang;

public class KhachHang_dao {
	//
	private EntityManager em;
	
	
	public KhachHang_dao(EntityManager em) {
		super();
		this.em = em;
	}




	public List<KhachHang> getListCustomer(){
		List<KhachHang> listKH = new ArrayList<KhachHang>();
		
		TypedQuery<KhachHang> resultQuery = em.createQuery("Select kh from KhachHang kh" , KhachHang.class);
		listKH = resultQuery.getResultList();
		return listKH;
	}
	
	public void addKhachHang(KhachHang kh) {
		try {
			em.getTransaction().begin();
			em.persist(kh);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	
	
}
