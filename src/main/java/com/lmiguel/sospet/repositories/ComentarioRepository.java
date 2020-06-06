package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmiguel.sospet.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
