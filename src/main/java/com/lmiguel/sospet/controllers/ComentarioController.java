package com.lmiguel.sospet.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.dto.NovoComentarioDTO;
import com.lmiguel.sospet.services.ComentarioService;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;

	
	// POST 
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertComentario(@Valid @RequestBody NovoComentarioDTO objDto){
		Comentario obj = comentarioService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
