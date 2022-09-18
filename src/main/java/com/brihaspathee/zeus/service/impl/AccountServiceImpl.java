package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.exception.AccountNotFoundException;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.EnrollmentSpanHelper;
import com.brihaspathee.zeus.helper.interfaces.PremiumSpanHelper;
import com.brihaspathee.zeus.mapper.interfaces.AccountMapper;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import com.brihaspathee.zeus.web.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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

    /**
     * Enrollment span helper to perform operations on the enrollment span
     */
    private final EnrollmentSpanHelper enrollmentSpanHelper;

    /**
     * Premium span helper to perform operations on the premium span
     */
    private final PremiumSpanHelper premiumSpanHelper;

    /**
     * Member service to perform operations on the member entity
     */
    private final MemberService memberService;

    /**
     * Create a new account
     * @param accountDto
     * @return
     */
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        final Account account = accountRepository.save(accountMapper.accountDtoToAccount(accountDto));
        accountDto.setAccountSK(account.getAccountSK());
        accountDto.getMembers().stream().forEach(memberDto -> {
            memberDto.setAccountSK(account.getAccountSK());
            memberDto.setMemberSK(memberService.createMember(memberDto).getMemberSK());
        });
        accountDto.getEnrollmentSpans().stream().forEach(enrollmentSpanDto -> {
            enrollmentSpanDto.setAccountSK(account.getAccountSK());
            UUID enrollmentSpanSK = enrollmentSpanHelper.createEnrollmentSpan(enrollmentSpanDto).getEnrollmentSpanSK();
            enrollmentSpanDto.setEnrollmentSpanSK(
                    enrollmentSpanSK);
            enrollmentSpanDto.getPremiumSpans().stream().forEach(premiumSpanDto -> {
                premiumSpanDto.getMemberPremiumSpans().stream().forEach(memberPremiumDto -> {
                    if(memberPremiumDto.getMemberSK() == null){
                        populateMemberSK(memberPremiumDto, accountDto.getMembers());
                    }
                });
                premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanSK);
                UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
                premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
            });

        });
        return accountDto;
    }

    /**
     * Get the details of the account using account mapper
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findAccountsByAccountNumber(accountNumber).orElseThrow(() -> {
            throw new AccountNotFoundException("Account with account number " + accountNumber + " not found" );
        });
        log.info("Retrieved account:{}",account);
        return accountMapper.accountToAccountDto(account);
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

    private void populateMemberSK(MemberPremiumDto memberPremiumDto, Set<MemberDto> memberDtos){
       MemberDto retrievedMember = memberDtos.stream()
               .filter(
                       memberDto ->
                               memberDto.getMemberCode().equals(memberPremiumDto.getMemberCode()))
               .findFirst()
               .orElseThrow(() -> {
                   throw new MemberNotFoundException("Member with member code " + memberPremiumDto.getMemberCode() + " not found");
               });
       memberPremiumDto.setMemberSK(retrievedMember.getMemberSK());
    }

}
