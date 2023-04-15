package com.pranav.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "Post Title Cannot be empty, It should have atleast 2 characters" )
	private String title;
	
	@NotEmpty(message = "Please entert the description")
	@Size(min = 10, message = "Post Description Cannot be empty, It should have atleast 10 characters" )
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
}
