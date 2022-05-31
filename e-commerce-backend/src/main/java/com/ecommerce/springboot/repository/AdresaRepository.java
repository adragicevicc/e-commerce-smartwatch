package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Adresa;

public interface AdresaRepository extends JpaRepository<Adresa, Integer> {

	//Collection<Adresa> findByNazivUliceIgnoreCase(String nazivUlice);

	Collection<Adresa> findAdresaByNazivUliceIgnoreCase(String nazivUlice);
	
}

