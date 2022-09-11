package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import com.brihaspathee.zeus.mapper.interfaces.PremiumSpanMapper;
import com.brihaspathee.zeus.web.model.PremiumSpanDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 9:19 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class PremiumSpanMapperImpl implements PremiumSpanMapper {

    /**
     * Convert premium span dto to premium span entity
     * @param premiumSpanDto
     * @return
     */
    @Override
    public PremiumSpan premiumSpanDtoToPremiumSpan(PremiumSpanDto premiumSpanDto) {
        if(premiumSpanDto == null){
            return null;
        }
        PremiumSpan premiumSpan = PremiumSpan.builder()
                .premiumSpanSK(premiumSpanDto.getPremiumSpanSK())
                .enrollmentSpan(EnrollmentSpan.builder().enrollmentSpanSK(premiumSpanDto.getEnrollmentSpanSK()).build())
                .totalPremiumAmount(premiumSpanDto.getTotalPremiumAmount())
                .totalResponsibleAmount(premiumSpanDto.getTotalResponsibleAmount())
                .aptcAmount(premiumSpanDto.getAptcAmount())
                .otherPayAmount(premiumSpanDto.getOtherPayAmount())
                .csrAmount(premiumSpanDto.getCsrAmount())
                .createdDate(premiumSpanDto.getCreatedDate())
                .updatedDate(premiumSpanDto.getUpdatedDate())
                .build();
        return premiumSpan;
    }

    /**
     * Convert premium span entity to premium span dto
     * @param premiumSpan
     * @return
     */
    @Override
    public PremiumSpanDto premiumSpanToPremiumSpanDto(PremiumSpan premiumSpan) {
        if(premiumSpan == null){
            return null;
        }
        PremiumSpanDto premiumSpanDto = PremiumSpanDto.builder()
                .premiumSpanSK(premiumSpan.getPremiumSpanSK())
                .enrollmentSpanSK(premiumSpan.getEnrollmentSpan().getEnrollmentSpanSK())
                .totalPremiumAmount(premiumSpan.getTotalPremiumAmount())
                .totalResponsibleAmount(premiumSpan.getTotalResponsibleAmount())
                .aptcAmount(premiumSpan.getAptcAmount())
                .otherPayAmount(premiumSpan.getOtherPayAmount())
                .csrAmount(premiumSpan.getCsrAmount())
                .createdDate(premiumSpan.getCreatedDate())
                .updatedDate(premiumSpan.getUpdatedDate())
                .build();
        return premiumSpanDto;
    }

    /**
     * Convert premium span dtos to premium span entities
     * @param premiumSpanDtos
     * @return
     */
    @Override
    public Set<PremiumSpan> premiumSpanDtosToPremiumSpans(Set<PremiumSpanDto> premiumSpanDtos) {
        return premiumSpanDtos.stream().map(this::premiumSpanDtoToPremiumSpan).collect(Collectors.toSet());
    }

    /**
     * Convert premium span entities to premium span dtos
     * @param premiumSpans
     * @return
     */
    @Override
    public Set<PremiumSpanDto> premiumSpansToPremiumSpanDtos(Set<PremiumSpan> premiumSpans) {
        return premiumSpans.stream().map(this::premiumSpanToPremiumSpanDto).collect(Collectors.toSet());
    }
}
