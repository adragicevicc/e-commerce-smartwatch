package com.ecommerce.springboot.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.springboot.model.Korisnik;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	@JsonIgnore
	private String lozinka;
	private Collection<? extends GrantedAuthority> authorities;
	public UserDetailsImpl(int id, String email, String lozinka,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.authorities = authorities;
	}
	public static UserDetailsImpl build(Korisnik korisnik) {
		List<GrantedAuthority> authorities = korisnik.getUloge().stream()
				.map(uloga -> new SimpleGrantedAuthority(uloga.getName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				korisnik.getId_korisnik(),
				korisnik.getEmail(),
				korisnik.getLozinka(), 
				authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return lozinka;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
