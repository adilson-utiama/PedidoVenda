package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.CategoriaRepository;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository categorias;
	
	private Produto produto;
	private Categoria categoriaPai;
	private List<Categoria> categoriaRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}

	public void salvar(){
		//throw new RuntimeException("Não é possivel salvar produto, função não implementada!");
		System.out.println("CategoriaPai : " + categoriaPai.getDescricao());
		System.out.println("Subcategoria selecionada : " + produto.getCategoria().getDescricao());
	}
	
	public void inicializar(){
		System.out.println("Inicializando lista de Categorias do banco...");
		
		if(FacesUtil.isNotPostback()){
			
			categoriaRaizes = categorias.raizes();
		}
		
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	
	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
	public void carregarSubcategorias(){
		
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}
	
	
}
