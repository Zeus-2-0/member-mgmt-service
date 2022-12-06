package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.adapter.interfaces.MessageAdapter;
import com.brihaspathee.zeus.broker.message.AccountUpdateRequest;
import com.brihaspathee.zeus.broker.message.AccountUpdateResponse;
import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.domain.entity.Sponsor;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.dto.account.MemberPremiumDto;
import com.brihaspathee.zeus.exception.AccountNotFoundException;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.*;
import com.brihaspathee.zeus.mapper.interfaces.AccountMapper;
import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import com.brihaspathee.zeus.util.ZeusRandomStringGenerator;
import com.brihaspathee.zeus.validator.interfaces.AccountValidator;
import com.brihaspathee.zeus.web.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
     * Broker helper to perform operations on the broker
     */
    private final BrokerHelper brokerHelper;

    /**
     * Sponsor helper to perform operations on the sponsor
     */
    private final SponsorHelper sponsorHelper;

    /**
     * Payer helper to perform operations on the payer
     */
    private final PayerHelper payerHelper;

    /**
     * The account validator instance to validate the account details before the changes are saved
     */
    private final AccountValidator accountValidator;

    /**
     * Member service to perform operations on the member entity
     */
    private final MemberService memberService;

    /**
     * Message Adapter to call the Kafka messaging topic
     */
    private final MessageAdapter messageAdapter;

    /**
     * Create a new account
     * @param accountDto
     * @return
     */
    @Override
    public AccountDto createAccount(AccountDto accountDto) throws JsonProcessingException {
        // validate the account details provided before saving the account
        accountValidator.validateAccount(accountDto);
//        String validationMessageId = ZeusRandomStringGenerator.randomString(15);
//        log.info("validationMessageId:{}",validationMessageId);
//        messageAdapter.publishAccountValidationMessage(AccountValidationRequest.builder()
//                        .validationMessageId(validationMessageId)
//                        .accountDto(accountDto)
//                .build());
        // save the account to the repository
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
        accountDto.getBrokers().stream().forEach(brokerDto -> {
            brokerDto.setAccountSK(account.getAccountSK());
            UUID brokerSK = brokerHelper.createBroker(brokerDto, account).getBrokerSK();
            brokerDto.setBrokerSK(brokerSK);
        });
        accountDto.getPayers().stream().forEach(payerDto -> {
            payerDto.setAccountSK(account.getAccountSK());
            UUID payerSK = payerHelper.createPayer(payerDto, account).getPayerSK();
            payerDto.setPayerSK(payerSK);
        });
        accountDto.getSponsors().stream().forEach(sponsorDto -> {
            sponsorDto.setAccountSK(account.getAccountSK());
            UUID sponsorSK = sponsorHelper.createSponsor(sponsorDto, account).getSponsorSK();
            sponsorDto.setSponsorSK(sponsorSK);
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

    /**
     * Process the account information
     * @param payloadTracker
     * @param accountDto
     * @return
     */
    @Override
    public Mono<AccountUpdateResponse> processAccount(PayloadTracker payloadTracker,
                                                      AccountDto accountDto) throws JsonProcessingException {
        log.info("Inside the account service for account:{}", accountDto.getAccountNumber());
        createAccount(accountDto);
        AccountUpdateResponse accountUpdateResponse =
                constructAccountProcessingResponse(payloadTracker, accountDto);
        return Mono.just(accountUpdateResponse).delayElement(Duration.ofSeconds(30));
    }

    /**
     * Populate the member surrogate key to the member premium objects
     * @param memberPremiumDto
     * @param memberDtos
     */
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

    /**
     * Construct the account processing response
     * @param payloadTracker
     * @param accountDto
     * @return
     */
    private AccountUpdateResponse constructAccountProcessingResponse(PayloadTracker payloadTracker,
                                                    AccountDto accountDto){
        return AccountUpdateResponse.builder()
                .responseId(ZeusRandomStringGenerator.randomString(15))
                .requestPayloadId(payloadTracker.getPayloadId())
                .accountNumber(accountDto.getAccountNumber())
                .build();
    }

}
