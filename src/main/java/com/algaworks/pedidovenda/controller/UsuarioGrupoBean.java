package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.repository.GrupoRepository;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@SessionScoped
public class UsuarioGrupoBean implements Serializable{

	private static final long serialVersionUID = -2700487807980584294L;
	
	@Inject
	private GrupoRepository grupoRepository;
	
	private Grupo grupo;
	
	private List<Grupo> grupos = new ArrayList<>();
	
	public UsuarioGrupoBean(){
		grupo = new Grupo();
	}
		
	
	public void removeGrupo(Grupo grupo){
		System.out.println("chamando removerGrupo");
		grupoRepository.removerGrupo(grupo);
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	
	public void inicializar(){
		System.out.println("Inicializando lista de Grupos...");
		
		if(FacesUtil.isNotPostback()){
			
			this.grupos = grupoRepository.grupos();
		}
		
	}
	
	
}
