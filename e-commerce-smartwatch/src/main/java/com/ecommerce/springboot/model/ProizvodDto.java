package com.ecommerce.springboot.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProizvodDto {
	
	private int id_proizvod;
	private String nazivProizvoda;
	private String karakteristike;
	private String url;
	private int cena;
	private int dostupna_kolicina;	
	private String nazivProizvodjaca;
	
	
	public ProizvodDto(Proizvod proizvod) {
		this.setNazivProizvoda(proizvod.getNazivProizvoda());
		this.setKarakteristike(proizvod.getKarakteristike());
		this.setUrl(proizvod.getUrl());
		this.setCena(proizvod.getCena());
		this.setDostupna_kolicina(proizvod.getDostupna_kolicina());
		this.setNazivProizvodjaca(proizvod.getProizvodjac().getNazivProizvodjaca());
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}
	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}
	
	

}
