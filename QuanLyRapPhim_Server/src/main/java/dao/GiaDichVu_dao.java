package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;


import enities.GiaDichVu;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class GiaDichVu_dao {
	private EntityManager em;

	public GiaDichVu_dao(EntityManager em) {
		super();
		this.em = em;
	}

	
	public List<GiaDichVu> getListGiaDichVu(String idDichVu){
		TypedQuery<GiaDichVu> query = em.createQuery("Select gdv from GiaDichVu gdv where gdv.DichVuAnUong.maDichVu = :idDichVu ", GiaDichVu.class);
		query.setParameter("idDichVu", idDichVu);
		List<GiaDichVu> listGiaDichVu = query.getResultList();
		return listGiaDichVu;
	}

	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}	
