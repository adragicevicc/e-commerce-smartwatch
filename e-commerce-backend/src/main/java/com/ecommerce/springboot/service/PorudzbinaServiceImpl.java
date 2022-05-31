package com.ecommerce.springboot.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.model.Porudzbina;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.KupacRepository;
import com.ecommerce.springboot.repository.PorudzbinaRepository;

@Service
public class PorudzbinaServiceImpl implements PorudzbinaService{

	@Autowired
	PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	KorpaRepository korpaRepository;
	
	@Autowired
	KupacRepository kupacRepository;

	@Override
	public Porudzbina addPPorudzbina(Porudzbina porudzbina) {
		Korpa k = korpaRepository.getById(porudzbina.getKorpa().getId_korpa());
		
		porudzbina.setKorpa(k);
		porudzbina.setUkupan_iznos(k.getUkupan_iznos_korpe());
		
		LocalDate localDate = LocalDate.now();
    
		porudzbina.setDatum_porudzbine(localDate);
		
		return porudzbinaRepository.save(porudzbina);
		
	}
	
}
