package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberIdentifier;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.domain.repository.MemberRepository;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.dto.account.MemberIdentifierDto;
import com.brihaspathee.zeus.exception.AccountNotFoundException;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.*;
import com.brihaspathee.zeus.mapper.interfaces.AlternateContactMapper;
import com.brihaspathee.zeus.mapper.interfaces.MemberMapper;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:42 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    /**
     * Mapper to convert entities to dtos and vice versa
     */
    private final MemberMapper memberMapper;

    /**
     * The member repository to get the data from DB
     */
    private final MemberRepository memberRepository;

    /**
     * The account repository to get the details from the account
     */
    private final AccountRepository accountRepository;

    /**
     * Address helper class
     */
    private final MemberAddressHelper memberAddressHelper;

    /**
     * Identifier helper class
     */
    private final MemberIdentifierHelper memberIdentifierHelper;

    /**
     * Language helper class
     */
    private final MemberLanguageHelper memberLanguageHelper;

    /**
     * Phone helper class
     */
    private final MemberPhoneHelper memberPhoneHelper;

    /**
     * Email helper class
     */
    private final MemberEmailHelper memberEmailHelper;

    /**
     * Alternate Contact helper instance
     */
    private final AlternateContactHelper alternateContactHelper;


    /**
     * Get member by the member code
     * @param memberCode the member code of the member
     * @return the member entity associated with the member code
     */
    @Override
    public MemberDto getMemberByCode(String memberCode) {
        Member member = memberRepository.findMemberByMemberCode(memberCode).orElseThrow(() ->{
            throw new MemberNotFoundException("Member with member code " + memberCode + " not found");
        });
        return memberMapper.memberToMemberDto(member);
    }

    /**
     * Get all the members associated with the account
     * @param accountNumber the account number of all the members
     * @return the set of members associated with the account
     */
    @Override
    public Set<MemberDto> getMembersOfAccount(String accountNumber) {
        Account account = accountRepository.findAccountsByAccountNumber(accountNumber).orElseThrow(() -> {
            throw new AccountNotFoundException("Account with account number " + accountNumber + " not found" );
        });
        Set<Member> members = account.getMembers();
        return memberMapper.membersToMemberDtos(members);
    }

    /**
     * Create a member
     * @param memberDto
     * @return
     */
    @Override
    public MemberDto createMember(MemberDto memberDto) {
        log.info("To be saved Member DTO height:{}", memberDto.getHeight());
        Member member = memberMapper.memberDtoToMember(memberDto);
        log.info("Saved Member height:{}", member.getHeight());
        member = memberRepository.save(member);
        createMemberAddress(memberDto, member.getMemberSK());
        createMemberIdentifier(memberDto,member.getMemberSK());
        createMemberLanguage(memberDto, member.getMemberSK());
        createMemberPhone(memberDto, member.getMemberSK());
        createMemberEmail(memberDto, member.getMemberSK());
        createAlternateContact(memberDto, member.getMemberSK());
        return memberMapper.memberToMemberDto(member);
    }

    /**
     * Get member by social security number
     * @param socialSecurityNumber
     * @return
     */
    @Override
    public List<MemberDto> getMemberBySSN(String socialSecurityNumber) {
        List<MemberIdentifier> memberIdentifiers =
                memberIdentifierHelper.getMemberIdentifiersByValue(
                        "SSN",
                        socialSecurityNumber,
                        true);
        return getMemberDtos(memberIdentifiers);
    }

    /**
     * Get the HOHs by SSN
     * @param socialSecurityNumber
     * @return
     */
    @Override
    public List<MemberDto> getHOHBySSN(String socialSecurityNumber) {
        List<MemberIdentifier> memberIdentifiers =
                memberIdentifierHelper.getMemberIdentifiersByValue(
                        "SSN",
                        socialSecurityNumber,
                        true);
        // Filter out to have only members who have the relationship type code "HOH"
        memberIdentifiers = memberIdentifiers.stream()
                .filter(memberId ->
                        memberId.getMember().getRelationshipTypeCode().equals("HOH"))
                .collect(Collectors.toList());
        return getMemberDtos(memberIdentifiers);
    }

    /**
     * Get member by first name, last name, gender type code and date of birth
     * @param firstName
     * @param lastName
     * @param genderTypeCode
     * @param dateOfBirth
     * @return
     */
    @Override
    public List<MemberDto> getMembersByNameAndDOB(String firstName, String lastName, String genderTypeCode, LocalDate dateOfBirth) {
        List<Member> members = memberRepository.findMemberByFirstNameAndLastNameAndGenderTypeCodeAndDateOfBirth(firstName,
                lastName,
                genderTypeCode,
                dateOfBirth);
        if(members == null || members.isEmpty()){
            return null;
        }
        return memberMapper.membersToMemberDtos(new HashSet<>(members)).stream().toList();
    }

    /**
     * Save member updates or create new members
     * @param accountDto
     */
    @Override
    public void saveMembers(AccountDto accountDto) {
        // Check if there are any members present in the account
        // when an account gets updated, it is not necessary for any member information to get update
        // if no members are updated then the account dto will not have any members
        if(accountDto.getMembers() != null && !accountDto.getMembers().isEmpty()){
            // Iterate through the member collection present in the account
            accountDto.getMembers().forEach(memberDto -> {
                // check if member sk is present in the member dto, if present then it is an existing member
                // if not then it is a new member
                UUID memberSK = memberDto.getMemberSK();
                if(memberSK == null){
                    // the member is not present in the account
                    // create the member
                    createMember(memberDto);
                }else{
                    // it is an existing member
                    if(memberDto.getChanged().get()){
                        memberDto.setAccountSK(accountDto.getAccountSK());
                        updateMember(memberDto);
                        // todo check if member address is updated
                        memberAddressHelper.saveMemberAddresses(memberDto.getMemberAddresses());
                        // todo check if member email is updated
                        memberEmailHelper.saveMemberEmail(memberDto.getMemberEmails());
                        // todo check if member phone is updated
                        memberPhoneHelper.saveMemberPhones(memberDto.getMemberPhones());
                        // todo check if member language is updated
                        memberLanguageHelper.saveMemberLanguages(memberDto.getMemberLanguages());
                        // todo check if member identifier is updated
                        memberIdentifierHelper.saveMemberIdentifiers(memberDto.getMemberIdentifiers());
                    }
                }
            });
        }

    }

    /**
     * Update the member entity
     * @param memberDto
     */
    private void updateMember(MemberDto memberDto){
        Member member = memberMapper.memberDtoToMember(memberDto);
        memberRepository.save(member);
    }

    /**
     * Get the member dtos from member identifier
     * @param memberIdentifiers
     * @return
     */
    private List<MemberDto> getMemberDtos(List<MemberIdentifier> memberIdentifiers) {
        if (memberIdentifiers != null && !memberIdentifiers.isEmpty()){
            List<Member> members = memberIdentifiers.stream().map(MemberIdentifier::getMember).toList();
            return memberMapper.membersToMemberDtos(new HashSet<>(members)).stream().toList();
        }
        return null;
    }

    /**
     * Call member address helper to create address if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createMemberAddress(MemberDto memberDto, UUID memberSK){
        if (memberDto.getMemberAddresses() != null && !memberDto.getMemberAddresses().isEmpty()){
            memberAddressHelper.validateMemberAddresses(memberDto.getMemberAddresses());
            memberDto.getMemberAddresses().forEach(memberAddressDto -> {
                memberAddressDto.setMemberSK(memberSK);
                memberAddressHelper.createMemberAddress(memberAddressDto);
            });
        }
    }

    /**
     * Call member identifier helper to create idenfiers if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createMemberIdentifier(MemberDto memberDto, UUID memberSK){
        if (memberDto.getMemberIdentifiers() != null && !memberDto.getMemberIdentifiers().isEmpty()){
            memberDto.getMemberIdentifiers().stream().forEach(memberIdentifierDto -> {
                memberIdentifierDto.setMemberSK(memberSK);
                memberIdentifierHelper.createMemberIdentifier(memberIdentifierDto);
            });
        }
    }

    /**
     * Call member language helper to create languages if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createMemberLanguage(MemberDto memberDto, UUID memberSK){
        if (memberDto.getMemberLanguages() != null && !memberDto.getMemberLanguages().isEmpty()){
            memberDto.getMemberLanguages().stream().forEach(memberLanguageDto -> {
                memberLanguageDto.setMemberSK(memberSK);
                memberLanguageHelper.createMemberLanguage(memberLanguageDto);
            });
        }
    }

    /**
     * Call member Phone helper to create phone numbers if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createMemberPhone(MemberDto memberDto, UUID memberSK){
        if (memberDto.getMemberPhones() != null && !memberDto.getMemberPhones().isEmpty()){
            memberDto.getMemberPhones().stream().forEach(memberPhoneDto -> {
                memberPhoneDto.setMemberSK(memberSK);
                memberPhoneHelper.createMemberPhone(memberPhoneDto);
            });
        }
    }

    /**
     * Call member email helper to create emails if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createMemberEmail(MemberDto memberDto, UUID memberSK){
        if (memberDto.getMemberEmails() != null && !memberDto.getMemberEmails().isEmpty()){
            memberDto.getMemberEmails().stream().forEach(memberEmailDto -> {
                memberEmailDto.setMemberSK(memberSK);
                memberEmailHelper.createMemberEmail(memberEmailDto);
            });
        }
    }

    /**
     * Call alternate contact helper to create alternate contacts if any present for the member
     * @param memberDto
     * @param memberSK
     */
    private void createAlternateContact(MemberDto memberDto, UUID memberSK){
        if (memberDto.getAlternateContacts() != null && !memberDto.getAlternateContacts().isEmpty()){
            memberDto.getAlternateContacts().stream().forEach(alternateContactDto -> {
                alternateContactDto.setMemberSK(memberSK);
                alternateContactHelper.createAlternateContact(alternateContactDto);
            });
        }
    }
}
