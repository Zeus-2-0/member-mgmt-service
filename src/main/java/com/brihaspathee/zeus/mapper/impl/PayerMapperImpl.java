package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Payer;
import com.brihaspathee.zeus.dto.account.PayerDto;
import com.brihaspathee.zeus.mapper.interfaces.PayerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 2:48 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PayerMapperImpl implements PayerMapper {

    /**
     * Convert payer entity to payer dto
     * @param payer
     * @return
     */
    @Override
    public PayerDto payerToPayerDto(Payer payer) {
        if(payer == null){
            return null;
        }
        PayerDto payerDto = PayerDto.builder()
                .payerSK(payer.getPayerSK())
                .payerCode(payer.getPayerCode())
                .payerId(payer.getPayerId())
                .payerName(payer.getPayerName())
                .ztcn(payer.getZtcn())
                .source(payer.getSource())
                .startDate(payer.getStartDate())
                .endDate(payer.getEndDate())
                .createdDate(payer.getCreatedDate())
                .updatedDate(payer.getUpdatedDate())
                .build();
        return payerDto;
    }

    /**
     * Convert payer dto to payer entity
     * @param payerDto
     * @return
     */
    @Override
    public Payer payerDtoToPayer(PayerDto payerDto) {
        if(payerDto == null){
            return null;
        }
        Payer payer = Payer.builder()
                .payerSK(payerDto.getPayerSK())
                .payerCode(payerDto.getPayerCode())
                .payerId(payerDto.getPayerId())
                .payerName(payerDto.getPayerName())
                .ztcn(payerDto.getZtcn())
                .source(payerDto.getSource())
                .startDate(payerDto.getStartDate())
                .endDate(payerDto.getEndDate())
                .createdDate(payerDto.getCreatedDate())
                .updatedDate(payerDto.getUpdatedDate())
                .build();
        return payer;
    }

    /**
     * Convert payer entities to payer dtos
     * @param payers
     * @return
     */
    @Override
    public List<PayerDto> payersToPayerDtos(List<Payer> payers) {

        return payers.stream().map(this::payerToPayerDto).collect(Collectors.toList());
    }

    /**
     * Convert payer dtos to payer entities
     * @param payerDtos
     * @return
     */
    @Override
    public List<Payer> payerDtosToPayers(List<PayerDto> payerDtos) {

        return payerDtos.stream().map(this::payerDtoToPayer).collect(Collectors.toList());
    }
}
