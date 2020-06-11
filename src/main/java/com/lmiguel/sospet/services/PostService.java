package com.lmiguel.sospet.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.dto.NovoPostDTO;
import com.lmiguel.sospet.dto.PostDTO;
import com.lmiguel.sospet.repositories.PostRepository;
import com.lmiguel.sospet.security.UserSS;
import com.lmiguel.sospet.services.exceptions.AuthorizationException;
import com.lmiguel.sospet.services.exceptions.DataIntegrityException;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.post}")
	private String prefix;
	
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> obj =  postRepository.findById(id);	
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Post.class.getName()));
	}
	
	public Page<Post> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return postRepository.findAll(pageRequest);
	}
	
	@Transactional
	public Post insert(NovoPostDTO objDto) {
		Usuario usuario = usuarioService.findById(objDto.getAutorId());
		Post obj = new Post(null, new Date(), objDto.getTitulo(), objDto.getCorpo(), "https://sospet.s3-sa-east-1.amazonaws.com/sem_foto.jpg", usuario);
		obj = postRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Post update(Post obj) {
		Post novoObj = findById(obj.getId());
		updateData(novoObj, obj);
		return postRepository.save(novoObj);
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		try {
			postRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o post pois há itens relacionados.");
		}
	}

	private void updateData(Post novoObj, Post obj) {
		novoObj.setCorpo(obj.getCorpo());
		novoObj.setTitulo(obj.getTitulo());
		novoObj.setData(new Date(System.currentTimeMillis()));
	}

	public Post fromDTO(PostDTO objDto) {
		return new Post(objDto.getId(), objDto.getData(), objDto.getTitulo(), objDto.getCorpo(), objDto.getImagemUrl(), null);
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile, Long id) {
		
		UserSS user = UserService.authenticated();
		Post post = findById(id);
		
		if (user == null && post == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		String fileName = prefix + post.getId() + ".jpg";
		post.setImagemUrl("https://sospet.s3.sa-east-1.amazonaws.com/"+fileName);
		postRepository.save(post);
			
		return s3Service.uploadFile(imageService.getInputStrem(jpgImage, "jpg"), fileName, "image");
	}
	
	
}