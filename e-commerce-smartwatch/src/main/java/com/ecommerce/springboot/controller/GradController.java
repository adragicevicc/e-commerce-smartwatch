package com.ecommerce.springboot.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Grad;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.repository.GradRepository;

@RestController
public class GradController {
	
	@Autowired
	private GradRepository gradRepository;
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/gradovi")
	public List<Grad> getAllGradovi() {
		return gradRepository.findAll();
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/gradovi/{id}")
	public ResponseEntity<Grad> getGradById(@PathVariable("id") int id) {
		Grad grad = gradRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji grad sa id: " + id));
		return ResponseEntity.ok(grad);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@GetMapping("/gradNaziv")
	public Collection<Grad> getGradByNazivGrada(@RequestParam(required=true) String nazivGrada) {
		return gradRepository.findGradByNazivGradaIgnoreCase(nazivGrada);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@PostMapping("/gradovi")
	public ResponseEntity<Grad> createGrad(@RequestBody Grad grad) {
		if(!gradRepository.existsById(grad.getId_grad())) {
			gradRepository.save(grad);
			return new ResponseEntity<Grad>(HttpStatus.OK);
		}
		return new ResponseEntity<Grad>(HttpStatus.CONFLICT);
	}
	
	@PreAuthorize("hasRole('ZAPOSLENI') or hasRole('KUPAC') or hasRole('ADMIN')")
	@PutMapping("/gradovi/{id}")
	public ResponseEntity<Grad> updateGrad(@PathVariable("id") int id, @RequestBody Grad newGrad) {		
		Grad grad = gradRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji grad sa id: " + id));
		grad.setNazivGrada(newGrad.getNazivGrada());
		grad.setPostanski_broj(newGrad.getPostanski_broj());
		grad.setAdrese(newGrad.getAdrese());
		
		Grad updatedGrad = gradRepository.save(grad);
		
		return ResponseEntity.ok(updatedGrad);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/gradovi/{id}")
	public ResponseEntity<Grad> deleteGrad(@PathVariable("id") int id){
		if (!gradRepository.existsById(id)) {
			return new ResponseEntity<Grad>(HttpStatus.NO_CONTENT);
		}
		
		gradRepository.deleteById(id);		
		return new ResponseEntity<Grad>(HttpStatus.OK);
	}

}
