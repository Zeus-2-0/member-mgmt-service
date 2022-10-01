package com.brihaspathee.zeus.validator.impl;

import com.brihaspathee.zeus.adapter.interfaces.MessageAdapter;
import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.util.ZeusRandomStringGenerator;
import com.brihaspathee.zeus.validator.interfaces.AccountValidator;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, September 2022
 * Time: 3:24 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.validator.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AccountValidatorImpl implements AccountValidator {

    /**
     * The message adapter to send the message for validation
     */
    private final MessageAdapter messageAdapter;

    /**
     * Validates the details of the account
     * @param accountDto
     * @return
     */
    @Override
    public boolean validateAccount(AccountDto accountDto) throws JsonProcessingException {
        String validationPayloadId = ZeusRandomStringGenerator.randomString(15);
        AccountValidationRequest accountValidationRequest =
                AccountValidationRequest.builder()
                        .accountDto(accountDto)
                        .validationMessageId(validationPayloadId)
                        .build();
        messageAdapter.publishAccountValidationMessage(accountValidationRequest);
        return false;
    }
}
