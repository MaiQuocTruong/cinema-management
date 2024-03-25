package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.XuatChieu;

public class XuatChieu_dao {
	private List<XuatChieu> listService;
	private EntityManagerFactory entityManagerFactory;
	
	public XuatChieu_dao() {
		super();
	}

	public XuatChieu_dao(List<XuatChieu> listService, EntityManagerFactory entityManagerFactory) {
		super();
		this.listService = listService;
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}
	
	void inTransaction(Consumer<EntityManager> work) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			work.accept(entityManager);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			if(entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}finally {
			entityManager.close();
		}
	}
	
	public List<XuatChieu> getService(){
		listService = new ArrayList<XuatChieu>();
		inTransaction(entityManager ->{
			TypedQuery<XuatChieu> query = entityManager.createQuery("SELECT xc FROM XuatChieu xc", XuatChieu.class);
			listService = query.getResultList();
		});
		return listService;
	}
	
	
}
