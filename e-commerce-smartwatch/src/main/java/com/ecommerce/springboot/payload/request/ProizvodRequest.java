package com.ecommerce.springboot.payload.request;

public class ProizvodRequest {
	private int id_proizvod;
	private String nazivProizvoda;
	private String karakteristike;
	private String url;
	private int cena;
	private int dostupna_kolicina;	
	private String nazivProizvodjaca;
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
