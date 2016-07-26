package com.algaworks.pedidovenda.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.algaworks.pedidovenda.model.Usuario;

public class VendedorValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario nome;
	private BigDecimal total;

	public VendedorValor() {
	}

	public VendedorValor(Usuario nome, BigDecimal total) {
		this.nome = nome;
		this.total = total;
	}

	public Usuario getNome() {
		return nome;
	}

	public void setNome(Usuario nome) {
		this.nome = nome;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
