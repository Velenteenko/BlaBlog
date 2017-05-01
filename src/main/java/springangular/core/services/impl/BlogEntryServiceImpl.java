package springangular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import springangular.core.model.entity.BlogEntry;
import springangular.core.repositories.BlogEntryRepository;
import springangular.core.services.BlogEntryService;

/**
 * Created by E5520 on 01.05.2017.
 */
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @Override
    public BlogEntry findBlogEntry(Long blogEntryId) {
        return blogEntryRepository.findBlogEntryById(blogEntryId);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long blogEntryId) {
        return blogEntryRepository.deleteBlogEntry(blogEntryId);
    }

    @Override
    public BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data) {
        return blogEntryRepository.updateBlogEntry(blogEntryId, data);
    }
}
