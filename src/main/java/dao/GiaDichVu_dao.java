package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.GiaDichVu;

public class GiaDichVu_dao {
	private List<GiaDichVu> listGia;
	private EntityManagerFactory entityManagerFactory;
	
	public GiaDichVu_dao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GiaDichVu_dao(List<GiaDichVu> listGia, EntityManagerFactory entityManagerFactory) {
		super();
		this.listGia = listGia;
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
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
	public List<GiaDichVu> getGiaDV() throws Exception {
		listGia= new ArrayList<GiaDichVu>();
		
       inTransaction(entityManager -> {
       	TypedQuery<GiaDichVu> query = entityManager.createQuery("SELECT giadv FROM GiaDichVu giadv", GiaDichVu.class);
       	listGia = query.getResultList();
       });
       return listGia;
   }
	
}
