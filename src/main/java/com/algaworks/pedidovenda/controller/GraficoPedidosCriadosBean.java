package com.algaworks.pedidovenda.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.PedidoRepository;
import com.algaworks.pedidovenda.security.UsuarioLogado;
import com.algaworks.pedidovenda.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	private CartesianChartModel model;
	
	@Inject
	private PedidoRepository pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;

	public void preRender() {
		this.model = new CartesianChartModel();

		adicionarSeries("Todos os Pedidos", null);
		adicionarSeries("Meus Pedidos", usuarioLogado.getUsuario());
	}

	private void adicionarSeries(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoreTotaisPorData(15, criadoPor);
		
		ChartSeries series = new ChartSeries(rotulo);
		
		for (Date data : valoresPorData.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		this.model.addSeries(series);

	}

	public CartesianChartModel getModel() {
		return model;
	}
}
