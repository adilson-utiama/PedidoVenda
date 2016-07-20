package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.ClienteRepository;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository clientes;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
		Cliente usuarioExistente = clientes.porEmail(cliente.getEmail());
		if(usuarioExistente != null && !usuarioExistente.equals(cliente)){
			throw new NegocioException("Ja existe um cliente com mesmo e-mail.");
		}
		return clientes.guardar(cliente);
	}

}
