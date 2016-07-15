package com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.pedidovenda.model.Produto;

public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		//transacao executada em Transactional em modo automatico
		//nao necessitando fazer na mao
		//consultar a anotcao Transactional para mais detalhes
		return manager.merge(produto);
	}

	public Produto porSKU(String sku) {
		try{
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase())
					.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
		
	}
	
	
	

}
