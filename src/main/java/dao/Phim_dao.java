package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.Phim;
import enities.XuatChieu;

public class Phim_dao{
	private List<Phim> listService;
	private EntityManagerFactory entityManagerFactory;
	private List<String> tenPhimList;
	
	public Phim_dao() {
		super();
	}

	public Phim_dao(List<Phim> listService, EntityManagerFactory entityManagerFactory) {
		super();
		this.listService = listService;
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}
	
	void inTransaction(Consumer<EntityManager> work) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			work.accept(entityManager);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			if(entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}finally {
			entityManager.close();
		}
	}
	
	public List<Phim> getServices() throws Exception{
		listService = new ArrayList<Phim>();
		
		inTransaction(entityManager -> {
			TypedQuery<Phim> query = entityManager.createQuery("SELECT ph FROM Phim ph", Phim.class);
			listService = query.getResultList();
		});
		
		return listService;
	}
	
	public List<String> getTenPhim() throws Exception {
		tenPhimList = new ArrayList<>();

	    inTransaction(entityManager -> {
	        TypedQuery<String> query = entityManager.createQuery("SELECT ph.tenPhim FROM Phim ph", String.class);
	        tenPhimList = query.getResultList();
	    });

	    return tenPhimList;
	}
}
