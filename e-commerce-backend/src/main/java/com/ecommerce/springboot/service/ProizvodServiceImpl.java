package com.ecommerce.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.model.Proizvodjac;
import com.ecommerce.springboot.payload.request.ProizvodRequest;
import com.ecommerce.springboot.repository.ProizvodRepository;
import com.ecommerce.springboot.repository.ProizvodjacRepository;

@Service
public class ProizvodServiceImpl implements ProizvodService{

	@Autowired
	ProizvodjacRepository proizvodjacRepository;
	
	@Autowired
	ProizvodRepository proizvodRepository;
	
	@Override
	public Proizvod addProizvod(Proizvod proizvod) {
		Proizvodjac p = proizvodjacRepository.getById(proizvod.getProizvodjac().getId_proizvodjac());
		
		proizvod.setProizvodjac(p);
		
		return proizvodRepository.save(proizvod);
	}

	@Override
	public Proizvod addProizvod(ProizvodRequest proizvodReq) {
		Proizvod proizvod = new Proizvod();
		proizvod.setNazivProizvoda(proizvodReq.getNazivProizvoda());
		proizvod.setCena(proizvodReq.getCena());
		proizvod.setKarakteristike(proizvodReq.getKarakteristike());
		proizvod.setDostupna_kolicina(proizvodReq.getDostupna_kolicina());
		proizvod.setUrl(proizvodReq.getUrl());
		
		Proizvodjac proizvodjac = new Proizvodjac();
		proizvodjac.setNazivProizvodjaca(proizvodReq.getNazivProizvodjaca());
		proizvodjac.getProizvod().add(proizvod);
		
		return this.proizvodRepository.save(proizvod);
	}
	
}
