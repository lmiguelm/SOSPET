package com.lmiguel.sospet.dto;

import java.io.Serializable;

import com.lmiguel.sospet.domain.enums.IdadeAnimal;
import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;
import com.lmiguel.sospet.domain.enums.TipoPelagem;

public class NovoAnimalDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	// ANIMAL
	
	private Integer tipo;
	
	private Integer sexo;
	
	private Integer porte;
	
	private Integer status;
	
	private Integer idade;
	
	
	// ACHADO
	
	private String dataEncontrado;
	
	
	// ADOÇÃO 
	
	private String nome;
	
	private Integer pelagem;
	
	private Boolean castrado;
	
	private String raca;
	
	
	// DESAPARECIDO
	
	private String ultimaVezVisto;	
	
	
	private Long usuarioId;
	
	public NovoAnimalDTO() {
		
	}

	public NovoAnimalDTO(TipoAnimal tipo, SexoAnimal sexo, PorteAnimal porte, StatusAnimal status, IdadeAnimal idade,
																					 String dataEncontrado,
																					 String nome, 
																					 TipoPelagem pelagem,
																					 Boolean castrado,
																					 String raca,
																					 String ultimaVezVisto, 
																					 Long usuarioId) {
		this.tipo = (tipo == null) ? null : tipo.getCode();
		this.sexo = (sexo == null) ? null : sexo.getCode();
		this.porte = (porte == null) ? null : porte.getCode();
		this.status = (status == null) ? null : status.getCode();
		this.idade = (idade == null) ? null : idade.getCode();
		this.dataEncontrado = dataEncontrado;
		this.nome = nome;
		this.pelagem = (pelagem == null) ? null : pelagem.getCode();
		this.castrado = (castrado == null) ? false : castrado;
		this.raca = raca;
		this.ultimaVezVisto = ultimaVezVisto;
		this.usuarioId = usuarioId;
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

	public String getDataEncontrado() {
		return dataEncontrado;
	}

	public void setDataEncontrado(String dataEncontrado) {
		this.dataEncontrado = dataEncontrado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getUltimaVezVisto() {
		return ultimaVezVisto;
	}

	public void setUltimaVezVisto(String ultimaVezVisto) {
		this.ultimaVezVisto = ultimaVezVisto;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuariIdo(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
