package com.brihaspathee.zeus.adapter.interfaces;

import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 22, September 2022
 * Time: 10:20 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.adapter.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MessageAdapter {

    /**
     * The method to publish validation messages
     * @param validationMessage - the account validation message
     */
    void publishAccountValidationMessage(AccountValidationRequest validationMessage) throws JsonProcessingException;
}
