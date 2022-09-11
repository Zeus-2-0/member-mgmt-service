package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.web.model.EnrollmentSpanDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 9:28 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface EnrollmentSpanMapper {

    /**
     * Convert the enrollment span dto to enrollment span entity
     * @param enrollmentSpanDto
     * @return
     */
    EnrollmentSpan enrollmentSpanDtoToEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto);

    /**
     * Convert the enrollment span entity to enrollment span dto
     * @param enrollmentSpan
     * @return
     */
    EnrollmentSpanDto enrollmentSpanToEnrollmentSpanDto(EnrollmentSpan enrollmentSpan);

    /**
     * Convert the enrollment span dtos to enrollment span entities
     * @param enrollmentSpanDtos
     * @return
     */
    Set<EnrollmentSpan> enrollmentSpanDtosToEnrollmentSpans(Set<EnrollmentSpanDto> enrollmentSpanDtos);

    /**
     * Convert the enrollment span entities to enrollment span dtos
     * @param enrollmentSpans
     * @return
     */
    Set<EnrollmentSpanDto> enrollmentSpansToEnrollmentSpanDtos(Set<EnrollmentSpan> enrollmentSpans);
}
