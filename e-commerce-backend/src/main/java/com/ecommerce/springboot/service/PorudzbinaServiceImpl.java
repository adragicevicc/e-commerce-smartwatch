package com.ecommerce.springboot.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.Kupac;
import com.ecommerce.springboot.model.Porudzbina;
import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.ProizvodUKorpi;
import com.ecommerce.springboot.payload.request.PorudzbinaDto;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.KupacRepository;
import com.ecommerce.springboot.repository.PorudzbinaRepository;
import com.ecommerce.springboot.repository.ProizvodRepository;

@Service
public class PorudzbinaServiceImpl implements PorudzbinaService{

	@Autowired
	PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	KorpaRepository korpaRepository;
	
	@Autowired
	KupacRepository kupacRepository;
	
	@Autowired
	ProizvodRepository proizvodRepository;

	@Override
	public Porudzbina addPPorudzbina(PorudzbinaDto porudzbina) {
		Korpa k = korpaRepository.getById(porudzbina.getId_korpa());
		
		Kupac kup = kupacRepository.getById(porudzbina.getId_kupac());
		
		Porudzbina p = new Porudzbina();
		p.setKorpa(k);
		p.setUkupan_iznos(k.getUkupan_iznos_korpe());
		p.setKupac(kup);
		
		for(ProizvodUKorpi prUKorpi : k.getProizvodi_u_korpi()) {
			Proizvod proizvod = proizvodRepository.getById(prUKorpi.getProizvod().getId_proizvod());
			p.getProizvodi().add(proizvod);
			
		}
		
		/*for(ProizvodUKorpi prUKorpi : k.getProizvodi_u_korpi()) {
			p.getProizvodi().add(prUKorpi.getProizvod().getNazivProizvoda());
			System.out.println(p.getProizvodi().toString());
			
		}*/
		
					
		
		LocalDate localDate = LocalDate.now();
    
		p.setDatum_porudzbine(localDate);
		
		return porudzbinaRepository.save(p);
		
	}
	
}
