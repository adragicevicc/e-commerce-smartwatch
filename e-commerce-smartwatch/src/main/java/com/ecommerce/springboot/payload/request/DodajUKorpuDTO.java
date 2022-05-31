package com.ecommerce.springboot.payload.request;

public class DodajUKorpuDTO {

	private int proizvodId;
	private int kolicina;
	
	public DodajUKorpuDTO() {}
	
	public DodajUKorpuDTO(int proizvodId, int kolicina) {
		super();
		this.proizvodId = proizvodId;
		this.kolicina = kolicina;
	}
	
	public int getProizvodId() {
		return proizvodId;
	}
	public void setProizvodId(int proizvodId) {
		this.proizvodId = proizvodId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	
}
