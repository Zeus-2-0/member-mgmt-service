package com.brihaspathee.zeus.validator.interfaces;

import com.brihaspathee.zeus.web.model.EnrollmentSpanDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, September 2022
 * Time: 3:32 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.validator.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface EnrollmentSpanValidator {

    /**
     * Validates the list of enrollment spans
     * @param enrollmentSpanDtos
     */
    void validateEnrollmentSpans(Set<EnrollmentSpanDto> enrollmentSpanDtos);
}
