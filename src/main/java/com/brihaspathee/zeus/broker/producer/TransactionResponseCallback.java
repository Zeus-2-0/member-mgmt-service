package com.brihaspathee.zeus.broker.producer;

import com.brihaspathee.zeus.broker.message.AccountUpdateResponse;
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
 * Date: 13, October 2022
 * Time: 1:04 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.broker.producer
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class TransactionResponseCallback implements
        ListenableFutureCallback<SendResult<String, ZeusMessagePayload<AccountUpdateResponse>>> {

    /**
     * The payload that is sent to the account processor service
     */
    private AccountUpdateResponse accountUpdateResponse;

    /**
     * This is invoked when there was a failure to publish the message
     * @param ex
     */
    @Override
    public void onFailure(Throwable ex) {
        log.info("The message failed to publish to APS");
    }

    /**
     * This is invoked when the message is published successfully
     * @param result
     */
    @Override
    public void onSuccess(SendResult<String, ZeusMessagePayload<AccountUpdateResponse>> result) {
        log.info("The message successfully published to APS");
    }
}
