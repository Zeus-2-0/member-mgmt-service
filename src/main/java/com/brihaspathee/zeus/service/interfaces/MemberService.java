package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.dto.account.MemberDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:40 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberService {

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    MemberDto getMemberByCode(String memberCode);

    /**
     * Get all the members of an account
     * @param accountNumber
     * @return
     */
    Set<MemberDto> getMembersOfAccount(String accountNumber);

    /**
     * Create a member
     * @param memberDto
     * @return
     */
    MemberDto createMember(MemberDto memberDto);
}
