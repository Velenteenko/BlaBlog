package springangular.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public class AccountListResource extends ResourceSupport {

    private List<AccountResource> accounts = new ArrayList<AccountResource>();

    public List<AccountResource> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResource> accounts) {
        this.accounts = accounts;
    }
}
