package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberLanguage;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.mapper.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:45 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberMapperImpl implements MemberMapper {

    /**
     * Address mapper to map the address entity
     */
    private final MemberAddressMapper addressMapper;

    /**
     * Attribute mapper to map the attribute entity
     */
    private final MemberAttributeMapper attributeMapper;

    /**
     * Email mapper to map the email entity
     */
    private final MemberEmailMapper emailMapper;

    /**
     * Identifier mapper to map the identifier entity
     */
    private final MemberIdentifierMapper identifierMapper;

    /**
     * Language mapper to map the language entity
     */
    private final MemberLanguageMapper languageMapper;

    /**
     * Phone mapper to mape the phone entity
     */
    private final MemberPhoneMapper phoneMapper;

    /**
     * Convert member dto to member entity
     * @param memberDto
     * @return
     */
    @Override
    public Member memberDtoToMember(MemberDto memberDto) {
        if(memberDto == null){
            return null;
        }
        Member member = Member.builder()
                .memberSK(memberDto.getMemberSK())
                .account(Account.builder().accountSK(memberDto.getAccountSK()).build())
                .memberCode(memberDto.getMemberCode())
                .firstName(memberDto.getFirstName())
                .middleName(memberDto.getMiddleName())
                .lastName(memberDto.getLastName())
                .relationshipTypeCode(memberDto.getRelationshipTypeCode())
                .dateOfBirth(memberDto.getDateOfBirth())
                .genderTypeCode(memberDto.getGenderTypeCode())
                .height(BigDecimal.valueOf(memberDto.getHeight()))
                .weight(BigDecimal.valueOf(memberDto.getWeight()))
                .memberAddresses(addressMapper.addressDtosToAddresses(memberDto.getMemberAddresses()))
                .memberAttributes(attributeMapper.memberAttributeDtosToMemberAttributes(memberDto.getMemberAttributes()))
                .memberEmails(emailMapper.emailDtosToEmails(memberDto.getMemberEmails()))
                .memberIdentifiers(identifierMapper.identifierDtosToIdentifiers(memberDto.getMemberIdentifiers()))
                .memberLanguages(languageMapper.languageDtosToLanguages(memberDto.getMemberLanguages()))
                .memberPhones(phoneMapper.phoneDtosToPhones(memberDto.getMemberPhones()))
                .createdDate(memberDto.getCreatedDate())
                .updatedDate(memberDto.getUpdatedDate())
                .build();
        return member;
    }

    /**
     * Convert member entity to member dto
     * @param member
     * @return
     */
    @Override
    public MemberDto memberToMemberDto(Member member) {
        if(member == null){
            return null;
        }
        MemberDto memberDto = MemberDto.builder()
                .memberSK(member.getMemberSK())
                .accountSK(member.getAccount().getAccountSK())
                .memberCode(member.getMemberCode())
                .firstName(member.getFirstName())
                .middleName(member.getMiddleName())
                .lastName(member.getLastName())
                .relationshipTypeCode(member.getRelationshipTypeCode())
                .dateOfBirth(member.getDateOfBirth())
                .genderTypeCode(member.getGenderTypeCode())
                .height(member.getHeight().doubleValue())
                .weight(member.getWeight().doubleValue())
                .memberAddresses(addressMapper.addressesToAddressDtos(member.getMemberAddresses()))
                .memberAttributes(attributeMapper.memberAttributesToMemberAttributeDtos(member.getMemberAttributes()))
                .memberEmails(emailMapper.emailsToEmailDtos(member.getMemberEmails()))
                .memberIdentifiers(identifierMapper.identifiersToIdentifierDtos(member.getMemberIdentifiers()))
                .memberLanguages(languageMapper.languagesToLanguageDtos(member.getMemberLanguages()))
                .memberPhones(phoneMapper.phonesToPhoneDtos(member.getMemberPhones()))
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .build();
        return memberDto;
    }

    /**
     * Convert member dtos to member entities
     * @param memberDtos
     * @return
     */
    @Override
    public Set<Member> memberDtosToMembers(Set<MemberDto> memberDtos) {
        if(memberDtos !=null && !memberDtos.isEmpty()){
            return memberDtos.stream().map(this::memberDtoToMember).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert member entities to member dtos
     * @param members
     * @return
     */
    @Override
    public Set<MemberDto> membersToMemberDtos(Set<Member> members) {
        if(members !=null && !members.isEmpty()){
            return members.stream().map(this::memberToMemberDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
