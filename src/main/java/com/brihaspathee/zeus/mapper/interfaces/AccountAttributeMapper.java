package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.AccountAttribute;
import com.brihaspathee.zeus.web.model.AccountAttributeDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 4:09 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AccountAttributeMapper {

    /**
     * Convert account attribute dto to account attribute entity
     * @param accountAttributeDto
     * @return
     */
    AccountAttribute accountAttributeDtoToAccountAttribute(AccountAttributeDto accountAttributeDto);

    /**
     * Convert account attribute entity to account attribute dto
     * @param accountAttribute
     * @return
     */
    AccountAttributeDto accountAttributeToAccountAttributeDto(AccountAttribute accountAttribute);

    /**
     * Convert account attribute dtos to account attribute entities
     * @param accountAttributeDtos
     * @return
     */
    Set<AccountAttribute> accountAttributeDtosToAccountAttributes(Set<AccountAttributeDto> accountAttributeDtos);

    /**
     * Convert account attribute entities to account attribute dtos
     * @param accountAttributes
     * @return
     */
    Set<AccountAttributeDto> accountAttributesToAccountAttributeDtos(Set<AccountAttribute> accountAttributes);
}
