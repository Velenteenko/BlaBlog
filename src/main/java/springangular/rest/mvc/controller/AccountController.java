package springangular.rest.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springangular.core.model.entity.Account;
import springangular.core.model.entity.Blog;
import springangular.core.services.AccountService;
import springangular.core.services.exceptions.AccountDoesnotExistException;
import springangular.core.services.exceptions.AccountExistsException;
import springangular.core.services.exceptions.BlogExistsException;
import springangular.core.services.util.AccountList;
import springangular.core.services.util.BlogList;
import springangular.rest.exceptions.BadRequestExeption;
import springangular.rest.exceptions.ConflictException;
import springangular.rest.exceptions.NotFoundException;
import springangular.rest.resources.AccountListResource;
import springangular.rest.resources.AccountResource;
import springangular.rest.resources.BlogListResource;
import springangular.rest.resources.BlogResource;
import springangular.rest.resources.asm.AccountListResourceAsm;
import springangular.rest.resources.asm.AccountResourceAsm;
import springangular.rest.resources.asm.BlogListResourceAsm;
import springangular.rest.resources.asm.BlogResourceAsm;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by E5520 on 26.03.2017.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "name", required = false) String name) {

        AccountList list = null;
        if (name == null) {
            list = accountService.findAllAccounts();
        }
        else {
            Account account = accountService.findByAccountName(name);
            if (account == null) {
                list = new AccountList(new ArrayList<Account>());
            }
            else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource listResource = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(listResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource accountResource = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(accountResource.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(accountResource,httpHeaders, HttpStatus.CREATED);
        } catch(AccountExistsException ex) {
            throw new ConflictException(ex);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {

        Account account = accountService.findAccount(accountId);
        if(account != null) {
            AccountResource resource = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource res) {

        try {
            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
            BlogResource createdBlogResource = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogResource.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogResource, headers, HttpStatus.CREATED);
        }catch(AccountDoesnotExistException ex) {
            throw new BadRequestExeption(ex);
        }catch(BlogExistsException ex) {
            throw new ConflictException(ex);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs",method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(@PathVariable Long accountId) {

        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogList);

            return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
        }
        catch(AccountDoesnotExistException ex) {
            throw new NotFoundException(ex);
        }
    }
}
