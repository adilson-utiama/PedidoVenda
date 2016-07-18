package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.UsuarioRepository;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarios;
	
	public Usuario salvar(Usuario usuario){
		usuarios.porNome(usuario.getNome());
		return usuarios.guardar(usuario);
	}

	public List<Grupo> grupos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
