package com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Endereco;

public class EnderecoRepository implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@Inject
	private EntityManager manager;
	
	public Endereco porId(Long id){
		return this.manager.find(Endereco.class, id);
	}
}
