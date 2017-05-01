package springangular.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import springangular.core.model.entity.Account;
import springangular.rest.mvc.controller.AccountController;
import springangular.rest.resources.AccountResource;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * Created by E5520 on 26.03.2017.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setUsername(account.getUsername());
        resource.setPassword(account.getPassword());
        resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withSelfRel());
        return resource;
    }
}
