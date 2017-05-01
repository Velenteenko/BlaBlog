package springangular.core.repositories;

import springangular.core.model.entity.Blog;

import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public interface BlogRepository {

     Blog createBlog(Blog data);
     List<Blog> findAllBlogs();
     Blog findBlog(Long blogId);
     Blog findBlogByTitle(String titleBlog);
    List<Blog> findBlogsByAccountId(Long accountId);

}
