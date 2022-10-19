package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import com.brihaspathee.zeus.dto.account.PremiumSpanDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 9:18 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PremiumSpanMapper {

    /**
     * Convert premium span dto to premium span entity
     * @param premiumSpanDto
     * @return
     */
    PremiumSpan premiumSpanDtoToPremiumSpan(PremiumSpanDto premiumSpanDto);

    /**
     * Convert premium span entity to premium span dto
     * @param premiumSpan
     * @return
     */
    PremiumSpanDto premiumSpanToPremiumSpanDto(PremiumSpan premiumSpan);

    /**
     * Convert premium span dtos to premium span entities
     * @param premiumSpanDtos
     * @return
     */
    Set<PremiumSpan> premiumSpanDtosToPremiumSpans(Set<PremiumSpanDto> premiumSpanDtos);

    /**
     * Convert premium span entities to premium span dtos
     * @param premiumSpans
     * @return
     */
    Set<PremiumSpanDto> premiumSpansToPremiumSpanDtos(Set<PremiumSpan> premiumSpans);
}
