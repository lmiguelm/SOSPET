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
		Estado est3 = new Estado(null, "Rio Grande do sul");
		Estado est4 = new Estado(null, "Santa Catarina");
		Estado est5 = new Estado(null, "Minas Gerais");
		Estado est6 = new Estado(null, "Bahia");
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5, est6));
		
		
		Cidade c1 = new Cidade(null, "Araraquara", est1);
		Cidade c2 = new Cidade(null, "Niterói", est2);
		Cidade c3 = new Cidade(null, "São paulo", est1);
		Cidade c4 = new Cidade(null, "Porto Alegre", est3);
		Cidade c5 = new Cidade(null, "Florianópolis", est4);
		Cidade c6 = new Cidade(null, "Belo Horizonte", est5);
		Cidade c7 = new Cidade(null, "Uberlândia", est5);
		Cidade c8 = new Cidade(null, "Salvador", est6);
		Cidade c9 = new Cidade(null, "Campinas", est1);
		Cidade c10 = new Cidade(null, "São Carlos", est1);
		Cidade c11 = new Cidade(null, "Matão", est1);
		Cidade c12 = new Cidade(null, "Angra dos Reis", est2);
		Cidade c13 = new Cidade(null, "Duque de caxias", est2);
		Cidade c14 = new Cidade(null, "Limeira", est1);
		Cidade c15 = new Cidade(null, "Rio Claro", est1);
		Cidade c16 = new Cidade(null, "Santos", est1);
		Cidade c17 = new Cidade(null, "Jundiaí", est1);
		Cidade c18 = new Cidade(null, "Ribeirão Preto", est1);
		Cidade c19 = new Cidade(null, "Bauro", est1);
		Cidade c20 = new Cidade(null, "Araçatuba", est1);
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20));
	
		
		Usuario u1 = new Usuario(null, "Miguel", "miguel@gmail.com", SexoPessoa.MASCULINO, pe.encode("123") );
		Usuario u2 = new Usuario(null, "Maria", "maria@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u3 = new Usuario(null, "Sophia", "sophia@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u4 = new Usuario(null, "Millene", "millene@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u5 = new Usuario(null, "Leonardo", "leonarod@gmail.com", SexoPessoa.MASCULINO, pe.encode("123"));
		Usuario u6 = new Usuario(null, "João Victor", "joao@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u7 = new Usuario(null, "Daniel", "daniel@gmail.com", SexoPessoa.MASCULINO, pe.encode("123"));
		Usuario u8 = new Usuario(null, "Mario", "mario@gmail.com", SexoPessoa.MASCULINO, pe.encode("123"));
		Usuario u9 = new Usuario(null, "Mariana", "mariana@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u10 = new Usuario(null, "Joana", "joana@gmail.com", SexoPessoa.FEMININO, pe.encode("123"));
		Usuario u11 = new Usuario(null, "ADMIN", "amdin@sospet.com", null, pe.encode("123"));
		
		u1.getTelefones().addAll(Arrays.asList("991012427", "991827192"));
		u2.getTelefones().addAll(Arrays.asList("991827361"));
		u3.getTelefones().addAll(Arrays.asList("981726122"));
		u4.getTelefones().addAll(Arrays.asList("998261728"));
		u5.getTelefones().addAll(Arrays.asList("981728192", "998172812"));
		u6.getTelefones().addAll(Arrays.asList("991829182"));
		u7.getTelefones().addAll(Arrays.asList("990027182"));
		u8.getTelefones().addAll(Arrays.asList("991281937"));
		u9.getTelefones().addAll(Arrays.asList("981271383"));
		u10.getTelefones().addAll(Arrays.asList("991273828"));
		
		u11.addPerfil(Perfil.ADMIN);
		
		
		Endereco e1 = new Endereco(null, "Carmo", "14800-212", "Rua das flores", 812, null, c1, u1);
		Endereco e2 = new Endereco(null, "Vila dos remédios", "19212-012", "Rua dos remédios", 128, null, c3, u1);
		Endereco e3 = new Endereco(null, "Jarim das flores", "12321-128", "Rua das flores", 911, "Apto", c2, u2);
		Endereco e4 = new Endereco(null, "Jarim das americas", "18217-128", "Rua americana", 912, null, c12, u3);
		Endereco e5 = new Endereco(null, "Hortencia", "11117-128", "Rua hortenciana", 123, "Apto", c11, u4);
		Endereco e6 = new Endereco(null, "Jarim das borboletas", "18217-128", "Rua 5", 136, null, c5, u5);
		Endereco e7 = new Endereco(null, "Jarim das plantas", "18217-128", "Rua 4", 642, "Apto", c3, u6);
		Endereco e8 = new Endereco(null, "Jarim das micelli", "18217-128", "Rua 3", 231, null, c9, u7);
		Endereco e9 = new Endereco(null, "Jarim dos manacas", "18217-128", "Rua 2", 356, "Apto", c18, u8);
		Endereco e10 = new Endereco(null, "Jarim do scott", "18217-128", "Rua 1", 12, null, c20, u9);
		
		u1.getEnderecos().addAll(Arrays.asList(e1, e2));
		u2.getEnderecos().addAll(Arrays.asList(e2));
		u3.getEnderecos().addAll(Arrays.asList(e3));
		u4.getEnderecos().add(e5);
		u5.getEnderecos().add(e6);
		u6.getEnderecos().add(e7);
		u7.getEnderecos().add(e8);
		u8.getEnderecos().add(e9);
		u9.getEnderecos().addAll(Arrays.asList(e1, e4));
		u10.getEnderecos().addAll(Arrays.asList(e2, e9));
		u11.getEnderecos().addAll(Arrays.asList(e7, e10));
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
		
		
		
		Animal a1 = new AnimalDesaparecido(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.GRANDE, StatusAnimal.DESAPARECIDO, IdadeAnimal.ADULTO, u1, "Rex", sdf1.parse("11/09/2019"), "Pitbull", TipoPelagem.CURTA, false);
		Animal a2 = new AnimalAchado(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.PEQUENO, StatusAnimal.ACHADO, IdadeAnimal.ADULTO, u1, sdf1.parse("09/12/2018"));
		Animal a3 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.MEDIO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u2, "Thor", TipoPelagem.MEDIA, false, "Poodle");
		Animal a4 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u3, "Ju", TipoPelagem.CURTA, true, "Ragdoll");
		Animal a5 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u4, "Zeus", TipoPelagem.LONGA, false, "Vira-Lata");
		Animal a6 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.MEDIO, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u5, "Amora", TipoPelagem.MEDIA, false, "Persa");
		Animal a7 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u6, "Rick", TipoPelagem.LONGA, true, "Pintcher");
		Animal a8 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.MEDIO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u7, "Totó", TipoPelagem.MEDIA, true, "Golden");
		Animal a9 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.GRANDE, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u9, "Kiara", TipoPelagem.CURTA, false, "Siamês");
		Animal a10 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u10, "Scott", TipoPelagem.MEDIA, true, "Pitbull");
		Animal a11 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u3, "Lola", TipoPelagem.LONGA, false, "Siamês");
		Animal a12 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.MEDIO, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u5, "Bidu", TipoPelagem.CURTA, true, "Labrador");
		Animal a13 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.MACHO, PorteAnimal.GRANDE, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u9, "Simba", TipoPelagem.LONGA, false, "Ragdoll");
		Animal a14 = new AnimalAdocao(null, TipoAnimal.CACHORRO, SexoAnimal.MACHO, PorteAnimal.PEQUENO, StatusAnimal.ADOCAO, IdadeAnimal.FILHOTE, u4, "Scoob", TipoPelagem.MEDIA, true, "Pastor Alemão");
		Animal a15 = new AnimalAdocao(null, TipoAnimal.GATO, SexoAnimal.FEMEA, PorteAnimal.GRANDE, StatusAnimal.ADOCAO, IdadeAnimal.ADULTO, u8, "Gigi", TipoPelagem.CURTA, false, "Persa");
		
		animalRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15));	
		
		
		Post p1 = new Post(null, sdf1.parse("06/06/2020"), "Animais Abandonados", "Lorem ipsum quam per litora enim ultrices sodales integer, venenatis posuere erat dolor curabitur taciti netus, nisl ligula curae class metus sem diam. fames sodales nec ", u11);
		Post p2 = new Post(null, sdf1.parse("17/01/2020"), "Animais Desaparecidos", "posuere lacinia duis aliquam fusce turpis sed nibh enim platea mi aenean lorem vestibulum ornare fames, malesuada adipiscing torquent turpis risus primis ut porta ", u11);
		Post p3 = new Post(null, sdf1.parse("07/03/2020"), "Animais Achados", "interdum cras quis enim proin erat lobortis risus. aenean odio neque pretium posuere enim nunc, eleifend porta aptent dolor integer quisque, nam quis feugiat nam lacus. ", u11);
		Post p4 = new Post(null, sdf1.parse("02/04/2020"), "Rações", "quisque mattis nec hac lobortis lectus, tempus justo lacus etiam faucibus gravida, tempus quisque neque lobortis pulvinar.  ", u11);
		Post p5 = new Post(null, sdf1.parse("09/02/2020"), "Melhor Amigo do homem", "Fringilla aliquam aenean feugiat sodales pellentesque ullamcorper taciti himenaeos sapien habitant erat nisi massa neque pretium tempor, ut iaculis mattis lacus libero.", u11);
		
		
		Comentario com1 = new Comentario(null, "quam fringilla augue aenean senectus, blandit pellentesque scelerisque elit apien aenean ", new Date(), p1, u2);
		Comentario com2 = new Comentario(null, "bibendum libero nec donec, aenean adipiscing rutrum suspendisse quis nibh sagittis ", new Date(), p1, u3);
		Comentario com3 = new Comentario(null, "Lorem ipsum eget est tristique erat maecenas faucibus morbi habitant lobortis ", new Date(), p2, u3);
		Comentario com4 = new Comentario(null, "Luctus quam fringilla augue aenean senectus, blandit pellentesque scelerisque elit ", new Date(), p3, u4);
		Comentario com5 = new Comentario(null, "congue pulvinar mattis gravida aenean eu feugiat euismod dictumst.", new Date(), p4, u7);
		Comentario com6 = new Comentario(null, "venenatis, aliquam quisque integer feugiat magna mauris sodales augue sodales dolor", new Date(), p5, u1);
		Comentario com7 = new Comentario(null, "laoreet commodo eget ut. cubilia vel primis in tortor luctus sit euismod ultricies nisl ", new Date(), p1, u8);
		Comentario com8 = new Comentario(null, "commodo consectetur quis lectus lobortis id hendrerit, consequat sociosqu augue turpis", new Date(), p2, u9);
		Comentario com9 = new Comentario(null, "elit himenaeos libero maecenas etiam nisi scelerisque in neque enim, purus suscipit ", new Date(), p4, u3);
		Comentario com10 = new Comentario(null, "semper torquent elit, tincidunt sem dapibus ligula dictum laoreet suspendisse taciti nam. ", new Date(), p5, u5);
		
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		comentarioRepository.saveAll(Arrays.asList(com1, com2, com3, com4, com5, com6, com7, com8, com9, com10));
	}

}
