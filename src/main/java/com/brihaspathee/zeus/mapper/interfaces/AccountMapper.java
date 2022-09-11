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

    /**
     * Convert account dto to account entity
     * @param accountDto
     * @return
     */
    Account accountDtoToAccount(AccountDto accountDto);

    /**
     * Convert account entity to account dto
     * @param account
     * @return
     */
    AccountDto accountToAccountDto(Account account);

    /**
     * Convert account dtos to account entities
     * @param accountDtos
     * @return
     */
    Set<Account> accountDtosToAccount(Set<AccountDto> accountDtos);

    /**
     * Convert account entities to account dtos
     * @param accounts
     * @return
     */
    Set<AccountDto> accountToAccountDtos(Set<Account> accounts);
}
