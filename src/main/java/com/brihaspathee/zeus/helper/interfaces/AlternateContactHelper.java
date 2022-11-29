package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.AlternateContactDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 3:50 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AlternateContactHelper {

    /**
     * Create the alternate contact
     * @param alternateContactDto
     * @return
     */
    AlternateContactDto createAlternateContact(AlternateContactDto alternateContactDto);
}
