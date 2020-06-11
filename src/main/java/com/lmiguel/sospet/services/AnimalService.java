package com.lmiguel.sospet.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.AnimalAchado;
import com.lmiguel.sospet.domain.AnimalAdocao;
import com.lmiguel.sospet.domain.AnimalDesaparecido;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.dto.NovoAnimalDTO;
import com.lmiguel.sospet.repositories.AnimalRepository;
import com.lmiguel.sospet.security.UserSS;
import com.lmiguel.sospet.services.exceptions.AuthorizationException;
import com.lmiguel.sospet.services.exceptions.DataIntegrityException;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.animal}")
	private String prefix;
	
	
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}
	
	public Animal findById(Long id) {
		Optional<Animal> obj =  animalRepository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Animal.class.getName()));
	}
	
	public Page<Animal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return animalRepository.findAll(pageRequest);
	}
	
	public Page<Animal> findMeusAnimais(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Usuario usuario = usuarioService.findById(user.getId());
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return animalRepository.findByUsuario(usuario, pageRequest);
	}
	
	@Transactional
	public Animal insert(NovoAnimalDTO objDto) {
		Animal obj = null;
		Usuario usuario = usuarioService.findById(objDto.getUsuarioId());
		objDto.getStatus();
		obj = statusAnimal(objDto);
		obj.setUsuario(usuario);
		obj = animalRepository.save(obj);
		return obj;	
	}
	
	@Transactional
	public Animal updateDesaparecido(NovoAnimalDTO obj, Long id) {
		
		AnimalDesaparecido novoObj = (AnimalDesaparecido) findById(id);
		
		AutorizacaoService.verificarAutorizacao(novoObj.getUsuario().getId());
		if(obj.getStatus() != novoObj.getStatus()) {
			throw new ObjectNotFoundException("Status do animal invalido");
		}
		
		updateDataDesaparecido(novoObj, obj);
		return animalRepository.save(novoObj);
	}
	
	@Transactional
	public Animal updateAdocao(NovoAnimalDTO obj, Long id) {
		AnimalAdocao novoObj = (AnimalAdocao) findById(id);
		
		AutorizacaoService.verificarAutorizacao(novoObj.getUsuario().getId());

		if(obj.getStatus() != novoObj.getStatus()) {
			throw new ObjectNotFoundException("Status do animal invalido");
		}
		
		updateDataAdocao(novoObj, obj);
		return animalRepository.save(novoObj);
	}
	
	@Transactional
	public Animal updateAchado(NovoAnimalDTO obj, Long id) {
		
		AnimalAchado novoObj = (AnimalAchado) findById(id);
		
		AutorizacaoService.verificarAutorizacao(novoObj.getUsuario().getId());
		
		if(obj.getStatus() != novoObj.getStatus()) {
			throw new ObjectNotFoundException("Status do animal invalido");
		}
		
		updateDataAchado(novoObj, obj);
		return animalRepository.save(novoObj);
	}
	
	@Transactional
	public void delete(Long id) {
		
		Animal animal = findById(id);
		
		AutorizacaoService.verificarAutorizacao(animal.getUsuario().getId());
		
		try {
			animalRepository.deleteById(id);			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel remover este animal, pois ele está relacionado a um usuário.");
		}
	}
	

	private void updateDataAchado(AnimalAchado novoObj, NovoAnimalDTO obj) {
		try {
			novoObj.setTipo(obj.getTipo());
			novoObj.setSexo(obj.getSexo());
			novoObj.setPorte(obj.getPorte());
			novoObj.setIdade(obj.getIdade());
			
			novoObj.setDataEncontrado(sdf.parse(obj.getDataEncontrado()));
		}
		catch (ParseException e) {
			throw new ObjectNotFoundException("Data invalida");
		}
	}

	private void updateDataAdocao(AnimalAdocao novoObj, NovoAnimalDTO obj) {
		novoObj.setTipo(obj.getTipo());
		novoObj.setSexo(obj.getSexo());
		novoObj.setPorte(obj.getPorte());
		novoObj.setIdade(obj.getIdade());
		
		novoObj.setCastrado(obj.getCastrado());
		novoObj.setNome(obj.getNome());
		novoObj.setTipoPelagem(obj.getPelagem());
		novoObj.setRaca(obj.getRaca());
	}

	private void updateDataDesaparecido(AnimalDesaparecido novoObj, NovoAnimalDTO obj) {
		try {
			novoObj.setTipo(obj.getTipo());
			novoObj.setSexo(obj.getSexo());
			novoObj.setPorte(obj.getPorte());
			novoObj.setIdade(obj.getIdade());
			
			novoObj.setNome(obj.getNome());
			novoObj.setUltimaVezVisto(sdf.parse(obj.getUltimaVezVisto()));
			novoObj.setRaca(obj.getRaca());
			novoObj.setPelagem(obj.getPelagem());
			novoObj.setCastrado(obj.getCastrado());
		}
		catch (ParseException e) {
			throw new ObjectNotFoundException("Data invalida");
		}	
	}
	
	private Animal statusAnimal(NovoAnimalDTO objDto) {
		try {
			Animal obj = null;
			if (objDto.getStatus() == StatusAnimal.DESAPARECIDO) {
				obj = new AnimalDesaparecido(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), "https://sospet.s3-sa-east-1.amazonaws.com/sem_foto.jpg", null, objDto.getNome(), sdf.parse(objDto.getUltimaVezVisto()), objDto.getRaca(), objDto.getPelagem(), objDto.getCastrado());
			}
			if (objDto.getStatus() == StatusAnimal.ACHADO) {
				obj = new AnimalAchado(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), "https://sospet.s3-sa-east-1.amazonaws.com/sem_foto.jpg", null, sdf.parse(objDto.getDataEncontrado()));
			}
			if (objDto.getStatus() == StatusAnimal.ADOCAO) {
				obj = new AnimalAdocao(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), "https://sospet.s3-sa-east-1.amazonaws.com/sem_foto.jpg", null, objDto.getNome(), objDto.getPelagem(), objDto.getCastrado(), objDto.getRaca());
			}
			if (obj == null) {
				throw new ObjectNotFoundException("Status inválido!");
			}
			return obj;
		}
		catch (ParseException e) {
			throw new ObjectNotFoundException("Data invalida");
		}
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile, Long id) {
		
		UserSS user = UserService.authenticated();
		Animal animal = findById(id);
		
		if (user == null && animal == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		String fileName = prefix + animal.getId() + ".jpg";
		animal.setImagemUrl("https://sospet.s3.sa-east-1.amazonaws.com/"+fileName);
		animalRepository.save(animal);
			
		return s3Service.uploadFile(imageService.getInputStrem(jpgImage, "jpg"), fileName, "image");
	}
}
