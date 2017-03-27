package springangular.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.repositories.AccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by E5520 on 27.03.2017.
 */
@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account findAccount(Long accountId) {
        return em.find(Account.class, accountId);
    }

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        return null;
    }
}
