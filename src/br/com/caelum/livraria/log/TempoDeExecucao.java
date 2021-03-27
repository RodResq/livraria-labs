package br.com.caelum.livraria.log;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Log
@Interceptor
public class TempoDeExecucao implements Serializable {
	
	
	@AroundInvoke
	public Object medeTempo(InvocationContext context) throws Exception {
		
		long tempoInicial = System.currentTimeMillis();
		
		Object resultado = context.proceed();
		
		long tempoFinal = System.currentTimeMillis();
		
		System.out.println("Tempo de execucao do metodo " + context.getMethod().getName() + " e " + (tempoFinal - tempoInicial) + " Milisegundos");
		
		return resultado;
	}

}
