package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private Produto produto;
	private List<Categoria> categoriaRaizes;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}

	public void salvar(){
		//throw new RuntimeException("Não é possivel salvar produto, função não implementada!");
	}
	
	public void inicializar(){
		System.out.println("Inicializando lista de Categorias do banco...");
		categoriaRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();
		
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	
	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}
}
