package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberAddress;
import com.brihaspathee.zeus.domain.repository.MemberAddressRepository;
import com.brihaspathee.zeus.dto.account.MemberAddressDto;
import com.brihaspathee.zeus.helper.interfaces.MemberAddressHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public void validateMemberAddresses(Set<MemberAddressDto> memberAddressDtos){
        log.info("All Addresses:{}", memberAddressDtos);
        Set<MemberAddressDto> residentialAddresses = memberAddressDtos.stream()
                .filter(
                        memberAddressDto -> memberAddressDto.getAddressTypeCode().equals("RES") )
                .collect(Collectors.toSet());
        log.info("Residential Addresses:{}", residentialAddresses);
        List<MemberAddressDto> sortedResAddresses = residentialAddresses.stream()
                .sorted(Comparator.comparing(MemberAddressDto::getStartDate))
                .collect(Collectors.toList());
//        sortedResAddresses.stream().
//                forEach(
//                        memberAddressDto ->
//                                log.info(String.valueOf(memberAddressDto.getStartDate())));
        long addressesWithNullEndDate = sortedResAddresses.stream().filter(memberAddressDto ->
                memberAddressDto.getEndDate() == null).count();
        log.info("Address with NULL end date:{}", addressesWithNullEndDate);
        MemberAddressDto prevAddress = sortedResAddresses.get(0);
        for(int i=1; i < sortedResAddresses.size(); i++){
            log.info("Inside the for loop");
            MemberAddressDto currAddress = sortedResAddresses.get(i);
            if(isDateOverlap(prevAddress.getEndDate(), currAddress.getStartDate())){
                log.info("Dates overlap");
            }
            prevAddress = currAddress;
        }

    }

    private boolean isDateOverlap(final LocalDate previousSpanEnDate, final LocalDate currentSpanStartDate){
        log.info("Previous span end date:{}", previousSpanEnDate);
        log.info("Current span start date:{}", currentSpanStartDate);
        log.info("previousSpanEnDate.isAfter(currentSpanStartDate):{}", previousSpanEnDate.isAfter(currentSpanStartDate));
        if(previousSpanEnDate == null || currentSpanStartDate == null){
            return false;
        }
        if (previousSpanEnDate.equals(currentSpanStartDate) || previousSpanEnDate.isAfter(currentSpanStartDate)){
            return true;
        }else{
            return false;
        }
    }
}
