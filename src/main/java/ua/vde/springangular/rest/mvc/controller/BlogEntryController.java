package ua.vde.springangular.rest.mvc.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.vde.springangular.core.entry.BlogEntry;
import ua.vde.springangular.core.services.BlogEntryService;
import ua.vde.springangular.rest.resources.BlogEntryResources;
import ua.vde.springangular.rest.resources.asm.BlogEntryResourceAsm;

/**
 * Created by E5520 on 19.03.2017.
 */
@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    public BlogEntryController(BlogEntryService service) {
        this.service = service;
    }


    @RequestMapping(value = "/{blogEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResources> getBlogEntryById (@PathVariable final Long blogEntryId) {
        BlogEntry blogEntry = service.findBlogEntry(blogEntryId);
        if (blogEntry != null) {
            BlogEntryResources res = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResources>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BlogEntryResources>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResources> deleteBlogEntryById(@PathVariable final Long blogEntryId) {
        BlogEntry blogEntry = service.deleteBlogEntry(blogEntryId);

        if(blogEntry != null) {
            BlogEntryResources resources = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResources>(resources, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BlogEntryResources>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResources> updateBlogEntryById(@PathVariable final Long blogEntryId,
                                                                  @RequestBody BlogEntryResources sentBlogEntry) {
        BlogEntry blogEntry = service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());

        if(blogEntry != null) {
            BlogEntryResources resources = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResources>(resources, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BlogEntryResources>(HttpStatus.NOT_FOUND);
        }
    }

}
