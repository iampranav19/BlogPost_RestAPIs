package com.pranav.service;

import java.util.List;

import com.pranav.payload.PostDto;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	List<PostDto> allPosts();
	
	PostDto getPostById(Long id);
}
