package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.CategoriaRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	//@Inject
	private CategoriaRepository categorias;
	
	public CategoriaConverter(){
		//retorna uma instancia de CategoriaRepo no contexto do CDI
		categorias = CDIServiceLocator.getBean(CategoriaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Categoria categoria = (Categoria) value;
			return String.valueOf(categoria.getId());
		}
		return "";
	}

}
