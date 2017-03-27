package springangular.core.services;

import springangular.core.entry.Blog;
import springangular.core.entry.BlogEntry;
import springangular.core.services.util.BlogList;
import springangular.core.services.util.BlogEntryList;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface BlogService {

    BlogEntry createBlogEntry(Long blogId, BlogEntry data);
    BlogList findAllBlogs();
    BlogEntryList findAllBlogEntries(Long blogId);
    Blog findBlog(Long id);

}
