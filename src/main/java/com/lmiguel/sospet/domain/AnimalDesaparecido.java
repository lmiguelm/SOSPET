package com.lmiguel.sospet.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;
import com.lmiguel.sospet.domain.enums.TipoPelagem;

@Entity
@JsonTypeName("animalDesaparecido")
public class AnimalDesaparecido extends Animal {

	private static final long serialVersionUID = 1L;
	
	
	// ATRIBUTOS
	
	private String nome;
	
	private Date ultimaVezVisto;
	
	private String raca;
	
	private Integer pelagem;
	
	private Boolean castrado;
	
	
	public AnimalDesaparecido() {
		
	}

	public AnimalDesaparecido(Long id, TipoAnimal tipo, SexoAnimal sexo, PorteAnimal porte, StatusAnimal status, Usuario usuario, String nome, Date ultimaVezVisto, String raca, TipoPelagem pelagem, Boolean castrado) {
		super(id, tipo, sexo, porte, status, usuario);
		this.nome = nome;
		this.ultimaVezVisto = ultimaVezVisto;
		this.raca = raca;
		this.pelagem = (pelagem == null) ? null : pelagem.getCode();
		this.castrado = castrado;
	}





	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getUltimaVezVisto() {
		return ultimaVezVisto;
	}


	public void setUltimaVezVisto(Date ultimaVezVisto) {
		this.ultimaVezVisto = ultimaVezVisto;
	}


	public String getRaca() {
		return raca;
	}


	public void setRaca(String raca) {
		this.raca = raca;
	}


	public TipoPelagem getPelagem() {
		return TipoPelagem.toEnum(pelagem);
	}


	public void setPelagem(TipoPelagem pelagem) {
		this.pelagem = pelagem.getCode();
	}


	public Boolean getCastrado() {
		return castrado;
	}


	public void setCastrado(Boolean castrado) {
		this.castrado = castrado;
	}
	
	

}
