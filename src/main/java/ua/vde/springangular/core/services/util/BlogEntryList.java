package ua.vde.springangular.core.services.util;

import ua.vde.springangular.core.entry.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogEntryList {

    private List<BlogEntry> entries = new ArrayList<>();
    private Long blogId;

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
