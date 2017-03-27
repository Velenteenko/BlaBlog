package springangular.core.services;

import springangular.core.entry.Account;
import springangular.core.entry.Blog;

/**
 * Created by E5520 on 19.03.2017.
 */
public interface AccountService {

     Account findAccount(Long accountId);
     Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);

}
