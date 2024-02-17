package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.domain.repository.MemberEmailRepository;
import com.brihaspathee.zeus.domain.repository.MemberIdentifierRepository;
import com.brihaspathee.zeus.dto.account.MemberIdentifierDto;
import com.brihaspathee.zeus.helper.interfaces.MemberIdentifierHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberEmailMapper;
import com.brihaspathee.zeus.mapper.interfaces.MemberIdentifierMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:16 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberIdentifierHelperImpl implements MemberIdentifierHelper {

    /**
     * Repository for CRUD operations
     */
    private final MemberIdentifierRepository memberIdentifierRepository;

    /**
     * Mapper to convert dtos to entities and vice versa
     */
    private final MemberIdentifierMapper memberIdentifierMapper;

    /**
     * Create member identifier
     * @param memberIdentifierDto
     * @return
     */
    @Override
    public MemberIdentifierDto createMemberIdentifier(MemberIdentifierDto memberIdentifierDto) {
        MemberIdentifier memberIdentifier = memberIdentifierMapper.identifierDtoToIdentifier(memberIdentifierDto);
        memberIdentifier = memberIdentifierRepository.save(memberIdentifier);
        return memberIdentifierMapper.identifierToIdentifierDto(memberIdentifier);
    }

    /**
     * Find the member using identifier type and value
     * @param identifierTypeCode
     * @param identifierValue
     * @param isActive
     * @return
     */
    @Override
    public List<MemberIdentifierDto> getMemberIdentifierDtosByValue(String identifierTypeCode, String identifierValue,
                                                                boolean isActive) {
        List<MemberIdentifier> memberIdentifiers = getMemberIdentifiersByValue(
                identifierTypeCode,
                identifierValue,
                isActive);
        return memberIdentifierMapper.identifiersToIdentifierDtos(
                memberIdentifiers.stream().collect(Collectors.toSet()))
                .stream().toList();
    }

    /**
     * Find the member using identifier type and value
     * @param identifierTypeCode
     * @param identifierValue
     * @param isActive
     * @return
     */
    @Override
    public List<MemberIdentifier> getMemberIdentifiersByValue(String identifierTypeCode, String identifierValue,
                                                                    boolean isActive) {
        return memberIdentifierRepository.findMemberIdentifierByAndIdentifierValueAndIdentifierTypeCode(
                identifierValue,
                identifierTypeCode);
    }

    /**
     * Save member identifiers
     * @param memberIdentifierDtos
     */
    @Override
    public void saveMemberIdentifiers(Set<MemberIdentifierDto> memberIdentifierDtos) {
        if(memberIdentifierDtos == null || memberIdentifierDtos.isEmpty()){
            return;
        }
        memberIdentifierDtos.forEach(memberIdentifierDto -> {
            if(memberIdentifierDto.getChanged().get()){
                createMemberIdentifier(memberIdentifierDto);
            }
        });
    }
}
