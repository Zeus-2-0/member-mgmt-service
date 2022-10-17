package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.validator.AccountProcessingResponse;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

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

    /**
     * Create a new account
     * @param accountDto
     * @return
     */
    AccountDto createAccount(AccountDto accountDto) throws JsonProcessingException;

    /**
     * Get account by account number
     * @param accountNumber
     * @return
     */
    AccountDto getAccountByNumber(String accountNumber);

    /**
     * Get all the accounts
     * @return
     */
    AccountList getAllAccounts();

    /**
     * Process the account information
     * @param payloadTracker
     * @param accountDto
     * @return
     */
    Mono<AccountProcessingResponse> processAccount(PayloadTracker payloadTracker,
                                                   AccountDto accountDto);

}
