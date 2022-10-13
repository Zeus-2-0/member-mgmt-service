package com.brihaspathee.zeus.broker.consumer;

import com.brihaspathee.zeus.domain.entity.PayloadTrackerDetail;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerDetailHelper;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerHelper;
import com.brihaspathee.zeus.message.Acknowledgement;
import com.brihaspathee.zeus.message.ZeusMessagePayload;
import com.brihaspathee.zeus.validator.AccountValidationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, September 2022
 * Time: 7:49 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.consumer
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationResponseListener {

    /**
     * Object mapper instance to convert the JSON to object
     */
    private final ObjectMapper objectMapper;

    /**
     * To perform operations on the payload tracker
     */
    private final PayloadTrackerHelper payloadTrackerHelper;

    /**
     * To perform operations on the payload tracker detail
     */
    private final PayloadTrackerDetailHelper payloadTrackerDetailHelper;

    /**
     * kafka consumer to consume the acknowledgement messages
     * @param consumerRecord
     * @throws JsonProcessingException
     */
    @KafkaListener(topics = "ZEUS.VALIDATOR.ACCOUNT.ACK")
    public void listenForValidationAcks(
            ConsumerRecord<String, ZeusMessagePayload<Acknowledgement>> consumerRecord
    ) throws JsonProcessingException {
        log.info("ACK received");
        String valueAsString = objectMapper.writeValueAsString(consumerRecord.value());
        ZeusMessagePayload<Acknowledgement> ackZeusMessagePayload =
                objectMapper.readValue(valueAsString,
                        new TypeReference<ZeusMessagePayload<Acknowledgement>>(){});
        createPayloadTrackerAckDetail(ackZeusMessagePayload);
        log.info("Request payload id:{}", ackZeusMessagePayload.getPayload().getRequestPayloadId());
        log.info("Ack id:{}",ackZeusMessagePayload.getPayload().getAckId());

    }

    /**
     * Kafka listener to consume the validation service responses
     * @param consumerRecord
     * @throws JsonProcessingException
     */
    @KafkaListener(topics = "ZEUS.VALIDATOR.ACCOUNT.RESP")
    public void listenForValidationResponse(
            ConsumerRecord<String, ZeusMessagePayload<AccountValidationResult>> consumerRecord
    ) throws JsonProcessingException {
        log.info("Validation Response received");
        String valueAsString = objectMapper.writeValueAsString(consumerRecord.value());
        ZeusMessagePayload<AccountValidationResult> accountValidationResultPayload =
                objectMapper.readValue(valueAsString,
                        new TypeReference<ZeusMessagePayload<AccountValidationResult>>() {});
        createPayloadTrackerRespDetail(accountValidationResultPayload);

    }

    /**
     * Log the details of the acknowledgment payload that was received
     * @param payload
     */
    private void createPayloadTrackerAckDetail(
            ZeusMessagePayload<Acknowledgement> payload) throws JsonProcessingException {
        String payloadAsString = objectMapper.writeValueAsString(payload);
        PayloadTrackerDetail payloadTrackerDetail = PayloadTrackerDetail.builder()
                .payloadTracker(payloadTrackerHelper.getPayloadTracker(payload.getPayload().getRequestPayloadId()))
                .responsePayload(payloadAsString)
                .responseTypeCode("ACKNOWLEDGEMENT")
                .responsePayloadId(payload.getPayload().getAckId())
                .payloadDirectionTypeCode("INBOUND")
                .sourceDestinations(payload.getMessageMetadata().getMessageSource())
                .build();
        payloadTrackerDetailHelper.createPayloadTrackerDetail(payloadTrackerDetail);
    }

    /**
     * Log the details of the response payload that was received
     * @param payload
     */
    private void createPayloadTrackerRespDetail(
            ZeusMessagePayload<AccountValidationResult> payload) throws JsonProcessingException {
        String payloadAsString = objectMapper.writeValueAsString(payload);
        PayloadTrackerDetail payloadTrackerDetail = PayloadTrackerDetail.builder()
                .payloadTracker(payloadTrackerHelper.getPayloadTracker(payload.getPayload().getRequestPayloadId()))
                .responsePayload(payloadAsString)
                .responseTypeCode("RESULT")
                .responsePayloadId(payload.getPayload().getResponseId())
                .payloadDirectionTypeCode("INBOUND")
                .sourceDestinations(payload.getMessageMetadata().getMessageSource())
                .build();
        payloadTrackerDetailHelper.createPayloadTrackerDetail(payloadTrackerDetail);
    }

}
