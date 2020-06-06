package com.lmiguel.sospet.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lmiguel.sospet.domain.enums.IdadeAnimal;
import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;

@Entity
@JsonTypeName("animalAchado")
public class AnimalAchado extends Animal {

	private static final long serialVersionUID = 1L;
	
	
	// ATRIBUTOS
	
	private Date dataEncontrado;
	
	
	public AnimalAchado() {
		
	}

	public AnimalAchado(Long id, TipoAnimal tipo, SexoAnimal sexo, PorteAnimal porte, StatusAnimal status, IdadeAnimal idade, Usuario usuario, Date dataEncontrado) {
		super(id, tipo, sexo, porte, status, idade, usuario);
		this.dataEncontrado = dataEncontrado;
	}

	public Date getDataEncontrado() {
		return dataEncontrado;
	}

	public void setDataEncontrado(Date dataEncontrado) {
		this.dataEncontrado = dataEncontrado;
	}	
}
