package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import springangular.core.entry.Blog;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(title);
        return blog;
    }
}
