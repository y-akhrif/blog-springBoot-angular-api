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
import com.nexio.blogapp.model.Comment;
import com.nexio.dto.CommentDto;
import com.nexio.exception.BusinessException;
import com.nexio.service.BlogService;
import com.nexio.service.CommentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentController {

	@Autowired
    private CommentService commentService;
	
	@Autowired
    private BlogService blogService;

	private static final Logger logger = LogManager.getLogger(CommentController.class);

	@PostMapping(path = {"post/{id}/comment"})
	public Comment addComment(@RequestBody CommentDto commentDto, @PathVariable("id") int blogId) throws ParseException{
		
		logger.info("addComment() - Trying to add new comment to the blog  {} ",blogId);
		Blog blog = blogService.findById(blogId);
		if (blog==null){
			logger.debug("addComment() - No blog found with id {}", blogId);
	   		throw new BusinessException("No blog found with id " + blogId);
		}
		commentDto.setBlogId(blogId);
	    Comment comment =  commentService.create(converDtotToEntity(commentDto));
	    if (comment==null){
       	 	logger.error("addComment() - an issue has been occured during creating the blog {}", blogId);
	   		throw new BusinessException("an issue has been occured during creating the blog" + blogId);
	    }
    	logger.info("addComment() - the comment  {} has been created successfully", comment.getId());
	    return comment;
	}

    @GetMapping(path = {"/post/{id}/comment"})
    public CommentDto findbyId(@PathVariable("id") int id){
    	Comment comment = commentService.findById(id);
    	if (comment==null){
    		logger.debug("findbyId() - No comment found with id {}", id);
	   		throw new BusinessException("No comment found with id " + id);
    	}
        return converEntitytToDto(comment);
    }

    @PutMapping(path = {"/post/{id}/comments"})
    public Comment updateComment(@PathVariable("id") int id, @RequestBody CommentDto commentDto) throws ParseException{
   	 
        logger.info("updateComment() - Tryinng to update the comment with id {} ",id);
        Comment comment = commentService.findById(id);
        if (comment==null){
        	logger.debug("updateComment() - No comment found with id {}", id);
      		throw new BusinessException("No comment found with id " + id);

        }
        commentDto.setId(id);
        comment = commentService.update(converDtotToEntity(commentDto));
    	if (comment == null){
    		 logger.error("updateComment() - an issue has been occured during updating the comment with id {}", id);
       		 throw new BusinessException("Issue has been occured ");
    	}
   	 	logger.info("updateBlog() - the comment with id {} has been updated successfully", id);

        return comment;
    }

    @DeleteMapping(path ={"/post/{id}/comments"})
    public Comment deleteComment(@PathVariable("id") int id) {
    	
    	logger.info("deleteBlog() - Tryinng to delete the comment with id {} ",id);
    	Comment comment = commentService.findById(id);
    	if (comment == null){
   		 	logger.error("updateComment() - an issue has been occured during updating the comment with id {}", id);
      		 throw new BusinessException( "Issue has been occured ");
    	}
        return commentService.delete(id);
    }
    
    @GetMapping(path = {"/post/{id}/comments"})
    public List<CommentDto> listCommentByBlog(@PathVariable("id") int id){
    	
    	List<Comment> comments = commentService.findCommentsByBlogId(id);
    	
    	logger.info("listCommentByBlog() - return list of all comment of the blog {}, {} items have are found",id, comments.size());

    	return comments.stream()
  	          .map(comment -> converEntitytToDto(comment))
  	          .collect(Collectors.toList());
    }
    
    /**
     * @param comment
     * @return commentDto
     * This function allow to convert Comment to CommentDto
     */
    private CommentDto converEntitytToDto(Comment comment) {
    	
    	CommentDto commentDto = new CommentDto();  
    	BeanUtils.copyProperties(comment, commentDto, new String[] {"createdAt", "updatedAt"});
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
    	
		// Convert the dates to strings
		if (comment.getCreatedAt()!=null){    		
            calendar.setTime(comment.getCreatedAt());
            dateFormat.setTimeZone(calendar.getTimeZone());
            commentDto.setCreatedAt(dateFormat.format(comment.getCreatedAt()));
    	}
    	
    	if (comment.getUpdatedAt()!=null){
            calendar.setTime(comment.getUpdatedAt());
            dateFormat.setTimeZone(calendar.getTimeZone());
            commentDto.setUpdatedAt(dateFormat.format(comment.getUpdatedAt()));
    	}
    	   
        return commentDto;
    }
    
    /**
     * @param commentDto
     * @return comment
     *  This function allow to convert CommentDto to Comment
     * @throws ParseException 
     */
    private Comment converDtotToEntity(CommentDto commentDto) throws ParseException {
    	 	
    	Comment comment = new Comment();  
    	BeanUtils.copyProperties(commentDto, comment, new String[] {"createdAt", "updatedAt","blog"});
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	// Treat the Case of new comment
    	if (commentDto.getId() != 0 ) {
            Comment commentOld = commentService.findById(commentDto.getId());
            comment.setId(commentDto.getId());
            comment.setBlog(commentOld.getBlog()); 
        
       // Treat the case of updating a comment
        }else if (!"".equals(commentDto.getBlogId())  ) {
            Blog blog = blogService.findById(commentDto.getBlogId()); 
            comment.setBlog(blog);
        }
    	// Convert the strings to dates
		if (!"".equals(commentDto.getCreatedAt()) && commentDto.getCreatedAt()!=null){    		
			comment.setCreatedAt(dateFormat.parse(commentDto.getCreatedAt()));
    	}
    	
    	if (!"".equals(commentDto.getUpdatedAt())  &&  commentDto.getUpdatedAt()!=null){
    		comment.setUpdatedAt(dateFormat.parse(commentDto.getUpdatedAt()));
    	}
    	
    	
        return comment;
    }
    
}

