package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.Sponsor;
import com.brihaspathee.zeus.dto.account.SponsorDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 2:19 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface SponsorMapper {

    /**
     * Convert sponsor entity to sponsor dto
     * @param sponsor
     * @return
     */
    SponsorDto sponsorToSponsorDto(Sponsor sponsor);

    /**
     * Convert sponsor dto to sponsor entity
     * @param sponsorDto
     * @return
     */
    Sponsor sponsorDtoToSponsor(SponsorDto sponsorDto);

    /**
     * Convert sponsor entities to sponsor dtos
     * @param sponsors
     * @return
     */
    List<SponsorDto> sponsorsToSponsorDtos(List<Sponsor> sponsors);

    /**
     * Convert sponsor dtos to sponsor entities
     * @param sponsorDtos
     * @return
     */
    List<Sponsor> sponsorDtosToSponsors(List<SponsorDto> sponsorDtos);
}
