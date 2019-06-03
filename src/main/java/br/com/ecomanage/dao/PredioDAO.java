package br.com.ecomanage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ecomanage.model.Predio;



public class PredioDAO implements GenericDAO <Predio> {
	@Inject
	EntityManager em;
	
	@Override
	public void salvar(Predio entidade) {
		if(entidade.getId() == null) {
			em.persist(entidade);
		}else {
			em.merge(entidade);
		}
		
	}

	@Override
	public void remover(int id) {
		Predio predio = em.find(Predio.class, id);
		
		em.remove(predio);

		
	}

	@Override
	public Predio buscarPorId(int id) {
		return em.find(Predio.class, id);
		
	}
	
	@Override
	public List<Predio> listar() {
		List<Predio> predios = new ArrayList<Predio>();
		predios = em.createQuery("from " + Predio.class.getSimpleName(), Predio.class).getResultList();
		return predios;

	}



}
