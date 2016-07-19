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
import com.algaworks.pedidovenda.service.CadastroProdutoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	private List<Categoria> categoriaRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}
	
	private void limpar(){
		produto = new Produto();
		categoriaPai = null;
		subcategorias = null;
	}

	public void salvar(){
		
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}
	
	public void inicializar(){
		System.out.println("Inicializando lista de Categorias do banco...");
		
		if(FacesUtil.isNotPostback()){
			
			categoriaRaizes = categorias.raizes();
			
			if(this.categoriaPai != null){
				carregarSubcategorias();
			}
		}
		
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		System.out.println("setProduto chamado");
		this.produto = produto;
		if(this.produto != null){
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
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
	
	public boolean isEditando() {
		return this.produto.getId() != null;
	}
	
	
}
