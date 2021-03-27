package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		
		System.out.println("Begin transaction ");
		// abre transacao
		manager.getTransaction().begin();
		
		Object resultado = context.proceed();

		System.out.println("Commit transaction ");
		// commita a transacao
		manager.getTransaction().commit();
		
		return resultado;
	}

}
