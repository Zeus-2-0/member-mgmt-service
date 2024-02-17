package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.MemberAddressDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 7:31 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberAddressHelper {

    /**
     * Create member address
     * @param memberAddressDto
     * @return Return the created member address
     */
    MemberAddressDto createMemberAddress(MemberAddressDto memberAddressDto);

    /**
     * Validate member address
     * @param memberAddressDtos
     */
    void validateMemberAddresses(Set<MemberAddressDto> memberAddressDtos);

    /**
     * Save member address
     * @param memberAddressDtos
     */
    void saveMemberAddresses(Set<MemberAddressDto> memberAddressDtos);
}
