package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.web.model.EnrollmentSpanDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, September 2022
 * Time: 6:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface EnrollmentSpanHelper {

    /**
     * Create an enrollment span
     * @param enrollmentSpanDto
     * @return
     */
    EnrollmentSpanDto createEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto);
}
