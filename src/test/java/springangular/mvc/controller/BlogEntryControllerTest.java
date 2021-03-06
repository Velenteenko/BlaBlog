package springangular.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springangular.core.model.entity.Blog;
import springangular.core.model.entity.BlogEntry;
import springangular.core.services.BlogEntryService;
import springangular.rest.mvc.controller.BlogEntryController;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
