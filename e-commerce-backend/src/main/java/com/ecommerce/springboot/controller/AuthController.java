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
	
	/*@PostMapping("/signup/zaposleni")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupZaposleniRequest signUpRequest) {
		if (korisnikRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
	
		
		Korisnik korisnik = new Korisnik(signUpRequest.getIme(), signUpRequest.getPrezime()
				 ,signUpRequest.getTip(), signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getLozinka()));
		Set<String> strRoles = signUpRequest.getUloga();
		System.out.println(signUpRequest.getUloga());
		Set<Uloga> uloge = new HashSet<>();
		/*if (strRoles == null) {
			Uloga zapUloga = ulogaRepository.findByName(EUloga.ROLE_ZAPOSLENI)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(zapUloga);
			
			Zaposleni zaposleni = new Zaposleni(korisnik, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
			zaposleniRepository.save(zaposleni);			
		} else {			
			strRoles.forEach(uloga -> {
				switch (uloga) {
				case "ROLE_ZAPOSLENI":
					Uloga zaposleniUloga = ulogaRepository.findByName(EUloga.ROLE_ZAPOSLENI)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					System.out.println(zaposleniUloga.getName());/////
					uloge.add(zaposleniUloga);
					Zaposleni zaposleni = new Zaposleni(korisnik, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
					zaposleniRepository.save(zaposleni);
					break;
				case "ROLE_ADMIN":
					Uloga adminUloga = ulogaRepository.findByName(EUloga.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(adminUloga);
					
					Zaposleni admin = new Zaposleni(korisnik, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
					zaposleniRepository.save(admin);
					break;
				default:
					Uloga zapUloga = ulogaRepository.findByName(EUloga.ROLE_ZAPOSLENI)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(zapUloga);
					
					Zaposleni defzaposleni = new Zaposleni(korisnik, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
					zaposleniRepository.save(defzaposleni);
				}
			});
		//}
		korisnik.setUloge(uloge);
		//korisnikRepository.save(korisnik);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@PostMapping("/signup/kupac")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupKupacRequest signUpRequest) {
		if (korisnikRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
	
		
		Korisnik korisnik = new Korisnik(signUpRequest.getIme(), signUpRequest.getPrezime()
				 ,signUpRequest.getTip(), signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getLozinka()));
		Set<String> strRoles = signUpRequest.getUloga();
		Set<Uloga> uloge = new HashSet<>();
		if (strRoles == null) {
			Uloga kupacUloga = ulogaRepository.findByName(EUloga.ROLE_KUPAC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(kupacUloga);
			
			Adresa adresa=new Adresa(signUpRequest.getAdresa().getGrad(), signUpRequest.getAdresa().getNazivUlice(), signUpRequest.getAdresa().getBroj());
			
			Kupac kupac = new Kupac(korisnik, adresa, signUpRequest.getBroj_telefona());
			kupacRepository.save(kupac);
		} else {
			Uloga kupacUloga = ulogaRepository.findByName(EUloga.ROLE_KUPAC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(kupacUloga);
					
			Kupac newKupac = new Kupac(korisnik, signUpRequest.getAdresa(), signUpRequest.getBroj_telefona());
			kupacRepository.save(newKupac);
				
		}
		korisnik.setUloge(uloge);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}*/
	
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
		Set<String> strRoles = signUpRequest.getUloga();
		Set<Uloga> uloge = new HashSet<>();
		if (strRoles == null) {
			Uloga kupac = ulogaRepository.findByName(EUloga.ROLE_KUPAC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			uloge.add(kupac);
			
			Adresa newadresa=new Adresa(signUpRequest.getNazivUlice(), signUpRequest.getBroj(), signUpRequest.getGrad());
			
			Korpa newkorpa = new Korpa();
		
			Kupac newkupac = new Kupac(korisnik, uloge, newadresa, signUpRequest.getBroj_telefona(), newkorpa);
			kupacRepository.save(newkupac);
			
		} else {
			strRoles.forEach(uloga -> {
				switch (uloga) {
				case "ROLE_ZAPOSLENI":
					Uloga zaposleniUloga = ulogaRepository.findByName(EUloga.ROLE_ZAPOSLENI)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(zaposleniUloga);
					
					Zaposleni zaposleni = new Zaposleni(korisnik, uloge, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
					System.out.println(uloge.size());
					zaposleniRepository.save(zaposleni);
					break;
				case "ROLE_ADMIN":
					Uloga adminUloga = ulogaRepository.findByName(EUloga.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					uloge.add(adminUloga);
					
					Zaposleni admin = new Zaposleni(korisnik, uloge, signUpRequest.getDatum_zaposlenja(), signUpRequest.getPozicija());
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
				
			});
		}
		//korisnik.setUloge(uloge);
		//korisnikRepository.save(korisnik);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
