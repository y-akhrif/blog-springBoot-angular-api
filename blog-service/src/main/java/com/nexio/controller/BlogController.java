package com.nexio.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexio.blogapp.model.Blog;
import com.nexio.dto.BlogDto;
import com.nexio.exception.BusinessException;
import com.nexio.service.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BlogController {

	private static final Logger logger = LogManager.getLogger(BlogController.class);

	@Autowired
    private BlogService blogService;	

	
	@PostMapping(path = {"/post"})
    public Blog createBlog(@RequestBody BlogDto blogDto) throws ParseException{
		
		logger.info("createBlog() - Creating a new blog with title {} ",blogDto.getTitle());
        Blog blog = blogService.create(converDtotToEntity( blogDto));
        if (blog==null){
        	 logger.error("createBlog() - an issue has been occured during creating the blog {}", blogDto.getTitle());
      		  throw new BusinessException("An issue has been occured during creating the blog "+ blogDto.getTitle());
       	}
    	logger.info("createBlog() - the blog  {} has been created successfully", blogDto.getTitle());
        return blog;

    }

    @GetMapping(path = {"/post/{id}"})
    public BlogDto findBlog(@PathVariable("id") int id) {
    	
		logger.info("findBlog() - trying to find the blog with id {} ",id);

    	Blog blog = blogService.findById(id);
    	if (blog==null){
    		logger.debug("findBlog() - No blog found with id {}", id);
       		throw new BusinessException("No blog found with id "+1);
       	}
        return converEntitytToDto(blog);
    }

    @PutMapping(path = {"/post/{id}"})
    public Blog updateBlog(@PathVariable("id") int id, @RequestBody BlogDto blogDto) throws ParseException, BusinessException{
		 
    	 logger.info("updateBlog() - Tryinng to update the blog with id {} ",id);
    	 Blog blog = blogService.findById(id);
    	 if (blog==null){
           	 logger.debug("updateBlog() - No blog found with id {}", id);
       		 throw new BusinessException("No blog found with id " + id);
         }	
    	 blog = blogService.update(converDtotToEntity( blogDto));
         if (blog==null){
        	 logger.error("updateBlog() - an issue has been occured during updating the blog {}", blogDto.getTitle());
       		 throw new BusinessException("an issue has been occured during updating the blog "+blogDto.getTitle());
         }  
    	 logger.info("updateBlog() - the blog with id {} has been updated successfully", id);
         return blog;
    }

    @DeleteMapping(path ={"post/{id}"})
    public Blog deleteBlog(@PathVariable("id") int id) {
    	
    	logger.info("deleteBlog() - Tryinng to delete the blog with id {} ",id);
    	Blog blog = blogService.findById(id);
    	if (blog==null){
          	 logger.debug("deleteBlog() - No blog found with id {}", id);
       		 throw new BusinessException("No blog found with id " + id);
         }	
        return blogService.delete(id);
    }

    @GetMapping(path ={"posts"})
    public List<BlogDto> findAllBlogs(){
    	
    	List<Blog> blogs = blogService.findAll();
    	logger.info("findAllBlogs() - return list of all blogs, {} items have are found",blogs.size());
    	
    	return blogs.stream()
  	          .map(blog -> converEntitytToDto(blog))
  	          .collect(Collectors.toList());

    }

    /**
     * @param blog
     * @return blogDto
     * This function allow to convert Blog to BlogDto.
     * 
     */
    private BlogDto converEntitytToDto(Blog blog) {
    	
    	BlogDto blogDto = new BlogDto(); 	
    	BeanUtils.copyProperties(blog, blogDto, new String[] {"createdAt", "updatedAt"});
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
    	
		// Convert the dates to strings
		if (blog.getCreatedAt()!=null){    		
            calendar.setTime(blog.getCreatedAt());
            dateFormat.setTimeZone(calendar.getTimeZone());
            blogDto.setCreatedAt(dateFormat.format(blog.getCreatedAt()));
    	}
    	
    	if (blog.getUpdatedAt()!=null){
            calendar.setTime(blog.getUpdatedAt());
            dateFormat.setTimeZone(calendar.getTimeZone());
            blogDto.setUpdatedAt(dateFormat.format(blog.getUpdatedAt()));
    	}
    	   
        return blogDto;
    }
    
    /**
     * @param blogDto
     * @return blog
     *  This function allow to convert BlogDto to Blog
     * @throws ParseException 
     */
    private Blog converDtotToEntity(BlogDto blogDto) throws ParseException {
    	
    	Blog blog = new Blog();
    	BeanUtils.copyProperties(blogDto, blog, new String[] {"createdAt", "updatedAt"});
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	System.out.println("blogDto.getCreatedAt() "+ blogDto.getCreatedAt());
    	System.out.println("blogDto.getUpdatedAt() "+ blogDto.getUpdatedAt());
		// Convert the strings to dates
		if (!"".equals(blogDto.getCreatedAt()) && blogDto.getCreatedAt()!=null){    		
			blog.setCreatedAt(dateFormat.parse(blogDto.getCreatedAt()));
    	}
    	
    	if (!"".equals(blogDto.getUpdatedAt()) && blogDto.getUpdatedAt()!=null){
    		blog.setUpdatedAt(dateFormat.parse(blogDto.getUpdatedAt()));
    	}
    	   
        return blog;
    }
}
