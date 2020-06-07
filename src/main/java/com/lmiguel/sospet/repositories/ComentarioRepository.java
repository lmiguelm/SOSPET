package com.lmiguel.sospet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lmiguel.sospet.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Comentario obj WHERE obj.post.id = :postId ORDER BY obj.id DESC")
	public List<Comentario> findComentarios(@Param("postId") Long postId);
}
