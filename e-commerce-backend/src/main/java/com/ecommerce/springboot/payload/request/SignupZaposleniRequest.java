package com.ecommerce.springboot.payload.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ecommerce.springboot.model.Adresa;

public class SignupZaposleniRequest {
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
    
    //zaposleni
    private LocalDate datum_zaposlenja;
    
    private String pozicija;
    
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

	public LocalDate getDatum_zaposlenja() {
		return datum_zaposlenja;
	}

	public void setDatum_zaposlenja(LocalDate datum_zaposlenja) {
		this.datum_zaposlenja = datum_zaposlenja;
	}

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

	public Set<String> getUloga() {
		return uloga;
	}

	public void setUloga(Set<String> uloga) {
		this.uloga = uloga;
	}
	
	
}
