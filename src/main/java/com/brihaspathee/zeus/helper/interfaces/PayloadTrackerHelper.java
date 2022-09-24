package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.domain.entity.PayloadTracker;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, September 2022
 * Time: 5:26 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PayloadTrackerHelper {

    /**
     * Create a new payload tracker
     * @param payloadTracker
     * @return
     */
    PayloadTracker createPayloadTracker(PayloadTracker payloadTracker);
}
