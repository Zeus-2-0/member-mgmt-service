package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Broker;
import com.brihaspathee.zeus.dto.account.BrokerDto;
import com.brihaspathee.zeus.mapper.interfaces.BrokerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 2:49 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerMapperImpl implements BrokerMapper {

    /**
     * Convert broker entity to broker dto
     * @param broker
     * @return
     */
    @Override
    public BrokerDto brokerToBrokerDto(Broker broker) {
        if(broker == null){
            return null;
        }
        BrokerDto brokerDto = BrokerDto.builder()
                .brokerSK(broker.getBrokerSK())
                .brokerCode(broker.getBrokerCode())
                .brokerId(broker.getBrokerId())
                .brokerName(broker.getBrokerName())
                .accountNumber1(broker.getAccountNumber1())
                .accountNumber2(broker.getAccountNumber2())
                .agencyId(broker.getAgencyId())
                .agencyName(broker.getAgencyName())
                .ztcn(broker.getZtcn())
                .source(broker.getSource())
                .startDate(broker.getStartDate())
                .endDate(broker.getEndDate())
                .createdDate(broker.getCreatedDate())
                .updatedDate(broker.getUpdatedDate())
                .build();
        return brokerDto;
    }

    /**
     * Convert broker dto to broker entity
     * @param brokerDto
     * @return
     */
    @Override
    public Broker brokerDtoToBroker(BrokerDto brokerDto) {
        if(brokerDto == null){
            return null;
        }
        Broker broker = Broker.builder()
                .brokerSK(brokerDto.getBrokerSK())
                .brokerCode(brokerDto.getBrokerCode())
                .brokerId(brokerDto.getBrokerId())
                .brokerName(brokerDto.getBrokerName())
                .accountNumber1(brokerDto.getAccountNumber1())
                .accountNumber2(brokerDto.getAccountNumber2())
                .agencyId(brokerDto.getAgencyId())
                .agencyName(brokerDto.getAgencyName())
                .ztcn(brokerDto.getZtcn())
                .source(brokerDto.getSource())
                .startDate(brokerDto.getStartDate())
                .endDate(brokerDto.getEndDate())
                .createdDate(brokerDto.getCreatedDate())
                .updatedDate(brokerDto.getUpdatedDate())
                .build();
        return broker;
    }

    /**
     * Convert broker entities to broker dtos
     * @param brokers
     * @return
     */
    @Override
    public List<BrokerDto> brokersToBrokerDtos(List<Broker> brokers) {
        return brokers.stream().map(this::brokerToBrokerDto).collect(Collectors.toList());
    }

    /**
     * Convert broker dtos to broker entities
     * @param brokerDtos
     * @return
     */
    @Override
    public List<Broker> brokerDtosToBrokers(List<BrokerDto> brokerDtos) {
        return brokerDtos.stream().map(this::brokerDtoToBroker).collect(Collectors.toList());
    }
}
