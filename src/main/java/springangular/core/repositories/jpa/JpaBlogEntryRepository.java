package springangular.core.repositories.jpa;

import springangular.core.model.entity.BlogEntry;
import springangular.core.repositories.BlogEntryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public class JpaBlogEntryRepository implements BlogEntryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BlogEntry> findAllBlogEntriesByBlogId(Long blogId) {

        Query query = em.createQuery("select b from BlogEntry b where b.blog.id=?1");
        query.setParameter(1, blogId);

        return query.getResultList();
    }

    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        em.persist(data);
        return data;
    }

    @Override
    public BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data) {

        BlogEntry blogEntry = em.find(BlogEntry.class, blogEntryId);
        blogEntry.setTitle(data.getTitle());
        blogEntry.setContent(data.getContent());
        em.merge(blogEntry);

        return blogEntry;
    }

    @Override
    public BlogEntry deleteBlogEntry(Long blogEntryId) {
        BlogEntry blogEntry = em.find(BlogEntry.class, blogEntryId);
        em.remove(blogEntry);
        return blogEntry;
    }

    @Override
    public BlogEntry findBlogEntryById(Long blogEntryId) {
        return em.find(BlogEntry.class, blogEntryId);
    }
}
