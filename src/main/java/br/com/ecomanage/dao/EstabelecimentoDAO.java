package br.com.ecomanage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ecomanage.model.Estabelecimento;



public class EstabelecimentoDAO implements GenericDAO <Estabelecimento> {
	@Inject
	EntityManager em;
	
	@Override
	public void salvar(Estabelecimento entidade) {
		if(entidade.getId() == null) {
			em.persist(entidade);
		}else {
			em.merge(entidade);
		}
		
	}

	@Override
	public void remover(int id) {
		Estabelecimento estabelecimento = em.find(Estabelecimento.class, id);
		
		em.remove(estabelecimento);

		
	}

	@Override
	public Estabelecimento buscarPorId(int id) {
		return em.find(Estabelecimento.class, id);
		
	}
	
	@Override
	public List<Estabelecimento> listar() {
		List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		estabelecimentos = em.createQuery("from " + Estabelecimento.class.getSimpleName(), Estabelecimento.class).getResultList();
		return estabelecimentos;

	}


}
