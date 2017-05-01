package springangular.core.services.util;

import springangular.core.model.entity.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5520 on 01.05.2017.
 */
public class AccountList {

    private List<Account> accountList = new ArrayList<>();

    public AccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
