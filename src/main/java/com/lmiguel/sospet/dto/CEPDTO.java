package com.lmiguel.sospet.dto;

import java.io.Serializable;

import viacep.ViaCEP;

public class CEPDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String bairro;
	private String cep;
	private String logradouro;
	private String cidade;	
	private String estado;	
	
	public CEPDTO() {
		
	}
	
	public CEPDTO(ViaCEP cep) {
		this.bairro = cep.getBairro();
		this.cep = cep.getCep();
		this.logradouro = cep.getLogradouro();
		this.cidade = cep.getLocalidade();
		this.estado = cep.getUf();
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
}
