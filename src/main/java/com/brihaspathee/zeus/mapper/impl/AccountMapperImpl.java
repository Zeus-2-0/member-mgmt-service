package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.mapper.interfaces.AccountMapper;
import com.brihaspathee.zeus.web.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {
    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if(accountDto == null){
            return null;
        }
        Account account = Account.builder()
                .accountSK(accountDto.getAccountSK())
                //.accountId(accountDto.getAccountId())
                //.lineOfBusinessTypeCode(accountDto.getLineOfBusinessTypeCode())
                .build();
        return account;
    }

    @Override
    public AccountDto accountToAccountDto(Account account) {
        if(account == null){
            return null;
        }
        AccountDto accountDto = AccountDto.builder()
                .accountSK(account.getAccountSK())
                //.accountId(account.getAccountId())
                //.lineOfBusinessTypeCode(account.getLineOfBusinessTypeCode())
                .build();
        return accountDto;
    }

    @Override
    public Set<Account> accountDtosToAccount(Set<AccountDto> accountDtos) {
        return accountDtos.stream().map(this::accountDtoToAccount).collect(Collectors.toSet());
    }

    @Override
    public Set<AccountDto> accountToAccountDtos(Set<Account> accounts) {
        return accounts.stream().map(this::accountToAccountDto).collect(Collectors.toSet());
    }
}
