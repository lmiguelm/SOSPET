package com.lmiguel.sospet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.enums.IdadeAnimal;
import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;

public class AnimalDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer sexo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer porte;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer status;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer idade;
	
	public AnimalDTO() {
	}
	
	public AnimalDTO(Animal obj) {
		id = obj.getId();
		tipo = obj.getTipo().getCode();
		sexo = obj.getSexo().getCode();
		porte = obj.getPorte().getCode();
		status = obj.getStatus().getCode();
		idade = (obj.getIdade().getCode());
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

	public void setTipo(TipoAnimal tipo) {
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

	public StatusAnimal getStatus() {
		return StatusAnimal.toEnum(status);
	}

	public void setStatus(StatusAnimal status) {
		this.status = status.getCode();
	}

	public IdadeAnimal getIdade() {
		return IdadeAnimal.toEnum(idade);
	}

	public void setIdade(IdadeAnimal idade) {
		this.idade = idade.getCode();
	}
}
