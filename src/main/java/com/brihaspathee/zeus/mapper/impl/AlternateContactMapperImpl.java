package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.AlternateContact;
import com.brihaspathee.zeus.dto.account.AlternateContactDto;
import com.brihaspathee.zeus.mapper.interfaces.AlternateContactMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 2:42 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlternateContactMapperImpl implements AlternateContactMapper {

    /**
     * Convert alternate contact entity to alternate contact dto
     * @param alternateContact
     * @return
     */
    @Override
    public AlternateContactDto alternateContactToAlternateContactDto(AlternateContact alternateContact) {
        if(alternateContact == null){
            return null;
        }
        AlternateContactDto alternateContactDto = AlternateContactDto.builder()
                .alternateContactSK(alternateContact.getAlternateContactSK())
                .alternateContactCode(alternateContact.getAlternateContactCode())
                .alternateContactTypeCode(alternateContact.getAlternateContactTypeCode())
                .firstName(alternateContact.getFirstName())
                .middleName(alternateContact.getMiddleName())
                .lastName(alternateContact.getLastName())
                .identifierTypeCode(alternateContact.getIdentifierTypeCode())
                .identifierValue(alternateContact.getIdentifierValue())
                .phoneTypeCode(alternateContact.getPhoneTypeCode())
                .phoneNumber(alternateContact.getPhoneNumber())
                .email(alternateContact.getEmail())
                .addressLine1(alternateContact.getAddressLine1())
                .addressLine2(alternateContact.getAddressLine2())
                .city(alternateContact.getCity())
                .stateTypeCode(alternateContact.getStateTypeCode())
                .zipCode(alternateContact.getZipCode())
                .ztcn(alternateContact.getZtcn())
                .source(alternateContact.getSource())
                .startDate(alternateContact.getStartDate())
                .endDate(alternateContact.getEndDate())
                .createdDate(alternateContact.getCreatedDate())
                .updatedDate(alternateContact.getUpdatedDate())
                .build();
        return alternateContactDto;
    }

    /**
     * Convert alternate contact dto to alternate contact entity
     * @param alternateContactDto
     * @return
     */
    @Override
    public AlternateContact alternateContactDtoToAlternateContact(AlternateContactDto alternateContactDto) {
        if(alternateContactDto == null){
            return null;
        }
        AlternateContact alternateContact = AlternateContact.builder()
                .alternateContactSK(alternateContactDto.getAlternateContactSK())
                .alternateContactCode(alternateContactDto.getAlternateContactCode())
                .alternateContactTypeCode(alternateContactDto.getAlternateContactTypeCode())
                .firstName(alternateContactDto.getFirstName())
                .middleName(alternateContactDto.getMiddleName())
                .lastName(alternateContactDto.getLastName())
                .identifierTypeCode(alternateContactDto.getIdentifierTypeCode())
                .identifierValue(alternateContactDto.getIdentifierValue())
                .phoneTypeCode(alternateContactDto.getPhoneTypeCode())
                .phoneNumber(alternateContactDto.getPhoneNumber())
                .email(alternateContactDto.getEmail())
                .addressLine1(alternateContactDto.getAddressLine1())
                .addressLine2(alternateContactDto.getAddressLine2())
                .city(alternateContactDto.getCity())
                .stateTypeCode(alternateContactDto.getStateTypeCode())
                .zipCode(alternateContactDto.getZipCode())
                .ztcn(alternateContactDto.getZtcn())
                .source(alternateContactDto.getSource())
                .startDate(alternateContactDto.getStartDate())
                .endDate(alternateContactDto.getEndDate())
                .createdDate(alternateContactDto.getCreatedDate())
                .updatedDate(alternateContactDto.getUpdatedDate())
                .build();
        return alternateContact;
    }

    /**
     * Convert alternate contact entities to alternate contact dtos
     * @param alternateContacts
     * @return
     */
    @Override
    public List<AlternateContactDto> alternateContactsToAlternateContactDtos(List<AlternateContact> alternateContacts) {
        return alternateContacts.stream().map(this::alternateContactToAlternateContactDto).collect(Collectors.toList());
    }

    /**
     * Convert alternate contact dtos to alternate contact entities
     * @param alternateContactDtos
     * @return
     */
    @Override
    public List<AlternateContact> alternateContactDTosAlternateContacts(List<AlternateContactDto> alternateContactDtos) {
        return alternateContactDtos.stream()
                .map(
                        this::alternateContactDtoToAlternateContact).collect(Collectors.toList());
    }
}
