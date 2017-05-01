package springangular.core.services;

import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.services.util.AccountList;
import springangular.core.services.util.BlogList;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface AccountService {

     Account findAccount(Long accountId);
     Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);
    BlogList findBlogsByAccount(Long accountId);
    AccountList findAllAccounts();
    Account findByAccountName(String accountName);

}
