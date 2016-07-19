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

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarios;
	
	private List<Usuario> usuariosFiltrados;
	private UsuarioFiltro filtro;
	
	public PesquisaUsuariosBean() {
		filtro = new UsuarioFiltro();
		
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

	
}
