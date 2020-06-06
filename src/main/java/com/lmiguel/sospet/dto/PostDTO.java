package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lmiguel.sospet.domain.Post;

public class PostDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private Date data;
	
	private String titulo;
	
	private String corpo;
	
	// ASSOÇIAÇÕES
	private AutorDTO autor;
	
	private List<ComentarioDTO> comentarios = new ArrayList<>();
		
	public PostDTO() {
		
	}


	public PostDTO(Post post, AutorDTO autor, List<ComentarioDTO> c) {
		this.id = post.getId();
		this.data = post.getData();
		this.titulo = post.getTitulo();
		this.corpo = post.getCorpo();
		this.autor = autor;
		this.comentarios = c;
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


	public AutorDTO getAutor() {
		return autor;
	}


	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}
	
	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}

}
