package com.brihaspathee.zeus.producer;

import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.message.MessageMetadata;
import com.brihaspathee.zeus.message.ZeusMessagePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 22, September 2022
 * Time: 10:39 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.producer
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class AccountValidationProducer {

    /**
     * Kafka template to produce and send messages
     */
    private final KafkaTemplate<String, ZeusMessagePayload<AccountValidationRequest>> kafkaTemplate;

    /**
     * ListenableFutureCallback class that is used after success or failure of publishing the message
     */
    private final AccountValidationCallback accountValidationCallback;

    /**
     * The method that publishes the messages to the kafka topic
     * @param accountValidationMessage
     */
    public void publishAccountDetails(AccountValidationRequest accountValidationMessage){
        String[] messageDestinations = {"VALIDATION-SERVICE"};
        ZeusMessagePayload<AccountValidationRequest> messagePayload = ZeusMessagePayload.<AccountValidationRequest>builder()
                .messageMetadata(MessageMetadata.builder()
                        .messageSource("MEMBER-MGMT-SERVICE")
                        .messageDestination(messageDestinations)
                        .messageCreationTimestamp(LocalDateTime.now())
                        .build())
                .payload(accountValidationMessage)
                .build();
        accountValidationCallback.setMessagePayload(accountValidationMessage);
        ProducerRecord<String, ZeusMessagePayload<AccountValidationRequest>> producerRecord =
                buildProducerRecord(messagePayload);
        kafkaTemplate.send(producerRecord).addCallback(accountValidationCallback);
        log.info("After the send method is called");
    }

    /**
     * The method to build the producer record
     * @param messagePayload
     */
    private ProducerRecord<String, ZeusMessagePayload<AccountValidationRequest>> buildProducerRecord(ZeusMessagePayload<AccountValidationRequest> messagePayload){
        RecordHeader messageHeader = new RecordHeader("payload-id",
                "test payload id".getBytes());
        return new ProducerRecord<>("ZEUS.VALIDATOR.ACCOUNT.REQ",
                null,
                "test payload id 2",
                messagePayload,
                Arrays.asList(messageHeader));
    }
}
