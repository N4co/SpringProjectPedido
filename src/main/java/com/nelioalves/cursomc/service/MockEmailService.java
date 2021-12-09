package com.nelioalves.cursomc.service;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado....");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("envio de HTML...");
		LOG.info(msg.toString());
		LOG.info("Email enviado....");
	}
	}

	
	
	
	

