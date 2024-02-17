package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.MemberEmailDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:17 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberEmailHelper {

    /**
     * Create member email
     * @param memberEmailDto
     * @return return member email
     */
    MemberEmailDto createMemberEmail(MemberEmailDto memberEmailDto);

    /**
     * Save member email
     * @param memberEmailDtos
     */
    void saveMemberEmail(Set<MemberEmailDto> memberEmailDtos);
}
