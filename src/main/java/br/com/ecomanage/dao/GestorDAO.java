package br.com.ecomanage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ecomanage.model.Gestor;
import br.com.ecomanage.util.JPAUtil;


public class GestorDAO implements GenericDAO <Gestor>{
	@Inject
	EntityManager em;
	
	@Override
	public void salvar(Gestor entidade) {
		if(entidade.getId() == null) {
			em.persist(entidade);
		}else {
			em.merge(entidade);
	}
}
	@Override
	public void remover(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			
			Gestor gestor = em.find(Gestor.class, id);
			
			em.remove(gestor);
			
			em.getTransaction().commit();
		}finally {
			em.close();
		}
		
	}

	@Override
	public Gestor buscarPorId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.find(Gestor.class, id);
		}finally {
			em.close();
		}
	}
	
	@Override
	public List<Gestor> listar() {
		List<Gestor> usuarios = new ArrayList<Gestor>();
		EntityManager em = JPAUtil.getEntityManager();
		try {
			usuarios = em.createQuery("from " + Gestor.class.getSimpleName(), Gestor.class).getResultList();
			return usuarios;
		}finally {
			em.close();
		}
	}
	
	public Gestor login(Gestor g) {
		EntityManager em = JPAUtil.getEntityManager();
		Gestor gestor;
		TypedQuery<Gestor> query = em.createQuery("select u from Funcionario u where u.login = :login and u.senha = :senha", Gestor.class);
		query.setParameter("login", g.getLogin());
		query.setParameter("senha", g.getSenha());
		
		try {
			gestor = query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		em.close();
		
		return gestor;
	}

}
