package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.repository.EnrollmentSpanRepository;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.helper.interfaces.EnrollmentSpanHelper;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, September 2022
 * Time: 6:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentSpanHelperImpl implements EnrollmentSpanHelper {

    /**
     * Mapper to convert dto to entity and vice versa
     */
    private final EnrollmentSpanMapper enrollmentSpanMapper;

    /**
     * Repository to perform CRUD operations
     */
    private final EnrollmentSpanRepository enrollmentSpanRepository;

    /**
     * Create an enrollment span
     * @param enrollmentSpanDto
     * @return
     */
    @Override
    public EnrollmentSpanDto createEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanMapper.enrollmentSpanDtoToEnrollmentSpan(enrollmentSpanDto);
        enrollmentSpan = enrollmentSpanRepository.save(enrollmentSpan);
        log.info("Created Enrollment span:{}", enrollmentSpan);
        return enrollmentSpanMapper.enrollmentSpanToEnrollmentSpanDto(enrollmentSpan);
    }

    /**
     * Get enrollment spans that match exchange subscriber id and state type code
     * @param exchangeSubscriberId
     * @param stateTypeCode
     * @return
     */
    @Override
    public List<EnrollmentSpan> getMatchingEnrollmentSpan(String exchangeSubscriberId, String stateTypeCode) {
        List<EnrollmentSpan> enrollmentSpans = enrollmentSpanRepository
                .findEnrollmentSpansByExchangeSubscriberIdAndStateTypeCode(exchangeSubscriberId,
                        stateTypeCode);
        return enrollmentSpans;
    }
}
