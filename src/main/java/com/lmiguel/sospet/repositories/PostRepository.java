package com.lmiguel.sospet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmiguel.sospet.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
