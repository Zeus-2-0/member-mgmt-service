package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberAddress;
import com.brihaspathee.zeus.mapper.interfaces.MemberAddressMapper;
import com.brihaspathee.zeus.web.model.MemberAddressDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:24 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class MemberAddressMapperImpl implements MemberAddressMapper {

    /**
     * Convert address dto to address entity
     * @param addressDto
     * @return
     */
    @Override
    public MemberAddress addressDtoToAddress(MemberAddressDto addressDto) {
        if(addressDto == null){
            return null;
        }
        MemberAddress address = MemberAddress.builder()
                .memberAddressSK(addressDto.getMemberAddressSK())
                .member(Member.builder().memberSK(addressDto.getMemberSK()).build())
                .addressTypeCode(addressDto.getAddressTypeCode())
                .addressLine1(addressDto.getAddressLine1())
                .addressLine2(addressDto.getAddressLine2())
                .city(addressDto.getCity())
                .stateTypeCode(addressDto.getStateTypeCode())
                .zipCode(addressDto.getZipCode())
                .startDate(addressDto.getStartDate())
                .endDate(addressDto.getEndDate())
                .createdDate(addressDto.getCreatedDate())
                .updatedDate(addressDto.getUpdatedDate())
                .build();
        return address;
    }

    /**
     * Convert address entity to address dto
     * @param address
     * @return
     */
    @Override
    public MemberAddressDto addressToAddressDto(MemberAddress address) {
        if(address == null){
            return null;
        }
        MemberAddressDto addressDto = MemberAddressDto.builder()
                .memberAddressSK(address.getMemberAddressSK())
                .memberSK(address.getMember().getMemberSK())
                .addressTypeCode(address.getAddressTypeCode())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .stateTypeCode(address.getStateTypeCode())
                .zipCode(address.getZipCode())
                .startDate(address.getStartDate())
                .endDate(address.getEndDate())
                .createdDate(address.getCreatedDate())
                .updatedDate(address.getUpdatedDate())
                .build();
        return addressDto;
    }

    /**
     * Convert address dtos to address entities
     * @param addressDtos
     * @return
     */
    @Override
    public Set<MemberAddress> addressDtosToAddresses(Set<MemberAddressDto> addressDtos) {
        return addressDtos.stream().map(this::addressDtoToAddress).collect(Collectors.toSet());
    }

    /**
     * Convert address entities to address dtos
     * @param addresses
     * @return
     */
    @Override
    public Set<MemberAddressDto> addressesToAddressDtos(Set<MemberAddress> addresses) {
        return addresses.stream().map(this::addressToAddressDto).collect(Collectors.toSet());
    }
}