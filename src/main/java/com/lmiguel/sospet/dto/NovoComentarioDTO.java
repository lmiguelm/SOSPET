package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class NovoComentarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private Date data;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String texto;
	
	// ASSOÇIAÇÕES
	
	private Long autorId;
	private Long postId;
	
	
	public NovoComentarioDTO() {
		
	}

	
	public NovoComentarioDTO(Long id, Date data, String titulo, String texto, Long postId, Long autorId) {
		super();
		this.id = id;
		this.data = data;
		this.texto = texto;
		this.setPostId(postId);
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

	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Long getAutorId() {
		return autorId;
	}


	public void setAutor(Long autorId) {
		this.autorId = autorId;
	}


	public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
	}
}
