package com.pranav.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pranav.entity.Post;
import com.pranav.exception.ResourceNotFoundException;
import com.pranav.payload.PostDto;
import com.pranav.payload.PostResponse;
import com.pranav.repository.PostRepository;
import com.pranav.service.PostService;
import org.springframework.data.repository.PagingAndSortingRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		Post post = new Post();
		BeanUtils.copyProperties(postDto, post);
		Post savedPost = postRepository.save(post);

		PostDto savedDto = new PostDto();
		BeanUtils.copyProperties(savedPost, savedDto);

		return savedDto;
	}

	@Override
	public PostResponse allPosts(int pageNo, int pageSize) {

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		
		Page<Post> allPostsPageing = postRepository.findAll(pageable);
		
		// To get the Content of all the Post
		List<Post> allPosts = allPostsPageing.getContent();

		List<PostDto> allPostDto = allPosts.stream().map(post -> maptoDto(post)).collect(Collectors.toList());
		
		// setting the value for the Page Response with Pagination
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(allPostDto);
		postResponse.setPageNo(allPostsPageing.getNumber());
		postResponse.setPageSize(allPostsPageing.getSize());
		postResponse.setTotalElements(allPostsPageing.getTotalElements());
		postResponse.setLastPage(allPostsPageing.isLast());
		postResponse.setTotalPages(allPostsPageing.getTotalPages());
		
		
		
		return postResponse;
	}

	public PostDto maptoDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		return postDto;
	}

	@Override
	public PostDto getPostById(Long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		PostDto dto = new PostDto();
		BeanUtils.copyProperties(post, dto);
		return dto;
	}

	@Override
	public PostDto updatePost(Long id, PostDto postDto) {

		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatedPost = postRepository.save(post);
		PostDto updatedDto = new PostDto();
		BeanUtils.copyProperties(post, updatedDto);

		return updatedDto;
	}

	@Override
	public String deletePost(Long id) {
		// TODO Auto-generated method stub
		getPostById(id);

		postRepository.deleteById(id);
		return "Post with Id:" + id + " Deleted Successfully!";
	}

}
