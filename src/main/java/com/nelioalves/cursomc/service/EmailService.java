package com.nelioalves.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {

	
	void sendOrderConfirmationEmail(Pedido obj);
	
	
	void sendEmail(SimpleMailMessage msg);
}
