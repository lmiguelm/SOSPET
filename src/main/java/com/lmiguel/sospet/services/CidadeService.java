package com.lmiguel.sospet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.domain.Estado;
import com.lmiguel.sospet.dto.NovaCidadeDTO;
import com.lmiguel.sospet.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
			
	@Autowired
	private EstadoService estadoService;

	public List<Cidade> findByEstado(Long estadoId) {
		return cidadeRepository.findCidades(estadoId);
	}

	public Cidade insert(NovaCidadeDTO objDto) {
		Estado estado = estadoService.findById(objDto.getEstadoId());
		Cidade c = new Cidade(null, objDto.getNome(), estado);
		return cidadeRepository.save(c);
	}
}