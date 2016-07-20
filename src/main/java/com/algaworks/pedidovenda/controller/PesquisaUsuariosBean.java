package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.UsuarioRepository;
import com.algaworks.pedidovenda.repository.filter.UsuarioFiltro;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarios;
	
	private List<Usuario> usuariosFiltrados;
	private UsuarioFiltro filtro;
	
	private Usuario usuarioSelecionado;
	
	public PesquisaUsuariosBean() {
		filtro = new UsuarioFiltro();
		
	}
	
	public void excluir(){
		if(this.usuarioSelecionado != null){
			usuarios.remover(this.usuarioSelecionado);
			usuariosFiltrados.remove(this.usuarioSelecionado);
			FacesUtil.addInfoMessage("Produto " + this.usuarioSelecionado.getNome() + " excluido com sucesso.");
		}else{
			FacesUtil.addInfoMessage("Produto esta Nulo");
		}
		
		
	}
	
	public void pesquisar(){
		usuariosFiltrados = usuarios.filtrados(filtro);
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	
	public UsuarioFiltro getFiltro() {
		return filtro;
	}
	
	public void setFiltro(UsuarioFiltro filtro) {
		this.filtro = filtro;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	
}
