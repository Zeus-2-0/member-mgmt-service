package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberAttribute;
import com.brihaspathee.zeus.web.model.MemberAddressDto;
import com.brihaspathee.zeus.web.model.MemberAttributeDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:32 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberAttributeMapper {

    /**
     * Convert member attribute dto to member attribute entity
     * @param memberAttributeDto
     * @return
     */
    MemberAttribute memberAttributeDtoToMemberAttribute(MemberAttributeDto memberAttributeDto);

    /**
     * Convert member attribute entity to member attribute dto
     * @param memberAttribute
     * @return
     */
    MemberAttributeDto memberAttributeToMemberAttributeDto(MemberAttribute memberAttribute);

    /**
     * Convert member attribute dtos to member attribute entities
     * @param memberAttributeDtos
     * @return
     */
    Set<MemberAttribute> memberAttributeDtosToMemberAttributes(Set<MemberAttributeDto> memberAttributeDtos);

    /**
     * Convert member attribute entities to member attribute dtos
     * @param memberAttributes
     * @return
     */
    Set<MemberAttributeDto> memberAttributesToMemberAttributeDtos(Set<MemberAttribute> memberAttributes);
}
