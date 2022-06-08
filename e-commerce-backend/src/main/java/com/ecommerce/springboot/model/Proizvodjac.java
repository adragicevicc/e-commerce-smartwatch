package com.ecommerce.springboot.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Proizvodjac")
public class Proizvodjac {
	
	@Id
	@SequenceGenerator(name="PROIZVODJAC_ID_GENERATOR", sequenceName="PROIZVODJAC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVODJAC_ID_GENERATOR")
	private int id_proizvodjac;
	@Column(name="naziv_proizvodjaca")
	private String nazivProizvodjaca;
	
	@OneToMany(mappedBy="proizvodjac", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Proizvod> proizvod;
	
	

	public Proizvodjac() {
		
	}

	public Proizvodjac(int id_proizvodjac, String nazivProizvodjaca, List<Proizvod> proizvod) {
		super();
		this.id_proizvodjac = id_proizvodjac;
		this.nazivProizvodjaca = nazivProizvodjaca;
		this.proizvod = proizvod;
	}

	public int getId_proizvodjac() {
		return id_proizvodjac;
	}

	public void setId_proizvodjac(int id_proizvodjac) {
		this.id_proizvodjac = id_proizvodjac;
	}

	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}
	
	public List<Proizvod> getProizvod() {
		return proizvod;
	}

	public void setProizvod(List<Proizvod> proizvod) {
		this.proizvod = proizvod;
	}

	/*public List<Proizvod> getProizvodi() {
		return proizvod;
	}

	public void setProizvodi(List<Proizvod> proizvod) {
		this.proizvod = proizvod;
	}*/
	
	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvod().add(proizvod);
		proizvod.setProizvodjac(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvod().remove(proizvod);
		proizvod.setProizvodjac(null);

		return proizvod;
	}
	
	
	
}
	