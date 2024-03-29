package com.ecommerce.springboot.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Porudzbina")
public class Porudzbina {
	
	@Id
	@SequenceGenerator(name="PORUDZBINA_ID_GENERATOR", sequenceName="PORUDZBINA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUDZBINA_ID_GENERATOR")
	private int id_porudzbina;
	private int ukupan_iznos;
	private LocalDate datum_porudzbine;
	
	/*@Transient
	private ArrayList<String> proizvodi = new ArrayList<String>();*/
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="id_korisnik")
	private Kupac kupac;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="id_korpa", referencedColumnName="id_korpa")	
	private Korpa korpa;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "porudzbina_proizvod", 
				joinColumns = @JoinColumn(name = "id_porudzbina"), 
				inverseJoinColumns = @JoinColumn(name = "id_proizvod"))
	private Set<Proizvod> proizvodi= new HashSet<>();
	
	public Porudzbina() {
		
	}

	public Porudzbina(int id_porudzbina, int ukupan_iznos, LocalDate datum_porudzbine, Kupac kupac, Korpa korpa) {
		super();
		this.id_porudzbina = id_porudzbina;
		this.ukupan_iznos = ukupan_iznos;
		this.datum_porudzbine = datum_porudzbine;
		this.korpa = korpa;
		this.kupac=kupac;
	}
	
	
	public Porudzbina(int id_porudzbina, int ukupan_iznos, LocalDate datum_porudzbine, Kupac kupac,
			Set<Proizvod> proizvodi, Korpa korpa) {
		super();
		this.id_porudzbina = id_porudzbina;
		this.ukupan_iznos = ukupan_iznos;
		this.datum_porudzbine = datum_porudzbine;
		this.kupac = kupac;
		this.proizvodi = proizvodi;
		this.korpa = korpa;
	}

	public void setProizvodi(Set<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	public Set<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public int getId_porudzbina() {
		return id_porudzbina;
	}

	public void setId_porudzbina(int id_porudzbina) {
		this.id_porudzbina = id_porudzbina;
	}

	public int getUkupan_iznos() {
		return ukupan_iznos;
	}

	public void setUkupan_iznos(int ukupan_iznos) {
		this.ukupan_iznos = ukupan_iznos;
	}

	public LocalDate getDatum_porudzbine() {
		return datum_porudzbine;
	}

	public void setDatum_porudzbine(LocalDate datum_porudzbine) {
		this.datum_porudzbine = datum_porudzbine;
	}


	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}
	
	
	

}
