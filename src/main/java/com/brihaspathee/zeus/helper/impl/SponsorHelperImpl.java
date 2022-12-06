package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Sponsor;
import com.brihaspathee.zeus.domain.repository.SponsorRepository;
import com.brihaspathee.zeus.dto.account.SponsorDto;
import com.brihaspathee.zeus.helper.interfaces.SponsorHelper;
import com.brihaspathee.zeus.mapper.interfaces.SponsorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 4:08 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SponsorHelperImpl implements SponsorHelper {

    /**
     * The repository instance to perform crud operations
     */
    private final SponsorRepository sponsorRepository;

    /**
     * The mapper instance to convert the dto to entity and vice versa
     */
    private final SponsorMapper sponsorMapper;

    /**
     * Create the sponsor
     * @param sponsorDto
     * @return
     */
    @Override
    public SponsorDto createSponsor(SponsorDto sponsorDto, Account account) {
        if(sponsorDto != null){
            Sponsor sponsor = sponsorMapper.sponsorDtoToSponsor(sponsorDto);
            sponsor.setAccount(account);
            sponsor = sponsorRepository.save(sponsor);
            return sponsorMapper.sponsorToSponsorDto(sponsor);
        }else {
            return null;
        }

    }
}
