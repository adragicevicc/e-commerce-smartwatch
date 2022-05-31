package com.ecommerce.springboot.services;

/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.springboot.model.Korisnik;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.repository.KupacRepository;

public class kupacServiceImpl implements kupacService {

	@Autowired
	private KupacRepository kupacRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UlogaService ulogaService;
	
	@Override
	public Kupac findById(Integer id) {
		 return kupacRepository.findById(id).orElseGet(null);
	}

	@Override
	public Kupac findByEmail(String email) {
		return kupacRepository.findByEmail(email);
	}

	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public Kupac save(Korisnik korisnik) {
		Kupac kupac = new Kupac(korisnik);
		
		
		kupac.setAdresa(si);
		
		
		return this.kupacRepository.save(kupac);
		
	}

}*/
