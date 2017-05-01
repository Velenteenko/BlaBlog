package springangular.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import springangular.core.model.entity.Account;
import springangular.core.repositories.AccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by E5520 on 27.03.2017.
 */
@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAllAccounts() {

        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    @Override
    public Account findAccountByName(String usernameAccount) {

        Query query = em.createQuery("SELECT a FROM Account a WHERE a.username=?1");
        query.setParameter(1, usernameAccount);

        List<Account> accountList = query.getResultList();
        if (accountList.size() == 0) {
            return null;
        }
        else {
            return accountList.get(0);
        }
    }

    @Override
    public Account findAccount(Long accountId) {
        return em.find(Account.class, accountId);
    }

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }
}
