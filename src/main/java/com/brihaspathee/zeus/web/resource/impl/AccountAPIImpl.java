package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ResponseEntity<ZeusApiResponse<AccountDto>> getAccountById(String accountId) {
        ZeusApiResponse<AccountDto> apiResponse = ZeusApiResponse.<AccountDto>builder()
                .response(AccountDto.builder().accountSK(UUID.randomUUID()).accountId("Test Id").build())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
