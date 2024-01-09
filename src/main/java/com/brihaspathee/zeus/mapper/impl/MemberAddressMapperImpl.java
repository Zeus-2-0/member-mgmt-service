package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberAddress;
import com.brihaspathee.zeus.dto.account.MemberAddressDto;
import com.brihaspathee.zeus.mapper.interfaces.MemberAddressMapper;
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
                .memberAddressCode(addressDto.getMemberAddressCode())
                .member(Member.builder().memberSK(addressDto.getMemberSK()).build())
                .addressTypeCode(addressDto.getAddressTypeCode())
                .addressLine1(addressDto.getAddressLine1())
                .addressLine2(addressDto.getAddressLine2())
                .city(addressDto.getCity())
                .stateTypeCode(addressDto.getStateTypeCode())
                .zipCode(addressDto.getZipCode())
                .ztcn(addressDto.getZtcn())
                .source(addressDto.getSource())
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
                .memberAddressCode(address.getMemberAddressCode())
                .memberSK(address.getMember().getMemberSK())
                .addressTypeCode(address.getAddressTypeCode())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .stateTypeCode(address.getStateTypeCode())
                .zipCode(address.getZipCode())
                .ztcn(address.getZtcn())
                .source(address.getSource())
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
        if(addressDtos !=null && !addressDtos.isEmpty()){
            return addressDtos.stream().map(this::addressDtoToAddress).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert address entities to address dtos
     * @param addresses
     * @return
     */
    @Override
    public Set<MemberAddressDto> addressesToAddressDtos(Set<MemberAddress> addresses) {
        if(addresses !=null && !addresses.isEmpty()){
            return addresses.stream().map(this::addressToAddressDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
