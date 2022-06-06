package com.ecommerce.springboot.service;

import com.ecommerce.springboot.model.Porudzbina;
import com.ecommerce.springboot.payload.request.PorudzbinaDto;

public interface PorudzbinaService {
	Porudzbina addPPorudzbina(PorudzbinaDto porudzbina);
}
