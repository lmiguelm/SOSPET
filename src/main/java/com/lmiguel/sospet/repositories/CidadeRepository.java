package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmiguel.sospet.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
