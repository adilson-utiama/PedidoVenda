package com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.service.NegocioException;

@Named
@RequestScoped
public class CadastroPedidoBean {

	private List<Integer> itens;

	public CadastroPedidoBean() {
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public List<Integer> getItens() {
		return itens;
	}
	
	public void salvar(){
		throw new NegocioException("Não é possível salvar pedido, função não implementada!");
	}
	
	
}
