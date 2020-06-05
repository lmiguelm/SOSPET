package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmiguel.sospet.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
