package com.ecommerce.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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
@Table(name="Korpa")
public class Korpa {
	
	@Id
	@SequenceGenerator(name="KORPA_ID_GENERATOR", sequenceName="KORPA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORPA_ID_GENERATOR")
	private int id_korpa;
	private int ukupan_iznos_korpe;
	private int broj_stavki;
	
//	@OneToOne
//	@JoinColumn(name="id_korisnik")
//	@JsonIgnore
//	private Kupac kupac;
	
	@OneToMany(mappedBy="korpa", cascade = {CascadeType.ALL})
	@JsonIgnore
	private List<ProizvodUKorpi> proizvodi_u_korpi;
	
//	@OneToMany(mappedBy="korpa")
//    @ElementCollection
//	private List<Integer> idProizvodiUKorpi;
//	
//	@OneToOne(mappedBy = "korpa")
//	@JsonIgnore
//	private Porudzbina porudzbina;
	
	public Korpa() {
		this.ukupan_iznos_korpe=0;
		this.broj_stavki=0;
		this.proizvodi_u_korpi = new ArrayList<>();
		
	}

	public Korpa(int id_korpa, int ukupan_iznos_korpe, int broj_stavki, List<ProizvodUKorpi> proizvodi_u_korpi,
			Porudzbina porudzbina, List<Porudzbina> porudzbine) {
		super();
		this.id_korpa = id_korpa;
		this.ukupan_iznos_korpe = ukupan_iznos_korpe;
		this.broj_stavki = broj_stavki;
		this.proizvodi_u_korpi = proizvodi_u_korpi;
//		this.porudzbina = porudzbina;
	}
	
	

//	public List<Integer> getIdProizvodiUKorpi() {
//		return idProizvodiUKorpi;
//	}
//
//	public void setIdProizvodiUKorpi(List<Integer> idProizvodiUKorpi) {
//		this.idProizvodiUKorpi = idProizvodiUKorpi;
//	}

	public int getId_korpa() {
		return id_korpa;
	}

	public void setId_korpa(int id_korpa) {
		this.id_korpa = id_korpa;
	}

	public int getUkupan_iznos_korpe() {
		return ukupan_iznos_korpe;
	}

	public void setUkupan_iznos_korpe(int ukupan_iznos_korpe) {
		this.ukupan_iznos_korpe = ukupan_iznos_korpe;
	}

	public int getBroj_stavki() {
		return broj_stavki;
	}

	public void setBroj_stavki(int broj_stavki) {
		this.broj_stavki = broj_stavki;
	}

	public List<ProizvodUKorpi> getProizvodi_u_korpi() {
		return proizvodi_u_korpi;
	}

	public void setProizvodi_u_korpi(List<ProizvodUKorpi> proizvodi_u_korpi) {
		this.proizvodi_u_korpi = proizvodi_u_korpi;
	}
//
//	public Porudzbina getPorudzbina() {
//		return porudzbina;
//	}
//
//	public void setPorudzbina(Porudzbina porudzbina) {
//		this.porudzbina = porudzbina;
//	}
	
	
	
//	public Kupac getKupac() {
//		return kupac;
//	}
//
//	public void setKupac(Kupac kupac) {
//		this.kupac = kupac;
//	}
//
//	public ProizvodUKorpi addProizvodUKorpi(ProizvodUKorpi proizvodi_u_korpi) {
//		getProizvodi_u_korpi().add(proizvodi_u_korpi);
//		proizvodi_u_korpi.setKorpa(this);
//
//		return proizvodi_u_korpi;
//	}
//
//	public ProizvodUKorpi removeProizvodUKorpi(ProizvodUKorpi proizvodi_u_korpi) {
//		getProizvodi_u_korpi().remove(proizvodi_u_korpi);
//		proizvodi_u_korpi.setKorpa(null);
//
//		return proizvodi_u_korpi;
//	}

} 
