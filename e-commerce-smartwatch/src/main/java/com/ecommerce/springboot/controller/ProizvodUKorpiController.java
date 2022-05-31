package com.ecommerce.springboot.controller;

import java.util.List;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
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
import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.Proizvod;
//import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.ProizvodUKorpi;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.ProizvodRepository;
//import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.repository.ProizvodUKorpiRepository;
import com.ecommerce.springboot.service.ProizvodUKorpiService;
import com.ecommerce.springboot.service.ProizvodUKorpiServiceImpl;

@RestController
@CrossOrigin
public class ProizvodUKorpiController {

	@Autowired
	private ProizvodUKorpiRepository proizvodUKorpiRepository;
	
	@Autowired
	ProizvodRepository proizvodRepository;
	
	@Autowired
	KorpaRepository korpaRepository;
	
	@Autowired
	ProizvodUKorpiService proizvodUKorpiService;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodiUKorpi")
	public List<ProizvodUKorpi> getAllProizvodiUKorpi() {
		return proizvodUKorpiRepository.findAll();
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN') or hasRole('KUPAC')")
	@GetMapping("/api/auth/proizvodiUKorpi/{id}")
	public ResponseEntity<ProizvodUKorpi> getProizvodUKorpiById(@PathVariable("id") int id) {
		ProizvodUKorpi proizvodUKorpi = proizvodUKorpiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji proizvod u korpi sa id: " + id));
		return ResponseEntity.ok(proizvodUKorpi);
	}
	
//	@PreAuthorize("hasRole('KUPAC')")
	@PostMapping("/api/auth/proizvodiUKorpi")
	public ResponseEntity<?> createProizvodUKorpi(@RequestBody ProizvodUKorpi proizvodUKorpi) {
	
		try {
			if(!proizvodUKorpiRepository.existsById(proizvodUKorpi.getId_proizvod_u_korpi())) {
				ProizvodUKorpi p = proizvodUKorpiService.addProizvodUKorpu(proizvodUKorpi);
				return new ResponseEntity<ProizvodUKorpi>(p, HttpStatus.OK);
			}
			return new ResponseEntity<ProizvodUKorpi>(HttpStatus.CONFLICT);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getCause().getCause().getMessage());
		}		
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ADMIN')")
	@PutMapping("/api/auth/proizvodiUKorpi/{id}")
	public ResponseEntity<ProizvodUKorpi> updateProizvodUKorpi(@PathVariable("id") int id, @RequestBody ProizvodUKorpi newProizvodUKorpi) {		
		ProizvodUKorpi proizvodUKorpi = proizvodUKorpiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji proizvod u korpi sa id: " + id));
		/*proizvodUKorpi.setKolicina(newProizvodUKorpi.getKolicina());
		proizvodUKorpi.setCena_za_kolicinu(newProizvodUKorpi.getCena_za_kolicinu());
		proizvodUKorpi.setKorpa(newProizvodUKorpi.getKorpa());
		proizvodUKorpi.setProizvod(newProizvodUKorpi.getProizvod());*/
		
		ProizvodUKorpi updatedProizvodUKorpi = proizvodUKorpiRepository.save(proizvodUKorpi);
		
		return ResponseEntity.ok(updatedProizvodUKorpi);
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole('ADMIN')")
	@DeleteMapping("/api/auth/proizvodiUKorpi/{id}")
	public ResponseEntity<ProizvodUKorpi> deleteProizvodUKorpi(@PathVariable("id") int id){
		if (!proizvodUKorpiRepository.existsById(id)) {
			return new ResponseEntity<ProizvodUKorpi>(HttpStatus.NO_CONTENT);
		}
		
		proizvodUKorpiRepository.deleteById(id);		
		return new ResponseEntity<ProizvodUKorpi>(HttpStatus.OK);
	}
}
