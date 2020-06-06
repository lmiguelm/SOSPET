package com.lmiguel.sospet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.repositories.PostRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository usuarioRepository;
	
	public Post find(Long id) {
		Optional<Post> obj =  usuarioRepository.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Post.class.getName()));
	}
}
