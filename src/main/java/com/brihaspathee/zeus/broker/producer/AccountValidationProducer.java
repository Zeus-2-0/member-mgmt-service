package com.brihaspathee.zeus.broker.producer;

import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerHelper;
import com.brihaspathee.zeus.message.AccountValidationRequest;
import com.brihaspathee.zeus.message.MessageMetadata;
import com.brihaspathee.zeus.message.ZeusMessagePayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
     * Payload tracker to track the payload before sending it out
     */
    private final PayloadTrackerHelper payloadTrackerHelper;

    /**
     * Object mapper to convert the payload to string
     */
    private final ObjectMapper objectMapper;

    /**
     * The method that publishes the messages to the kafka topic
     * @param accountValidationMessage
     */
    public void publishAccountDetails(AccountValidationRequest accountValidationMessage) throws JsonProcessingException {
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
        createPayloadTracker(messagePayload);
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

    /**
     * Invokes the helper to create the payload tracker
     * @param payload
     */
    private void createPayloadTracker(ZeusMessagePayload<AccountValidationRequest> payload) throws JsonProcessingException {
        String payloadAsString = objectMapper.writeValueAsString(payload);
        PayloadTracker payloadTracker = PayloadTracker.builder()
                .accountNumber(payload.getPayload().getAccountDto().getAccountNumber())
                .payloadId(payload.getPayload().getValidationMessageId())
                .payloadDirectionTypeCode("OUTBOUND")
                .sourceDestinations(StringUtils.join(payload.getMessageMetadata().getMessageDestination()))
                .payload(payloadAsString)
                .build();
        payloadTrackerHelper.createPayloadTracker(payloadTracker);

    }
}
