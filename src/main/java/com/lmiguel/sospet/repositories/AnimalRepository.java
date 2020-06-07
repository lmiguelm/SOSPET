package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmiguel.sospet.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
