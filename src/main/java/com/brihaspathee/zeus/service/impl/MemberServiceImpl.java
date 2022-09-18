package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.repository.AccountRepository;
import com.brihaspathee.zeus.domain.repository.MemberRepository;
import com.brihaspathee.zeus.exception.AccountNotFoundException;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.MemberMapper;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import com.brihaspathee.zeus.web.model.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        Member member = memberMapper.memberDtoToMember(memberDto);
        member = memberRepository.save(member);
        return memberMapper.memberToMemberDto(member);
    }
}
