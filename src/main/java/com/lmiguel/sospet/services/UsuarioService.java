package com.lmiguel.sospet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.repositories.UsuarioRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario find(Long id) {
		Optional<Usuario> obj =  usuarioRepository.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Usuario.class.getName()));
	}
}
