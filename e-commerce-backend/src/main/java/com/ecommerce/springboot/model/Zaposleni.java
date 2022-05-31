package com.ecommerce.springboot.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Zaposleni")
public class Zaposleni extends Korisnik{
	
	private LocalDate datum_zaposlenja;
	private String pozicija;
	
	public Zaposleni() {
		
	}

	public Zaposleni(LocalDate datum_zaposlenja, String pozicija) {
		super();
		this.datum_zaposlenja = datum_zaposlenja;
		this.pozicija = pozicija;
	}

	public Zaposleni(Korisnik korisnik, LocalDate datum_zaposlenja, String pozicija) {
		super(korisnik);
		this.datum_zaposlenja=datum_zaposlenja;
		this.pozicija=pozicija;
	}

	public Zaposleni(Korisnik korisnik, Set<Uloga> uloge, LocalDate datum_zaposlenja, String pozicija) {
		super(korisnik);
		super.setUloge(uloge);
		this.datum_zaposlenja=datum_zaposlenja;
		this.pozicija=pozicija;
	}

	public LocalDate getDatum_zaposlenja() {
		return datum_zaposlenja;
	}


	public void setDatum_zaposlenja(LocalDate datum_zaposlenja) {
		this.datum_zaposlenja = datum_zaposlenja;
	}


	public String getPozicija() {
		return pozicija;
	}


	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

}
