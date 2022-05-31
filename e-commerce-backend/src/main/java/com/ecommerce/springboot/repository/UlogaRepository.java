package com.ecommerce.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.springboot.model.EUloga;
import com.ecommerce.springboot.model.Uloga;
@Repository
public interface UlogaRepository extends JpaRepository<Uloga, Long>{
	
	Optional<Uloga> findByName(EUloga name);

	//Uloga getOne(Integer id);

}
