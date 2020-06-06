package com.lmiguel.sospet.dto;

import java.io.Serializable;

import com.lmiguel.sospet.domain.Usuario;

public class AutorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private String email;
	
	public AutorDTO() {
		
	}
	
	public AutorDTO (Usuario obj) {
		email = obj.getEmail();
		nome = obj.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
