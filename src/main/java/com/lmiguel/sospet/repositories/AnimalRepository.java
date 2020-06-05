package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmiguel.sospet.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
