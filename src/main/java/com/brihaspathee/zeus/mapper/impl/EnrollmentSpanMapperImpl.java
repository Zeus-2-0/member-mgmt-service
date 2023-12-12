package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import com.brihaspathee.zeus.mapper.interfaces.PremiumSpanMapper;
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
                .enrollmentSpanCode(enrollmentSpanDto.getEnrollmentSpanCode())
                .account(Account.builder().accountSK(enrollmentSpanDto.getAccountSK()).build())
                .businessUnitTypeCode(enrollmentSpanDto.getBusinessUnitTypeCode())
                .coverageTypeCode(enrollmentSpanDto.getCoverageTypeCode())
                .planId(enrollmentSpanDto.getPlanId())
                .groupPolicyId(enrollmentSpanDto.getGroupPolicyId())
                .productTypeCode(enrollmentSpanDto.getProductTypeCode())
                .marketplaceTypeCode(enrollmentSpanDto.getMarketplaceTypeCode())
                .stateTypeCode(enrollmentSpanDto.getStateTypeCode())
                .statusTypeCode(enrollmentSpanDto.getStatusTypeCode())
                .effectiveReason(enrollmentSpanDto.getEffectiveReason())
                .termReason(enrollmentSpanDto.getTermReason())
                .effectuationDate(enrollmentSpanDto.getEffectuationDate())
                .startDate(enrollmentSpanDto.getStartDate())
                .endDate(enrollmentSpanDto.getEndDate())
                .exchangeSubscriberId(enrollmentSpanDto.getExchangeSubscriberId())
                .delinqInd(enrollmentSpanDto.isDelinqInd())
                .ztcn(enrollmentSpanDto.getZtcn())
                .paidThroughDate(enrollmentSpanDto.getPaidThroughDate())
                .claimPaidThroughDate(enrollmentSpanDto.getClaimPaidThroughDate())
                //.premiumSpans(premiumSpanMapper.premiumSpanDtosToPremiumSpans(enrollmentSpanDto.getPremiumSpans()))
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
        log.info("to be mapped enrollment span:{}", enrollmentSpan);
        EnrollmentSpanDto enrollmentSpanDto = EnrollmentSpanDto.builder()
                .enrollmentSpanSK(enrollmentSpan.getEnrollmentSpanSK())
                .enrollmentSpanCode(enrollmentSpan.getEnrollmentSpanCode())
                .accountSK(enrollmentSpan.getAccount().getAccountSK())
                .businessUnitTypeCode(enrollmentSpan.getBusinessUnitTypeCode())
                .coverageTypeCode(enrollmentSpan.getCoverageTypeCode())
                .planId(enrollmentSpan.getPlanId())
                .groupPolicyId(enrollmentSpan.getGroupPolicyId())
                .productTypeCode(enrollmentSpan.getProductTypeCode())
                .marketplaceTypeCode(enrollmentSpan.getMarketplaceTypeCode())
                .stateTypeCode(enrollmentSpan.getStateTypeCode())
                .statusTypeCode(enrollmentSpan.getStatusTypeCode())
                .effectiveReason(enrollmentSpan.getEffectiveReason())
                .termReason(enrollmentSpan.getTermReason())
                .effectuationDate(enrollmentSpan.getEffectuationDate())
                .startDate(enrollmentSpan.getStartDate())
                .endDate(enrollmentSpan.getEndDate())
                .exchangeSubscriberId(enrollmentSpan.getExchangeSubscriberId())
                .delinqInd(enrollmentSpan.isDelinqInd())
                .paidThroughDate(enrollmentSpan.getPaidThroughDate())
                .claimPaidThroughDate(enrollmentSpan.getClaimPaidThroughDate())
                .ztcn(enrollmentSpan.getZtcn())
                .premiumSpans(premiumSpanMapper.premiumSpansToPremiumSpanDtos(enrollmentSpan.getPremiumSpans()))
                .createdDate(enrollmentSpan.getCreatedDate())
                .updatedDate(enrollmentSpan.getUpdatedDate())
                .build();
        log.info("mapped enrollment span dto:{}", enrollmentSpanDto);
        return enrollmentSpanDto;
    }

    /**
     * Convert the enrollment span dtos to enrollment span entities
     * @param enrollmentSpanDtos
     * @return
     */
    @Override
    public Set<EnrollmentSpan> enrollmentSpanDtosToEnrollmentSpans(Set<EnrollmentSpanDto> enrollmentSpanDtos) {
        if(enrollmentSpanDtos !=null && !enrollmentSpanDtos.isEmpty()){
            return enrollmentSpanDtos.stream().map(this::enrollmentSpanDtoToEnrollmentSpan).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert the enrollment span entities to enrollment span dtos
     * @param enrollmentSpans
     * @return
     */
    @Override
    public Set<EnrollmentSpanDto> enrollmentSpansToEnrollmentSpanDtos(Set<EnrollmentSpan> enrollmentSpans) {
        if(enrollmentSpans !=null && !enrollmentSpans.isEmpty()){
            return enrollmentSpans.stream().map(this::enrollmentSpanToEnrollmentSpanDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
