package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.web.model.AccountDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:23 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AccountMapper {

    Account accountDtoToAccount(AccountDto accountDto);
    AccountDto accountToAccountDto(Account account);

    Set<Account> accountDtosToAccount(Set<AccountDto> accountDtos);
    Set<AccountDto> accountToAccountDtos(Set<Account> accounts);
}
