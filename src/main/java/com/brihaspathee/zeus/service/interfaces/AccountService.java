package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.broker.message.AccountUpdateResponse;
import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cglib.core.Local;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
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
     * @return
     */
    Mono<AccountUpdateResponse> processAccount(PayloadTracker payloadTracker,
                                               AccountDto accountDto) throws JsonProcessingException;

    /**
     * Get enrollment span that matches the plan and group policy id
     * @param accountNumber
     * @param planId
     * @param groupPolicyId
     * @param startDate
     * @return
     */
    EnrollmentSpanDto getMatchingEnrollmentSpan(String accountNumber,
                                                String planId,
                                                String groupPolicyId,
                                                LocalDate startDate);

    /**
     * Get the enrollment span that is prior to the start date
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    EnrollmentSpanDto getPriorEnrollmentSpan(String accountNumber,
                                             LocalDate startDate,
                                             boolean matchCancelSpans);

    /**
     * Get matching enrollment spans by the date
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    List<EnrollmentSpanDto> getMatchingEnrollmentSpan(String accountNumber,
                                                      LocalDate startDate,
                                                      boolean matchCancelSpans);

    /**
     * Get matching enrollment spans by the date if available else return the prior enrollment span
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    List<EnrollmentSpanDto> getMatchingOrPriorEnrollmentSpan(String accountNumber,
                                                      LocalDate startDate,
                                                      boolean matchCancelSpans);

}
