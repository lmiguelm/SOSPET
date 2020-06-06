package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.repositories.AnimalRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}
	
	public Animal findById(Long id) {
		Optional<Animal> obj =  animalRepository.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Animal.class.getName()));
	}
}
