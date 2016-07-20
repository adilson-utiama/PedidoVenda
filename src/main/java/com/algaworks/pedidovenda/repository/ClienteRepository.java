package com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ClienteRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@Transactional
	public Cliente guardar(Cliente cliente){
		return manager.merge(cliente);
	}

	public Cliente porEmail(String email) {
		try{
			return manager.createQuery("from Cliente where upper(email) = :email", Cliente.class)
			.setParameter("email", email.toUpperCase())
			.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
}
