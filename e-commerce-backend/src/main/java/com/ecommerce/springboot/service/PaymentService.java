package com.ecommerce.springboot.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.springboot.model.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class PaymentService {
	
	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;
	
	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}
	
	public String charge(PaymentRequest chargeRequest) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", chargeRequest.getAmount());
		chargeParams.put("currency", PaymentRequest.Currency.USD);
		chargeParams.put("source", chargeRequest.getToken().getId());
		
		Charge charge = Charge.create(chargeParams);
		
		System.out.println("IZVRSENA UPLATA, ID: " + charge.getId() + " IZNOS: " + charge.getAmount());
		return charge.getId();
	}
}
