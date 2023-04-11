package com.pranav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.payload.CommentDto;
import com.pranav.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	// all comments
	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long postId)
	{
		return new ResponseEntity<List<CommentDto>>(commentService.allComments(postId), HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> getComment(@PathVariable long postId,@PathVariable long commentId)
	{
		return new ResponseEntity<CommentDto>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, 
			@PathVariable long commentId, @RequestBody CommentDto commentDto )
	{
		return new ResponseEntity<CommentDto>(commentService.updateCommentById(postId, commentId, commentDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId, 
			@PathVariable long commentId)
	{
		return new ResponseEntity<String>(commentService.deleteComment(postId, commentId), HttpStatus.OK);
	}
	
	

}
