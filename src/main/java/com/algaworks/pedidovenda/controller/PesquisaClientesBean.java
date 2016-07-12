package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cliente> clientes;

	private Cliente cliente = new Cliente();

	public PesquisaClientesBean() {
		clientes = new ArrayList<>();

	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void removeCliente() {
		clientes.remove(this.cliente);
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
