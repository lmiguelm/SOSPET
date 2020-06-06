package com.lmiguel.sospet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.repositories.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository usuarioRepository;
	
	public Animal find(Long id) {
		Optional<Animal> obj =  usuarioRepository.findById(id);
		
		return obj.orElseThrow();
	}
}
