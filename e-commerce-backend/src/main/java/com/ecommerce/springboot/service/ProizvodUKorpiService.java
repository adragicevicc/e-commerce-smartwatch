package com.ecommerce.springboot.service;

import com.ecommerce.springboot.model.ProizvodUKorpi;

public interface ProizvodUKorpiService {

	ProizvodUKorpi addProizvodUKorpu(ProizvodUKorpi proizvodUKorpi);
	ProizvodUKorpi findById(Integer id);
}
