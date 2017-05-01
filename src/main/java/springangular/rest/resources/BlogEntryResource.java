package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import springangular.core.model.entity.BlogEntry;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle(title);
        return blogEntry;
    }
}
