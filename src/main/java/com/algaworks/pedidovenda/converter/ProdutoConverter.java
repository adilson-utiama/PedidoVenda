package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.ProdutoRepository;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject
	private ProdutoRepository produtos;
	
	public ProdutoConverter(){
		//retorna uma instancia de CategoriaRepo no contexto do CDI
		produtos = CDIServiceLocator.getBean(ProdutoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("converter id para produto");
		Produto retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("converter produto para id");
		if(value != null){
			Produto produto = (Produto) value;
			return String.valueOf(produto.getId());
		}
		return "";
	}

}
