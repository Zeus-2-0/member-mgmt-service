package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.PayloadTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, September 2022
 * Time: 5:18 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface PayloadTrackerRepository extends JpaRepository<PayloadTracker, UUID> {
}
