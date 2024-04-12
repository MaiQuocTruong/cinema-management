package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import enities.NhanVien;
public class NhanVien_dao {
	private EntityManager em;
	
	public NhanVien_dao(EntityManager em) {
		super();
		this.em = em;
	}
	public List<NhanVien> getListNhanVien(){
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		TypedQuery<NhanVien> resultQuery = em.createQuery("Select nv from NhanVien nv" , NhanVien.class);
		listNV = resultQuery.getResultList();
		return listNV;
	}

	public void addNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(nv);
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	public NhanVien findEmployeeOnPhoneNumber(String sdt) {
		return em.createQuery("Select nv from NhanVien nv where nv.sdt = :sdt", NhanVien.class)
				   .setParameter("sdt", sdt)
				   .getSingleResult();
	}
	public void deleteNV(String idNhanVien) {
		try {
			NhanVien nvDelete = em.find(NhanVien.class, idNhanVien);
			if(nvDelete != null && em.contains(nvDelete)) {
				em.getTransaction().begin();
				em.remove(nvDelete);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}
	public void updateNV(NhanVien nv_update) {
		try {
			em.getTransaction().begin();
			em.merge(nv_update);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			
			e.printStackTrace();
		}
	}
}
