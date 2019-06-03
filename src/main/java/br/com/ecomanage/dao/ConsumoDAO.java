package br.com.ecomanage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ecomanage.model.Consumo;


public class ConsumoDAO implements GenericDAO<Consumo> {
	@Inject
	EntityManager em;
	
	@Override
	public void salvar(Consumo entidade) {
		if(entidade.getId() == null) {
			em.persist(entidade);
		}else {
			em.merge(entidade);
		}
		
	}
	@Override
	public void remover(int id) {
		Consumo consumo = em.find(Consumo.class, id);
		
		em.remove(consumo);

		
	}

	@Override
	public Consumo buscarPorId(int id) {
		return em.find(Consumo.class, id);
		
	}
	
	@Override
	public List<Consumo> listar() {
		List<Consumo> consumos = new ArrayList<Consumo>();
		consumos = em.createQuery("from " + Consumo.class.getSimpleName(), Consumo.class).getResultList();
		return consumos;

	}

}
