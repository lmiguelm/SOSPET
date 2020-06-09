package com.lmiguel.sospet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmiguel.sospet.dto.CEPDTO;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

import viacep.ViaCEP;
import viacep.ViaCEPException;

@RestController
@RequestMapping(value = "/cep")
public class CepController {
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CEPDTO> findEnderecoByCep(@PathVariable String id){
		try {
			ViaCEP obj;
			obj = new ViaCEP(id);
			CEPDTO objDto = new CEPDTO(obj);
			return ResponseEntity.ok().body(objDto);
		} catch (ViaCEPException e) {
			throw new ObjectNotFoundException("CEP inválido");
		}
	}
}
