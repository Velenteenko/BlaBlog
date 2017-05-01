package springangular.rest.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springangular.core.model.entity.BlogEntry;
import springangular.core.services.BlogEntryService;
import springangular.rest.resources.BlogEntryResource;
import springangular.rest.resources.asm.BlogEntryResourceAsm;

/**
 * Created by E5520 on 19.03.2017.
 */
@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    @Autowired
    public BlogEntryController(BlogEntryService service) {
        this.service = service;
    }


    @RequestMapping(value = "/{blogEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntryById (@PathVariable final Long blogEntryId) {
        BlogEntry blogEntry = service.findBlogEntry(blogEntryId);
        if (blogEntry != null) {
            BlogEntryResource res = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntryById(@PathVariable final Long blogEntryId) {
        BlogEntry blogEntry = service.deleteBlogEntry(blogEntryId);

        if(blogEntry != null) {
            BlogEntryResource resources = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResource>(resources, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntryById(@PathVariable final Long blogEntryId,
                                                                 @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry blogEntry = service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());

        if(blogEntry != null) {
            BlogEntryResource resources = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResource>(resources, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

}
