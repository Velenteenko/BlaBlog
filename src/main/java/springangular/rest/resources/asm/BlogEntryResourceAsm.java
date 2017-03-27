package springangular.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import springangular.core.entry.BlogEntry;
import springangular.rest.mvc.controller.BlogController;
import springangular.rest.mvc.controller.BlogEntryController;
import springangular.rest.resources.BlogEntryResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {

        BlogEntryResource resources = new BlogEntryResource();
        resources.setTitle(blogEntry.getTitle());

        Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        resources.add(link.withSelfRel());
        if(blogEntry.getBlog() != null) {
            resources.add(linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog"));
        }

        return resources;
    }
}
