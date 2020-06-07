package com.lmiguel.sospet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Endereco obj WHERE obj.usuario.id = :usuarioId ORDER BY obj.id DESC")
	public List<Endereco> findEnderecos(@Param("usuarioId") Long usuarioId);
}
