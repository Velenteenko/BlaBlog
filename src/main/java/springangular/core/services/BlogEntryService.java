package springangular.core.services;

import springangular.core.entry.BlogEntry;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface BlogEntryService {

    BlogEntry findBlogEntry(Long blogEntryId);
    BlogEntry deleteBlogEntry(Long blogEntryId);
    BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data);
}
