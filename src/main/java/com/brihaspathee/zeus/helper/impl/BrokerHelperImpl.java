package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Broker;
import com.brihaspathee.zeus.domain.repository.BrokerRepository;
import com.brihaspathee.zeus.dto.account.BrokerDto;
import com.brihaspathee.zeus.helper.interfaces.BrokerHelper;
import com.brihaspathee.zeus.mapper.interfaces.BrokerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 4:04 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerHelperImpl implements BrokerHelper {

    /**
     * The broker repository instance to perform CRUD operations
     */
    private final BrokerRepository brokerRepository;

    /**
     * The broker mapper instance to convert dto to entity and vice versa
     */
    private final BrokerMapper brokerMapper;

    /**
     * Create the broker
     * @param brokerDto
     * @return
     */
    @Override
    public BrokerDto createBroker(BrokerDto brokerDto, Account account) {
        if(brokerDto != null){
            Broker broker = brokerMapper.brokerDtoToBroker(brokerDto);
            broker.setAccount(account);
            broker = brokerRepository.save(broker);
            return brokerMapper.brokerToBrokerDto(broker);
        }else{
            return null;
        }

    }
}
