package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:18 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface MemberIdentifierRepository extends JpaRepository<MemberIdentifier, UUID> {

    /**
     * Find the member identifier by type and value
     * @param identifierValue
     * @param identifierTypeCode
     * @return
     */
    List<MemberIdentifier> findMemberIdentifierByAndIdentifierValueAndIdentifierTypeCode(String identifierValue,
                                                                                      String identifierTypeCode);

    /**
     * Find member identifier by identifier value
     * @param identifierValue
     * @return
     */
    List<MemberIdentifier> findMemberIdentifierByIdentifierValue(String identifierValue);

    /**
     * Find member identifier by identifier type code
     * @param identifierTypeCode
     * @return
     */
    List<MemberIdentifier> findMemberIdentifierByIdentifierTypeCode(String identifierTypeCode);
}
