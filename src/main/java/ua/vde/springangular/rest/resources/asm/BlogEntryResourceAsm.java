package ua.vde.springangular.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ua.vde.springangular.core.entry.BlogEntry;
import ua.vde.springangular.rest.mvc.controller.BlogEntryController;
import ua.vde.springangular.rest.resources.BlogEntryResources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResources> {

    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResources.class);
    }

    @Override
    public BlogEntryResources toResource(BlogEntry blogEntry) {

        BlogEntryResources resources = new BlogEntryResources();
        resources.setTitle(blogEntry.getTitle());

        Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        resources.add(link.withSelfRel());

        return resources;
    }
}
