package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.PremiumSpanDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, September 2022
 * Time: 4:33 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PremiumSpanHelper {

    /**
     * Create the premium spans
     * @param premiumSpanDto
     * @return
     */
    PremiumSpanDto createPremiumSpan(PremiumSpanDto premiumSpanDto);
}
