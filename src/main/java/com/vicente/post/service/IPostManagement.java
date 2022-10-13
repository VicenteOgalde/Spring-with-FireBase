package com.vicente.post.service;

import java.util.List;

import com.vicente.post.DTO.PostDTO;

public interface IPostManagement {

	List<PostDTO> listPost();
	Boolean addPost(PostDTO post);
	Boolean updatePost(String id,PostDTO post);
	Boolean deletePost(String id);
}
