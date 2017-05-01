package springangular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.repositories.AccountRepository;
import springangular.core.repositories.BlogRepository;
import springangular.core.services.AccountService;
import springangular.core.services.exceptions.AccountDoesnotExistException;
import springangular.core.services.exceptions.AccountExistsException;
import springangular.core.services.exceptions.BlogExistsException;
import springangular.core.services.util.AccountList;
import springangular.core.services.util.BlogList;
import springangular.rest.mvc.controller.AccountController;
import springangular.rest.mvc.controller.BlogController;
import springangular.rest.mvc.controller.BlogEntryController;

/**
 * Created by E5520 on 01.05.2017.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BlogRepository blogRepository;


    @Override
    public Account findAccount(Long accountId) {
        return accountRepository.findAccount(accountId);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepository.findAccountByName(data.getUsername());
        if (account != null) {
            throw new AccountExistsException();
        }
        return accountRepository.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blog = blogRepository.findBlogByTitle(data.getTitle());

        if (blog != null) {
            throw new BlogExistsException();
        }

        Account account = accountRepository.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesnotExistException();
        }

        Blog createdBlog = blogRepository.createBlog(data);
        createdBlog.setOwner(account);

        return createdBlog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepository.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesnotExistException();
        }

        return new BlogList(blogRepository.findBlogsByAccountId(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepository.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountRepository.findAccountByName(accountName);
    }
}
