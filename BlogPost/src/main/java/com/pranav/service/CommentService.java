package com.pranav.service;

import java.util.List;

import com.pranav.payload.CommentDto;

public interface CommentService {

	
	CommentDto createComment(long postId,CommentDto commentDto);
	
	List<CommentDto> allComments(Long postId);
	
	CommentDto getCommentById(long postId, long commentId);
	
	CommentDto updateCommentById(long postId,long commentId, CommentDto commentDto);
	
	String deleteComment(long postId,long commentId);
}
