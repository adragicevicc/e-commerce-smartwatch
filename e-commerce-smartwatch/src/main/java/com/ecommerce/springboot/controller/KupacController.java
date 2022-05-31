package com.ecommerce.springboot.controller;

import java.util.ArrayList;
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
import com.ecommerce.springboot.model.KupacDto;
import com.ecommerce.springboot.repository.KupacRepository;

@RestController
@CrossOrigin
public class KupacController {
	
	@Autowired
	private KupacRepository kupacRepository;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/kupci")
	public List<KupacDto> getAllKupci() {
		List<KupacDto> kupci = new ArrayList<>();
		for(Kupac k : kupacRepository.findAll()) {
			kupci.add(new KupacDto(k));
		}
		return kupci;
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/kupci/{id}")
	public ResponseEntity<KupacDto> getKupacById(@PathVariable("id") int id) {
		Kupac kupac = kupacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji kupac sa id: " + id));
		return ResponseEntity.ok(new KupacDto(kupac));
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/kupacIme")
	public Collection<Kupac> getKupacByIme(@RequestParam(required=true) String ime) {
		return kupacRepository.findKupacByImeIgnoreCase(ime);
	}
	
	/*@PreAuthorize("hasRole('KUPAC') or hasRole('ADMIN')")
	@PostMapping("/kupci")
	public ResponseEntity<Kupac> createKupac(@RequestBody Kupac kupac) {
		if(!kupacRepository.existsById(kupac.getId_korisnik())) {
			kupacRepository.save(kupac);
			return new ResponseEntity<Kupac>(HttpStatus.OK);
		}
		return new ResponseEntity<Kupac>(HttpStatus.CONFLICT);
	}
	*/
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@PutMapping("/api/auth/kupci/{id}")
	public ResponseEntity<Kupac> updateKupac(@PathVariable("id") int id, @RequestBody Kupac newKupac) {		
		Kupac kupac = kupacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji kupac sa id: " + id));
		kupac.setBroj_telefona(newKupac.getBroj_telefona());
		kupac.setAdresa(newKupac.getAdresa());
		
		Kupac updatedKupac = kupacRepository.save(kupac);
		
		return ResponseEntity.ok(updatedKupac);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@DeleteMapping("/api/auth/kupci/{id}")
	public ResponseEntity<Kupac> deleteKupac(@PathVariable("id") int id){
		if (!kupacRepository.existsById(id)) {
			return new ResponseEntity<Kupac>(HttpStatus.NO_CONTENT);
		}
		
		kupacRepository.deleteById(id);		
		return new ResponseEntity<Kupac>(HttpStatus.OK);
	}
}
