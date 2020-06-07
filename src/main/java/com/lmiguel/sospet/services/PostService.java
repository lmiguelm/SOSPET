package com.lmiguel.sospet.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.NovoPostDTO;
import com.lmiguel.sospet.dto.PostDTO;
import com.lmiguel.sospet.repositories.PostRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> obj =  postRepository.findById(id);	
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Post.class.getName()));
	}
	
	@Transactional
	public Post insert(NovoPostDTO objDto) {
		Usuario usuario = usuarioService.findById(objDto.getAutorId());
		Post obj = new Post(null, new Date(), objDto.getTitulo(), objDto.getCorpo(), usuario);
		obj = postRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Post update(Post obj) {
		Post novoObj = findById(obj.getId());
		updateData(novoObj, obj);
		return postRepository.save(novoObj);
	}

	private void updateData(Post novoObj, Post obj) {
		novoObj.setCorpo(obj.getCorpo());
		novoObj.setTitulo(obj.getTitulo());
		novoObj.setData(new Date(System.currentTimeMillis()));
	}

	public Post fromDTO(PostDTO objDto) {
		return new Post(objDto.getId(), objDto.getData(), objDto.getTitulo(), objDto.getCorpo(), null);
	}
}