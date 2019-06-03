package br.com.ecomanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ecomanage.dao.EstabelecimentoDAO;
import br.com.ecomanage.model.Estabelecimento;
import br.com.ecomanage.transaction.Transactional;


@Named
@javax.faces.view.ViewScoped
public class EstabelecimentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Estabelecimento estabelecimento; 
	
	@Inject
	private EstabelecimentoDAO eDAO;
	
	private List<Estabelecimento> estabelecimentos;
	
	@PostConstruct
	public void init() {
		estabelecimentos = new ArrayList<Estabelecimento>();
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
    @Transactional
	public void cadastrarEstabelecimento() {
		if(estabelecimento.getId()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso", "O estabelecimento foi cadastrado com Sucesso!"));
		}else {	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", "O estabelecimento foi alterado com Sucesso!"));
		}
		this.eDAO.salvar(estabelecimento);
		estabelecimento = new Estabelecimento();
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		estabelecimentos = this.eDAO.listar();
		
		return estabelecimentos;
	}
	
	@Transactional
	public void deletarEstabelecimento(Estabelecimento estabelecimento) {
		//System.out.println("Aqui");
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		System.out.println(estabelecimento.getId());
		this.eDAO.remover(estabelecimento.getId());
	}
	
	@Transactional
	public void alterarVeiculo(Estabelecimento estabelecimento) {
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		this.estabelecimento = this.eDAO.buscarPorId(estabelecimento.getId());
	}

}
