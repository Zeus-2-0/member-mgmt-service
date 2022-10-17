package com.brihaspathee.zeus.subscriber;

import com.brihaspathee.zeus.broker.producer.TransactionResponseProducer;
import com.brihaspathee.zeus.validator.AccountProcessingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, October 2022
 * Time: 2:39 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.subscriber
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AccountProcessingSubscriber<T> extends BaseSubscriber<T> {

    /**
     * Producer to send the response message back the transaction manager service
     */
    private final TransactionResponseProducer transactionResponseProducer;

    /**
     * This is called when the processing of the account is completed
     * @param value
     */
    @Override
    protected void hookOnNext(T value) {
        log.info("Inside the hookOnNext():");
        AccountProcessingResponse accountProcessingResponse = (AccountProcessingResponse) value;
        try {
            transactionResponseProducer.sendAccountProcessingResponse(accountProcessingResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void hookOnComplete() {
        super.hookOnComplete();
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        super.hookOnError(throwable);
    }
}
