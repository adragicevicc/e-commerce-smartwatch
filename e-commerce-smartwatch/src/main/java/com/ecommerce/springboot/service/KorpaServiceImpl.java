package com.ecommerce.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.KorpaDto;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.KupacRepository;

@Service
public class KorpaServiceImpl implements KorpaService{

	@Autowired
	KupacRepository kupacRepository;
	
	@Autowired
	KorpaRepository korpaRepository;
	
	@Override
	public Korpa addKorpa(Korpa korpa) {
//		Kupac k = kupacRepository.getById(korpa.getKupac().getId_korisnik());
//		korpa.setKupac(k);
//		
//		return korpaRepository.save(korpa);
		return null;
	}

//	@Override
//	public KorpaDto createDto(Korpa korpa) {
//		return new KorpaDto(korpa);
//	}

}
