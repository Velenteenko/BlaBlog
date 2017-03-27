package springangular.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import springangular.core.entry.Blog;
import springangular.rest.mvc.controller.AccountController;
import springangular.rest.mvc.controller.BlogController;
import springangular.rest.resources.BlogResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource> {

    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource resource = new BlogResource();
        resource.setTitle(blog.getTitle());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("entries").withRel("entries"));

        if(blog.getOwner() != null) {
            resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        }

        return resource;
    }
}
