package com.nexio.service;

import java.util.List;

import com.nexio.blogapp.model.Blog;


public interface BlogService  {

	List<Blog> findAll();
	
	Blog findById(int id);
	
	Blog create(Blog blog);
	
	Blog update(Blog blog);
	
	Blog delete(int id);
	
}
