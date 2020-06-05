package com.lmiguel.sospet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lmiguel.sospet.domain.Usuario;
import com.lmiguel.sospet.domain.enums.SexoPessoa;

@SpringBootApplication
public class SospetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SospetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
	}	
}
