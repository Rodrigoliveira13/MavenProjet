package br.com.ecomanage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Consumo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private float kwh;
	@Temporal(TemporalType.DATE)
	private Date dataCaptura;

	
	@ManyToOne
	private Estabelecimento estabelecimento;
	@ManyToOne
	private Predio predio;
	
	public Consumo() {
		
	}

	public Consumo(Integer id, float kwh, Date dataCaptura, Estabelecimento estabelecimento, Predio predio) {
		super();
		this.id = id;
		this.kwh = kwh;
		this.dataCaptura = dataCaptura;
		this.estabelecimento = estabelecimento;
		this.predio = predio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getKwh() {
		return kwh;
	}

	public void setKwh(float kwh) {
		this.kwh = kwh;
	}

	public Date getDataCaptura() {
		return dataCaptura;
	}

	public void setDataCaptura(Date dataCaptura) {
		this.dataCaptura = dataCaptura;
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
	
}
