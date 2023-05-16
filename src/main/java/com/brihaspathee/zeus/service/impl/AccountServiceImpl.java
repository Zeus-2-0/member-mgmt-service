package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.adapter.interfaces.MessageAdapter;
import com.brihaspathee.zeus.broker.message.AccountUpdateRequest;
import com.brihaspathee.zeus.broker.message.AccountUpdateResponse;
import com.brihaspathee.zeus.constants.EnrollmentSpanStatus;
import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.domain.entity.Sponsor;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.dto.account.*;
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

import java.nio.file.LinkOption;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
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
//            enrollmentSpanDto.setAccountSK(account.getAccountSK());
//            UUID enrollmentSpanSK = enrollmentSpanHelper.createEnrollmentSpan(enrollmentSpanDto).getEnrollmentSpanSK();
//            enrollmentSpanDto.setEnrollmentSpanSK(
//                    enrollmentSpanSK);
//            enrollmentSpanDto.getPremiumSpans().stream().forEach(premiumSpanDto -> {
//                premiumSpanDto.getMemberPremiumSpans().stream().forEach(memberPremiumDto -> {
//                    if(memberPremiumDto.getMemberSK() == null){
//                        populateMemberSK(memberPremiumDto, accountDto.getMembers());
//                    }
//                });
//                premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanSK);
//                UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
//                premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
//            });
            createEnrollmentSpan(accountDto, enrollmentSpanDto);

        });
        if(accountDto.getBrokers() != null && accountDto.getBrokers().size() > 0){
            accountDto.getBrokers().stream().forEach(brokerDto -> {
                brokerDto.setAccountSK(account.getAccountSK());
                UUID brokerSK = brokerHelper.createBroker(brokerDto, account).getBrokerSK();
                brokerDto.setBrokerSK(brokerSK);
            });
        }
        if(accountDto.getPayers() != null && accountDto.getPayers().size() > 0){
            accountDto.getPayers().stream().forEach(payerDto -> {
                payerDto.setAccountSK(account.getAccountSK());
                UUID payerSK = payerHelper.createPayer(payerDto, account).getPayerSK();
                payerDto.setPayerSK(payerSK);
            });
        }

        if(accountDto.getSponsors() != null && accountDto.getSponsors().size() > 0){
            accountDto.getSponsors().stream().forEach(sponsorDto -> {
                sponsorDto.setAccountSK(account.getAccountSK());
                UUID sponsorSK = sponsorHelper.createSponsor(sponsorDto, account).getSponsorSK();
                sponsorDto.setSponsorSK(sponsorSK);
            });
        }

        return accountDto;
    }

    /**
     * Update an existing account
     * @param accountDto
     * @return
     */
    @Override
    public AccountDto updateAccount(AccountDto accountDto) throws JsonProcessingException {
        log.info("Inside the update account method");
        if(accountDto.getEnrollmentSpans() != null && !accountDto.getEnrollmentSpans().isEmpty()){
            accountDto.getEnrollmentSpans().stream().forEach(enrollmentSpanDto -> {
                if(enrollmentSpanDto.getEnrollmentSpanSK() == null){
                    // The enrollment span does not exist for the account
                    // it has to be created
                    createEnrollmentSpan(accountDto, enrollmentSpanDto);
                }else{
                    // Enrollment span exist for the account
                    if(enrollmentSpanDto.getChanged().get() == true){
                        enrollmentSpanDto.setAccountSK(accountDto.getAccountSK());
                        enrollmentSpanDto.getPremiumSpans().stream().forEach(premiumSpanDto -> premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK()));
                        // Enrollment span has changed hence it has to be updated
                        enrollmentSpanHelper.updateEnrollmentSpan(enrollmentSpanDto, accountDto);
                        enrollmentSpanDto.getPremiumSpans().stream().forEach(premiumSpanDto -> {
                            // premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK());
                            // Check if the premium span already exists
                            if(premiumSpanDto.getPremiumSpanSK() == null){
                                // Premium span does not exist for the enrollment span
                                // so create it
                                UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
                                premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
                            }else{
                                // Premium span exist check if it has to be updated
                                if(premiumSpanDto.getChanged().get() == true){
                                    // premium span has to be updated
                                    premiumSpanHelper.updatePremiumSpan(premiumSpanDto);
                                }
                            }
                        });
                    }else{
                        // enrollment span is not updated
                        // check if premium spans are updated
                    }
                }

                //enrollmentSpanHelper.updateEnrollmentSpan(enrollmentSpanDto, accountDto);
            });
        }
        return accountDto;
    }

    /**
     * Create a new enrollment span for the account
     * @param accountDto
     * @param enrollmentSpanDto
     */
    private void createEnrollmentSpan(AccountDto accountDto, EnrollmentSpanDto enrollmentSpanDto) {
        enrollmentSpanDto.setAccountSK(accountDto.getAccountSK());
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

        String accountNumber = accountDto.getAccountNumber();
        UUID accountSK = accountDto.getAccountSK();
        log.info("Inside the account service for account:{}", accountNumber);
        if(accountSK == null){
            // If account sk is null, then the account needs to be created
            createAccount(accountDto);
        }else{
            // if account sk is not null, then the account needs to be updated
            updateAccount(accountDto);
        }
        AccountUpdateResponse accountUpdateResponse =
                constructAccountProcessingResponse(payloadTracker, accountDto);
        return Mono.just(accountUpdateResponse).delayElement(Duration.ofSeconds(30));
    }

    /**
     * Get enrollment span that matches the plan and group policy id
     * @param accountNumber
     * @param planId
     * @param groupPolicyId
     * @param startDate
     * @return
     */
    @Override
    public EnrollmentSpanDto getMatchingEnrollmentSpan(String accountNumber,
                                                       String planId,
                                                       String groupPolicyId,
                                                       LocalDate startDate) {
        AccountDto accountDto = getAccountByNumber(accountNumber);
        List<EnrollmentSpanDto> enrollmentSpanDtos = accountDto.getEnrollmentSpans().stream().toList();
        Optional<EnrollmentSpanDto> matchedEnrollmentSpan = enrollmentSpanDtos.stream().filter(enrollmentSpanDto -> {
            return enrollmentSpanDto.getPlanId().equals(planId) &&
                    enrollmentSpanDto.getGroupPolicyId().equals(groupPolicyId) &&
                    enrollmentSpanDto.getStartDate().isEqual(startDate);
        }).findFirst();
        enrollmentSpanDtos.stream().sorted(Comparator.comparing(EnrollmentSpanDto::getStartDate)).collect(Collectors.toList());
        if(matchedEnrollmentSpan.isPresent()){
            return matchedEnrollmentSpan.get();
        }
        return null;
    }

    /**
     * Get the enrollment span that is prior to the start date
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public EnrollmentSpanDto getPriorEnrollmentSpan(String accountNumber, LocalDate startDate, boolean matchCancelSpans) {
        AccountDto accountDto = getAccountByNumber(accountNumber);
        List<EnrollmentSpanDto> enrollmentSpanDtos = accountDto.getEnrollmentSpans().stream().toList();
        enrollmentSpanDtos =
                enrollmentSpanDtos.stream()
                        .sorted(Comparator.comparing(EnrollmentSpanDto::getStartDate))
                        .collect(Collectors.toList());
        enrollmentSpanDtos =
                enrollmentSpanDtos.stream()
                        .takeWhile(
                                enrollmentSpanDto ->
                                        enrollmentSpanDto.getEndDate().isBefore(startDate))
                        .collect(Collectors.toList());
        if(!matchCancelSpans){
            enrollmentSpanDtos = removeCanceledSpans(enrollmentSpanDtos);
        }
        if(enrollmentSpanDtos != null && enrollmentSpanDtos.size() > 0){
            return enrollmentSpanDtos.get(enrollmentSpanDtos.size() - 1);
        }else {
            return null;
        }
    }

    /**
     * Get matching enrollment spans by the date
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public List<EnrollmentSpanDto> getMatchingEnrollmentSpan(String accountNumber,
                                                             LocalDate startDate,
                                                             boolean matchCancelSpans) {
        AccountDto accountDto = getAccountByNumber(accountNumber);
        List<EnrollmentSpanDto> enrollmentSpanDtos = accountDto.getEnrollmentSpans().stream().toList();
        enrollmentSpanDtos =
                enrollmentSpanDtos.stream()
                        .sorted(Comparator.comparing(EnrollmentSpanDto::getStartDate))
                        .collect(Collectors.toList());
        enrollmentSpanDtos = enrollmentSpanDtos.stream()
                .dropWhile(
                        enrollmentSpanDto ->
                                enrollmentSpanDto.getStartDate().isBefore(startDate))
                .collect(Collectors.toList());
        enrollmentSpanDtos = enrollmentSpanDtos.stream()
                .takeWhile(
                        enrollmentSpanDto ->
                                enrollmentSpanDto.getStartDate().getYear() == startDate.getYear())
                .collect(Collectors.toList());
        if(!matchCancelSpans){
            enrollmentSpanDtos = removeCanceledSpans(enrollmentSpanDtos);
        }
        if(enrollmentSpanDtos != null && enrollmentSpanDtos.size() > 0){
            return enrollmentSpanDtos;
        }
        return null;
    }

    /**
     * Get matching enrollment spans by the date if available else return the prior enrollment span
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public List<EnrollmentSpanDto> getMatchingOrPriorEnrollmentSpan(String accountNumber,
                                                                    LocalDate startDate,
                                                                    boolean matchCancelSpans) {
        List<EnrollmentSpanDto> enrollmentSpanDtos = getMatchingEnrollmentSpan(accountNumber, startDate, matchCancelSpans);
        if(enrollmentSpanDtos != null && enrollmentSpanDtos.size() > 0){
            return enrollmentSpanDtos;
        }else{
            EnrollmentSpanDto enrollmentSpanDto = getPriorEnrollmentSpan(accountNumber,
                    startDate, matchCancelSpans);
            if(enrollmentSpanDto != null){
                List<EnrollmentSpanDto> priorEnrollmentSpanDto = new ArrayList<>();
                priorEnrollmentSpanDto.add(enrollmentSpanDto);
                return priorEnrollmentSpanDto;
            }
        }
        return null;
    }

    /**
     * Get accounts that match the exchange subscriber id and state type code
     * @param accountMatchParam
     * @return
     */
    @Override
    public AccountList getMatchingAccounts(AccountMatchParam accountMatchParam) {
        // The account list that will be returned
        // Initially there will be no accounts in the list
        AccountList accountList = AccountList.builder().build();
        // Check if the account number is present in the search
        // If account number is present, search for the account is using the account number
        // and return the account
        if(accountMatchParam.getAccountNumber() != null){
            AccountDto accountDto = getAccountByNumber(accountMatchParam.getAccountNumber());
            accountList.setAccountDtos(Set.of(accountDto));
            return accountList;
        }
        // If account number is not present then first try to find the account using exchange subscriber id and state
        if(accountMatchParam.getExchangeSubscriberId() != null &&
            accountMatchParam.getStateTypeCode() != null){
            accountList = getAccountByExchangeSubId(accountMatchParam);
        }
        // If no account is found using exchange subscriber id,
        // if SSN is provided in the search, see if an account can be found using SSN of the
        // primary subscriber
        if(accountList.getAccountDtos() == null || accountList.getAccountDtos().isEmpty()){
            getAccountBySSN(accountMatchParam, accountList);
        }
        // If no account is found using SSN or no SSN is provided, then
        // try to find an account using the first name, last name, gender and date of birth of the
        // primary subscriber
        if(accountList.getAccountDtos() ==null || accountList.getAccountDtos().isEmpty()){
            getAccountByNameAndDOB(accountMatchParam, accountList);
        }
        return accountList;
    }

    /**
     * Get account by name, date of birth and gender of the primary subscriber
     * @param accountMatchParam
     * @param accountList
     */
    private void getAccountByNameAndDOB(AccountMatchParam accountMatchParam, AccountList accountList) {
        if(accountMatchParam.getFirstName() !=null && accountMatchParam.getLastName() != null &&
            accountMatchParam.getGenderTypeCode() != null && accountMatchParam.getDateOfBirth() !=null){
            List<MemberDto> memberDtos = memberService.getMembersByNameAndDOB(accountMatchParam.getFirstName(), accountMatchParam.getLastName(),
                    accountMatchParam.getGenderTypeCode(), accountMatchParam.getDateOfBirth());
            if(memberDtos!=null && !memberDtos.isEmpty()){
                Set<AccountDto> accountDtos = memberDtos
                        .stream().map(memberDto -> getMemberAccount(memberDto))
                        .collect(Collectors.toSet());
                accountList.setAccountDtos(accountDtos);
            }

        }
    }

    /**
     * Get account by matching with the SSN of the primary subscriber
     * @param accountMatchParam
     * @param accountList
     */
    private void getAccountBySSN(AccountMatchParam accountMatchParam, AccountList accountList) {
        if(accountMatchParam.getSocialSecurityNumber() != null){
            List<MemberDto> memberDtos = memberService.getHOHBySSN(accountMatchParam.getSocialSecurityNumber());
            if(memberDtos!=null && !memberDtos.isEmpty()){
                Set<AccountDto> accountDtos = memberDtos
                        .stream().map(memberDto -> getMemberAccount(memberDto)).collect(Collectors.toSet());
                accountList.setAccountDtos(accountDtos);
            }
        }
    }

    /**
     * Get Member account from member dto
     * @param memberDto
     * @return
     */
    private AccountDto getMemberAccount(MemberDto memberDto) {
        Account account = accountRepository.findById(memberDto.getAccountSK()).orElse(null);
        if(account!=null){
            return accountMapper.accountToAccountDto(account);
        }else {
            return null;
        }
    }

    /**
     * Get the account that matches the exchange subscriber id
     * @param accountMatchParam
     * @return
     */
    private AccountList getAccountByExchangeSubId(AccountMatchParam accountMatchParam) {
        List<EnrollmentSpan> enrollmentSpans = enrollmentSpanHelper
                .getMatchingEnrollmentSpan(accountMatchParam.getExchangeSubscriberId(),
                        accountMatchParam.getStateTypeCode());
        AccountList accountList = AccountList.builder().build();
        if(enrollmentSpans !=null && enrollmentSpans.size() > 0){
            if(enrollmentSpans.size() > 1){
                // This removes enrollment spans for the same account and keeps only one enrollment span per account
                enrollmentSpans = enrollmentSpans.stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(enrollmentSpan -> {
                            Account account = enrollmentSpan.getAccount();
                            return account.getAccountSK();
                        })))).stream().toList();
            }
            Set<AccountDto> accountDtos =
                    enrollmentSpans.stream()
                            .map(enrollmentSpan -> {
                                Account account = enrollmentSpan.getAccount();
                                return accountMapper.accountToAccountDto(account);
                            }).collect(Collectors.toSet());
            accountList.setAccountDtos(accountDtos);
        }
        return accountList;
    }

    /**
     * Remove canceled enrollment span from the list
     * @param enrollmentSpanDtos
     * @return
     */
    private List<EnrollmentSpanDto> removeCanceledSpans(List<EnrollmentSpanDto> enrollmentSpanDtos){
        List<EnrollmentSpanDto> nonCanceledEnrollmentSpans = enrollmentSpanDtos.stream()
                .filter(
                        enrollmentSpanDto ->
                                !enrollmentSpanDto.getStatusTypeCode()
                                        .equals(EnrollmentSpanStatus.CANCELED))
                .collect(Collectors.toList());
        return nonCanceledEnrollmentSpans;
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
