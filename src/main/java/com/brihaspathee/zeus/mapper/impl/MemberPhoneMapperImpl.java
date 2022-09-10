package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberEmail;
import com.brihaspathee.zeus.domain.entity.MemberPhone;
import com.brihaspathee.zeus.mapper.interfaces.MemberPhoneMapper;
import com.brihaspathee.zeus.web.model.MemberPhoneDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 5:19 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class MemberPhoneMapperImpl implements MemberPhoneMapper {

    /**
     * Convert MemberPhoneDto to MemberPhone
     * @param phoneDto
     * @return
     */
    @Override
    public MemberPhone phoneDtoToPhone(MemberPhoneDto phoneDto) {
        if(phoneDto == null){
            return null;
        }
        MemberPhone phone = MemberPhone.builder()
                .memberPhoneSK(phoneDto.getMemberPhoneSK())
                .member(Member.builder().memberSK(phoneDto.getMemberSK()).build())
                .phoneTypeCode(phoneDto.getPhoneTypeCode())
                .phoneNumber(phoneDto.getPhoneNumber())
                .startDate(phoneDto.getStartDate())
                .endDate(phoneDto.getEndDate())
                .createdDate(phoneDto.getCreatedDate())
                .updatedDate(phoneDto.getUpdatedDate())
                .build();
        return phone;
    }

    /**
     * Convert MemberPhone to MemberPhoneDto
     * @param phone
     * @return
     */
    @Override
    public MemberPhoneDto phoneToPhoneDto(MemberPhone phone) {
        if(phone == null){
            return null;
        }
        MemberPhoneDto phoneDto = MemberPhoneDto.builder()
                .memberPhoneSK(phone.getMemberPhoneSK())
                .memberSK(phone.getMember().getMemberSK())
                .phoneTypeCode(phone.getPhoneTypeCode())
                .phoneNumber(phone.getPhoneNumber())
                .startDate(phone.getStartDate())
                .endDate(phone.getEndDate())
                .createdDate(phone.getCreatedDate())
                .updatedDate(phone.getUpdatedDate())
                .build();
        return phoneDto;
    }

    /**
     * Convert list of member phone dtos to list of member phone entity
     * @param phoneDtos
     * @return
     */
    @Override
    public Set<MemberPhone> phoneDtosToPhones(Set<MemberPhoneDto> phoneDtos) {
        return phoneDtos.stream().map(this::phoneDtoToPhone).collect(Collectors.toSet());
    }

    /**
     * Convert list of member phone entities to member phone dtos
     * @param phones
     * @return
     */
    @Override
    public Set<MemberPhoneDto> phonesToPhoneDtos(Set<MemberPhone> phones) {
        return phones.stream().map(this::phoneToPhoneDto).collect(Collectors.toSet());
    }
}
