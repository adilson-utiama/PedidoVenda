package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UsuarioGrupoBean implements Serializable{

	private static final long serialVersionUID = -2700487807980584294L;
	
	private String grupo;
	private List<String> grupos = new ArrayList<>(Arrays.asList("AUXILIARES","ADMINISTRADORES","VENDEDORES","OUTROS"));
	
	
	public UsuarioGrupoBean(){
		
	}
	
	public List<String> getGrupos() {
		return grupos;
	}
	
		
	public void setGrupo(String grupo) {
		System.out.println("Chamando setGrupo");
		this.grupo = grupo;
	}
	
	public void removeGrupo(){
		System.out.println("chamando removerGrupo");
		grupos.remove(this.grupo);
	}
	
	
}
