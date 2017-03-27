package springangular.core.repositories;

import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;

/**
 * Created by E5520 on 27.03.2017.
 */
public interface AccountRepository {
    Account findAccount(Long accountId);
    Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);
}
