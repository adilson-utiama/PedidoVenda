package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.PedidoRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter{
	
	private PedidoRepository repository;

	public PedidoConverter() {
		repository = CDIServiceLocator.getBean(PedidoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String value) {
		Pedido retorno = null;
		if(value != null){
			Long id = new Long(value);
			 retorno = repository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		return "";
	}

}
