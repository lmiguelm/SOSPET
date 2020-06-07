package com.lmiguel.sospet.dto;

import java.io.Serializable;

import com.lmiguel.sospet.domain.Cidade;

public class NovaCidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long estadoId;

	public NovaCidadeDTO() {
	}

	public NovaCidadeDTO(Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
		estadoId = obj.getEstado().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
}