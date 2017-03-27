package springangular.core.repositories.jpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springangular.core.model.entity.Account;
import springangular.core.repositories.AccountRepository;

/**
 * Created by E5520 on 27.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    private Account account;

    @Before
    @Transactional
    @Rollback(value = false)
    public void setup() {
        account = new Account();
        account.setUsername("test");
        account.setPassword("test");
        repository.createAccount(account);
    }
    @Test
    @Transactional
    public void someTest() {
        Assert.assertNotNull(repository.findAccount(account.getId()));
    }
}
