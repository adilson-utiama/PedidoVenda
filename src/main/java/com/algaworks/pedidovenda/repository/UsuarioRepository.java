package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.model.vo.DataValor;
import com.algaworks.pedidovenda.model.vo.VendedorValor;
import com.algaworks.pedidovenda.repository.filter.UsuarioFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class UsuarioRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Transactional
	public Usuario guardar(Usuario usuario){
		return manager.merge(usuario);
	}
	
	@Transactional
	public void remover(Usuario usuario){
		try{
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e){
			throw new NegocioException("Usuario não pode ser excluido.");
		}
	}

	public Usuario porNome(String nome) {
		try{
			return manager.createQuery("from Usuario where upper(nome) = :nome", Usuario.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
		
	}

	public Usuario porId(Long id) {
		return manager.createQuery("from Usuario where id = :id", Usuario.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Usuario porEmail(String email) {
		try{
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
					.setParameter("email", email.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
		
	}
	
	public List<Usuario> vendedores(){
		//TODO fitrar vendedores(todos os usuarios sao vendedores)
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, BigDecimal> valoreTotaisPorVendedor(){
		Session session = manager.unwrap(Session.class);
		Map<String, BigDecimal> resultado = new TreeMap<>();
		
		Criteria criteria = session.createCriteria(Pedido.class);
		
		//sugestao 1
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.sqlGroupProjection("vendedor.nome as nome",
//						"vendedor.nome", new String[]{ "nome" }, 
//						new Type[]{ }))
//				.add(Projections.sum("valorTotal").as("total"))
//				.add(Projections.sqlProjection("status != 'CANCELADO'", new String[]{}, new Type[]{}))
//			);
		
		criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("vendedor").as("nome"))
				 .add(Projections.sum("valorTotal").as("total"))
				 .add(Projections.groupProperty("vendedor")))
		.add(Restrictions.eq("status", StatusPedido.EMITIDO));
		
		 List<VendedorValor> valores = criteria.setResultTransformer(Transformers.aliasToBean(VendedorValor.class)).list();
		 
		  for(VendedorValor usuarioValor : valores){
			  resultado.put(usuarioValor.getNome().getNome(), usuarioValor.getTotal());
		  }
		
		  
		  //sugestao 2
//		List<VendedorValor> valorUsuario = new ArrayList<>();
//
//		valorUsuario = manager.createQuery("select "
//				+ "new com.algaworks.pedidovenda.model.vo.VendedorValor(p.vendedor as nome, sum(p.valorTotal) as total) "
//				+ "	from Pedido p  group by p.vendedor").getResultList();
//				
//		for (VendedorValor queryResult : valorUsuario) {
//			resultado.put(queryResult.getNome().getNome(), queryResult.getTotal());
//		}

		return resultado;
		
		
//		List<VendedorValor> valoresPorData = criteria
//				.setResultTransformer(Transformers.aliasToBean(VendedorValor.class)).list();
//		
//		for (VendedorValor dataValor : valoresPorData) {
//			resultado.put(dataValor.getNome(), dataValor.getTotal());
//		}
		
		//select date(data_criacao) as data, sum(valor_total) as valor
		//from pedido where data_criacao >= :dataInicial 
		//and vendedor_id = :criadoPor 
		//group by date(data_criacao)
		
		
	}

}
