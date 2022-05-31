package com.ecommerce.springboot.service;

import com.ecommerce.springboot.model.Proizvod;
import com.ecommerce.springboot.payload.request.ProizvodRequest;

public interface ProizvodService {
	Proizvod addProizvod(Proizvod proizvod);

	Proizvod addProizvod(ProizvodRequest proizvodReq);
}
