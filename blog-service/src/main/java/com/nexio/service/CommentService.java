package com.nexio.service;

import java.util.List;

import com.nexio.blogapp.model.Comment;

public interface CommentService {

	List<Comment> findAll();
	
	List<Comment> findCommentsByBlogId(int blogId);
	
	Comment findById(int id);
	
	Comment create(Comment comment);
	
	Comment update(Comment comment);
	
	Comment delete(int id);
}
