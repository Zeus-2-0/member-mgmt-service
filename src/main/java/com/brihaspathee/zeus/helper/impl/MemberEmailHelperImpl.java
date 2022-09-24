package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberEmail;
import com.brihaspathee.zeus.domain.repository.MemberEmailRepository;
import com.brihaspathee.zeus.helper.interfaces.MemberEmailHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberEmailMapper;
import com.brihaspathee.zeus.web.model.MemberEmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:22 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEmailHelperImpl implements MemberEmailHelper {

    /**
     * Repository for CRUD operations
     */
    private final MemberEmailRepository memberEmailRepository;

    /**
     * Mapper to convert dtos to entities and vice versa
     */
    private final MemberEmailMapper memberEmailMapper;

    /**
     * Create the member email
     * @param memberEmailDto
     * @return the created member email
     */
    @Override
    public MemberEmailDto createMemberEmail(MemberEmailDto memberEmailDto) {
        MemberEmail memberEmail = memberEmailMapper.emailDtoToEmail(memberEmailDto);
        memberEmail = memberEmailRepository.save(memberEmail);
        return memberEmailMapper.emailToEmailDto(memberEmail);
    }
}