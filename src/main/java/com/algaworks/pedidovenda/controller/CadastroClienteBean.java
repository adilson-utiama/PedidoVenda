package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.service.CadastroClienteService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 4686308244408445736L;

	private Cliente cliente;
	private Endereco endereco;
	
	@Inject
	private CadastroClienteService clienteService;

	public CadastroClienteBean() {
		System.out.println("##Chamando CadastroClienteBean");
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
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
		
	
	public void addEndereco(){
		System.out.println("Incluindo Endereco");
		this.endereco.setCliente(this.cliente);
		cliente.getEnderecos().add(this.endereco);
		
	}
	
	public void removeEndereco(){
		
	}
	
	public void salvar(){
		if(this.cliente != null){
			this.cliente = clienteService.salvar(this.cliente);
			limpar();
			FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
		}else{
			FacesUtil.addErrorMessage("Erro ao salvar Cliente!");
		}
		
	}

	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
		
	}
	
	public boolean isEditando(){
		return this.cliente.getId() != null;
	}

}
