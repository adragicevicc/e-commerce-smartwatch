package com.ecommerce.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.ProizvodUKorpi;

public interface ProizvodUKorpiRepository extends JpaRepository<ProizvodUKorpi, Integer> {
	
}