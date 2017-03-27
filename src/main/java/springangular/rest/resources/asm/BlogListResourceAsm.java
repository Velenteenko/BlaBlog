package springangular.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import springangular.core.services.util.BlogList;
import springangular.rest.mvc.controller.BlogController;
import springangular.rest.resources.BlogListResource;

import java.util.List;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource blogListResource = new BlogListResource();
        blogListResource.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return blogListResource;
    }
}
