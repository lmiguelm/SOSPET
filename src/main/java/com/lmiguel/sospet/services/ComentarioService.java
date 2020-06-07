package com.lmiguel.sospet.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.ComentarioDTO;
import com.lmiguel.sospet.dto.NovoComentarioDTO;
import com.lmiguel.sospet.repositories.ComentarioRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

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
	
	public Comentario findById(Long id) {
		Optional<Comentario> obj = comentarioRepository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Comentario.class.getName()));
	}
	
	@Transactional
	public Comentario insert(NovoComentarioDTO objDto) {
		Usuario usuario = usuarioService.findById(objDto.getAutorId());
		Post post = postService.findById(objDto.getPostId());
		Comentario com = new Comentario(null, objDto.getTexto(), new Date(System.currentTimeMillis()), post, usuario);
		return comentarioRepository.save(com);
	}
	
	@Transactional
	public Comentario update(Comentario obj) {
		Comentario novoObj = findById(obj.getId());
		updateData(novoObj, obj);
		return comentarioRepository.save(novoObj);
	}

	private void updateData(Comentario novoObj, Comentario obj) {
		novoObj.setInstante(new Date(System.currentTimeMillis()));
		novoObj.setTexto(obj.getTexto());
	}

	public Comentario fromDTO(ComentarioDTO objDto) {
		return new Comentario(objDto.getId(), objDto.getTexto(), objDto.getInstante(), null, null);
	}
}


