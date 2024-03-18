package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import enities.NhanVien;

public class NhanVien_dao {
	private EntityManagerFactory entityManergerfactory;
	private List<NhanVien> listNV;

	public NhanVien_dao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien_dao(EntityManagerFactory entityManergerfactory, List<NhanVien> listNV) {
		super();
		this.entityManergerfactory = entityManergerfactory;
		this.listNV = listNV;
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

	public List<NhanVien> getListNV() throws Exception {
		listNV = new ArrayList<NhanVien>();

		inTransaction(entityManager -> {
			TypedQuery<NhanVien> query = entityManager.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class);
			listNV = query.getResultList();
		});
		return listNV;
	}

	public void addNV(NhanVien nv) throws Exception {
		inTransaction(entitymanager -> {
			entitymanager.persist(nv);
		});
	}


}
