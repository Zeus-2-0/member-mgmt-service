package com.brihaspathee.zeus.helper.interfaces;

import com.brihaspathee.zeus.web.model.MemberIdentifierDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberIdentifierHelper {

    /**
     * create the member identifier
     * @param memberIdentifierDto
     * @return
     */
    MemberIdentifierDto createMemberIdentifier(MemberIdentifierDto memberIdentifierDto);
}
