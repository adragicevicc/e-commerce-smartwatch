package com.ecommerce.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Grad")

public class Grad {
	
	@Id
	@SequenceGenerator(name="GRAD_ID_GENERATOR", sequenceName="GRAD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRAD_ID_GENERATOR")
	private int id_grad;
	@Column(name="naziv_grada")
	private String nazivGrada;
	private int postanski_broj;
	
	@OneToMany(mappedBy="grad", cascade = {CascadeType.DETACH, CascadeType.ALL})
	@JsonIgnore
	private List<Adresa> adrese;
	
	public Grad() {
		
	}
	
	public Grad(Grad grad) {
		this.id_grad=grad.id_grad;
		this.nazivGrada=grad.nazivGrada;
		this.postanski_broj=grad.postanski_broj;
	}

	public Grad(int id_grad, String nazivGrada, int postanski_broj) {
		super();
		this.id_grad = id_grad;
		this.nazivGrada = nazivGrada;
		this.postanski_broj = postanski_broj;
	}

	public int getId_grad() {
		return id_grad;
	}

	public void setId_grad(int id_grad) {
		this.id_grad = id_grad;
	}

	public String getNazivGrada() {
		return nazivGrada;
	}

	public void setNazivGrada(String nazivGrada) {
		this.nazivGrada = nazivGrada;
	}

	public int getPostanski_broj() {
		return postanski_broj;
	}

	public void setPostanski_broj(int postanski_broj) {
		this.postanski_broj = postanski_broj;
	}
	
	
	
	public List<Adresa> getAdrese() {
		return adrese;
	}

	public void setAdrese(List<Adresa> adrese) {
		this.adrese = adrese;
	}

	public Adresa addAdresa(Adresa adresa) {
		getAdrese().add(adresa);
		adresa.setGrad(this);

		return adresa;
	}

	public Adresa removeAdresa(Adresa adresa) {
		getAdrese().remove(adresa);
		adresa.setGrad(null);

		return adresa;
	}
	
	
}
