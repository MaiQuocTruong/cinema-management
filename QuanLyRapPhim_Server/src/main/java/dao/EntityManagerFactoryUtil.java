package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



public class EntityManagerFactoryUtil {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public EntityManagerFactoryUtil() {
		emf = Persistence.createEntityManagerFactory("project-rapphim");
		em = emf.createEntityManager();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void closeEnManager() {
		em.close();
	}
	
	public void closeEnManagerFactory() {
		em.close();
	}
	
	
}
