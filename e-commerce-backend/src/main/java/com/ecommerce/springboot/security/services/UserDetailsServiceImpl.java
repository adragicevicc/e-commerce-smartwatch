package com.ecommerce.springboot.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.springboot.model.Korisnik;
import com.ecommerce.springboot.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findByEmail(email)
		        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
		    return UserDetailsImpl.build(korisnik);
	}
	
	

	
	

}
