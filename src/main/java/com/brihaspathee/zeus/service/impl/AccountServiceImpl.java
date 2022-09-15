package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.mapper.interfaces.AccountMapper;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:33 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    /**
     * The account repository to perform CRUD operations
     */
    private final AccountRepository accountRepository;

    /**
     * Mapper to convert dto to entity and vice versa
     */
    private final AccountMapper accountMapper;

    @Override
    public Account createAccount(AccountDto accountDto) {
        return null;
    }

    @Override
    public Account getAccountById(String accountId) {
        return null;
    }

    /**
     * Get all the accounts in the system
     * @return AccountList that contains all the accounts
     */
    @Override
    public AccountList getAllAccounts() {
        Set<Account> accounts = accountRepository.findAll().stream().collect(Collectors.toSet());
        Set<AccountDto> accountDtos = accountMapper.accountToAccountDtos(accounts);
        AccountList accountList = AccountList.builder()
                .accountDtos(accountDtos)
                .build();
        return accountList;
    }

    @Override
    public Account updateAccount(AccountDto accountDto) {
        return null;
    }
}
