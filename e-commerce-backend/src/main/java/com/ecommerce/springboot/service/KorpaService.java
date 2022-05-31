package com.ecommerce.springboot.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.KorpaDto;

public interface KorpaService {
	
	Korpa addKorpa(Korpa korpa);

//	KorpaDto createDto(Korpa korpa);
}
