package com.ecommerce.springboot.model;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.springboot.service.ProizvodUKorpiService;


public class KorpaDto {
	private int id_korpa;
	private int ukupan_iznos_korpe;
	private int broj_stavki;
//	private int id_kupac;

	private List<ProizvodUKorpiBezKorpeDTO> proizvodiUKorpi;
	
	
	public KorpaDto(Korpa korpa) {		 

		this.setId_korpa(korpa.getId_korpa());
		this.setUkupan_iznos_korpe(korpa.getUkupan_iznos_korpe());
		this.setBroj_stavki(korpa.getBroj_stavki());
//		this.setId_kupac(korpa.getKupac().getId_korisnik());
		this.proizvodiUKorpi = new ArrayList<>();
		for(ProizvodUKorpi p : korpa.getProizvodi_u_korpi()) {
			this.proizvodiUKorpi.add(new ProizvodUKorpiBezKorpeDTO(p));
		}
		
	}

	public List<ProizvodUKorpiBezKorpeDTO> getProizvodiUKorpi() {
		return proizvodiUKorpi;
	}

	public void setProizvodiUKorpi(List<ProizvodUKorpiBezKorpeDTO> proizvodiUKorpi) {
		this.proizvodiUKorpi = proizvodiUKorpi;
	}

	public int getId_korpa() {
		return id_korpa;
	}

	public void setId_korpa(int id_korpa) {
		this.id_korpa = id_korpa;
	}

	public int getUkupan_iznos_korpe() {
		return ukupan_iznos_korpe;
	}

	public void setUkupan_iznos_korpe(int ukupan_iznos_korpe) {
		this.ukupan_iznos_korpe = ukupan_iznos_korpe;
	}

	public int getBroj_stavki() {
		return broj_stavki;
	}

	public void setBroj_stavki(int broj_stavki) {
		this.broj_stavki = broj_stavki;
	}
//
//	public int getId_kupac() {
//		return id_kupac;
//	}
//
//	public void setId_kupac(int id_kupac) {
//		this.id_kupac = id_kupac;
//	}
//	
	
	
	
}
