package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.SponsorDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 3:59 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface SponsorHelper {

    /**
     * Create the sponsor
     * @param sponsorDto
     * @return
     */
    SponsorDto createSponsor(SponsorDto sponsorDto);
}
