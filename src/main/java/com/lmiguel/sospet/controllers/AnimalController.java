package com.lmiguel.sospet.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.dto.AnimalDTO;
import com.lmiguel.sospet.dto.NovoAnimalDTO;
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
	
	
	// POST
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NovoAnimalDTO objDto){
		Animal obj = animalService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		System.out.println(obj.getId());
		return ResponseEntity.created(uri).build();
	}
	
	
	// PUT
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody NovoAnimalDTO objDto, @PathVariable Long id) {
		
		Animal obj = animalService.findById(id);
		
		if(obj.getStatus() == StatusAnimal.DESAPARECIDO)		animalService.updateDesaparecido(objDto, id);
		if(obj.getStatus() == StatusAnimal.ADOCAO) 				animalService.updateAdocao(objDto, id);
		if(obj.getStatus() == StatusAnimal.ACHADO) 				animalService.updateAchado(objDto, id);

		return ResponseEntity.noContent().build();
	}
}
