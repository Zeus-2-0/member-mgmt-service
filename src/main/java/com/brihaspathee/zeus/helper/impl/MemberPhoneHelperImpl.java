package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberPhone;
import com.brihaspathee.zeus.domain.repository.MemberPhoneRepository;
import com.brihaspathee.zeus.dto.account.MemberPhoneDto;
import com.brihaspathee.zeus.helper.interfaces.MemberPhoneHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberPhoneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:21 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberPhoneHelperImpl implements MemberPhoneHelper {

    /**
     * Repository for CRUD operations
     */
    private final MemberPhoneRepository memberPhoneRepository;

    /**
     * Mapper to convert dtos to entities and vice versa
     */
    private final MemberPhoneMapper memberPhoneMapper;

    /**
     * Create the member phone
     * @param memberPhoneDto
     * @return the created member phone
     */
    @Override
    public MemberPhoneDto createMemberPhone(MemberPhoneDto memberPhoneDto) {
        MemberPhone memberPhone = memberPhoneMapper.phoneDtoToPhone(memberPhoneDto);
        memberPhone = memberPhoneRepository.save(memberPhone);
        return memberPhoneMapper.phoneToPhoneDto(memberPhone);
    }

    /**
     * Save member phones
     * @param memberPhoneDtos
     */
    @Override
    public void saveMemberPhones(Set<MemberPhoneDto> memberPhoneDtos) {
        if(memberPhoneDtos == null || memberPhoneDtos.isEmpty()){
            return;
        }
        memberPhoneDtos.forEach(memberPhoneDto -> {
            if(memberPhoneDto.getChanged().get()){
                createMemberPhone(memberPhoneDto);
            }
        });
    }
}
