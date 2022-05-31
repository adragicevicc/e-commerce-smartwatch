package com.ecommerce.springboot.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
	UserDetails loadKorisnikByEmail(String email) throws UsernameNotFoundException;
}
