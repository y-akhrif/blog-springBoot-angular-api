package com.nexio.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nexio.blogapp.model.Comment;
import com.nexio.blogapp.repository.CommentRepository;
import com.nexio.service.CommentService;

@Service
@Component
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository repository;

	
	public List<Comment> findAll() {
		
		return repository.findAll();
	}
	
	public List<Comment> findCommentsByBlogId(int blogtId){
		
		return repository.findByBlogId(blogtId);
	}

	public Comment findById(int id) {
		return repository.findById(id);
	}

	
	public Comment create(Comment comment) {
		Date createdDate = new Date ();
		comment.setCreatedAt(createdDate);
		comment.setUpdatedAt(createdDate);
		return  repository.save(comment);
	}

	public Comment update(Comment comment) {
		Date updateDate = new Date ();
		comment.setUpdatedAt(updateDate);
		return  repository.save(comment);
	}

	
	public Comment delete(int id) {
		Comment comment = findById(id);
        if(comment != null){
            repository.delete(comment);
        }
        return comment;
	}

}
