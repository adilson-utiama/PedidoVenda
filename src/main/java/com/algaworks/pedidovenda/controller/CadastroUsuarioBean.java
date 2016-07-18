package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.GrupoRepository;
import com.algaworks.pedidovenda.service.CadastroUsuarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService usuarios;
	
	@Inject
	private GrupoRepository grupoRepository;

	private Usuario usuario;
	private Grupo grupoSelecionado;
	private Grupo grupo;

	private List<Grupo> grupos;

	public CadastroUsuarioBean() {
		limpar();
	}

	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}
	
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
		
	public List<Grupo> getGrupos() {
		return grupos;
	}
	
	
	public void salvar() {
		System.out.println(usuario.getNome() + " : " + usuario.getEmail() + " : " + usuario.getSenha());
		this.usuario = usuarios.salvar(this.usuario);
		limpar();
		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	private void limpar() {
		usuario = new Usuario();
		grupos = new ArrayList<>();
		//grupoSelecionado = null;
		
	}
	
	private void insereGrupo(){
		Grupo teste = new Grupo();
		teste.setNome("Teste");
		teste.setDescricao("Teste de insercao de grupo");
		usuario.getGrupos().add(teste );
	}

	public void adicionaGrupo() {
		System.out.println("grupoSelecionado: " + grupoSelecionado);
		//insereGrupo();
		if(grupoSelecionado == null){
			FacesUtil.addInfoMessage("Grupo vazio");
		}else{
			usuario.getGrupos().add(this.grupoSelecionado);
		}
	}

	public void removeGrupo() {
		usuario.getGrupos().remove(grupo);
	}
	
	@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando lista de Grupos...");
		//grupoSelecionado = new Grupo();
		if(FacesUtil.isNotPostback()){
			this.grupos = grupoRepository.grupos();
		}
		
	}

}
