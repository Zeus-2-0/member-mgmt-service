package com.brihaspathee.zeus.broker.consumer;

import com.brihaspathee.zeus.message.ZeusMessagePayload;
import com.brihaspathee.zeus.validator.AccountValidationResult;
import com.brihaspathee.zeus.web.model.AccountDto;
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
 * Date: 09, October 2022
 * Time: 7:58 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.broker.consumer
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionRequestListener {

    /**
     * Object mapper instance to convert the JSON to object
     */
    private final ObjectMapper objectMapper;

    /**
     * Kafka listener to consume the validation service responses
     * @param consumerRecord
     * @throws JsonProcessingException
     */
    @KafkaListener(topics = "ZEUS.ACCOUNT.PROCESSING.REQ")
    public void listenForValidationResponse(
            ConsumerRecord<String, ZeusMessagePayload<AccountDto>> consumerRecord
    ) throws JsonProcessingException {
        log.info("Validation Response received");
        String valueAsString = objectMapper.writeValueAsString(consumerRecord.value());
        ZeusMessagePayload<AccountDto> accountValidationResultPayload =
                objectMapper.readValue(valueAsString,
                        new TypeReference<ZeusMessagePayload<AccountDto>>() {});
        // createPayloadTrackerRespDetail(accountValidationResultPayload);
        log.info("Account information received in the member management service for processing:{}", accountValidationResultPayload.getPayload());

    }


}
