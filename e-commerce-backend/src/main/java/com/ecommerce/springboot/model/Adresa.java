package com.ecommerce.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Adresa")
public class Adresa {
	
	@Id
	@SequenceGenerator(name="ADRESA_ID_GENERATOR", sequenceName="ADRESA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADRESA_ID_GENERATOR")
	private int id_adresa;
	@Column(name="naziv_ulice")
	private String nazivUlice;
	private String broj;
	
	@Column(name="grad")
	private String grad;
	
	@OneToMany(mappedBy="adresa", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	@JsonIgnore
	private List<Kupac> kupci;
	
	public Adresa() {
		
	}
	
	public Adresa(int id_adresa, String nazivUlice, String broj, String grad, List<Kupac> kupci) {
		super();
		this.id_adresa = id_adresa;
		this.nazivUlice = nazivUlice;
		this.broj = broj;
		this.grad = grad;
		this.kupci = kupci;
	}

	public Adresa(Adresa adresa) {
		this.id_adresa=adresa.id_adresa;
		this.nazivUlice=adresa.nazivUlice;
		this.broj=adresa.broj;
		this.grad=adresa.grad;
	}

	public Adresa(String nazivUlice, String broj, String grad) {
		this.nazivUlice=nazivUlice;
		this.broj=broj;
		this.grad=grad;
	}

	public int getId_adresa() {
		return id_adresa;
	}

	public void setId_adresa(int id_adresa) {
		this.id_adresa = id_adresa;
	}

	public String getNazivUlice() {
		return nazivUlice;
	}

	public void setNazivUlice(String nazivUlice) {
		this.nazivUlice = nazivUlice;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}



	public List<Kupac> getKupci() {
		return kupci;
	}



	public void setKupci(List<Kupac> kupci) {
		this.kupci = kupci;
	}
	
	public Kupac addKupac(Kupac kupac) {
		getKupci().add(kupac);
		kupac.setAdresa(this);

		return kupac;
	}

	public Kupac removeKupac(Kupac kupac) {
		getKupci().remove(kupac);
		kupac.setAdresa(null);

		return kupac;
	}

	
	
}
