package com.pranav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
}

