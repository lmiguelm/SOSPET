package com.lmiguel.sospet.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.SexoPessoa;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email
	private String email;
	
	private Integer sexo;
	
	private String imagemUrl;
	
	private Set<String> telefones = new HashSet<>();
	 
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		sexo = (obj.getSexo() == null) ? null :  obj.getSexo().getCode();
		telefones.addAll(obj.getTelefones());
		imagemUrl = obj.getImagemUrl();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SexoPessoa getSexo() {
		return SexoPessoa.toEnum(sexo);
	}

	public void setSexo(SexoPessoa sexo) {
		this.sexo = sexo.getCode();
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
	
}
