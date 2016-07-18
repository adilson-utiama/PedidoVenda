package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Grupo;

public class GrupoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public List<Grupo> grupos() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}

	public Grupo porId(Long id) {
		return manager.createQuery("from Grupo where id = :id", Grupo.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public void removerGrupo(Grupo grupo) {
		manager.remove(grupo);
	}

}
