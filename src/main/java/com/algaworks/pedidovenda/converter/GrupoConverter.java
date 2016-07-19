package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.repository.GrupoRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter{
	
	private GrupoRepository grupos;
	
	public GrupoConverter() {
		grupos = CDIServiceLocator.getBean(GrupoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println(value);
		Grupo retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = grupos.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Grupo grupo = (Grupo) value;
			System.out.println(grupo.getId() + " : " + grupo.getNome());
			//return grupo.getId() == null ? null : grupo.getId().toString();
			return String.valueOf(grupo.getId());
		}
		return "";
	}

}
