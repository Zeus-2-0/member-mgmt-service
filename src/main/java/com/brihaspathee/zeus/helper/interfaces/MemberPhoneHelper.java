package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.MemberPhoneDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:17 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberPhoneHelper {

    /**
     * Create member phone
     * @param memberPhoneDto
     * @return return the created phone
     */
    MemberPhoneDto createMemberPhone(MemberPhoneDto memberPhoneDto);
}
