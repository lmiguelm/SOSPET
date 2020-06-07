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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.NovoUsuarioDTO;
import com.lmiguel.sospet.dto.UsuarioDTO;
import com.lmiguel.sospet.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	
	// GET
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<UsuarioDTO> objDto = usuarioService.findAll().stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario obj = usuarioService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email){
		Usuario obj = usuarioService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	// POST
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NovoUsuarioDTO objDto){
		Usuario obj = usuarioService.fromDTO(objDto);
		obj = usuarioService.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	// PUT
	
	// DELETE
	
	
}
