package com.ecommerce.springboot.controller;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.model.Zaposleni;
import com.ecommerce.springboot.repository.KupacRepository;
import com.ecommerce.springboot.repository.ZaposleniRepository;

@RestController
@CrossOrigin
public class ZaposleniController {
	
	@Autowired
	private ZaposleniRepository zaposleniRepository;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/auth/zaposleni")
	public List<Zaposleni> getAllZaposleni() {
		return zaposleniRepository.findAll();
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/auth/zaposleni/{id}")
	public ResponseEntity<Zaposleni> getZaposleniById(@PathVariable("id") int id) {
		Zaposleni zaposleni = zaposleniRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji zaposleni sa id: " + id));
		return ResponseEntity.ok(zaposleni);
	}
	
	//@PreAuthorize("hasRole('ADMIN') or hasRole('ZAPOSLENI')")
	@GetMapping("/zaposleniIme/")
	public Collection<Zaposleni> getZaposleniByIme(@RequestParam(required=true) String ime) {
		return zaposleniRepository.findZaposleniByImeIgnoreCase(ime);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/zaposleni")
	public ResponseEntity<Zaposleni> createZaposleni(@RequestBody Zaposleni zaposleni) {
		if(!zaposleniRepository.existsById(zaposleni.getId_korisnik())) {
			zaposleniRepository.save(zaposleni);
			return new ResponseEntity<Zaposleni>(HttpStatus.OK);
		}
		return new ResponseEntity<Zaposleni>(HttpStatus.CONFLICT);
	}
	
	//@PreAuthorize("hasRole('ADMIN') or hasRole('ZAPOSLENI')")
	@PutMapping("/api/auth/zaposleni/{id}")
	public ResponseEntity<Zaposleni> updateZaposleni(@PathVariable("id") int id, @RequestBody Zaposleni newZaposleni) {		
		Zaposleni zaposleni = zaposleniRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji kupac sa id: " + id));
		zaposleni.setDatum_zaposlenja(newZaposleni.getDatum_zaposlenja());
		zaposleni.setPozicija(newZaposleni.getPozicija());
		
		Zaposleni updatedZaposleni = zaposleniRepository.save(zaposleni);
		
		return ResponseEntity.ok(updatedZaposleni);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/auth/zaposleni/{id}")
	public ResponseEntity<Zaposleni> deleteZaposleni(@PathVariable("id") int id){
		if (!zaposleniRepository.existsById(id)) {
			return new ResponseEntity<Zaposleni>(HttpStatus.NO_CONTENT);
		}
		
		zaposleniRepository.deleteById(id);		
		return new ResponseEntity<Zaposleni>(HttpStatus.OK);
	}
}
