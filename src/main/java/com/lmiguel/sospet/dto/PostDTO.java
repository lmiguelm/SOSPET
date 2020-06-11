package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.Date;

import com.lmiguel.sospet.domain.Post;

public class PostDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private Date data;
	
	private String titulo;
	
	private String corpo;
	
	private String imagemUrl;
	
	// ASSOÇIAÇÕES
	private AutorDTO autor;
	
		
	public PostDTO() {
		
	}


	public PostDTO(Post post, AutorDTO autor) {
		this.id = post.getId();
		this.data = post.getData();
		this.titulo = post.getTitulo();
		this.corpo = post.getCorpo();
		this.autor = autor;
		this.setImagemUrl(post.getImagemUrl());
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


	public String getImagemUrl() {
		return imagemUrl;
	}


	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
}
