package com.algaworks.pedidovenda.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.ProdutoRepository;
import com.algaworks.pedidovenda.repository.filter.ProdutoFilter;

@Named
@RequestScoped
public class PesquisaProdutosBean {

	@Inject
	private ProdutoRepository produtos;
	
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
		
	public void pesquisar(){
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public ProdutoFilter getFiltro() {
		return filtro;
	}
}
