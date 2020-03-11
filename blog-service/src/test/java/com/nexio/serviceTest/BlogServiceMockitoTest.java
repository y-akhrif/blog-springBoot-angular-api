package com.nexio.serviceTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.nexio.blogapp.model.Blog;
import com.nexio.service.BlogService;


public class BlogServiceMockitoTest {

	
	private BlogService blogService;

    
    @Before
    public void setUp() {

    	blogService = mock(BlogService.class);
    }
    
    @Test
    public void findAllBlogsTest(){
    	
    	List<Blog> blogs = new ArrayList<Blog>();
		when(blogService.findAll()).thenReturn(blogs);
		List<Blog> returned = blogService.findAll();
        assertEquals(blogs, returned);
    }
    
    @Test
    public void findAllfindByIdTest(){
    	
    	Blog blog = new Blog();
		when(blogService.findById(5)).thenReturn(blog);
		Blog returned = blogService.findById(5);
        assertEquals(blog, returned);
    }
    
    @Test
    public void createBlockTest(){
    	
    	Blog blog = new Blog();
		when(blogService.create(blog)).thenReturn(blog);
		Blog returned = blogService.create(blog);
        assertEquals(blog, returned);
    }
    
    @Test
    public void updateBlogTest(){
    	
    	Blog blog = new Blog();
		when(blogService.update(blog)).thenReturn(blog);
		Blog returned = blogService.update(blog);
        assertEquals(blog, returned);
    }
    
    @Test
    public void deleteBlogTest(){
    	
    	Blog blog = new Blog();
		when(blogService.delete(1)).thenReturn(blog);
		Blog returned = blogService.delete(1);
        assertEquals(blog, returned);
    }
    
    
    
}
