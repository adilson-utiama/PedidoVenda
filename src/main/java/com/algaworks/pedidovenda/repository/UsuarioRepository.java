package com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class UsuarioRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Transactional
	public Usuario guardar(Usuario usuario){
		return manager.merge(usuario);
	}

	public Usuario porNome(String nome) {
		try{
			return manager.createQuery("from Usuario where upper(nome) = :nome", Usuario.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
		
	}

	public Usuario porId(Long id) {
		return manager.createQuery("from Usuario where id = :id", Usuario.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
