package com.ecommerce.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.payload.request.ProizvodRequest;

public interface ProizvodService {
	Proizvod addProizvod(Proizvod proizvod);

	Proizvod addProizvod(ProizvodRequest proizvodReq);
	
	Page<Proizvod> findPaginated(int pageNo, int pageSize);
}
