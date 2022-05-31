package com.ecommerce.springboot.payload.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ecommerce.springboot.model.Adresa;

public class SignupKupacRequest {
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String lozinka;
    
    @NotBlank
    private String ime;
    
    @NotBlank
    private String prezime;
    
    @NotBlank
    private String tip;
    
    //kupac
    private Adresa adresa;
    
    private int broj_telefona;  
    
    public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	//uloga
	private Set<String> uloga;


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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public int getBroj_telefona() {
		return broj_telefona;
	}

	public void setBroj_telefona(int broj_telefona) {
		this.broj_telefona = broj_telefona;
	}

	public Set<String> getUloga() {
		return uloga;
	}

	public void setUloga(Set<String> uloga) {
		this.uloga = uloga;
	}
	
	

}
