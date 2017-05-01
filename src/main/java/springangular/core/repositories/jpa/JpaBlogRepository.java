package springangular.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import springangular.core.model.entity.Blog;
import springangular.core.repositories.BlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
@Repository
public class JpaBlogRepository implements BlogRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Blog createBlog(Blog data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Blog> findAllBlogs() {

        Query query = em.createQuery("SELECT b FROM Blog b");

        return query.getResultList();
    }

    @Override
    public Blog findBlog(Long blogId) {
        return em.find(Blog.class, blogId);
    }

    @Override
    public Blog findBlogByTitle(String titleBlog) {

        Query query = em.createQuery("SELECT b FROM Blog b where b.title=?1");
        query.setParameter(1, titleBlog);
        List<Blog> blogList = query.getResultList();

        if (blogList.isEmpty()) {
            return null;
        }
        else {
            return blogList.get(0);
        }
    }

    @Override
    public List<Blog> findBlogsByAccountId(Long accountId) {

        Query query = em.createQuery("SELECT b FROM Blog b where b.owner.id=?1");
        query.setParameter(1, accountId);

        return query.getResultList();
    }

}
