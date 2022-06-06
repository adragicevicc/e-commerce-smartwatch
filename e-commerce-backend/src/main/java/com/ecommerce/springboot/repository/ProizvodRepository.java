package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;

import com.ecommerce.springboot.model.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{


	//Collection<Proizvod> findByNazivProizvodaIgnoreCase(String nazivProizvoda);

	Collection<Proizvod> findProizvodByNazivProizvodaIgnoreCase(String nazivProizvoda);

	

}

