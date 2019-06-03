package br.com.ecomanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ecomanage.dao.GestorDAO;
import br.com.ecomanage.model.Gestor;
import br.com.ecomanage.transaction.Transactional;


@Named
@javax.faces.view.ViewScoped
public class GestorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Gestor gestor;
	
	@Inject
	private GestorDAO gDAO;
	
	private List<Gestor> gestores;
	
	@PostConstruct
	public void init() {
		gestores = new ArrayList<Gestor>();
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	 @Transactional
		public void cadastrarGestor() {
			if(gestor.getId()==null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso", "Um novo gestor foi cadastrado com Sucesso!"));
			}else {	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", "O gestor foi alterado com Sucesso!"));
			}
			this.gDAO.salvar(gestor);
			gestor = new Gestor();
		}
		
		public List<Gestor> getGestores() {
			gestores = this.gDAO.listar();
			
			return gestores;
		}
		
		@Transactional
		public void deletarGestor(Gestor gestor) {
			//System.out.println("Aqui");
			//VeiculoDAO veiculoDao = new VeiculoDAO();
			System.out.println(gestor.getId());
			this.gDAO.remover(gestor.getId());
		}
		
		@Transactional
		public void alterarGestor(Gestor gestor) {
			//VeiculoDAO veiculoDao = new VeiculoDAO();
			this.gestor = this.gDAO.buscarPorId(gestor.getId());
		}
}
