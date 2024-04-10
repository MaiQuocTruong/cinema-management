package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
