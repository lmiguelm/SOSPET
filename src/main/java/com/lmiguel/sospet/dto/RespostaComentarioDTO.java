package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.Date;

import com.lmiguel.sospet.domain.RespostaComentario;
import com.lmiguel.sospet.domain.Usuario;

public class RespostaComentarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String texto;
	private Date instante;
	private AutorDTO autor;
	
	public RespostaComentarioDTO() {
		
	}
	
	public RespostaComentarioDTO(RespostaComentario r, Usuario autor) {
		id = r.getId();
		texto = r.getTexto();
		instante = r.getInstante();
		this.autor = new AutorDTO(autor);	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public AutorDTO getAutor() {
		return autor;
	}

	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}
}
