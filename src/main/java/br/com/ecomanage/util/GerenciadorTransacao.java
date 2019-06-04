package br.com.ecomanage.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.ecomanage.transaction.Transactional;


@Interceptor
@Transactional
public class GerenciadorTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executarTransacao(InvocationContext context) throws Exception {
		
		em.getTransaction().begin();
		
		Object retorno = context.proceed();
		
		em.getTransaction().commit();
		
		return retorno;
		
	}

}
