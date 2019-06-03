package br.com.ecomanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ecomanage.dao.PredioDAO;
import br.com.ecomanage.model.Estabelecimento;
import br.com.ecomanage.model.Predio;
import br.com.ecomanage.transaction.Transactional;


public class PredioBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Predio predio;
	@Inject
	private PredioDAO pDAO;
	@Inject 
	private Estabelecimento estabelecimento;
	
	private List<Predio>predios;
	
	@PostConstruct
	public void init() {
		predios = new ArrayList<Predio>();
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	 @Transactional
		public void cadastrarPredio() {
			if(predio.getId()==null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso", "Um novo prédio foi cadastrado com Sucesso!"));
			}else {	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", "O prédio foi alterado com Sucesso!"));
			}
			this.pDAO.salvar(predio);
			predio = new Predio();
		}
		
		public List<Predio> getPredios() {
			predios = this.pDAO.listar();
			
			return predios;
		}
		@Transactional
		public void deletarPredio(Predio predio) {
			//System.out.println("Aqui");
			//VeiculoDAO veiculoDao = new VeiculoDAO();
			System.out.println(predio.getId());
			this.pDAO.remover(predio.getId());
		}
		
		@Transactional
		public void alterarPredio(Predio predio) {
			//VeiculoDAO veiculoDao = new VeiculoDAO();
			this.predio = this.pDAO.buscarPorId(predio.getId());
		}
}




