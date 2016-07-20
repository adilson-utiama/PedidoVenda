package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.UsuarioRepository;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarios;
	
	public Usuario salvar(Usuario usuario){
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		if(usuarioExistente != null && !usuarioExistente.equals(usuario)){
			throw new NegocioException("Ja existe um usuario com mesmo e-mail.");
		}
		return usuarios.guardar(usuario);
	}

	public List<Grupo> grupos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
