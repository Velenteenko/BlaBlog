package springangular.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import springangular.core.services.util.AccountList;
import springangular.rest.mvc.controller.AccountController;
import springangular.rest.resources.AccountListResource;
import springangular.rest.resources.AccountResource;

import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accounts) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accounts.getAccountList());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
