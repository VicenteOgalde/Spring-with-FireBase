package com.vicente.post.controller;



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

import com.vicente.post.DTO.PostDTO;
import com.vicente.post.service.PostManagementImpl;

@RestController
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostManagementImpl postManagementImpl;
	
	@GetMapping(value="/list")
	public ResponseEntity listPost(){
		return new ResponseEntity(postManagementImpl.listPost(),HttpStatus.OK);
	}
	@PostMapping(value= "/add")
	public ResponseEntity addPost(@RequestBody PostDTO post) {
		return new ResponseEntity(postManagementImpl.addPost(post),HttpStatus.OK);
	}
	@PutMapping(value = "/update/{id}")
	public ResponseEntity updatePost(@PathVariable(value="id")String id, @RequestBody PostDTO post){
		return new ResponseEntity(postManagementImpl.updatePost(id, post),HttpStatus.OK);
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity deletePost(@PathVariable(value="id")String id) {
		return new ResponseEntity(postManagementImpl.deletePost(id), HttpStatus.OK);
		
	}
	
}
