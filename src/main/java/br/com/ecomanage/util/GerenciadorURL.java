package br.com.ecomanage.util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import br.com.ecomanage.qualifiers.Production;
import br.com.ecomanage.qualifiers.Test;


public class GerenciadorURL {
	
	@Produces
	@Test
	@Default
	public String URLTeste() {
		return "http://Teste";
	}
	
	@Produces
	@Production
	public String URLProducao() {
		return "http://Producao";
	}

}
