package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Usuario;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	private Usuario usuario;

	public PesquisaUsuariosBean() {
		usuario = new Usuario();
		usuarios = new ArrayList<>();
		

	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void removeUsuario() {
		usuarios.remove(this.usuario);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
