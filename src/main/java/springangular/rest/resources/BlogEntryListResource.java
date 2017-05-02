package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogEntryListResource extends ResourceSupport {

    private List<BlogEntryResource> entries;

    public List<BlogEntryResource> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntryResource> entries) {
        this.entries = entries;
    }
}
