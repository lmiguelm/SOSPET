package com.lmiguel.sospet.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.AnimalAchado;
import com.lmiguel.sospet.domain.AnimalAdocao;
import com.lmiguel.sospet.domain.AnimalDesaparecido;
import com.lmiguel.sospet.domain.Cidade;
import com.lmiguel.sospet.domain.Comentario;
import com.lmiguel.sospet.domain.Endereco;
import com.lmiguel.sospet.domain.Estado;
import com.lmiguel.sospet.domain.Post;
import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.IdadeAnimal;
import com.lmiguel.sospet.domain.enums.Perfil;
import com.lmiguel.sospet.domain.enums.PorteAnimal;
import com.lmiguel.sospet.domain.enums.SexoAnimal;
import com.lmiguel.sospet.domain.enums.SexoPessoa;
import com.lmiguel.sospet.domain.enums.StatusAnimal;
import com.lmiguel.sospet.domain.enums.TipoAnimal;
import com.lmiguel.sospet.domain.enums.TipoPelagem;
import com.lmiguel.sospet.repositories.AnimalRepository;
import com.lmiguel.sospet.repositories.CidadeRepository;
import com.lmiguel.sospet.repositories.ComentarioRepository;
import com.lmiguel.sospet.repositories.EnderecoRepository;
import com.lmiguel.sospet.repositories.EstadoRepository;
import com.lmiguel.sospet.repositories.PostRepository;
import com.lmiguel.sospet.repositories.UsuarioRepository;

@Service
public class DBService {
	
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instantiateTestDatabase() throws ParseException {
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Rio de janeiro");
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		
		Cidade c1 = new Cidade(null, "Araraquara", est1);
		Cidade c2 = new Cidade(null, "Niterói", est2);
		Cidade c3 = new Cidade(null, "São paulo", est1);
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	
		
		Usuario u1 = new Usuario(null, "Miguel", "miguel@gmail.com", SexoPessoa.MASCULINO, pe.encode("123") );
		u1.getTelefones().addAll(Arrays.asList("991012427", "99293721"));
		
		Usuario u2 = new Usuario(null, "Maria", "maria@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		u2.getTelefones().add("998162831");
		
		Usuario u3 = new Usuario(null, "Sophia", "sophia@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		u2.getTelefones().addAll(Arrays.asList("991021212"));
		
		Usuario u4 = new Usuario(null, "Admin", "admin@sospet.com", null, pe.encode("123"));
		u4.addPerfil(Perfil.ADMIN);
		
		
		Endereco e1 = new Endereco(null, "Carmo", "14800-212", "Rua das flores", 812, null, c1, u1);
		Endereco e2 = new Endereco(null, "Vila dos remédios", "19212-012", "Rua dos remédios", 128, null, c3, u1);
		Endereco e3 = new Endereco(null, "Jarim das armas", "18217-128", "Rua armamentista", 912, "Apto", c2, u2);
		Endereco e4 = new Endereco(null, "ADMIN", "ADMIN", "ADMIN", 000, null, c1, u4);
		
		u1.getEnderecos().addAll(Arrays.asList(e1, e2));
		u2.getEnderecos().addAll(Arrays.asList(e2));
		u3.getEnderecos().addAll(Arrays.asList(e3));
		u4.getEnderecos().add(e4);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		
		
		Animal a1 = new AnimalDesaparecido(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.GRANDE, StatusAnimal.DESAPARECIDO, IdadeAnimal.ADULTO, u1, "Rex", sdf1.parse("11/09/2019"), "Pitbull", TipoPelagem.CURTA, false);
		Animal a2 = new AnimalAchado(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.PEQUENO, StatusAnimal.ACHADO, IdadeAnimal.FILHOTE, u1, sdf1.parse("09/12/2018"));
		Animal a3 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u2, "Thor", TipoPelagem.LONGA, true, "Poodle");
		
		animalRepository.saveAll(Arrays.asList(a1, a2, a3));	
		
		
		Post p1 = new Post(null, sdf1.parse("06/06/2020"), "Animais Abandonados", "testestestestestestestestestesteste", u1);
		
		Comentario com1 = new Comentario(null, "ok, it's lit", new Date(), p1, u2);
		Comentario com2 = new Comentario(null, "Olá !! :)", new Date(), p1, u3);
		
		
		postRepository.saveAll(Arrays.asList(p1));
		comentarioRepository.saveAll(Arrays.asList(com1, com2));
	}

}
