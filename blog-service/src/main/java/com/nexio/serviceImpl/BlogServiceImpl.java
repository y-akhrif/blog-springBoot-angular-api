package com.nexio.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nexio.blogapp.model.Blog;
import com.nexio.blogapp.repository.BlogRepository;
import com.nexio.service.BlogService;

@Service
@Component
public class BlogServiceImpl implements BlogService{

	private static final Logger logger = LogManager.getLogger(BlogServiceImpl.class);
	
	@Autowired
	private BlogRepository repository;
	
	
	public List<Blog> findAll() {
		
		logger.info("List of all blogs");
		return repository.findAll();
	}

	
	public Blog findById(int id) {
		
		return repository.findById(id);
	}


	public Blog create(Blog blog) {
		logger.debug("Tryring to create the blog {%s}",blog.getTitle());
		Date createdDate = new Date ();
		blog.setCreatedAt(createdDate);
		blog.setUpdatedAt(createdDate);
		return  repository.save(blog);
	}

	public Blog update(Blog blog) {
		Date updatedDate = new Date ();
		blog.setUpdatedAt(updatedDate);
		return  repository.save(blog);
	}

	public Blog delete(int id) {
		Blog blog = findById(id);
        if(blog != null){
            repository.delete(blog);
        }
        return blog;
	}

}
