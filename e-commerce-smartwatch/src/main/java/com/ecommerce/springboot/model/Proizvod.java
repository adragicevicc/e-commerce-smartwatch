package com.ecommerce.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Proizvod")
public class Proizvod {
	
	@Id
	@SequenceGenerator(name="PROIZVOD_ID_GENERATOR", sequenceName="PROIZVOD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_ID_GENERATOR")
	private int id_proizvod;
	@Column(name="naziv_proizvoda")
	private String nazivProizvoda;
	private String karakteristike;
	private String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private int cena;
	private int dostupna_kolicina;
	
	@ManyToOne
	@JoinColumn(name="id_proizvodjac")
	private Proizvodjac proizvodjac;
	
	public Proizvod() {
		
	}

	public Proizvod(int id_proizvod, String nazivProizvoda, String karakteristike, int cena, int dostupna_kolicina,
			Proizvodjac proizvodjac) {
		super();
		this.id_proizvod = id_proizvod;
		this.nazivProizvoda = nazivProizvoda;
		this.karakteristike = karakteristike;
		this.cena = cena;
		this.dostupna_kolicina = dostupna_kolicina;
		this.proizvodjac = proizvodjac;
	}

	public int getId_proizvod() {
		return id_proizvod;
	}

	public void setId_proizvod(int id_proizvod) {
		this.id_proizvod = id_proizvod;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	public String getKarakteristike() {
		return karakteristike;
	}

	public void setKarakteristike(String karakteristike) {
		this.karakteristike = karakteristike;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getDostupna_kolicina() {
		return dostupna_kolicina;
	}

	public void setDostupna_kolicina(int dostupna_kolicina) {
		this.dostupna_kolicina = dostupna_kolicina;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	
	
}