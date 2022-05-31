package com.ecommerce.springboot.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.Korpa;
import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.ProizvodUKorpi;
import com.ecommerce.springboot.repository.KorpaRepository;
import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.repository.ProizvodUKorpiRepository;

@Service
public class ProizvodUKorpiServiceImpl implements ProizvodUKorpiService{
	
	@Autowired
	private ProizvodUKorpiRepository proizvodUKorpiRepository;
	
	@Autowired
	ProizvodRepository proizvodRepository;
	
	@Autowired
	KorpaRepository korpaRepository;

	@Override
	public ProizvodUKorpi addProizvodUKorpu(ProizvodUKorpi proizvodUKorpi) {
		
		Proizvod proizvod = proizvodRepository.getById(proizvodUKorpi.getProizvod().getId_proizvod());
		Korpa k = korpaRepository.getById(proizvodUKorpi.getKorpa().getId_korpa());
		
		for (ProizvodUKorpi prUKorpi : k.getProizvodi_u_korpi()) {
			if (prUKorpi.getProizvod().getId_proizvod() == proizvod.getId_proizvod()) {
				prUKorpi.setKolicina(prUKorpi.getKolicina() + proizvodUKorpi.getKolicina());
				prUKorpi.getProizvod().setDostupna_kolicina(prUKorpi.getProizvod().getDostupna_kolicina() - proizvodUKorpi.getKolicina());
				prUKorpi.setCena_za_kolicinu(prUKorpi.getKolicina()*prUKorpi.getProizvod().getCena());
				k.setUkupan_iznos_korpe(k.getUkupan_iznos_korpe() + proizvodUKorpi.getCena_za_kolicinu());
				
				return proizvodUKorpiRepository.save(prUKorpi);

			}
		}	
		
		k.setBroj_stavki(k.getBroj_stavki() + 1);
		k.setUkupan_iznos_korpe(k.getUkupan_iznos_korpe() + proizvod.getCena());
		
//		proizvodUKorpi.setProizvod(proizvod);
//		proizvodUKorpi.setKorpa(k);
//		proizvodUKorpi.setCena_za_kolicinu(proizvod.getCena());
		
		proizvodUKorpi.getProizvod().setDostupna_kolicina(proizvodUKorpi.getProizvod().getDostupna_kolicina() - 1);
		return proizvodUKorpiRepository.save(proizvodUKorpi);

	}

	@Override
	public ProizvodUKorpi findById(Integer id) {
		return proizvodUKorpiRepository.findById(id).orElse(null);
	}
	
	

}
