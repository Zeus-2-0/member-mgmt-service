package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanList;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.util.ZeusRandomStringGenerator;
import com.brihaspathee.zeus.web.model.AccountMatchParam;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, March 2022
 * Time: 3:28 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountAPIImpl implements AccountAPI {

    /**
     * The account service object to perform all the operations on the account
     */
    private final AccountService accountService;

    /**
     * Get the account for the account number passed in the input
     * @param accountNumber
     * @return The account details of the account
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAccountByNumber(String accountNumber) {
        log.info("Inside the API Endpoint to get the account by account number:{}", accountNumber);
        AccountDto accountDto = accountService.getAccountByNumber(accountNumber);
        log.info("Account Dto returned:{}", accountDto);
        Set<AccountDto> accountDtos = new HashSet<>();
        if(accountDto != null){
            accountDtos.add(accountDto);
        }
        AccountList accountList = AccountList.builder()
                .accountDtos(accountDtos)
                .build();
        log.info("Account List returned:{}", accountList);
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ZeusRandomStringGenerator.randomString(20))
                .build();
        log.info("API Response:{}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get all the accounts in the system
     * @return returns all the accounts in the system
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAllAccounts() {
        AccountList accountList = accountService.getAllAccounts();
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Create a new account
     * @param accountDto
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountDto>> createAccount(AccountDto accountDto) throws JsonProcessingException {
        AccountDto savedAccount = accountService.createAccount(accountDto);
        log.info("saves:{}",savedAccount);
        ZeusApiResponse<AccountDto> apiResponse = ZeusApiResponse.<AccountDto>builder()
            .response(savedAccount)
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CREATED)
            .statusCode(201)
            .message(ApiResponseConstants.SUCCESS)
            .developerMessage(ApiResponseConstants.SUCCESS_REASON)
        .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Update an existing account
     * @param accountDto
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountDto>> updateAccount(AccountDto accountDto) throws JsonProcessingException {
        AccountDto updatedAccount = accountService.updateAccount(accountDto);
        log.info("Updated Account:{}",updatedAccount);
        ZeusApiResponse<AccountDto> apiResponse = ZeusApiResponse.<AccountDto>builder()
                .response(updatedAccount)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.ACCEPTED)
                .statusCode(201)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    /**
     * Get Enrollment spans by plan id and group policy id
     * @param accountNumber
     * @param planId
     * @param groupPolicyId
     * @param startDate
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<EnrollmentSpanDto>> matchEnrollmentSpanByPlanAndGroupPolicyId(String accountNumber,
                                                                                                        String planId,
                                                                                                        String groupPolicyId,
                                                                                                        LocalDate startDate) {
        EnrollmentSpanDto enrollmentSpanDto = accountService.getMatchingEnrollmentSpan(accountNumber,
                planId,
                groupPolicyId,
                startDate);
        ZeusApiResponse<EnrollmentSpanDto> apiResponse = ZeusApiResponse.<EnrollmentSpanDto>builder()
                .response(enrollmentSpanDto)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get enrollment span that is prior to the start date provided in the input
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<EnrollmentSpanDto>> getPriorEnrollmentSpan(String accountNumber,
                                                                                     LocalDate startDate,
                                                                                     boolean matchCancelSpans) {
        EnrollmentSpanDto enrollmentSpanDto = accountService.getPriorEnrollmentSpan(accountNumber,
                startDate,
                matchCancelSpans);
        ZeusApiResponse<EnrollmentSpanDto> apiResponse = ZeusApiResponse.<EnrollmentSpanDto>builder()
                .response(enrollmentSpanDto)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get Enrollment spans that match the start date provided
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<EnrollmentSpanList>> matchEnrollmentSpanByDate(String accountNumber,
                                                                                         LocalDate startDate,
                                                                                         boolean matchCancelSpans) {
        List<EnrollmentSpanDto> enrollmentSpanDtos = accountService.getMatchingEnrollmentSpan(accountNumber,
                startDate,
                matchCancelSpans);
        EnrollmentSpanList enrollmentSpanList = null;
        if(enrollmentSpanDtos!=null && enrollmentSpanDtos.size() > 0){
            enrollmentSpanList = EnrollmentSpanList.builder()
                    .enrollmentSpans(enrollmentSpanDtos)
                    .build();
        }
        ZeusApiResponse<EnrollmentSpanList> apiResponse = ZeusApiResponse.<EnrollmentSpanList>builder()
                .response(enrollmentSpanList)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get enrollment spans by the date or get the prior enrollment span
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<EnrollmentSpanList>> matchOrGetPriorEnrollmentSpan(String accountNumber,
                                                                                             LocalDate startDate,
                                                                                             boolean matchCancelSpans) {
        List<EnrollmentSpanDto> enrollmentSpanDtos = accountService.getMatchingOrPriorEnrollmentSpan(accountNumber,
                startDate,
                matchCancelSpans);
        EnrollmentSpanList enrollmentSpanList = null;
        if(enrollmentSpanDtos!=null && enrollmentSpanDtos.size() > 0){
            enrollmentSpanList = EnrollmentSpanList.builder()
                    .enrollmentSpans(enrollmentSpanDtos)
                    .build();
        }
        ZeusApiResponse<EnrollmentSpanList> apiResponse = ZeusApiResponse.<EnrollmentSpanList>builder()
                .response(enrollmentSpanList)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Match accounts by search parameters
     * @param accountMatchParam
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getMatchingAccounts(AccountMatchParam accountMatchParam) {
        log.info("Account Match Parameters:{}", accountMatchParam);
        AccountList accountList = accountService.getMatchingAccounts(accountMatchParam);
        log.info("Account List:{}",accountList);
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Match accounts by ssn
     * @param ssn
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAccountBySSN(String ssn) {
        log.info("SSN Passed in as input:{}", ssn);
        AccountList accountList = accountService.getAccountsBySSN(ssn);
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Method to clean the data base
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<String>> cleanUp() {
        accountService.deleteAll();
        ZeusApiResponse<String> apiResponse = ZeusApiResponse.<String>builder()
                .response("Member management service cleaned up")
                .statusCode(204)
                .status(HttpStatus.NO_CONTENT)
                .developerMessage(ApiResponseConstants.SUCCESS)
                .message(ApiResponseConstants.SUCCESS_REASON)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

    /**
     * Update the enrollment span with status and paid through dates
     * @param enrollmentSpanDto - The enrollment span that needs to be updated
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResponseEntity<ZeusApiResponse<String>> updatePaidThroughDate(EnrollmentSpanDto enrollmentSpanDto) throws JsonProcessingException {
        log.info("EnrollmentSpanDto received for update:{}", enrollmentSpanDto);
        accountService.updateEnrollmentSpan(enrollmentSpanDto);
        ZeusApiResponse<String> apiResponse = ZeusApiResponse.<String>builder()
                .response("Enrollment span updated successfully")
                .statusCode(204)
                .status(HttpStatus.NO_CONTENT)
                .developerMessage(ApiResponseConstants.SUCCESS)
                .message(ApiResponseConstants.SUCCESS_REASON)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }


}
