package com.lmiguel.sospet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.repositories.PostRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> obj =  postRepository.findById(id);	
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Post.class.getName()));
	}
}
