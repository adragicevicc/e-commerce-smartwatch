package com.ecommerce.springboot.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Uloga {
	@Id
	@SequenceGenerator(name="ULOGA_ID_GENERATOR", sequenceName="ULOGA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ULOGA_ID_GENERATOR")
	private int id_uloga;
	
	@Enumerated(EnumType.STRING)
	private EUloga name;
	
	
	public Uloga() {
		
	}
	
	public Uloga(EUloga name) {
		this.name=name;
	}

	public int getId_uloga() {
		return id_uloga;
	}

	public void setId_uloga(int id_uloga) {
		this.id_uloga = id_uloga;
	}

	public EUloga getName() {
		return name;
	}

	public void setName(EUloga name) {
		this.name = name;
	}
	
	
}
