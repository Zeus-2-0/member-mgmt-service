package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.AccountAttribute;
import com.brihaspathee.zeus.domain.entity.Attribute;
import com.brihaspathee.zeus.dto.account.AccountAttributeDto;
import com.brihaspathee.zeus.mapper.interfaces.AccountAttributeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 4:09 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class AccountAttributeMapperImpl implements AccountAttributeMapper {

    /**
     * Convert account attribute dto to account attribute entity
     * @param accountAttributeDto
     * @return
     */
    @Override
    public AccountAttribute accountAttributeDtoToAccountAttribute(AccountAttributeDto accountAttributeDto) {
        if(accountAttributeDto == null){
            return null;
        }
        AccountAttribute accountAttribute = AccountAttribute.builder()
                .accountAttributeSK(accountAttributeDto.getAccountAttributeSK())
                .attribute(Attribute.builder().attributeSK(accountAttributeDto.getAttributeSK()).build())
                .account(Account.builder().accountSK(accountAttributeDto.getAccountSK()).build())
                .attributeValue(accountAttributeDto.getAttributeValue())
                .createdDate(accountAttributeDto.getCreatedDate())
                .updatedDate(accountAttributeDto.getUpdatedDate())
                .build();
        return accountAttribute;
    }

    /**
     * Convert account attribute entity to account attribute dto
     * @param accountAttribute
     * @return
     */
    @Override
    public AccountAttributeDto accountAttributeToAccountAttributeDto(AccountAttribute accountAttribute) {
        if(accountAttribute == null){
            return null;
        }
        AccountAttributeDto accountAttributeDto = AccountAttributeDto.builder()
                .accountAttributeSK(accountAttribute.getAccountAttributeSK())
                .attributeSK(accountAttribute.getAttribute().getAttributeSK())
                .accountSK(accountAttribute.getAccount().getAccountSK())
                .attributeValue(accountAttribute.getAttributeValue())
                .createdDate(accountAttribute.getCreatedDate())
                .updatedDate(accountAttribute.getUpdatedDate())
                .build();
        return accountAttributeDto;
    }

    /**
     * Convert account attribute dtos to account attribute entities
     * @param accountAttributeDtos
     * @return
     */
    @Override
    public Set<AccountAttribute> accountAttributeDtosToAccountAttributes(Set<AccountAttributeDto> accountAttributeDtos) {
        if(accountAttributeDtos !=null && !accountAttributeDtos.isEmpty()){
            return accountAttributeDtos.stream().map(this::accountAttributeDtoToAccountAttribute).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert account attribute entities to account attribute dtos
     * @param accountAttributes
     * @return
     */
    @Override
    public Set<AccountAttributeDto> accountAttributesToAccountAttributeDtos(Set<AccountAttribute> accountAttributes) {
        if(accountAttributes !=null && !accountAttributes.isEmpty()){
            return accountAttributes.stream().map(this::accountAttributeToAccountAttributeDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
