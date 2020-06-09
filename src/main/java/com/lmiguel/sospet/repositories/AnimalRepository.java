package com.lmiguel.sospet.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Animal;
import com.lmiguel.sospet.domain.Usuario;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	@Transactional(readOnly=true)
	Page<Animal> findByUsuario(Usuario usuario, Pageable pageRequest);
}
