package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.repository.EnderecoRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter{

	private EnderecoRepository repository;
	

	public EnderecoConverter() {
		repository = CDIServiceLocator.getBean(EnderecoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Endereco retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = repository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Endereco endereco = (Endereco) value;
			return endereco.getId() == null ? null : endereco.getId().toString();
		}
		return "";
	}

}
