package com.lmiguel.sospet.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.AnimalAchado;
import com.lmiguel.sospet.domain.AnimalAdocao;
import com.lmiguel.sospet.domain.AnimalDesaparecido;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.dto.NovoAnimalDTO;
import com.lmiguel.sospet.repositories.AnimalRepository;
import com.lmiguel.sospet.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}
	
	public Animal findById(Long id) {
		Optional<Animal> obj =  animalRepository.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", tipo: "+Animal.class.getName()));
	}
	
	@Transactional
	public Animal insert(NovoAnimalDTO objDto) {
		try {
			Animal obj = null;
			Usuario usuario = usuarioService.findById(objDto.getUsuarioId());
			objDto.getStatus();
			
			System.out.println(objDto.getStatus());
			
			if (StatusAnimal.DESAPARECIDO == objDto.getStatus()) {
				obj = new AnimalDesaparecido(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), usuario, objDto.getNome(), sdf.parse(objDto.getUltimaVezVisto()), objDto.getRaca(), objDto.getPelagem(), objDto.getCastrado());
			}
			if (StatusAnimal.ACHADO == objDto.getStatus()) {
				obj = new AnimalAchado(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), usuario, sdf.parse(objDto.getDataEncontrado()));
			}
			if (StatusAnimal.ADOCAO == objDto.getStatus()) {
				obj = new AnimalAdocao(null, objDto.getTipo(), objDto.getSexo(), objDto.getPorte(), objDto.getStatus(), objDto.getIdade(), usuario,objDto.getNome(), objDto.getPelagem(), objDto.getCastrado(), objDto.getRaca());
			}
			if (obj == null) {
				throw new ObjectNotFoundException("Status inválido!");
			}
			obj = animalRepository.save(obj);
			return obj;
		}
		catch (ParseException e) {
			throw new ObjectNotFoundException("Data invalida");
		}
		
	}
}
