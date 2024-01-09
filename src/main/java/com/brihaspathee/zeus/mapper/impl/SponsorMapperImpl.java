package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Sponsor;
import com.brihaspathee.zeus.dto.account.SponsorDto;
import com.brihaspathee.zeus.mapper.interfaces.SponsorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 2:46 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SponsorMapperImpl implements SponsorMapper {

    /**
     * Convert sponsor entity to sponsor dto
     * @param sponsor
     * @return
     */
    @Override
    public SponsorDto sponsorToSponsorDto(Sponsor sponsor) {
        if(sponsor == null){
            return null;
        }
        SponsorDto sponsorDto = SponsorDto.builder()
                .sponsorSK(sponsor.getSponsorSK())
                .sponsorCode(sponsor.getSponsorCode())
                .sponsorId(sponsor.getSponsorId())
                .sponsorName(sponsor.getSponsorName())
                .ztcn(sponsor.getZtcn())
                .source(sponsor.getSource())
                .startDate(sponsor.getStartDate())
                .endDate(sponsor.getEndDate())
                .createdDate(sponsor.getCreatedDate())
                .updatedDate(sponsor.getUpdatedDate())
                .build();
        return sponsorDto;
    }

    /**
     * Convert sponsor dto to sponsor entity
     * @param sponsorDto
     * @return
     */
    @Override
    public Sponsor sponsorDtoToSponsor(SponsorDto sponsorDto) {
        if(sponsorDto == null){
            return null;
        }
        Sponsor sponsor = Sponsor.builder()
                .sponsorSK(sponsorDto.getSponsorSK())
                .sponsorCode(sponsorDto.getSponsorCode())
                .sponsorId(sponsorDto.getSponsorId())
                .sponsorName(sponsorDto.getSponsorName())
                .ztcn(sponsorDto.getZtcn())
                .source(sponsorDto.getSource())
                .startDate(sponsorDto.getStartDate())
                .endDate(sponsorDto.getEndDate())
                .createdDate(sponsorDto.getCreatedDate())
                .updatedDate(sponsorDto.getUpdatedDate())
                .build();
        return sponsor;
    }

    /**
     * Convert sponsor entities to sponsor dtos
     * @param sponsors
     * @return
     */
    @Override
    public List<SponsorDto> sponsorsToSponsorDtos(List<Sponsor> sponsors) {

        return sponsors.stream().map(this::sponsorToSponsorDto).collect(Collectors.toList());
    }

    /**
     * Convert sponsor dtos to sponsor entities
     * @param sponsorDtos
     * @return
     */
    @Override
    public List<Sponsor> sponsorDtosToSponsors(List<SponsorDto> sponsorDtos) {

        return sponsorDtos.stream().map(this::sponsorDtoToSponsor).collect(Collectors.toList());
    }
}
