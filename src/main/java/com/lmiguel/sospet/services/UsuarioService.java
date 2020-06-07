package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.domain.Endereco;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.SexoPessoa;
import com.lmiguel.sospet.dto.NovoUsuarioDTO;
import com.lmiguel.sospet.dto.UsuarioDTO;
import com.lmiguel.sospet.repositories.EnderecoRepository;
import com.lmiguel.sospet.repositories.UsuarioRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	
	public List<Usuario> findAll() {
		return  usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj =  usuarioRepository.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Usuario.class.getName()));
	}
	
	public Usuario findByEmail(String email) {
		Usuario obj = usuarioRepository.findByEmail(email);
		return obj;
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = usuarioRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getSexo());
	}
	
	public Usuario fromDTO(NovoUsuarioDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getNome(), objDto.getEmail(), SexoPessoa.toEnum(objDto.getSexo()));
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getBairro(), objDto.getCep(), objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), cidade, usuario);
		
		usuario.getEnderecos().add(endereco);
		usuario.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2() != null) {
			usuario.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			usuario.getTelefones().add(objDto.getTelefone3());
		}
		return usuario;
	}
}


