package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.dto.account.MemberDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    /**
     * Get enrollment spans that match the exchange subcriber id and state type code
     * @param exchangeSubscriberId
     * @param stateTypeCode
     * @return
     */
    List<EnrollmentSpan> getMatchingEnrollmentSpan(String exchangeSubscriberId, String stateTypeCode);

    /**
     * Update an enrollment span
     * @param enrollmentSpanDto
     */
    void updateEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto, AccountDto accountDto);

}
