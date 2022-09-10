package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberEmail;
import com.brihaspathee.zeus.web.model.MemberEmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 4:51 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberEmailMapper {

    /**
     * Convert MemberEmailDto to MemberEmail
     * @param emailDto
     * @return
     */
    MemberEmail emailDtoToEmail(MemberEmailDto emailDto);

    /**
     * Convert MemberEmail to MemberEmailDto
     * @param email
     * @return
     */
    MemberEmailDto emailToEmailDto(MemberEmail email);

    /**
     * Convert list of member email dtos to list of member email entity
     * @param emailDtos
     * @return
     */
    Set<MemberEmail> emailDtosToEmails(Set<MemberEmailDto> emailDtos);

    /**
     * Convert list of member email entities to member email dtos
     * @param emails
     * @return
     */
    Set<MemberEmailDto> emailsToEmailDtos(Set<MemberEmail> emails);
}
