package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer> {

	//Collection<Proizvodjac> findByNazivProizvodjacaIgnoreCase(String nazivProizvodjaca);

	Collection<Proizvodjac> findProizvodjacByNazivProizvodjacaIgnoreCase(String nazivProizvodjaca);
	

}
