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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.dto.AutorDTO;
import com.lmiguel.sospet.dto.ComentarioDTO;
import com.lmiguel.sospet.dto.NovoComentarioDTO;
import com.lmiguel.sospet.dto.NovoPostDTO;
import com.lmiguel.sospet.dto.PostDTO;
import com.lmiguel.sospet.dto.PostDTO2;
import com.lmiguel.sospet.services.ComentarioService;
import com.lmiguel.sospet.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ComentarioService comentarioService;

	
	// GET
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO2>> findAll(){
		List<Post> post = postService.findAll();
		List<PostDTO2> objDto = post.stream().map(p -> new PostDTO2(p, new AutorDTO(p.getAutor()))).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PostDTO2> find(@PathVariable Long id){
		Post obj = postService.findById(id);
		AutorDTO autor = new AutorDTO(obj.getAutor());
		PostDTO2 objDto = new PostDTO2(obj, autor);
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(value="/{postId}/comentarios", method=RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> findComentarios(@PathVariable Long postId) {
		List<Comentario> list = comentarioService.findByPost(postId);
		List<ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(obj, new AutorDTO(obj.getAutor()))).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	// POST 
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NovoPostDTO objDto){
		Post obj = postService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{postId}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> insertComentario(@Valid @RequestBody NovoComentarioDTO objDto){
		Comentario obj = comentarioService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	// PUT
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PostDTO objDto, @PathVariable Long id) {
		Post obj = postService.fromDTO(objDto);
		obj.setId(id);
		obj = postService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{postId}/comentarios/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateComentario(@Valid @RequestBody ComentarioDTO objDto, @PathVariable Long postId, @PathVariable Long id) {
		Comentario obj = comentarioService.fromDTO(objDto);
		obj.setId(id);
		obj = comentarioService.update(obj);
		return ResponseEntity.noContent().build();
	}
}
