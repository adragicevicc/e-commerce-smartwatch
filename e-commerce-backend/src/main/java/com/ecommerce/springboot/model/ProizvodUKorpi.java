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

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Proizvod_u_korpi")
public class ProizvodUKorpi {
	
	@Id
	@SequenceGenerator(name="PROIZVOD_U_KORPI_ID_GENERATOR", sequenceName="PROIZVOD_U_KORPI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_U_KORPI_ID_GENERATOR")
	private int id_proizvod_u_korpi;
	private int kolicina;
	private int cena_za_kolicinu;
	
	@ManyToOne
	@JoinColumn(name="id_korpa")
	private Korpa korpa;	
	
	@ManyToOne
	@JoinColumn(name="id_proizvod")
	private Proizvod proizvod;
	
	public ProizvodUKorpi() {
		
	}
	
	public ProizvodUKorpi(int kolicina, int cena_za_kolicinu, Korpa korpa, Proizvod proizvod) {
		super();
		this.kolicina = kolicina;
		this.korpa = korpa;
		this.proizvod = proizvod;
		this.cena_za_kolicinu = cena_za_kolicinu;
	}


	public int getId_proizvod_u_korpi() {
		return id_proizvod_u_korpi;
	}


	public void setId_proizvod_u_korpi(int id_proizvod_u_korpi) {
		this.id_proizvod_u_korpi = id_proizvod_u_korpi;
	}


	public int getKolicina() {
		return kolicina;
	}


	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}


	public int getCena_za_kolicinu() {
		return cena_za_kolicinu;
	}


	public void setCena_za_kolicinu(int cena_za_kolicinu) {
		this.cena_za_kolicinu = cena_za_kolicinu;
	}


	public Korpa getKorpa() {
		return korpa;
	}


	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}


	public Proizvod getProizvod() {
		return proizvod;
	}


	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	/*public int setCenaKolicina(Proizvod proizvod) {
		int cena = proizvod.getCena() * kolicina;
		return cena;
	}*/

	
	
	

}
