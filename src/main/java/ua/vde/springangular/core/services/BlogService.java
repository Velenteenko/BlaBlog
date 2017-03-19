package ua.vde.springangular.core.services;

import ua.vde.springangular.core.entry.Blog;
import ua.vde.springangular.core.entry.BlogEntry;
import ua.vde.springangular.core.services.util.BlogEntryList;
import ua.vde.springangular.core.services.util.BlogList;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface BlogService {

    BlogEntry createBlogEntry(Long blogId, BlogEntry data);
    BlogList findAllBlogs();
    BlogEntryList findAllBlogEntries(Long blogId);
    Blog findBlog(Long id);

}
