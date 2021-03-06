package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.UsuarioRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{

	//@Inject
    private UsuarioRepository repository;
    
    public UsuarioConverter () {
    	repository = CDIServiceLocator.getBean(UsuarioRepository.class);
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 Usuario retorno = null;
	        if (value != null) {
	            Long id = new Long(value);
	            retorno = repository.porId(id);
	        }
	        
	        return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent compoent, Object value) {
		if(value != null) {
            //return ((Usuario) value).getId().toString();           
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }
        return "";
	}

}
