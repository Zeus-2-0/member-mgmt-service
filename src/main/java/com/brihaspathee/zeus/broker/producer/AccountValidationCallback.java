package com.brihaspathee.zeus.broker.producer;

import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.message.ZeusMessagePayload;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 22, September 2022
 * Time: 1:12 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.producer
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class AccountValidationCallback implements ListenableFutureCallback<SendResult<String, ZeusMessagePayload<AccountValidationRequest>>> {

    /**
     * The message payload that was sent to the topic
     */
    private AccountValidationRequest messagePayload;

    @Override
    public void onFailure(Throwable ex) {
        log.info("The message failed to publish");
    }

    @Override
    public void onSuccess(SendResult<String, ZeusMessagePayload<AccountValidationRequest>> result) {
        log.info("The message successfully published");
    }
}
