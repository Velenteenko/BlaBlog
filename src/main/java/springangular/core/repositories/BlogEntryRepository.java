package springangular.core.repositories;

import springangular.core.model.entity.BlogEntry;

import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public interface BlogEntryRepository {

    List<BlogEntry> findAllBlogEntriesByBlogId(Long blogId);
    BlogEntry createBlogEntry(BlogEntry data);
    BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data);
    BlogEntry deleteBlogEntry(Long blogEntryId);
    BlogEntry findBlogEntryById(Long blogEntryId);

}
