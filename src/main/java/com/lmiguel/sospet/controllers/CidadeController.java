package com.lmiguel.sospet.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.dto.NovaCidadeDTO;
import com.lmiguel.sospet.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	
	//POST
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody NovaCidadeDTO objDto){
		Cidade obj = cidadeService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}