package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.mapper.interfaces.MemberIdentifierMapper;
import com.brihaspathee.zeus.web.model.MemberIdentifierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:14 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class MemberIdentifierMapperImpl implements MemberIdentifierMapper {

    /**
     * Convert identifier dto to identifier entity
     * @param memberIdentifierDto
     * @return
     */
    @Override
    public MemberIdentifier identifierDtoToIdentifier(MemberIdentifierDto memberIdentifierDto) {
        if(memberIdentifierDto == null){
            return null;
        }
        MemberIdentifier memberIdentifier = MemberIdentifier.builder()
                .memberIdentifierSK(memberIdentifierDto.getMemberIdentifierSK())
                .member(Member.builder().memberSK(memberIdentifierDto.getMemberSK()).build())
                .identifierTypeCode(memberIdentifierDto.getIdentifierTypeCode())
                .identifierValue(memberIdentifierDto.getIdentifierValue())
                .createdDate(memberIdentifierDto.getCreatedDate())
                .updatedDate(memberIdentifierDto.getUpdatedDate())
                .build();
        return memberIdentifier;
    }

    /**
     * Convert identifier entity to identifier dto
     * @param memberIdentifier
     * @return
     */
    @Override
    public MemberIdentifierDto identifierToIdentifierDto(MemberIdentifier memberIdentifier) {
        if(memberIdentifier == null){
            return null;
        }
        MemberIdentifierDto memberIdentifierDto = MemberIdentifierDto.builder()
                .memberIdentifierSK(memberIdentifier.getMemberIdentifierSK())
                .memberSK(memberIdentifier.getMember().getMemberSK())
                .identifierTypeCode(memberIdentifier.getIdentifierTypeCode())
                .identifierValue(memberIdentifier.getIdentifierValue())
                .createdDate(memberIdentifier.getCreatedDate())
                .updatedDate(memberIdentifier.getUpdatedDate())
                .build();
        return memberIdentifierDto;
    }

    /**
     * Convert identifier dtos to identifier entities
     * @param memberIdentifierDtos
     * @return
     */
    @Override
    public Set<MemberIdentifier> identifierDtosToIdentifiers(Set<MemberIdentifierDto> memberIdentifierDtos) {
        return memberIdentifierDtos.stream().map(this::identifierDtoToIdentifier).collect(Collectors.toSet());
    }

    /**
     * Convert identifier entities to identifier dtos
     * @param memberIdentifiers
     * @return
     */
    @Override
    public Set<MemberIdentifierDto> identifiersToIdentifierDtos(Set<MemberIdentifier> memberIdentifiers) {
        return memberIdentifiers.stream().map(this::identifierToIdentifierDto).collect(Collectors.toSet());
    }
}
