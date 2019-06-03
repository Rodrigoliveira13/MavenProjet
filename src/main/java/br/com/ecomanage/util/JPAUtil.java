package br.com.ecomanage.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static EntityManagerFactory instance;
	
	static {
		if(instance == null) {
			instance = Persistence.createEntityManagerFactory("projeto");
		}
	}
	
	@Produces
	@RequestScoped
	public static EntityManager getEntityManager() {
		return instance.createEntityManager();

		
	}
	
	public void close(@Disposes EntityManager em) {
		em.close();
	}
	

}
