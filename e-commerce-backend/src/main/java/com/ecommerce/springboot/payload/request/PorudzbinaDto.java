package com.ecommerce.springboot.payload.request;

import java.util.List;

public class PorudzbinaDto {
	
	private int id_korpa;
	
	private int id_kupac;
	
	private List<String> proizvodi;
	
	public PorudzbinaDto(){
		
	};
	
	public PorudzbinaDto(int id_korpa, int id_kupac, List<String> proizvodi) {
		this.id_korpa=id_korpa;
		this.id_kupac=id_kupac;
		this.proizvodi=proizvodi;
	}

	public int getId_korpa() {
		return id_korpa;
	}

	public void setId_korpa(int id_korpa) {
		this.id_korpa = id_korpa;
	}

	public int getId_kupac() {
		return id_kupac;
	}

	public void setId_kupac(int id_kupac) {
		this.id_kupac = id_kupac;
	};
	
	
	
	
	

}
