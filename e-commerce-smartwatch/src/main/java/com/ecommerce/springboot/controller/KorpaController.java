package com.ecommerce.springboot.controller;

import java.util.ArrayList;
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
import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.KorpaDto;
import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.ProizvodUKorpi;
import com.ecommerce.springboot.payload.request.DodajUKorpuDTO;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.service.KorpaService;
import com.ecommerce.springboot.service.ProizvodUKorpiService;

@RestController
@CrossOrigin 
public class KorpaController {
	
	@Autowired
	private KorpaRepository korpaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	KorpaService korpaService;
	
	@Autowired
	ProizvodUKorpiService proizvodUKorpiService;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@GetMapping("/api/auth/korpe")
	public List<KorpaDto> getAllKorpe() {
		List<KorpaDto> korpe = new ArrayList<>();
		for(Korpa k : korpaRepository.findAll()) {
			korpe.add(new KorpaDto(k));
			
		}
		return korpe;
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/korpe/{id}")
	public ResponseEntity<KorpaDto> getKorpaById(@PathVariable("id") int id) {
		Korpa korpa = korpaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji korpa sa id: " + id));
		
		
		return ResponseEntity.ok(new KorpaDto(korpa));
	}
	
	//@PreAuthorize("hasRole('KUPAC')")
	@PostMapping("/api/auth/korpe")
	public ResponseEntity<Korpa> createKorpa(@RequestBody Korpa korpa) {
		if(!korpaRepository.existsById(korpa.getId_korpa())) {
			Korpa k = korpaService.addKorpa(korpa);
			return new ResponseEntity<Korpa>(HttpStatus.OK);
		}
		return new ResponseEntity<Korpa>(HttpStatus.CONFLICT);
	}
	
//	//@PreAuthorize("hasRole('KUPAC')")
//	@PutMapping("/api/auth/korpe/{id}")
//	public ResponseEntity<Korpa> updateKorpa(@PathVariable("id") int id, @RequestBody Korpa newKorpa) {		
//		Korpa korpa = korpaRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji korpa sa id: " + id));
//		korpa.setBroj_stavki(newKorpa.getBroj_stavki());
//		korpa.setUkupan_iznos_korpe(newKorpa.getUkupan_iznos_korpe());
//		korpa.setProizvodi_u_korpi(newKorpa.getProizvodi_u_korpi());
//		korpa.setPorudzbina(newKorpa.getPorudzbina());
//		
//		Korpa updatedKorpa = korpaRepository.save(korpa);
//		
//		return ResponseEntity.ok(updatedKorpa);
//	}
	
	//@PreAuthorize("hasRole('KUPAC')")
	@PutMapping("/api/auth/korpe/{id}")
	public ResponseEntity<Korpa> updateKorpa(@PathVariable("id") int id, @RequestBody DodajUKorpuDTO dodajUKorpuDTO) {		
		Korpa korpa = korpaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji korpa sa id: " + id));
		
		Proizvod proizvod = proizvodRepository.findById(dodajUKorpuDTO.getProizvodId()).orElse(null);
		ProizvodUKorpi p = new ProizvodUKorpi(dodajUKorpuDTO.getKolicina(), dodajUKorpuDTO.getKolicina() * proizvod.getCena(), korpa, proizvod);
		
		p = proizvodUKorpiService.addProizvodUKorpu(p);

		return new ResponseEntity<Korpa>(HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('KUPAC') or hasRole(Admin)")
	@DeleteMapping("/api/auth/korpe/{id}")
	public ResponseEntity<Korpa> deleteKorpa(@PathVariable("id") int id){
		if (!korpaRepository.existsById(id)) {
			return new ResponseEntity<Korpa>(HttpStatus.NO_CONTENT);
		}
		
		korpaRepository.deleteById(id);		
		return new ResponseEntity<Korpa>(HttpStatus.OK);
	}
}