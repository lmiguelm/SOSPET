package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmiguel.sospet.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
}
