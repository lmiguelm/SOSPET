package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.domain.Endereco;
import com.lmiguel.sospet.domain.Estado;
import com.lmiguel.sospet.dto.NovaCidadeDTO;
import com.lmiguel.sospet.repositories.CidadeRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
			
	@Autowired
	private EstadoService estadoService;

	public Cidade findById(Long id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Endereco.class.getName()));
	}
	
	public List<Cidade> findByEstado(Long estadoId) {
		return cidadeRepository.findCidades(estadoId);
	}
	
	@Transactional
	public Cidade insert(NovaCidadeDTO objDto) {
		Estado estado = estadoService.findById(objDto.getEstadoId());
		Cidade c = new Cidade(null, objDto.getNome(), estado);
		return cidadeRepository.save(c);
	}

	
}