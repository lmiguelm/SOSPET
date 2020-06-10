package com.lmiguel.sospet.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${deafult.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void emailConfirmacaoAnimal(Animal obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Animal obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUsuario().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Implementar ...");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.getUsuario().getNome());
		return null;
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+newPass);
		return sm;
	}
	
	protected String htmlFromTemplateNovaSenha(Usuario obj) {
		Context context = new Context();
		context.setVariable("usuario", obj);
		return templateEngine.process("email/novaSenha", context);
	}
	
	@Override
	public void sendNewPasswordHtmlEmail(Usuario usuario, String newPass) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageNewPassword(usuario, newPass);
			sendHtmlEmail(mm);
		} 
		catch (MessagingException e) {
			sendNewPasswordEmail(usuario, newPass);
		}
	}

	private MimeMessage prepareMimeMessageNewPassword(Usuario usuario, String newPass) throws MessagingException {
		usuario.setSenha(newPass);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(usuario.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Solicitação de nova senha");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateNovaSenha(usuario), true);
		return mimeMessage;
	}
}
