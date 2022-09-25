package com.brihaspathee.zeus.adapter.impl;

import com.brihaspathee.zeus.adapter.interfaces.MessageAdapter;
import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.broker.producer.AccountValidationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 22, September 2022
 * Time: 1:07 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.adapter.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessagingImpl implements MessageAdapter {

    /**
     * The producer instance to send the message
     */
    private final AccountValidationProducer accountValidationProducer;

    /**
     * Publish account validation message to kafka topic
     * @param validationMessage - the account validation message
     */
    @Override
    public void publishAccountValidationMessage(AccountValidationRequest validationMessage) throws JsonProcessingException {
        accountValidationProducer.publishAccountDetails(validationMessage);
    }
}
