package com.nexio.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexio.blogapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	 void delete(Comment comment);

	 List<Comment> findAll();

	 Comment findById(int id);

	 Comment save(Comment comment);
	 
	 List<Comment> findByBlogId(int blogId);
	 

}
