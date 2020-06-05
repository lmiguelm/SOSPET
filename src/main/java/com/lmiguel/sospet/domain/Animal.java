package com.lmiguel.sospet.domain;

import java.io.Serializable;

import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;

public abstract class Animal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	
	private Long id;
	
	private Integer tipo;
	
	private Integer sexo;
	
	private Integer porte;
	
	private Integer status;
	
	
	// ASSOCIAÇÕES
	
	private Usuario usuario;
	
	
	public Animal() {
		
	}


	public Animal(Long id, TipoAnimal tipo, SexoAnimal sexo, PorteAnimal porte, StatusAnimal status, Usuario usuario) {
		super();
		this.id = id;
		this.tipo = (tipo == null) ? null : tipo.getCode();
		this.sexo = (sexo == null) ? null : sexo.getCode();
		this.porte = (porte == null) ? null : porte.getCode();
		this.status = (status == null) ? null : status.getCode();
		this.usuario = usuario;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TipoAnimal getTipo() {
		return TipoAnimal.toEnum(tipo);
	}


	public void setTipo(TipoAnimal  tipo) {
		this.tipo = tipo.getCode();
	}


	public SexoAnimal getSexo() {
		return SexoAnimal.toEnum(sexo);
	}


	public void setSexo(SexoAnimal sexo) {
		this.sexo = sexo.getCode();
	}


	public PorteAnimal getPorte() {
		return PorteAnimal.toEnum(porte);
	}


	public void setPorte(PorteAnimal porte) {
		this.porte = porte.getCode();
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
