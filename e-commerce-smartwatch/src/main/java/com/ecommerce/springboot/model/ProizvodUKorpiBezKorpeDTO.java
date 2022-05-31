package com.ecommerce.springboot.model;

public class ProizvodUKorpiBezKorpeDTO {

	private int id_proizvod_u_korpi;
	private int kolicina;
	private int cena_za_kolicinu;

	private Proizvod proizvod;
	
	public ProizvodUKorpiBezKorpeDTO() {
		
	}
	
	public ProizvodUKorpiBezKorpeDTO(ProizvodUKorpi proizvodUKorpi) {
		super();
		this.id_proizvod_u_korpi = proizvodUKorpi.getId_proizvod_u_korpi();
		this.kolicina = proizvodUKorpi.getKolicina();
		this.proizvod = proizvodUKorpi.getProizvod();
		this.cena_za_kolicinu = proizvodUKorpi.getCena_za_kolicinu();
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

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	

}
