package com.brihaspathee.zeus.validator.impl;

import com.brihaspathee.zeus.validator.interfaces.AccountValidator;
import com.brihaspathee.zeus.validator.interfaces.EnrollmentSpanValidator;
import com.brihaspathee.zeus.web.model.AccountDto;
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
     * Enrollment span validator instance to validate the enrollment spans
     */
    private final EnrollmentSpanValidator enrollmentSpanValidator;

    /**
     * Validates the details of the account
     * @param accountDto
     * @return
     */
    @Override
    public boolean validateAccount(AccountDto accountDto) {
        enrollmentSpanValidator.validateEnrollmentSpans(accountDto.getEnrollmentSpans());
        return false;
    }
}
