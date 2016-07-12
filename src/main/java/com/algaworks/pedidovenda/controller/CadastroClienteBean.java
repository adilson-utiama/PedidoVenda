package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;

@Named
@RequestScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 4686308244408445736L;

	private Cliente cliente;
	private Endereco endereco;

	public CadastroClienteBean() {
		cliente = new Cliente();
		endereco = new Endereco();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void addEndereco(){}
	
	public void removeEndereco(){}
	
	public void salvar(){}

}
