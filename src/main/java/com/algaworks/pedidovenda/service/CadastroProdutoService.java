package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.ProdutoRepository;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoRepository produtos;
	
	@Transactional
	public Produto salvar(Produto produto){
		System.out.println(produto.getSku() + produto.getNome());
		
		Produto produtoExistente = produtos.porSKU(produto.getSku());
		if(produtoExistente != null){
			throw new NegocioException("Ja existe um produto com o SKU informado.");
		}
		return produtos.guardar(produto);
	}

	

}
