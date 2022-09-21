package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.domain.repository.MemberEmailRepository;
import com.brihaspathee.zeus.domain.repository.MemberIdentifierRepository;
import com.brihaspathee.zeus.helper.interfaces.MemberIdentifierHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberEmailMapper;
import com.brihaspathee.zeus.mapper.interfaces.MemberIdentifierMapper;
import com.brihaspathee.zeus.web.model.MemberIdentifierDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
