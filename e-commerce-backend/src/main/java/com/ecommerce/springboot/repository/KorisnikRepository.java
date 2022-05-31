package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.springboot.model.EUloga;
import com.ecommerce.springboot.model.Korisnik;
import com.ecommerce.springboot.model.Uloga;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	Optional<Korisnik> findByEmail(String email);
	Boolean existsByEmail(String email);
	
}
