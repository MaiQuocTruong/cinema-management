package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.DichVuAnUong;




public class DichVuAnUong_dao {
	private List<DichVuAnUong> listService;
	private EntityManagerFactory entityManagerFactory;
	
	
	public DichVuAnUong_dao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public DichVuAnUong_dao(List<DichVuAnUong> listService, EntityManagerFactory entityManagerFactory) {
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
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
	
	
	public List<DichVuAnUong> getServices() throws Exception {
		 listService= new ArrayList<DichVuAnUong>();
		
        inTransaction(entityManager -> {
        	TypedQuery<DichVuAnUong> query = entityManager.createQuery("SELECT dv FROM DichVuAnUong dv", DichVuAnUong.class);
            listService = query.getResultList();
        });
        return listService;
    }
	
}
