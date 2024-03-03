package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import com.brihaspathee.zeus.domain.repository.PayloadTrackerRepository;
import com.brihaspathee.zeus.exception.PayloadTrackerNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.PayloadTrackerHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, September 2022
 * Time: 5:29 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PayloadTrackerHelperImpl implements PayloadTrackerHelper {

    /**
     * Payload tracker repository for CRUD operations
     */
    private final PayloadTrackerRepository payloadTrackerRepository;

    /**
     * Create the payload tracker
     * @param payloadTracker
     * @return
     */
    @Override
    public PayloadTracker createPayloadTracker(PayloadTracker payloadTracker) {
        PayloadTracker savedPayloadTracker = payloadTrackerRepository.save(payloadTracker);
        return savedPayloadTracker;
    }

    /**
     * Get payload tracker by payload id
     * @param payloadId
     * @return
     */
    @Override
    public PayloadTracker getPayloadTracker(String payloadId) {
        PayloadTracker payloadTracker = payloadTrackerRepository.findPayloadTrackerByPayloadId(payloadId).orElseThrow(
                () -> {
                   throw new PayloadTrackerNotFoundException("Paylod with payload id " + payloadId + " not found");
                });
        return payloadTracker;
    }

    /**
     * Clean up the payload tracker
     */
    @Override
    public void deleteAll() {
        payloadTrackerRepository.deleteAll();
    }
}
