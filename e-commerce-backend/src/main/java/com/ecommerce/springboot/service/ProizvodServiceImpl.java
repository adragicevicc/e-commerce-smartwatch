package com.ecommerce.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		
		System.out.println("naziv proizvoda: "+ proizvodReq.getNazivProizvoda());
		System.out.println("naziv proizvoda: "+ proizvodReq.getCena());
		System.out.println("naziv proizvoda: "+ proizvodReq.getKarakteristike());
		System.out.println("naziv proizvoda: "+ proizvodReq.getDostupna_kolicina());
		
		Proizvodjac proizvodjac = proizvodjacRepository.findProizvodjacByNazivProizvodjacaIgnoreCase(proizvodReq.getNazivProizvodjaca());
		System.out.println("NAZIVVV: " + proizvodReq.getNazivProizvodjaca());
		if(proizvodjac == null) {
			proizvodjac = new Proizvodjac();
			proizvodjac.setNazivProizvodjaca(proizvodReq.getNazivProizvodjaca());
			System.out.println("NE POSTOJI PA SE PRAVI");
		}
		
		proizvod.setProizvodjac(proizvodjac);
		System.out.println("POSTOJI ILI JE NAPRAVLJEN");
		//proizvodjac.getProizvod().add(proizvod);
		
		return this.proizvodRepository.save(proizvod);
	}

	@Override
	public Page<Proizvod> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		System.out.println("PageNumber: " + paging.getPageNumber() + " PageSize: " + paging.getPageSize());
        Page<Proizvod> pagedResult = proizvodRepository.findAll(paging);
        System.out.println(pagedResult.toString());
        
        return pagedResult;
	}
	
}
