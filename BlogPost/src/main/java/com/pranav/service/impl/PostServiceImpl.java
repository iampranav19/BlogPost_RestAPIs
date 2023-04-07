package com.pranav.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranav.entity.Post;
import com.pranav.payload.PostDto;
import com.pranav.repository.PostRepository;
import com.pranav.service.PostService;

@Service 
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		Post post=new Post();
		BeanUtils.copyProperties(postDto, post);
		Post savedPost = postRepository.save(post);
		
		PostDto savedDto=new PostDto();
		BeanUtils.copyProperties(savedPost, savedDto);
		
		return savedDto;
	}

	@Override
	public List<PostDto> allPosts() {
		
		List<Post> allPosts = postRepository.findAll();
		
		
		return allPosts.stream().map( post -> maptoDto(post)).collect(Collectors.toList());
	}
	
	public PostDto maptoDto(Post post)
	{
		PostDto postDto=new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		return postDto;
	}

	
	
	
}
