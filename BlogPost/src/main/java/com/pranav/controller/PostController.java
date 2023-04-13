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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.payload.PostDto;
import com.pranav.payload.PostResponse;
import com.pranav.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	// JSON
	/*
	{
    "title": "Post -8",
    "description": "Python Fast API- New Microservice King",
    "content": "The newest long-term support release for the Java SE platform is Java 17 LTS. Under the Oracle No-Fee Terms and Cond"
	}
	 */
	
	// Create new Post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
	{
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	// all Post
	@GetMapping
	public ResponseEntity<PostResponse> allPosts(
			@RequestParam(value ="pageNo", defaultValue = "0" , required = false ) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10" , required = false) int pageSize
			)
	{
		return new ResponseEntity<PostResponse>(postService.allPosts(pageNo, pageSize), HttpStatus.OK);
	}
	
	// Post based on Id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id)
	{
		return new ResponseEntity<PostDto>(postService.getPostById(id), HttpStatus.OK);
	}
	
	// To update the Post
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @PathVariable Long id, @RequestBody PostDto postDto)
	{
		return new ResponseEntity<PostDto>(postService.updatePost(id, postDto), HttpStatus.OK);
	}
	
	// Deleting the Post based on Id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id)
	{
		return new ResponseEntity<String>(postService.deletePost(id), HttpStatus.OK);
	}
	
}
