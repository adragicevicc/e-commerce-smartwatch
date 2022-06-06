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
import com.ecommerce.springboot.model.Porudzbina;
import com.ecommerce.springboot.payload.request.PorudzbinaDto;
import com.ecommerce.springboot.repository.PorudzbinaRepository;
import com.ecommerce.springboot.service.PorudzbinaService;


@RestController
@CrossOrigin
public class PorudzbinaController {
	
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	PorudzbinaService porudzbinaService;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/porudzbine")
	public List<Porudzbina> getAllPorudzbine() {
		List<Porudzbina> p = porudzbinaRepository.findAll();
		for(Porudzbina por : p) {
			System.out.println(por.getProizvodi().toString());
		}
		return p;
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/porudzbine/{id}")
	public ResponseEntity<Porudzbina> getPorudzbinaById(@PathVariable("id") int id) {
		Porudzbina porudzbina = porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji porudzbina sa id: " + id));
		return ResponseEntity.ok(porudzbina);
	}
	

	
	//@PreAuthorize("hasRole('KUPAC')")
	@PostMapping("/api/auth/porudzbine")
	public ResponseEntity<Porudzbina> createPorudzbina(@RequestBody PorudzbinaDto porudzbina) {
		
			Porudzbina p = porudzbinaService.addPPorudzbina(porudzbina);

			System.out.println(p.getProizvodi());
			return new ResponseEntity<Porudzbina>(HttpStatus.OK);
		
	}
	
	//@PreAuthorize("hasRole('KUPAC')")
	@PutMapping("/api/auth/porudzbine/{id}")
	public ResponseEntity<Porudzbina> updatePorudzbina(@PathVariable("id") int id, @RequestBody Porudzbina newPorudzbina) {		
		Porudzbina porudzbina = porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji porudzbina sa id: " + id));
		porudzbina.setUkupan_iznos(newPorudzbina.getUkupan_iznos());
		porudzbina.setDatum_porudzbine(newPorudzbina.getDatum_porudzbine());
		porudzbina.setKorpa(newPorudzbina.getKorpa());
		
		Porudzbina updatedPorudzbina = porudzbinaRepository.save(porudzbina);
		
		return ResponseEntity.ok(updatedPorudzbina);
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@DeleteMapping("/api/auth/porudzbine/{id}")
	public ResponseEntity<Porudzbina> deletePorudzbina(@PathVariable("id") int id){
		if (!porudzbinaRepository.existsById(id)) {
			return new ResponseEntity<Porudzbina>(HttpStatus.NO_CONTENT);
		}
		
		porudzbinaRepository.deleteById(id);		
		return new ResponseEntity<Porudzbina>(HttpStatus.OK);
	}

}
