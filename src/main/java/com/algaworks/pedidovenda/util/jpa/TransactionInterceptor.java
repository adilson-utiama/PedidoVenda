package com.algaworks.pedidovenda.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private @Inject EntityManager manager;
	
	//configurado em src/main/resources/META-INF/beans.xml
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception{
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;
		
		try{
			if(!trx.isActive()){
				//truque para fazer rollback no que ja passou
				//(senao, um futuro commit, confirmaria ate mesmo operacoes sem transacao)
				trx.begin();
				trx.rollback();
				
				//agora sim a transacao
				trx.begin();
				criador = true;
			}
			return context.proceed();
		} catch(Exception e){
			if(trx != null && criador){
				trx.rollback();
			}
			throw e;
		} finally {
			if(trx != null && trx.isActive() && criador){
				trx.commit();
			}
		}
	}

}
