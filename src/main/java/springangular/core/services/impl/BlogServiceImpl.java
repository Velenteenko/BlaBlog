package springangular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springangular.core.model.entity.Blog;
import springangular.core.model.entity.BlogEntry;
import springangular.core.repositories.BlogEntryRepository;
import springangular.core.repositories.BlogRepository;
import springangular.core.services.BlogService;
import springangular.core.services.exceptions.BlogNotFoundException;
import springangular.core.services.util.BlogEntryList;
import springangular.core.services.util.BlogList;

/**
 * Created by E5520 on 01.05.2017.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {

        Blog blog = blogRepository.findBlog(blogId);
        if (blog == null) {
            throw new BlogNotFoundException();
        }

        BlogEntry blogEntry = blogEntryRepository.createBlogEntry(data);
        blogEntry.setBlog(blog);

        return blogEntry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepository.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepository.findBlog(blogId);
        if (blog == null) {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogEntryRepository.findAllBlogEntriesByBlogId(blogId), blogId);
    }

    @Override
    public Blog findBlog(Long id) {
        return blogRepository.findBlog(id);
    }
}
