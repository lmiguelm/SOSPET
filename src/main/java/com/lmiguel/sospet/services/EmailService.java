package com.lmiguel.sospet.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.Usuario;

public interface EmailService {

	public void emailConfirmacaoAnimal(Animal obj);
	
	public void sendEmail(SimpleMailMessage msg);
	
	public void sendNewPasswordEmail(Usuario usuario, String newPass);
	
	public void sendNewPasswordHtmlEmail(Usuario usuario, String newPass);
	
	public void sendHtmlEmail(MimeMessage msg);
}
