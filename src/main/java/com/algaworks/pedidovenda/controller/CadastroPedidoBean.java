package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.EnderecoEntrega;
import com.algaworks.pedidovenda.model.FormaPagamento;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.ClienteRepository;
import com.algaworks.pedidovenda.repository.UsuarioRepository;
import com.algaworks.pedidovenda.service.CadastroPedidoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarios;
	
	@Inject
	private ClienteRepository clientes;
	
	@Inject
	private CadastroPedidoService pedidos;
	
	private Pedido pedido;
	private Usuario vendedor;
	private List<Usuario> vendedores;
	
	//private List<Integer> itens;

	public CadastroPedidoBean() {
		limpar();
		
	}
	
	private void limpar(){
		pedido = new Pedido();
		vendedor = new Usuario();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		//itens = new ArrayList<>();
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			vendedores = usuarios.vendedores();
			this.recalcularPedido();
		}
	}
	
	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
	
//	public List<Integer> getItens() {
//		return itens;
//	}
	
	public void salvar(){
		this.pedido = pedidos.salvar(this.pedido);
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		//throw new NegocioException("Não é possível salvar pedido, função não implementada!");
	}
	
	
	public void recalcularPedido(){
		if(this.pedido != null){
			this.pedido.recalcularValorTotal();
		}
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Usuario getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando(){
		return this.pedido.getId() != null;
	}
	
	
}
