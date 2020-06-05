package com.lmiguel.sospet.domain;

import java.io.Serializable;
import java.util.Date;



public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	
	private Long id;
	
	private String texto;
	
	private Date date;
	
	
	// ASSOÇIAÇÕES
	
	private Usuario autor;
	
	
	public Comentario() {
		
	}


	public Comentario(Long id, String texto, Date date, Usuario autor) {
		super();
		this.id = id;
		this.texto = texto;
		this.date = date;
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Usuario getAutor() {
		return autor;
	}


	public void setAutor(Usuario autor) {
		this.autor = autor;
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
