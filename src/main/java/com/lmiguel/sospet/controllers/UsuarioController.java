package com.lmiguel.sospet.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Endereco;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.EnderecoDTO;
import com.lmiguel.sospet.dto.NovoUsuarioDTO;
import com.lmiguel.sospet.dto.UsuarioDTO;
import com.lmiguel.sospet.services.EnderecoService;
import com.lmiguel.sospet.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoService enderecoService;

	
	// GET
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email){
		Usuario obj = usuarioService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{usuarioId}/enderecos", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findEnderecosByUser(@PathVariable Long usuarioId){
		List<Endereco> list = enderecoService.findByUsuario(usuarioId);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/enderecos", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findEnderecos(@PathVariable Long usuarioId){
		List<Endereco> list = enderecoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	
	// POST
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NovoUsuarioDTO objDto){
		Usuario obj = usuarioService.fromDTO(objDto);
		obj = usuarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{usuarioId}/enderecos", method = RequestMethod.POST)
	public ResponseEntity<Void> insertEndereco(@Valid @PathVariable Long usuarioId, @RequestBody EnderecoDTO objDto){
		Endereco obj = enderecoService.fromDto(objDto, usuarioId);
		obj = enderecoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {	
		URI uri = usuarioService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
	
	
	// PUT
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Long id) {
		Usuario obj = usuarioService.fromDTO(objDto);
		obj.setId(id);
		obj = usuarioService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{usuarioId}/enderecos/{id}")
	public ResponseEntity<Void> updateEndereco(@Valid @RequestBody EnderecoDTO objDto, @PathVariable Long usuarioId, @PathVariable Long id){
		Endereco obj = enderecoService.fromDto(objDto, usuarioId);
		obj.setId(id);
		obj = enderecoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{usuarioId}/enderecos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEndereco(@PathVariable Long usuarioId, @PathVariable Long id){
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
