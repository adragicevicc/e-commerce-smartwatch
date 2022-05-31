package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Grad;

public interface GradRepository extends JpaRepository<Grad, Integer> {

	//Collection<Grad> findByNazivGradaIgnoreCase(String nazivGrada);

	//Collection<Grad> findByNazivGradaContainingIgnoreCase(String nazivGrada);

	Collection<Grad> findGradByNazivGradaIgnoreCase(String nazivGrada);
	

}
