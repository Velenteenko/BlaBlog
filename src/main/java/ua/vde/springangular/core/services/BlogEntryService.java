package ua.vde.springangular.core.services;

import ua.vde.springangular.core.entry.BlogEntry;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface BlogEntryService {

    BlogEntry findBlogEntry(Long blogEntryId);
    BlogEntry deleteBlogEntry(Long blogEntryId);
    BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data);
}
