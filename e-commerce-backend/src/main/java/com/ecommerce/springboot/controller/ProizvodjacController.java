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
import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.Proizvodjac;
import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.repository.ProizvodjacRepository;

@RestController
@CrossOrigin
public class ProizvodjacController {
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodjaci")
	public List<Proizvodjac> getAllProizvodjaci() {
		return proizvodjacRepository.findAll();
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodjaci/{id}")
	public ResponseEntity<Proizvodjac> getProizvodjacById(@PathVariable("id") int id) {
		Proizvodjac proizvodjac = proizvodjacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji proizvodjac sa id: " + id));
		return ResponseEntity.ok(proizvodjac);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodjacNaziv")
	public Proizvodjac getProizvodjacByNazivProizvodjaca(@RequestParam(required=true) String nazivProizvodjaca) {
		return proizvodjacRepository.findProizvodjacByNazivProizvodjacaIgnoreCase(nazivProizvodjaca);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@PostMapping("/api/auth/proizvodjaci")
	public ResponseEntity<Proizvodjac> createProizvodjac(@RequestBody Proizvodjac proizvodjac) {
		if(!proizvodjacRepository.existsById(proizvodjac.getId_proizvodjac())) {
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		}
		return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@PutMapping("/api/auth/proizvodjaci/{id}")
	public ResponseEntity<Proizvodjac> updateProizvodjac(@PathVariable("id") int id, @RequestBody Proizvodjac newProizvodjac) {		
		Proizvodjac proizvodjac = proizvodjacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji proizvodjac sa id: " + id));
		proizvodjac.setNazivProizvodjaca(newProizvodjac.getNazivProizvodjaca());
		proizvodjac.setProizvod(newProizvodjac.getProizvod());
		
		Proizvodjac updatedProizvodjac = proizvodjacRepository.save(proizvodjac);
		
		return ResponseEntity.ok(updatedProizvodjac);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@DeleteMapping("/api/auth/proizvodjaci/{id}")
	public ResponseEntity<Proizvodjac> deleteProizvodjac(@PathVariable("id") int id){
		if (!proizvodjacRepository.existsById(id)) {
			return new ResponseEntity<Proizvodjac>(HttpStatus.NO_CONTENT);
		}
		
		proizvodjacRepository.deleteById(id);		
		return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
	}

}
