package springangular.rest.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springangular.core.entry.Account;
import springangular.core.entry.Blog;
import springangular.core.entry.BlogEntry;
import springangular.core.services.AccountService;
import springangular.core.services.exceptions.AccountDoesnotExistException;
import springangular.core.services.exceptions.AccountExistsException;
import springangular.core.services.exceptions.BlogExistsException;
import springangular.rest.exceptions.BadRequestExeption;
import springangular.rest.exceptions.ConflictException;
import springangular.rest.resources.AccountResource;
import springangular.rest.resources.BlogResource;
import springangular.rest.resources.asm.AccountResourceAsm;
import springangular.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

/**
 * Created by E5520 on 26.03.2017.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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
}
