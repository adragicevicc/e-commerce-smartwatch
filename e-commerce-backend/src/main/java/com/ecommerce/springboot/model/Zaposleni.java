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
	
	private String pozicija;
	
	public Zaposleni() {
		
	}

	public Zaposleni(String pozicija) {
		super();
		this.pozicija = pozicija;
	}

	public Zaposleni(Korisnik korisnik, String pozicija) {
		super(korisnik);
		this.pozicija=pozicija;
	}

	public Zaposleni(Korisnik korisnik, Set<Uloga> uloge, String pozicija) {
		super(korisnik);
		super.setUloge(uloge);
		this.pozicija=pozicija;
	}


	public String getPozicija() {
		return pozicija;
	}


	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

}
