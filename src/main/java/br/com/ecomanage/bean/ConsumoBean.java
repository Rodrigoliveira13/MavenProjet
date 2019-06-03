package br.com.ecomanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ecomanage.dao.ConsumoDAO;
import br.com.ecomanage.model.Consumo;
import br.com.ecomanage.model.Estabelecimento;
import br.com.ecomanage.model.Predio;
import br.com.ecomanage.transaction.Transactional;


public class ConsumoBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Consumo consumo;
	
	@Inject
	private ConsumoDAO cDAO;
	
	@Inject
	private Estabelecimento estabelecimento;
	
	@Inject
	private Predio predio;
	
	private List<Consumo> consumos;
	
	public Consumo getConsumo() {
		return consumo;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	@PostConstruct
	public void init() {
		consumos= new ArrayList<Consumo>();
	}
	@Transactional
	public void cadastrarConsumo() {
		if(consumo.getId()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso", "Um novo consumo foi cadastrado com Sucesso!"));
		}else {	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", "O consumo foi alterado com Sucesso!"));
		}
		this.cDAO.salvar(consumo);
		consumo = new Consumo();
	}
	
	public List<Consumo> getConsumos() {
		consumos = this.cDAO.listar();
		
		return consumos;
	}
	
	@Transactional
	public void deletarGestor(Consumo consumo) {
		//System.out.println("Aqui");
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		System.out.println(consumo.getId());
		this.cDAO.remover(consumo.getId());
	}
	
	@Transactional
	public void alterarConsumo(Consumo consumo) {
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		this.consumo = this.cDAO.buscarPorId(consumo.getId());
	}
	

}
