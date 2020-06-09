package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.domain.Endereco;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.EnderecoDTO;
import com.lmiguel.sospet.repositories.EnderecoRepository;
import com.lmiguel.sospet.services.exceptions.DataIntegrityException;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;

	public List<Endereco> findAll() {
		AutorizacaoService.verificarAutorizacao(null);
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(Long id) {
		AutorizacaoService.verificarAutorizacao(null);
		Optional<Endereco> obj =  enderecoRepository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Endereco.class.getName()));
	}
	
	public List<Endereco> findByUsuario(Long usuarioId) {
		AutorizacaoService.verificarAutorizacao(usuarioId);
		return enderecoRepository.findEnderecos(usuarioId);
	}
	
	
	@Transactional
	public Endereco insert(Endereco obj) {
		obj.setId(null);
		return enderecoRepository.save(obj);
	}
	
	@Transactional
	public Endereco update(Endereco obj) {
		Endereco novoObj = findById(obj.getId());
		updateData(novoObj, obj);
		return enderecoRepository.save(obj);
	}
	
	public void delete(Long id) {
		Endereco obj = findById(id);
		AutorizacaoService.verificarAutorizacao(obj.getUsuario().getId());
		
		try {			
			enderecoRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Está tabela sendo utilizada como chave estrangeira em outra tatela. Erro: "+e.getMessage());
		}
	}

	private void updateData(Endereco novoObj, Endereco obj) {		
		novoObj.setBairro(obj.getBairro());
		novoObj.setCep(obj.getCep());
		novoObj.setComplemento(obj.getComplemento());
		novoObj.setNumero(obj.getNumero());
		novoObj.setCidade(obj.getCidade());
	}

	public Endereco fromDto(EnderecoDTO objDto, Long usuarioId) {
		Cidade cidade = cidadeService.findById(objDto.getCidadeId());
		Usuario usuario = usuarioService.findById(usuarioId);
		return new Endereco(objDto.getId(), objDto.getBairro(), objDto.getCep(), objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), cidade, usuario);
	}
}