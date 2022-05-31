package com.ecommerce.springboot.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private LocalDate datum_isporuke;
	private String status;
	private boolean uplata;
	
	/*@ManyToOne
	@JoinColumn(name="id_kupac")
	private Kupac kupac;*/
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_korpa", referencedColumnName="id_korpa")
	private Korpa korpa;
	
	public Porudzbina() {
		
	}

	public Porudzbina(int id_porudzbina, int ukupan_iznos, LocalDate datum_porudzbine, LocalDate datum_isporuke,
			String status, boolean uplata, Kupac kupac, Korpa korpa) {
		super();
		this.id_porudzbina = id_porudzbina;
		this.ukupan_iznos = ukupan_iznos;
		this.datum_porudzbine = datum_porudzbine;
		this.datum_isporuke = datum_isporuke;
		this.status = status;
		this.uplata = uplata;
		this.korpa = korpa;
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

	public LocalDate getDatum_isporuke() {
		return datum_isporuke;
	}

	public void setDatum_isporuke(LocalDate datum_isporuke) {
		this.datum_isporuke = datum_isporuke;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isUplata() {
		return uplata;
	}

	public void setUplata(boolean uplata) {
		this.uplata = uplata;
	}


	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}
	
	
	

}
