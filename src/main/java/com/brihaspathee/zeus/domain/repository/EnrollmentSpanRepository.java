package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:15 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface EnrollmentSpanRepository extends JpaRepository<EnrollmentSpan, UUID> {

    /**
     * Find all the enrollment spans that match the exchange subscriber id and state type code
     * @param exchangeSubscriberId
     * @param stateTypeCode
     * @return
     */
    List<EnrollmentSpan> findEnrollmentSpansByExchangeSubscriberIdAndStateTypeCode(String exchangeSubscriberId,
                                                                   String stateTypeCode);

    /**
     * Find enrollment span by enrollment span code
     * @param enrollmentSpanCode
     * @return
     */
    Optional<EnrollmentSpan> findEnrollmentSpanByEnrollmentSpanCode(String enrollmentSpanCode);
}
