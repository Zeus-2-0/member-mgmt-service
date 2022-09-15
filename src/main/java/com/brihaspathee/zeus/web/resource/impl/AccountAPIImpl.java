package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

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
     * Get the account for the account id passed in the input
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountDto>> getAccountById(String accountId) {
        ZeusApiResponse<AccountDto> apiResponse = ZeusApiResponse.<AccountDto>builder()
                .response(AccountDto.builder().accountSK(UUID.randomUUID()).accountNumber("Test Id").build())
                .build();
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
}
