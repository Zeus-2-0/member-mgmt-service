package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberEmail;
import com.brihaspathee.zeus.domain.entity.MemberPhone;
import com.brihaspathee.zeus.web.model.MemberPhoneDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 5:14 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberPhoneMapper {

    /**
     * Convert MemberPhoneDto to MemberPhone
     * @param phoneDto
     * @return
     */
    MemberPhone phoneDtoToPhone(MemberPhoneDto phoneDto);

    /**
     * Convert MemberPhone to MemberPhoneDto
     * @param phone
     * @return
     */
    MemberPhoneDto phoneToPhoneDto(MemberPhone phone);

    /**
     * Convert list of member phone dtos to list of member phone entity
     * @param phoneDtos
     * @return
     */
    Set<MemberPhone> phoneDtosToPhones(Set<MemberPhoneDto> phoneDtos);

    /**
     * Convert list of member phone entities to member phone dtos
     * @param phones
     * @return
     */
    Set<MemberPhoneDto> phonesToPhoneDtos(Set<MemberPhone> phones);
}
