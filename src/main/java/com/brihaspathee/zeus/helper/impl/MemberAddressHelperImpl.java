package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberAddress;
import com.brihaspathee.zeus.domain.repository.MemberAddressRepository;
import com.brihaspathee.zeus.helper.interfaces.MemberAddressHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberAddressMapper;
import com.brihaspathee.zeus.web.model.MemberAddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 7:32 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAddressHelperImpl implements MemberAddressHelper {

    /**
     * Mapper to map the dto to entities and vice versa
     */
    private final MemberAddressMapper memberAddressMapper;

    /**
     * Member Address Repository to perform CRUD operations
     */
    private final MemberAddressRepository memberAddressRepository;

    /**
     * Create member address
     * @param memberAddressDto
     * @return return the created member address
     */
    @Override
    public MemberAddressDto createMemberAddress(MemberAddressDto memberAddressDto) {
        // log.info("Member DTO sk:{}", memberAddressDto.getMemberSK());
        MemberAddress memberAddress = memberAddressMapper.addressDtoToAddress(memberAddressDto);
        // log.info("Member sk:{}", memberAddress.getMember().getMemberSK());
        memberAddress = memberAddressRepository.save(memberAddress);
        return memberAddressMapper.addressToAddressDto(memberAddress);
    }
}
