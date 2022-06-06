package com.ecommerce.springboot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.model.Adresa;
import com.ecommerce.springboot.model.EUloga;
import com.ecommerce.springboot.model.Korisnik;
import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.model.Uloga;
import com.ecommerce.springboot.model.Zaposleni;
import com.ecommerce.springboot.payload.request.LoginRequest;
import com.ecommerce.springboot.payload.request.SignupKupacRequest;
import com.ecommerce.springboot.payload.request.SignupRequest;
import com.ecommerce.springboot.payload.request.SignupZaposleniRequest;
import com.ecommerce.springboot.payload.response.JwtResponse;
import com.ecommerce.springboot.payload.response.MessageResponse;
import com.ecommerce.springboot.repository.AdresaRepository;
import com.ecommerce.springboot.repository.KorisnikRepository;
import com.ecommerce.springboot.repository.KupacRepository;
import com.ecommerce.springboot.repository.UlogaRepository;
import com.ecommerce.springboot.repository.ZaposleniRepository;
import com.ecommerce.springboot.security.jwt.JwtUtils;
import com.ecommerce.springboot.security.services.UserDetailsImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	KupacRepository kupacRepository;
	
	@Autowired
	ZaposleniRepository zaposleniRepository;
	
	@Autowired
	AdresaRepository adresaRepository;
	
	@Autowired
	UlogaRepository ulogaRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getLozinka()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println(userDetails.getAuthorities().stream().findFirst());
		
		Korpa korpa = null;
		if (roles.get(0) == "ROLE_KUPAC") {
			Kupac k = kupacRepository.findById(userDetails.getId()).orElse(null);
			korpa = k.getKorpa();
			return ResponseEntity.ok(new JwtResponse(jwt, 
					 userDetails.getId(),
					 userDetails.getEmail(), 
					 roles, korpa.getId_korpa()));
		}
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(),
				 userDetails.getEmail(), 
				 roles, -1));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (korisnikRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
				
		Korisnik korisnik = new Korisnik(signUpRequest.getIme(), signUpRequest.getPrezime()
				 ,signUpRequest.getTip(), signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getLozinka()));
		String strRoles = signUpRequest.getUloga();
		Set<Uloga> uloge = new HashSet<>();
		/*if (strRoles == null) {
			/*Uloga kupac = ulogaRepository.findByName(EUloga.ROLE_KUPAC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(kupac);
			
			Adresa newadresa=new Adresa(signUpRequest.getNazivUlice(), signUpRequest.getBroj(), signUpRequest.getGrad());
			
			Korpa newkorpa = new Korpa();
		
			Kupac newkupac = new Kupac(korisnik, uloge, newadresa, signUpRequest.getBroj_telefona(), newkorpa);
			kupacRepository.save(newkupac);
			
			Uloga adminnnUloga = ulogaRepository.findByName(EUloga.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(adminnnUloga);
			
			Zaposleni adminnn = new Zaposleni(korisnik, uloge,signUpRequest.getPozicija());
			//korisnik.setUloge(uloge);
			zaposleniRepository.save(adminnn);*/
	
			
		//} else {
			
				switch (strRoles) {
				case "ROLE_ADMIN":
					Uloga adminUloga = ulogaRepository.findByName(EUloga.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(adminUloga);
					
					Zaposleni admin = new Zaposleni(korisnik, uloge, signUpRequest.getPozicija());
					//korisnik.setUloge(uloge);
					zaposleniRepository.save(admin);
					break;
				default:
					Uloga kupacUloga = ulogaRepository.findByName(EUloga.ROLE_KUPAC)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(kupacUloga);
					
					Adresa adresa=new Adresa(signUpRequest.getNazivUlice(), signUpRequest.getBroj(), signUpRequest.getGrad());
					
					Korpa newkorpa = new Korpa();
					
					Kupac kupac = new Kupac(korisnik, uloge, adresa, signUpRequest.getBroj_telefona(), newkorpa);
					//korisnik.setUloge(uloge);
					kupacRepository.save(kupac);
				}
				
			
		//}
		//korisnik.setUloge(uloge);
		//korisnikRepository.save(korisnik);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
