package com.returnordermanag.paymentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanag.paymentService.exception.CardNotFoundException;
import com.returnordermanag.paymentService.service.CardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CardController {
	@Autowired
	private CardService cardService;

	@GetMapping("/card/{cardNumber}/{charge}")
	@ResponseStatus(code = HttpStatus.OK)
	public double getBalance(@PathVariable long cardNumber, @PathVariable double charge) throws CardNotFoundException {
		try {
			log.info("Inside Payment Service Controller");
			return cardService.processPayment(cardNumber, charge);
		} catch (CardNotFoundException ex) {
			throw new CardNotFoundException();
		}

	}
}
