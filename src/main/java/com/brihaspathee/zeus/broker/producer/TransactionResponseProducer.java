package com.brihaspathee.zeus.broker.producer;

import com.brihaspathee.zeus.broker.message.AccountUpdateResponse;
import com.brihaspathee.zeus.constants.ZeusServiceNames;
import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.domain.entity.PayloadTrackerDetail;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerDetailHelper;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerHelper;
import com.brihaspathee.zeus.message.MessageMetadata;
import com.brihaspathee.zeus.message.ZeusMessagePayload;
import com.brihaspathee.zeus.validator.TransactionValidationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

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
@Component
@RequiredArgsConstructor
public class TransactionResponseProducer {

    /**
     * Kafka template to produce and send messages
     */
    private final KafkaTemplate<String, ZeusMessagePayload<AccountUpdateResponse>> kafkaTemplate;

    /**
     * Object mapper that can covert the object into a string
     */
    private final ObjectMapper objectMapper;

    /**
     * Payload tracker helper instance to get the payload tracker for which the detail is being inserted
     */
    private final PayloadTrackerHelper payloadTrackerHelper;

    /**
     * Payload tracker detail helper instance to create the payload tracker detail record
     */
    private final PayloadTrackerDetailHelper payloadTrackerDetailHelper;

    /**
     * ListenableFutureCallback class that is used after success or failure of publishing the message
     */
    private final TransactionResponseCallback transactionResponseCallback;

    /**
     * Send the account update response back to transaction manager
     * @param accountUpdateResponse
     */
    public void sendAccountProcessingResponse(AccountUpdateResponse accountUpdateResponse) throws JsonProcessingException {
        log.info("Inside the account processing response producer:{}", accountUpdateResponse);

        // Create the result payload that is to be sent to the account processor service
        String[] messageDestinations = {ZeusServiceNames.ACCOUNT_PROCESSOR_SERVICE};
        ZeusMessagePayload<AccountUpdateResponse> messagePayload = ZeusMessagePayload.<AccountUpdateResponse>builder()
                .messageMetadata(MessageMetadata.builder()
                        .messageSource(ZeusServiceNames.MEMBER_MGMT_SERVICE)
                        .messageDestination(messageDestinations)
                        .messageCreationTimestamp(LocalDateTime.now())
                        .build())
                .payload(accountUpdateResponse)
                .build();
        // Create the payload tracker detail record for the validation result payload
        PayloadTrackerDetail payloadTrackerDetail = createPayloadTrackerDetail(messagePayload);
        transactionResponseCallback.setAccountUpdateResponse(accountUpdateResponse);
        // Build the producer record
        ProducerRecord<String, ZeusMessagePayload<AccountUpdateResponse>> producerRecord =
                buildProducerRecord(payloadTrackerDetail.getResponsePayloadId(), messagePayload);
        // Send to kafka topic
        kafkaTemplate.send(producerRecord);//.addCallback(transactionResponseCallback);
        log.info("After the sending the processing response to transaction manager is called");
    }

    /**
     * Create the payload tracker detail record
     * @param messagePayload
     * @return
     * @throws JsonProcessingException
     */
    private PayloadTrackerDetail createPayloadTrackerDetail(
            ZeusMessagePayload<AccountUpdateResponse> messagePayload) throws JsonProcessingException {
        // Convert the payload as String
        String valueAsString = objectMapper.writeValueAsString(messagePayload);
        // Store the response in the detail table
        PayloadTrackerDetail payloadTrackerDetail = PayloadTrackerDetail.builder()
                .payloadTracker(payloadTrackerHelper.getPayloadTracker(messagePayload.getPayload().getRequestPayloadId()))
                .responseTypeCode("UPDATE-RESPONSE")
                .responsePayload(valueAsString)
                .responsePayloadId(messagePayload.getPayload().getResponseId())
                .payloadDirectionTypeCode("OUTBOUND")
                .sourceDestinations(StringUtils.join(
                        messagePayload.getMessageMetadata().getMessageDestination(),
                        ','))
                .build();
        payloadTrackerDetailHelper.createPayloadTrackerDetail(payloadTrackerDetail);
        return payloadTrackerDetail;
    }

    /**
     * The method to build the producer record
     * @param payloadId
     * @param messagePayload
     */
    private ProducerRecord<String, ZeusMessagePayload<AccountUpdateResponse>> buildProducerRecord(
            String payloadId,
            ZeusMessagePayload<AccountUpdateResponse> messagePayload){
        RecordHeader messageHeader = new RecordHeader("payload-id",
                "test payload id".getBytes());
        return new ProducerRecord<>("ZEUS.ACCOUNT.UPDATE.RESP",
                null,
                payloadId,
                messagePayload,
                Arrays.asList(messageHeader));
    }

}
