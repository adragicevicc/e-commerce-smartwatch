package com.ecommerce.springboot.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class KupacDto {
	
	private int broj_telefona;
	private int id_adresa;
	private int id_korisnik;
	private String ime;
	private String prezime;	
	private String email;
	private String lozinka;
	private String tip;
	private Set<Uloga> uloge;
	
	public KupacDto(Kupac kupac) {
		this.setId_korisnik(kupac.getId_korisnik());
		this.setIme(kupac.getIme());
		this.setPrezime(kupac.getPrezime());
		this.setEmail(kupac.getEmail());
		this.setLozinka(kupac.getLozinka());
		this.setTip(kupac.getTip());
		this.setUloge(kupac.getUloge());
		this.setBroj_telefona(kupac.getBroj_telefona());
		this.setId_adresa(kupac.getAdresa().getId_adresa());
	}

	public int getBroj_telefona() {
		return broj_telefona;
	}

	public void setBroj_telefona(int broj_telefona) {
		this.broj_telefona = broj_telefona;
	}

	public int getId_adresa() {
		return id_adresa;
	}

	public void setId_adresa(int id_adresa) {
		this.id_adresa = id_adresa;
	}

	public int getId_korisnik() {
		return id_korisnik;
	}

	public void setId_korisnik(int id_korisnik) {
		this.id_korisnik = id_korisnik;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Set<Uloga> getUloge() {
		return uloge;
	}

	public void setUloge(Set<Uloga> uloge) {
		this.uloge = uloge;
	}
	
	
	
}
