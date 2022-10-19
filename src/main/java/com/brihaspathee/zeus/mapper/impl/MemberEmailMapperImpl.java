package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberEmail;
import com.brihaspathee.zeus.dto.account.MemberEmailDto;
import com.brihaspathee.zeus.mapper.interfaces.MemberEmailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 4:56 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEmailMapperImpl implements MemberEmailMapper {

    /**
     * Convert MemberEmailDto to MemberEmail
     * @param emailDto
     * @return
     */
    @Override
    public MemberEmail emailDtoToEmail(MemberEmailDto emailDto) {
        if(emailDto == null){
            return null;
        }
        MemberEmail email = MemberEmail.builder()
                .memberEmailSK(emailDto.getMemberEmailSK())
                .member(Member.builder().memberSK(emailDto.getMemberSK()).build())
                .emailTypeCode(emailDto.getEmailTypeCode())
                .email(emailDto.getEmail())
                .isPrimary(emailDto.isPrimary())
                .startDate(emailDto.getStartDate())
                .endDate(emailDto.getEndDate())
                .createdDate(emailDto.getCreatedDate())
                .updatedDate(emailDto.getUpdatedDate())
                .build();
        return email;
    }

    /**
     * Convert MemberEmail to MemberEmailDto
     * @param email
     * @return
     */
    @Override
    public MemberEmailDto emailToEmailDto(MemberEmail email) {
        if(email == null){
            return null;
        }
        MemberEmailDto emailDto = MemberEmailDto.builder()
                .memberEmailSK(email.getMemberEmailSK())
                .memberSK(email.getMember().getMemberSK())
                .emailTypeCode(email.getEmailTypeCode())
                .email(email.getEmail())
                .isPrimary(email.isPrimary())
                .startDate(email.getStartDate())
                .endDate(email.getEndDate())
                .createdDate(email.getCreatedDate())
                .updatedDate(email.getUpdatedDate())
                .build();
        return emailDto;
    }

    /**
     * Convert list of member email dtos to list of member email entity
     * @param emailDtos
     * @return
     */
    @Override
    public Set<MemberEmail> emailDtosToEmails(Set<MemberEmailDto> emailDtos) {
        if(emailDtos !=null && !emailDtos.isEmpty()){
            return emailDtos.stream().map(this::emailDtoToEmail).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert list of member email entities to member email dtos
     * @param emails
     * @return
     */
    @Override
    public Set<MemberEmailDto> emailsToEmailDtos(Set<MemberEmail> emails) {
        if(emails !=null && !emails.isEmpty()){
            return emails.stream().map(this::emailToEmailDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
