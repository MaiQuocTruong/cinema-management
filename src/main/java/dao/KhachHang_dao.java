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
	private EntityManagerFactory entityManergerfactory;
	private List<KhachHang> listCust;
	
	public KhachHang_dao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang_dao(EntityManagerFactory entityManergerfactory, List<KhachHang> listCust) {
		super();
		this.entityManergerfactory = entityManergerfactory;
		this.listCust = listCust;
	}
	
	public void setUp() {
		entityManergerfactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}
	
	void inTransaction(Consumer<EntityManager> work) throws Exception {
        EntityManager entityManager = entityManergerfactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            work.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
	
	
	
	
	public List<KhachHang> getListKH() throws Exception {
		 listCust= new ArrayList<KhachHang>();
		
        inTransaction(entityManager -> {
        	TypedQuery<KhachHang> query = entityManager.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class);
            listCust = query.getResultList();
        });
        return listCust;
    }
	
	
	public void addKH(KhachHang kh) throws Exception {
		inTransaction(entitymanager -> {
			entitymanager.persist(kh);
		});
	}
	
}
