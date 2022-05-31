package com.ecommerce.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.model.PaymentRequest;
import com.ecommerce.springboot.service.PaymentService;
import com.stripe.exception.StripeException;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	
	@PostMapping("/api/auth/payment")
	public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws 
	StripeException {
		String chargeId=paymentService.charge(request);
		return chargeId!=null? new ResponseEntity<String>(chargeId, HttpStatus.OK):
			new ResponseEntity<String>("Molimo proverite unete podatke", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public String handleError(StripeException ex) {
		return ex.getMessage();
	}
}
