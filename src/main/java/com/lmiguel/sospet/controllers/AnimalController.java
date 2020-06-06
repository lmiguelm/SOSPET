package com.lmiguel.sospet.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.dto.AnimalDTO;
import com.lmiguel.sospet.services.AnimalService;

@RestController
@RequestMapping(value = "/animais")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	// GET
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AnimalDTO>> findAll(){
		List<AnimalDTO> objDto = animalService.findAll().stream().map(obj -> new AnimalDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Animal> find(@PathVariable Long id){
		Animal obj = animalService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
