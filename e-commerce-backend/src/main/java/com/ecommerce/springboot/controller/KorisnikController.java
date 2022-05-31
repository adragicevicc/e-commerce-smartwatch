package com.ecommerce.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Korisnik;
import com.ecommerce.springboot.repository.KorisnikRepository;

@RestController
@CrossOrigin
public class KorisnikController {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/auth/korisnici")
	public List<Korisnik> getAllKorisnici() {
		return korisnikRepository.findAll();
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/auth/korisnici/{id}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable("id") int id) {
		Korisnik korisnik = korisnikRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji korisnik sa id: " + id));
		return ResponseEntity.ok(korisnik);
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ADMIN')")
	@PostMapping("/api/auth/korisnici")
	public ResponseEntity<Korisnik> createKorisnik(@RequestBody Korisnik korisnik) {
		if(!korisnikRepository.existsById(korisnik.getId_korisnik())) {
			korisnikRepository.save(korisnik);
			return new ResponseEntity<Korisnik>(HttpStatus.OK);
		}
		return new ResponseEntity<Korisnik>(HttpStatus.CONFLICT);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@PutMapping("/api/auth/korisnici/{id}")
	public ResponseEntity<Korisnik> updateKorisnik(@PathVariable("id") int id, @RequestBody Korisnik newKorisnik) {		
		Korisnik korisnik = korisnikRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji korisnik sa id: " + id));
		korisnik.setIme(newKorisnik.getIme());
		korisnik.setPrezime(newKorisnik.getPrezime());
		korisnik.setEmail(newKorisnik.getEmail());
		korisnik.setLozinka(newKorisnik.getLozinka());
		korisnik.setUloge(newKorisnik.getUloge());
		
		Korisnik updatedKorisnik = korisnikRepository.save(korisnik);
		
		return ResponseEntity.ok(updatedKorisnik);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/auth/korisnici/{id}")
	public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") int id){
		if (!korisnikRepository.existsById(id)) {
			return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
		}
		
		korisnikRepository.deleteById(id);		
		return new ResponseEntity<Korisnik>(HttpStatus.OK);
	}
}
