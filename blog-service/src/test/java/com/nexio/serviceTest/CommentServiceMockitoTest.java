package com.nexio.serviceTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.nexio.blogapp.model.Comment;
import com.nexio.service.CommentService;


public class CommentServiceMockitoTest {

	
	private CommentService commentService;

    
    @Before
    public void setUp() {

    	commentService = mock(CommentService.class);
    }
    
    @Test
    public void findAllCommentsTest(){
    	
    	List<Comment> blogs = new ArrayList<Comment>();
		when(commentService.findAll()).thenReturn(blogs);
		List<Comment> returned = commentService.findAll();
        assertEquals(blogs, returned);
    }
    
    @Test
    public void findAllfindByIdTest(){
    	
    	Comment blog = new Comment();
		when(commentService.findById(5)).thenReturn(blog);
		Comment returned = commentService.findById(5);
        assertEquals(blog, returned);
    }
    
    @Test
    public void createCommentTest(){
    	
    	Comment blog = new Comment();
		when(commentService.create(blog)).thenReturn(blog);
		Comment returned = commentService.create(blog);
        assertEquals(blog, returned);
    }
    
    @Test
    public void updateBlockTest(){
    	
    	Comment blog = new Comment();
		when(commentService.update(blog)).thenReturn(blog);
		Comment returned = commentService.update(blog);
        assertEquals(blog, returned);
    }
    
    @Test
    public void deleteCommentTest(){
    	
    	Comment blog = new Comment();
		when(commentService.delete(1)).thenReturn(blog);
		Comment returned = commentService.delete(1);
        assertEquals(blog, returned);
    }
    
    
}
