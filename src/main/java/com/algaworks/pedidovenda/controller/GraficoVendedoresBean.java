package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import com.algaworks.pedidovenda.repository.UsuarioRepository;

@Named
@RequestScoped
public class GraficoVendedoresBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private PieChartModel model;
	
	@Inject
	private UsuarioRepository usuarios;
	
	public void preRender() {
		this.model = new PieChartModel();

		adicionarSeries("Todos os Vendedores");
		
	}

	private void adicionarSeries(String rotulo) {
		Map<String, BigDecimal> valoresPorVendedor = this.usuarios.valoreTotaisPorVendedor();
		System.out.println(valoresPorVendedor);
		
		for (String dados : valoresPorVendedor.keySet()) {
			this.model.set(dados, valoresPorVendedor.get(dados));
		}
		
		
//		this.model.set("Adilson", 1250.0);
//		this.model.set("Wilson", 2500.0);
//		this.model.set("Manolo", 125.0);
		
		

	}

	public PieChartModel getModel() {
		return this.model;
	}
	
}
