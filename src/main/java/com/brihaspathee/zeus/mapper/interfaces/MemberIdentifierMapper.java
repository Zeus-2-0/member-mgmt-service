package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.dto.account.MemberIdentifierDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:09 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberIdentifierMapper {

    /**
     * Convert identifier dto to identifier entity
     * @param memberIdentifierDto
     * @return
     */
    MemberIdentifier identifierDtoToIdentifier(MemberIdentifierDto memberIdentifierDto);

    /**
     * Convert identifier entity to identifier dto
     * @param memberIdentifier
     * @return
     */
    MemberIdentifierDto identifierToIdentifierDto(MemberIdentifier memberIdentifier);

    /**
     * Convert identifier dtos to identifier entities
     * @param memberIdentifierDtos
     * @return
     */
    Set<MemberIdentifier> identifierDtosToIdentifiers(Set<MemberIdentifierDto> memberIdentifierDtos);

    /**
     * Convert identifier entities to identifier dtos
     * @param memberIdentifiers
     * @return
     */
    Set<MemberIdentifierDto> identifiersToIdentifierDtos(Set<MemberIdentifier> memberIdentifiers);
}
