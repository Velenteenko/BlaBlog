package springangular.rest.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springangular.core.entry.Blog;
import springangular.core.entry.BlogEntry;
import springangular.core.services.BlogService;
import springangular.core.services.exceptions.BlogNotFoundException;
import springangular.core.services.util.BlogEntryList;
import springangular.core.services.util.BlogList;
import springangular.rest.exceptions.NotFoundException;
import springangular.rest.resources.BlogEntryListResource;
import springangular.rest.resources.BlogEntryResource;
import springangular.rest.resources.BlogListResource;
import springangular.rest.resources.BlogResource;
import springangular.rest.resources.asm.BlogEntryListResourceAsm;
import springangular.rest.resources.asm.BlogEntryResourceAsm;
import springangular.rest.resources.asm.BlogListResourceAsm;
import springangular.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

/**
 * Created by E5520 on 26.03.2017.
 */
@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs() {
        BlogList blogList = blogService.findAllBlogs();
        BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogList);
        return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
    }

    @RequestMapping(value="/{blogId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId)
    {
        Blog blog = blogService.findBlog(blogId);
        BlogResource res = new BlogResourceAsm().toResource(blog);
        return new ResponseEntity<BlogResource>(res, HttpStatus.OK);
    }

    @RequestMapping(value="/{blogId}/blog-entries",
            method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            @PathVariable Long blogId,
            @RequestBody BlogEntryResource sentBlogEntry
    ) {
        BlogEntry createdBlogEntry = null;
        try {
            createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResource createdResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value="/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
            @PathVariable Long blogId)
    {
        try {
            BlogEntryList list = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
        } catch(BlogNotFoundException exception)
        {
            throw new NotFoundException(exception);
        }
    }
}
