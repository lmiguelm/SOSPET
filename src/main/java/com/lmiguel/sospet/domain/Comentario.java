package com.lmiguel.sospet.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	private Date instante;
	
	
	// ASSOCIAÇÕES
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;	
	
	public Comentario() {
		
	}


	public Comentario(Long id, String texto, Date instante, Post post, Usuario autor) {
		super();
		this.id = id;
		this.texto = texto;
		this.instante = instante;
		this.autor = autor;
		this.post = post;
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


	public Usuario getAutor() {
		return autor;
	}


	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
