package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.repository.PedidoRepository;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido){
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		pedido.recalcularValorTotal();
		if(pedido.getItens().isEmpty()){
			throw new NegocioException("O pedido precisa possuir pelo menos 1 item.");
		}
		if(pedido.isValorTotalNegativo()){
			throw new NegocioException("Valor total do pedido não pode ser negativo. ");
		}
		return pedidos.guardar(pedido);
	}
	
}
