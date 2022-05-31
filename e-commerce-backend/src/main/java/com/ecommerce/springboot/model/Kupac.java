package com.ecommerce.springboot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Kupac")
public class Kupac extends Korisnik {
	
	private int broj_telefona;
	
	/*@OneToMany(mappedBy="kupac", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	@JsonIgnore
	private List<Porudzbina> porudzbine;*/
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_adresa")
	private Adresa adresa;
	
//	@OneToOne(mappedBy = "kupac")
//	@JsonIgnoreProperties("id_korisnik")
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="id_korpa")
	private Korpa korpa;
	
	
	
	
	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Kupac() {
		
	}

	public Kupac(Korisnik korisnik) {
		super(korisnik);
	}

	public Kupac(Korisnik korisnik, Adresa adresa, int broj_telefona) {
		super(korisnik);
		this.adresa=adresa;
		this.broj_telefona=broj_telefona;
	}

	public Kupac(Korisnik korisnik, Set<Uloga> uloge, Adresa adresa, int broj_telefona, Korpa korpa) {
		super(korisnik);
		super.setUloge(uloge);
		this.adresa=adresa;
		this.broj_telefona=broj_telefona;
		this.korpa=korpa;
	}

	public int getBroj_telefona() {
		return broj_telefona;
	}

	public void setBroj_telefona(int broj_telefona) {
		this.broj_telefona = broj_telefona;
	}	

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	/*public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		getPorudzbine().add(porudzbina);
		porudzbina.setKupac(this);

		return porudzbina;
	}

	public Porudzbina removePorudzbina(Porudzbina porudzbina) {
		getPorudzbine().remove(porudzbina);
		porudzbina.setKupac(null);

		return porudzbina;
	}*/
	
}
