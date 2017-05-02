package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import springangular.core.model.entity.BlogEntry;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryResource extends ResourceSupport {

    private String title;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle(title);
        blogEntry.setContent(content);
        return blogEntry;
    }
}
