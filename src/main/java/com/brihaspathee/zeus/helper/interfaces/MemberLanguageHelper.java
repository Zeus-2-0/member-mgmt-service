package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.dto.account.MemberLanguageDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:16 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberLanguageHelper {

    /**
     * Create member language
     * @param memberLanguageDto
     * @return return the created member language
     */
    MemberLanguageDto createMemberLanguage(MemberLanguageDto memberLanguageDto);
}
