package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import com.brihaspathee.zeus.mapper.interfaces.PremiumSpanMapper;
import com.brihaspathee.zeus.web.model.EnrollmentSpanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 9:29 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentSpanMapperImpl implements EnrollmentSpanMapper {

    /**
     * premium span mapper to map the premium span
     */
    private final PremiumSpanMapper premiumSpanMapper;

    /**
     * Convert the enrollment span dto to enrollment span entity
     * @param enrollmentSpanDto
     * @return
     */
    @Override
    public EnrollmentSpan enrollmentSpanDtoToEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto) {
        if(enrollmentSpanDto == null){
            return null;
        }
        EnrollmentSpan enrollmentSpan = EnrollmentSpan.builder()
                .enrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK())
                .account(Account.builder().accountSK(enrollmentSpanDto.getAccountSK()).build())
                .businessUnitTypeCode(enrollmentSpanDto.getBusinessUnitTypeCode())
                .planId(enrollmentSpanDto.getPlanId())
                .groupPolicyId(enrollmentSpanDto.getGroupPolicyId())
                .productTypeCode(enrollmentSpanDto.getProductTypeCode())
                .marketplaceTypeCode(enrollmentSpanDto.getMarketplaceTypeCode())
                .stateTypeCode(enrollmentSpanDto.getStateTypeCode())
                .statusTypeCode(enrollmentSpanDto.getStatusTypeCode())
                .effectuationDate(enrollmentSpanDto.getEffectuationDate())
                .startDate(enrollmentSpanDto.getStartDate())
                .endDate(enrollmentSpanDto.getEndDate())
                .premiumSpans(premiumSpanMapper.premiumSpanDtosToPremiumSpans(enrollmentSpanDto.getPremiumSpans()))
                .createdDate(enrollmentSpanDto.getCreatedDate())
                .updatedDate(enrollmentSpanDto.getUpdatedDate())
                .build();
        return enrollmentSpan;
    }

    /**
     * Convert the enrollment span entity to enrollment span dto
     * @param enrollmentSpan
     * @return
     */
    @Override
    public EnrollmentSpanDto enrollmentSpanToEnrollmentSpanDto(EnrollmentSpan enrollmentSpan) {
        if(enrollmentSpan == null){
            return null;
        }
        EnrollmentSpanDto enrollmentSpanDto = EnrollmentSpanDto.builder()
                .enrollmentSpanSK(enrollmentSpan.getEnrollmentSpanSK())
                .accountSK(enrollmentSpan.getAccount().getAccountSK())
                .businessUnitTypeCode(enrollmentSpan.getBusinessUnitTypeCode())
                .planId(enrollmentSpan.getPlanId())
                .groupPolicyId(enrollmentSpan.getGroupPolicyId())
                .productTypeCode(enrollmentSpan.getProductTypeCode())
                .marketplaceTypeCode(enrollmentSpan.getMarketplaceTypeCode())
                .stateTypeCode(enrollmentSpan.getStateTypeCode())
                .statusTypeCode(enrollmentSpan.getStatusTypeCode())
                .effectuationDate(enrollmentSpan.getEffectuationDate())
                .startDate(enrollmentSpan.getStartDate())
                .endDate(enrollmentSpan.getEndDate())
                .premiumSpans(premiumSpanMapper.premiumSpansToPremiumSpanDtos(enrollmentSpan.getPremiumSpans()))
                .createdDate(enrollmentSpan.getCreatedDate())
                .updatedDate(enrollmentSpan.getUpdatedDate())
                .build();
        return enrollmentSpanDto;
    }

    /**
     * Convert the enrollment span dtos to enrollment span entities
     * @param enrollmentSpanDtos
     * @return
     */
    @Override
    public Set<EnrollmentSpan> enrollmentSpanDtosToEnrollmentSpans(Set<EnrollmentSpanDto> enrollmentSpanDtos) {
        return enrollmentSpanDtos.stream().map(this::enrollmentSpanDtoToEnrollmentSpan).collect(Collectors.toSet());
    }

    /**
     * Convert the enrollment span entities to enrollment span dtos
     * @param enrollmentSpans
     * @return
     */
    @Override
    public Set<EnrollmentSpanDto> enrollmentSpansToEnrollmentSpanDtos(Set<EnrollmentSpan> enrollmentSpans) {
        return enrollmentSpans.stream().map(this::enrollmentSpanToEnrollmentSpanDto).collect(Collectors.toSet());
    }
}