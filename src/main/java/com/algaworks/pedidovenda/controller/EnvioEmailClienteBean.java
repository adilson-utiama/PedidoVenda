package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioEmailClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@ClienteEdicao
	private Cliente cliente;
	
	public void enviarCliente(){
		MailMessage message = mailer.novaMensagem();
		
		try{
			message.to(this.cliente.getEmail())
			.subject("Dados cadastrais do cliente " + this.cliente.getNome())
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
			.put("cliente", this.cliente)
			.send();
		
			FacesUtil.addInfoMessage("Dados do Cliente enviados por email com sucesso!");
		}catch(Exception e){
			throw new NegocioException("Não foi possivel o envio de email para o cliente!.");
		}
		
	}

}
