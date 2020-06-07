package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmiguel.sospet.domain.Comentario;

public class ComentarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String texto;
	private Date instante;
	private AutorDTO autor;
	
	@JsonIgnore
	private Long postId;
	
	public ComentarioDTO() {
		
	}
	
	public ComentarioDTO(Comentario c, AutorDTO autor) {
		id = c.getId();
		texto = c.getTexto();
		instante = c.getInstante();
		setPostId(c.getPost().getId());
		this.autor = autor;
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

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

}
