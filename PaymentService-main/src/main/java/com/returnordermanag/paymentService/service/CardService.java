package com.returnordermanag.paymentService.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnordermanag.paymentService.dao.CardRepo;
import com.returnordermanag.paymentService.exception.CardNotFoundException;
import com.returnordermanag.paymentService.model.CreditCard;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardService {

	@Autowired
	CardRepo cardRepo;

	@Transactional
	public double processPayment(long cardNumber, double charge) throws NoSuchElementException, CardNotFoundException {
		
		log.info("Inside processPayment method");
		CreditCard card = cardRepo.findByCardNumber(cardNumber);
		log.info("Processing Payment");
		double balance = card.getCardLimit() - charge;
		if (balance > 0) {
			card.setCardLimit(balance);
			cardRepo.save(card);
			return balance;

		} else {
			return -1;
		}
	}
}
