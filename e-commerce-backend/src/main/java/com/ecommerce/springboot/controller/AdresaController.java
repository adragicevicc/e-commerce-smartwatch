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
import com.ecommerce.springboot.model.Adresa;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.repository.AdresaRepository;

@RestController
public class AdresaController {

	@Autowired
	private AdresaRepository adresaRepository;
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/adrese")
	public List<Adresa> getAllAdrese() {
		return adresaRepository.findAll();
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/adrese/{id}")
	public ResponseEntity<Adresa> getAdresaById(@PathVariable("id") int id) {
		Adresa adresa = adresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji adresa sa id: " + id));
		return ResponseEntity.ok(adresa);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/adresaUlica")
	public Collection<Adresa> getAdresaByNazivUlice(@RequestParam(required=true) String nazivUlice) {
		return adresaRepository.findAdresaByNazivUliceIgnoreCase(nazivUlice);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@PostMapping("/adrese")
	public ResponseEntity<Adresa> createAdresa(@RequestBody Adresa adresa) {
		if(!adresaRepository.existsById(adresa.getId_adresa())) {
			adresaRepository.save(adresa);
			return new ResponseEntity<Adresa>(HttpStatus.OK);
		}
		return new ResponseEntity<Adresa>(HttpStatus.CONFLICT);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@PutMapping("/adrese/{id}")
	public ResponseEntity<Adresa> updateAdresa(@PathVariable("id") int id, @RequestBody Adresa newAdresa) {		
		Adresa adresa = adresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji adresa sa id: " + id));
		adresa.setNazivUlice(newAdresa.getNazivUlice());
		adresa.setBroj(newAdresa.getBroj());
		adresa.setGrad(newAdresa.getGrad());
		adresa.setKupci(newAdresa.getKupci());
		
		Adresa updatedAdresa = adresaRepository.save(adresa);
		
		return ResponseEntity.ok(updatedAdresa);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/adrese/{id}")
	public ResponseEntity<Adresa> deleteAdresa(@PathVariable("id") int id){
		if (!adresaRepository.existsById(id)) {
			return new ResponseEntity<Adresa>(HttpStatus.NO_CONTENT);
		}
		
		adresaRepository.deleteById(id);		
		return new ResponseEntity<Adresa>(HttpStatus.OK);
	}
	
	
}
