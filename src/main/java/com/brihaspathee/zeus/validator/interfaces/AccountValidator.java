package com.brihaspathee.zeus.validator.interfaces;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, September 2022
 * Time: 3:24 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.validator.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AccountValidator {

    /**
     * Validates the information of the account
     * @param accountDto
     * @return
     */
    boolean validateAccount(AccountDto accountDto) throws JsonProcessingException;
}
