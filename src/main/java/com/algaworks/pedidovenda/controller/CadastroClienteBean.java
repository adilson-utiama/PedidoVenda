package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
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

	@Produces
	@ClienteEdicao
	private Cliente cliente;
	
	private Endereco endereco;
	
	private Endereco enderecoSelecionado;
	
	
	@Inject
	private CadastroClienteService clienteService;

	public CadastroClienteBean() {
		System.out.println("##Chamando CadastroClienteBean");
		limpar();
		
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
		System.out.println("setEndereco");
		this.endereco = endereco;
	}
	
	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}
	
	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
		
	
	public void adicionarEndereco(){
		System.out.println("Incluindo Endereco");
		if(!this.cliente.getEnderecos().contains(this.endereco)){
			this.endereco.setCliente(this.cliente);
			this.cliente.getEnderecos().add(this.endereco);
		}
		
		//this.endereco = new Endereco();
		
	}
	
	public void novoEndereco(){
		this.endereco = new Endereco();
	}
	
	
	public void removeEndereco(){
		//BUG sempre remove o primeiro item
		this.cliente.getEnderecos().remove(this.enderecoSelecionado);
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
		enderecoSelecionado = new Endereco();
	}
	
	public boolean isEditando(){
		return this.cliente.getId() != null && this.endereco.getId() != null;
	}
	
	
}
