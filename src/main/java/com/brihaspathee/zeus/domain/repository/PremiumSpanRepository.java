package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:20 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface PremiumSpanRepository extends JpaRepository<PremiumSpan, UUID> {
}
