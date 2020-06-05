package com.lmiguel.sospet.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lmiguel.sospet.domain.enums.SexoPessoa;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	
	// ATRIBUTOS DA CLASSE
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private Integer sexo;
	
	private Set<String> telefones = new HashSet<>();
	
	
	// ASSOCIAÇÕES
	
	private List<Post> posts = new ArrayList<>();
	
	private List<Endereco> enderecos = new ArrayList<>();
		
	
	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String email, SexoPessoa sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.sexo = (sexo == null) ? null : sexo.getCode();
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

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
