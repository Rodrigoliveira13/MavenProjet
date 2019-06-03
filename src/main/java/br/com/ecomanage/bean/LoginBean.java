package br.com.ecomanage.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ecomanage.dao.GestorDAO;
import br.com.ecomanage.model.Gestor;


@Named
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Gestor gestor;
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private String url;

	public Gestor getGestor() {
		return gestor;
	}
	
	public String efetuarLogin() {
		System.out.println(url);
		GestorDAO gDao = new GestorDAO();
		Gestor g = gDao.login(gestor);
		boolean existe = g != null;
		System.out.println(existe);
		if(existe) {
			facesContext.getExternalContext().getSessionMap().put("Gestor", g);
			return "pages/index?faces-redirect=true";
		}else {
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage("Gestor não existe"));
			return "login?faces-redirect=true";
		}
	}
	
	public String efetuarLogout() {
		
		facesContext.getExternalContext().getSessionMap().remove("gestor");
		
		System.out.println("logout");
		
		return "/login?faces-redirect=true";
	}

}
