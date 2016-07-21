package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.repository.PedidoRepository;
import com.algaworks.pedidovenda.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidos;
	
	private List<Pedido> pedidosFiltrados;
	private PedidoFilter filtro;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
		
	}
	
	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}
	
	public void pesquisar(){
		pedidosFiltrados = pedidos.filtrados(filtro);
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
	public PedidoFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}
}
