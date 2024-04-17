package dao;

import java.util.ArrayList;
import java.util.List;


import enities.Phim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Phim_dao {
	private EntityManager em;

	public Phim_dao(EntityManager em) {
		super();
		this.em = em;
	}
	
	
	public List<Phim> getListMovies(){
		List<Phim> listMovies = new ArrayList<Phim>();
		
		TypedQuery<Phim> resultQuery = em.createQuery("Select p from Phim p" , Phim.class);
		listMovies = resultQuery.getResultList();
		return listMovies;
	}
}
