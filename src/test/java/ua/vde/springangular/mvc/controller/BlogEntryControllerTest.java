package ua.vde.springangular.mvc.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.vde.springangular.core.entry.Blog;
import ua.vde.springangular.core.entry.BlogEntry;
import ua.vde.springangular.core.services.BlogEntryService;
import ua.vde.springangular.rest.mvc.controller.BlogEntryController;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController mainController;

    @Mock
    private BlogEntryService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    @Ignore
    public void testMainController() throws Exception {
        mockMvc.perform(post("/").content("{\"title\":\"Test Blog JSON\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Test Blog JSON")))
                .andDo(print());
    }

    @Test
    public void testGetExistingBlogEntry() throws Exception {

        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1L);
        blogEntry.setTitle("Test title for blog 1");

        Blog blog = new Blog();
        blog.setId(1L);

        blogEntry.setBlog(blog);

        when(service.findBlogEntry(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItems(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingBlogEntry() throws Exception {

        when(service.findBlogEntry(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
