package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class NovoPostDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private Date data;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String titulo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String corpo;
	
	// ASSOÇIAÇÕES
	
	private Long autorId;
	
	
	public NovoPostDTO() {
		
	}

	
	public NovoPostDTO(Long id, Date data, String titulo, String corpo, Long autorId) {
		super();
		this.id = id;
		this.data = data;
		this.titulo = titulo;
		this.corpo = corpo;
		this.autorId = autorId;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getCorpo() {
		return corpo;
	}


	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}


	public Long getAutorId() {
		return autorId;
	}


	public void setAutor(Long autorId) {
		this.autorId = autorId;
	}
}
