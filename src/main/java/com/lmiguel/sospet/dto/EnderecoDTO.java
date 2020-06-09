package com.lmiguel.sospet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.lmiguel.sospet.domain.Endereco;

public class EnderecoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	private Integer numero;
	
	private String complemento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cidade;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String estado;
	
	public EnderecoDTO() {
		
	}

	public EnderecoDTO(Endereco obj) {
		id = obj.getId();
		bairro = obj.getBairro();
		cep = obj.getCep();
		logradouro = obj.getLogradouro();
		numero = obj.getNumero();
		complemento = obj.getComplemento();
		cidade = obj.getCidade();
		estado = obj.getEstado();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
