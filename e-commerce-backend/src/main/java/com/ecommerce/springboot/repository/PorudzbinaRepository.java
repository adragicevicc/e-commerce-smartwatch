package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {


	//List<Porudzbina> findByStatusIgnoreCase(String status);

	Collection<Porudzbina> findPorudzbinaByStatusIgnoreCase(String status);
	

}

