package springangular.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.model.entity.BlogEntry;
import springangular.core.services.BlogService;
import springangular.core.services.util.BlogList;
import springangular.rest.mvc.controller.BlogController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogControllerTest {

    @InjectMocks
    private BlogController controller;

    @Mock
    private BlogService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindAllBlogs() throws Exception {
        List<Blog> blogs = new ArrayList<Blog>();
        Blog blog1 = new Blog();
        blog1.setId(1L);
        blog1.setTitle("Title 1");
        blogs.add(blog1);

        blog1 = new Blog();
        blog1.setId(2L);
        blog1.setTitle("Title 2");
        blogs.add(blog1);

        BlogList allBlogs = new BlogList(blogs);
//        allBlogs.setBlogs(blogs);

        when(service.findAllBlogs()).thenReturn(allBlogs);
        mockMvc.perform(get("/rest/blogs"))
                .andDo(print())
                .andExpect(jsonPath("$.blogs[*].title", hasItems(endsWith("Title 1"), endsWith("Title 2"))))
                .andExpect(jsonPath("$.blogs[*].links[*].href", hasItems(endsWith("entries"))))
        .andExpect(status().isOk());
    }

    @Test
    public void getBlog() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("First blog");

        Account account = new Account();
        account.setId(101L);
        blog.setOwner(account);

        when(service.findBlog(1L)).thenReturn(blog);

        mockMvc.perform(get("/rest/blogs/1"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItems(endsWith("/blogs/1"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1/blog-entries"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/101"))))
                .andExpect(jsonPath("$.links[*].rel", hasItems(is("self"), is("owner"), is("entries"))))
                .andExpect(jsonPath("$.title", is("First blog")))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateBlogEntry() throws Exception {

        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1L);
        blogEntry.setTitle("Entry title");

        when(service.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(blogEntry);

        mockMvc.perform(post("/rest/blogs/1/blog-entries")
        .content("{\"title\":\"Generic Title\"}")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
        .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
        .andExpect(jsonPath("$.links[*].href", hasItems(endsWith("rest/blog-entries/1"))))
        .andExpect(header().string("Location", endsWith("rest/blog-entries/1")))
        .andExpect(status().isCreated());
    }
}
