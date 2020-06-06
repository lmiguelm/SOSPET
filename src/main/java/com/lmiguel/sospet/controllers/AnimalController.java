package com.lmiguel.sospet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.services.AnimalService;

@RestController
@RequestMapping(value = "/animais")
public class AnimalController {
	
	@Autowired
	private AnimalService AnimalService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Animal> find(@PathVariable Long id){
		Animal obj = AnimalService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
