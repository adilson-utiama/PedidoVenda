package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}

	public void salvar(){
		//throw new RuntimeException("Não é possivel salvar produto, função não implementada!");
	}
	
	
	public Produto getProduto() {
		return produto;
	}
}
