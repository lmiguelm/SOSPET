package com.lmiguel.sospet.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.dto.AutorDTO;
import com.lmiguel.sospet.dto.ComentarioDTO;
import com.lmiguel.sospet.dto.NovoComentarioDTO;
import com.lmiguel.sospet.dto.NovoPostDTO;
import com.lmiguel.sospet.dto.PostDTO;
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
	public ResponseEntity<List<PostDTO>> findAll(){
		List<Post> post = postService.findAll();
		List<PostDTO> objDto = post.stream().map(p -> new PostDTO(p, new AutorDTO(p.getAutor()))).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PostDTO> find(@PathVariable Long id){
		Post obj = postService.findById(id);
		AutorDTO autor = new AutorDTO(obj.getAutor());
		PostDTO objDto = new PostDTO(obj, autor);
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(value="/{postId}/comentarios", method=RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> findComentarios(@PathVariable Long postId) {
		List<Comentario> list = comentarioService.findByPost(postId);
		List<ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(obj, new AutorDTO(obj.getAutor()))).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PostDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Post> list = postService.findPage(page, linesPerPage, orderBy, direction);
		Page<PostDTO> listDto = list.map(obj -> new PostDTO(obj, new AutorDTO(obj.getAutor())));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// POST 
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	@RequestMapping(value = "/picture/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file) {	
		URI uri = postService.uploadProfilePicture(file, id);
		return ResponseEntity.created(uri).build();
	}
	
	
	// PUT
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	// DELETE
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{postId}/comentarios/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteComentario(@PathVariable Long postId, @PathVariable Long id){
		comentarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
