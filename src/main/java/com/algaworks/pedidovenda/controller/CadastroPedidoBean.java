package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.EnderecoEntrega;
import com.algaworks.pedidovenda.model.FormaPagamento;
import com.algaworks.pedidovenda.model.ItemPedido;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.ClienteRepository;
import com.algaworks.pedidovenda.repository.ProdutoRepository;
import com.algaworks.pedidovenda.repository.UsuarioRepository;
import com.algaworks.pedidovenda.service.CadastroPedidoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarios;
	@Inject
	private ClienteRepository clientes;
	@Inject
	private ProdutoRepository produtos;
	@Inject
	private CadastroPedidoService pedidoService;

	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private String sku;
	private Usuario vendedor;
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	public CadastroPedidoBean() {
		limpar();
		
	}
	
	private void limpar(){
		pedido = new Pedido();
		vendedor = new Usuario();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event){
		this.pedido = event.getPedido();
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			vendedores = usuarios.vendedores();
			this.pedido.adicionarItemVazio();
			this.recalcularPedido();
		}
	}
	
	public List<Produto> completarProduto(String nome){
		return this.produtos.porNome(nome);
	}
	
	public void carregarProdutoPorSku(){
		if(StringUtils.isNotEmpty(this.sku)){
			this.produtoLinhaEditavel = produtos.porSKU(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public void carregarProdutoLinhaEditavel(){
		ItemPedido item = this.pedido.getItens().get(0);
		
		if(this.produtoLinhaEditavel != null){
			if(this.existeItemComProduto(this.produtoLinhaEditavel)){
				FacesUtil.addErrorMessage("Ja existe um item no pedido com o produto informado");
			}else{
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.pedido.recalcularValorTotal();
			}
			
		}
	}
	
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		for(ItemPedido item : this.getPedido().getItens()){
			if(produto.equals(item.getProduto())){
				existeItem = true;
				break;
			}
		}
		return existeItem;
	}

	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
	
	public void salvar(){
		this.pedido.removerItemVazio();
		try{
			if(this.pedido.isEmitido()){
				FacesUtil.addErrorMessage("Pedido ja foi emitido.");
			}else{
				this.pedido = pedidoService.salvar(this.pedido);
				FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
			}
			
		}finally{
			this.pedido.adicionarItemVazio();
		}
		
	}
	
	
	public void recalcularPedido(){
		if(this.pedido != null){
			this.pedido.recalcularValorTotal();
		}
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha){
		if(item.getQuantidade() < 1){
			if(linha == 0){
				item.setQuantidade(1);
			}else{
				this.getPedido().getItens().remove(linha);
			}
		}
		this.pedido.recalcularValorTotal();
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
	
	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}
	
	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}
	
	@SKU
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
}
