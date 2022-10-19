package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberAddress;
import com.brihaspathee.zeus.dto.account.MemberAddressDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:20 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberAddressMapper {

    /**
     * Convert address dto to address entity
     * @param addressDto
     * @return
     */
    MemberAddress addressDtoToAddress(MemberAddressDto addressDto);

    /**
     * Convert address entity to address dto
     * @param address
     * @return
     */
    MemberAddressDto addressToAddressDto(MemberAddress address);

    /**
     * Convert address dtos to address entities
     * @param addressDtos
     * @return
     */
    Set<MemberAddress> addressDtosToAddresses(Set<MemberAddressDto> addressDtos);

    /**
     * Convert address entities to address dtos
     * @param addresses
     * @return
     */
    Set<MemberAddressDto> addressesToAddressDtos(Set<MemberAddress> addresses);
}
