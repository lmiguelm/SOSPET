package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.Estado;
import com.lmiguel.sospet.repositories.EstadoRepository;
import com.lmiguel.sospet.services.exceptions.DataIntegrityException;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> findAll() {
		return estadoRepository.findAllByOrderByNome();
	}
	
	public Estado findById(Long id) {
		Optional<Estado> obj =  estadoRepository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Animal.class.getName()));
	}
	
	@Transactional
	public Estado insert(Estado obj) {
		obj.setId(null);
		return estadoRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			estadoRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir poeque há cidades relacionadas.");
		}
	}
}