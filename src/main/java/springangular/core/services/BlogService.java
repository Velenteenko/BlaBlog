package springangular.core.services;

import springangular.core.model.entity.Blog;
import springangular.core.model.entity.BlogEntry;
import springangular.core.services.util.BlogEntryList;
import springangular.core.services.util.BlogList;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface BlogService {

    BlogEntry createBlogEntry(Long blogId, BlogEntry data);
    BlogList findAllBlogs();
    BlogEntryList findAllBlogEntries(Long blogId);
    Blog findBlog(Long id);

}
