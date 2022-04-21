package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.web.model.AccountDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:32 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AccountService {

    Account createAccount(AccountDto accountDto);
    Account getAccountById(String accountId);
    Set<Account> getAllAccounts();
    Account updateAccount(AccountDto accountDto);
}
