package com.lmiguel.sospet.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.NovoComentarioDTO;
import com.lmiguel.sospet.repositories.ComentarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PostService postService;
	
	public List<Comentario> findByPost(Long postId) {
		return comentarioRepository.findComentarios(postId);
	}
	
	@Transactional
	public Comentario insert(NovoComentarioDTO objDto) {
		Usuario usuario = usuarioService.findById(objDto.getAutorId());
		Post post = postService.findById(objDto.getPostId());
		Comentario com = new Comentario(null, objDto.getTexto(), new Date(), post, usuario);
		return comentarioRepository.save(com);
	}

	
}


