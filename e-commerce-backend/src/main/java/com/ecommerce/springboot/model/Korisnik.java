package com.ecommerce.springboot.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Korisnik")
public class Korisnik{
	
	@Id
	@SequenceGenerator(name="KORISNIK_ID_GENERATOR", sequenceName="KORISNIK_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORISNIK_ID_GENERATOR")
	private int id_korisnik;
	private String ime;
	private String prezime;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String lozinka;
	
	private String tip;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "korisnik_uloge", 
				joinColumns = @JoinColumn(name = "id_korisnik"), 
				inverseJoinColumns = @JoinColumn(name = "id_uloga"))
	private Set<Uloga> uloge= new HashSet<>();
	
	public Korisnik() {
		
	}

	public Korisnik(String ime, String prezime, String tip, String email, String lozinka) {
		this.ime=ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.tip=tip;
	}
	

	public Korisnik(@NotBlank @Email String email, @NotBlank String lozinka) {
		this.email = email;
		this.lozinka = lozinka;
	}

	public Korisnik(Korisnik korisnik) {
		this.ime=korisnik.ime;
		this.prezime = korisnik.prezime;
		this.email = korisnik.email;
		this.lozinka = korisnik.lozinka;
		this.tip=korisnik.tip;
		this.uloge=korisnik.uloge;
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

	public Set<Uloga> getUloge() {
		
		return uloge;
	}

	public void setUloge(Set<Uloga> uloge) {
		System.out.println("ULOGEEEE:" + uloge.size());
		this.uloge = uloge;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	


	
	
}
