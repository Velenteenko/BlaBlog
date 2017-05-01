package springangular.core.repositories;

import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;

import java.util.List;

/**
 * Created by E5520 on 27.03.2017.
 */
public interface AccountRepository {

    List<Account> findAllAccounts();
    Account findAccount(Long accountId);
    Account findAccountByName(String usernameAccount);
    Account createAccount(Account data);
//    Blog createBlog(Long accountId, Blog data);
}
