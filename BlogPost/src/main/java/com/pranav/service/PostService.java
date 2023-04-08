package com.pranav.service;

import java.util.List;

import com.pranav.payload.PostDto;
import com.pranav.payload.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	PostResponse allPosts(int pageNo, int pageSize);
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(Long id, PostDto postDto);
	
	String deletePost(Long id);
}
