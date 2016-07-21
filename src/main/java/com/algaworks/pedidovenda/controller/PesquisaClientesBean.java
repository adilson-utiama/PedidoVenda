package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.ClienteRepository;
import com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clientes;
	
	private List<Cliente> clientesFiltrados;
	private ClienteFilter filtro;
	private Cliente clienteSelecionado;

	public PesquisaClientesBean() {
		clienteSelecionado = new Cliente();
		filtro = new ClienteFilter();
		//clientesFiltrados = new ArrayList<>();

	}
	
	
	public void excluir(){}
	
	public void pesquisar(){
		clientesFiltrados = clientes.filtrados(filtro);
	}
		
	public void excluirCliente() {
		if(clienteSelecionado != null){
			clientes.excluir(clienteSelecionado);
			clientesFiltrados.remove(this.clienteSelecionado);
			FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome() + " - " +
					clienteSelecionado.getDocumentoReceitaFederal() + " excluido com sucesso!");
		}else{
			FacesUtil.addErrorMessage("Falha ao tentar excluir cliente!");
		}
		
	}
	
	
	
	public List<Cliente> getClientes() {
		return clientesFiltrados;
	}
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}
	
	public ClienteFilter getFiltro() {
		return filtro;
	}

	

	
}
