package com.pranav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.payload.PostDto;
import com.pranav.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	// Create new Post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
	{
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PostDto>> allPosts()
	{
		return new ResponseEntity<List<PostDto>>(postService.allPosts(), HttpStatus.OK);
	}
}
