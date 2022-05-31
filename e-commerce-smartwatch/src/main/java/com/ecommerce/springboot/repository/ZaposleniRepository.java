package com.ecommerce.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springboot.model.Zaposleni;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {

	//Collection<Zaposleni> findByImeIgnoreCase(String ime);

	Collection<Zaposleni> findZaposleniByImeIgnoreCase(String ime);

	Zaposleni findByEmail(String email);

}