package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogListResource extends ResourceSupport {

    private List<BlogResource> blogs = new ArrayList<>();

    public List<BlogResource> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResource> blogs) {
        this.blogs = blogs;
    }
}
