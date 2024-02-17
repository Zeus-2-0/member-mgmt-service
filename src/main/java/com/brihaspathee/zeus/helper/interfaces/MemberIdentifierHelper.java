package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.dto.account.MemberIdentifierDto;

import java.util.List;
import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberIdentifierHelper {

    /**
     * create the member identifier
     * @param memberIdentifierDto
     * @return
     */
    MemberIdentifierDto createMemberIdentifier(MemberIdentifierDto memberIdentifierDto);

    /**
     * Find the member using identifier type and value
     * @param identifierTypeCode
     * @param identifierValue
     * @param isActive
     * @return
     */
    List<MemberIdentifierDto> getMemberIdentifierDtosByValue(String identifierTypeCode,
                                                         String identifierValue,
                                                         boolean isActive);

    /**
     * Find the member using identifier type and value
     * @param identifierTypeCode
     * @param identifierValue
     * @param isActive
     * @return
     */
    List<MemberIdentifier> getMemberIdentifiersByValue(String identifierTypeCode,
                                                      String identifierValue,
                                                      boolean isActive);

    /**
     * Save member identifier
     * @param memberIdentifierDtos
     */
    void saveMemberIdentifiers(Set<MemberIdentifierDto> memberIdentifierDtos);
}
