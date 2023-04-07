package com.pranav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
