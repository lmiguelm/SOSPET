package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lmiguel.sospet.domain.Comentario;

public class ComentarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String texto;
	private Date instante;
	private AutorDTO autor;
	private List<RespostaComentarioDTO> respostasComentario = new ArrayList<>();
	
	public ComentarioDTO() {
		
	}
	
	public ComentarioDTO(Comentario c, AutorDTO autor) {
		id = c.getId();
		texto = c.getTexto();
		instante = c.getInstante();
		this.autor = autor;
		respostasComentario = c.getRespostas().stream().map(r -> new RespostaComentarioDTO(r, r.getAutor())).collect(Collectors.toList());
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

	public List<RespostaComentarioDTO> getRespostas() {
		return respostasComentario;
	}

	public void setRc(List<RespostaComentarioDTO> respostasComentario) {
		this.respostasComentario = respostasComentario;
	}
}
