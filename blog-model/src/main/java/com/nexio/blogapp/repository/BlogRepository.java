package com.nexio.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexio.blogapp.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	 void delete(Blog blog);

	 List<Blog> findAll();

	 Blog findById(int id);

	 Blog save(Blog blog);
	 
}
