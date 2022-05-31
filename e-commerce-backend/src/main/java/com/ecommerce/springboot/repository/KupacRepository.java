package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Integer> {


	//Collection<Kupac> findByImeIgnoreCase(String ime);

	Collection<Kupac> findKupacByImeIgnoreCase(String ime);

	Kupac findByEmail(String email);
	

}

