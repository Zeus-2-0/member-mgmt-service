package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Broker;
import com.brihaspathee.zeus.dto.account.BrokerDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 3:58 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface BrokerHelper {

    /**
     * Create the broker
     * @param brokerDto
     * @return
     */
    BrokerDto createBroker(BrokerDto brokerDto, Account account);
}
