package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.Payer;
import com.brihaspathee.zeus.domain.repository.PayerRepository;
import com.brihaspathee.zeus.dto.account.PayerDto;
import com.brihaspathee.zeus.helper.interfaces.PayerHelper;
import com.brihaspathee.zeus.mapper.interfaces.PayerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 4:13 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PayerHelperImpl implements PayerHelper {

    /**
     * Payer repository instance to perform CRUD operations
     */
    private final PayerRepository payerRepository;

    /**
     * Payer mapper to convert dto to entity and vice versa
     */
    private final PayerMapper payerMapper;

    /**
     * Create the payer
     * @param payerDto
     * @return
     */
    @Override
    public PayerDto createPayer(PayerDto payerDto) {
        Payer payer = payerMapper.payerDtoToPayer(payerDto);
        payer = payerRepository.save(payer);
        return payerMapper.payerToPayerDto(payer);
    }
}
