package com.ecommerce.springboot.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.ecommerce.springboot.payload.request.ProizvodRequest;
import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.service.ProizvodService;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class ProizvodController {
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	ProizvodService proizvodService;
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodi/{pageNo}/{pageSize}")
	public Page<Proizvod> getAllProizvodi(@PathVariable int pageNo, @PathVariable int pageSize) {
		return proizvodService.findPaginated(pageNo, pageSize);
	}
	
	@GetMapping("/api/auth/proizvodi")
	public List<Proizvod> getAllProizvodi() {
		return proizvodRepository.findAll();
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodi/{id}")
	public ResponseEntity<Proizvod> getProizvodById(@PathVariable("id") int id) {
		Proizvod proizvod = proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji proizvod sa id: " + id));
		return ResponseEntity.ok(proizvod);
	}
	
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/api/auth/proizvodNaziv")
	public Collection<Proizvod> getProizvodByNazivProizvoda(@RequestParam(required=true) String nazivProizvoda) {
		return proizvodRepository.findProizvodByNazivProizvodaIgnoreCase(nazivProizvoda);
	}
	
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@PostMapping("/api/auth/proizvodi")
	public ResponseEntity<Proizvod> createProizvod(@RequestBody ProizvodRequest proizvodReq) {
		Proizvod p = this.proizvodService.addProizvod(proizvodReq);
		if (p == null) {
            return new ResponseEntity<Proizvod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@PutMapping("/api/auth/proizvodi/{id}")
	public ResponseEntity<Proizvod> updateProizvod(@PathVariable("id") int id, @RequestBody Proizvod newProizvod) {		
		Proizvod proizvod = proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji porudzbina sa id: " + id));
		proizvod.setNazivProizvoda(newProizvod.getNazivProizvoda());
		proizvod.setKarakteristike(newProizvod.getKarakteristike());
		proizvod.setCena(newProizvod.getCena());
		proizvod.setDostupna_kolicina(newProizvod.getDostupna_kolicina());
		proizvod.setProizvodjac(newProizvod.getProizvodjac());
		
		Proizvod updatedProizvod = proizvodRepository.save(proizvod);
		
		return ResponseEntity.ok(updatedProizvod);
	}
	
	//@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	@DeleteMapping("/api/auth/proizvodi/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable("id") int id){
		if (!proizvodRepository.existsById(id)) {
			return new ResponseEntity<Proizvod>(HttpStatus.NO_CONTENT);
		}
		
		proizvodRepository.deleteById(id);		
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}

}
