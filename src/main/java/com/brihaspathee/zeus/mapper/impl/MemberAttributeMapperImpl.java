package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Attribute;
import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberAttribute;
import com.brihaspathee.zeus.mapper.interfaces.MemberAttributeMapper;
import com.brihaspathee.zeus.web.model.MemberAttributeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:37 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class MemberAttributeMapperImpl implements MemberAttributeMapper {

    /**
     * Convert member attribute dto to member attribute entity
     * @param memberAttributeDto
     * @return
     */
    @Override
    public MemberAttribute memberAttributeDtoToMemberAttribute(MemberAttributeDto memberAttributeDto) {
        if(memberAttributeDto == null){
            return null;
        }
        MemberAttribute memberAttribute = MemberAttribute.builder()
                .memberAttributeSK(memberAttributeDto.getMemberAttributeSK())
                .member(Member.builder().memberSK(memberAttributeDto.getMemberSK()).build())
                .attribute(Attribute.builder().attributeSK(memberAttributeDto.getAttributeSK()).build())
                .attributeValue(memberAttributeDto.getAttributeValue())
                .createdDate(memberAttributeDto.getCreatedDate())
                .updatedDate(memberAttributeDto.getUpdatedDate())
                .build();
        return memberAttribute;
    }

    /**
     * Convert member attribute entity to member attribute dto
     * @param memberAttribute
     * @return
     */
    @Override
    public MemberAttributeDto memberAttributeToMemberAttributeDto(MemberAttribute memberAttribute) {
        if(memberAttribute == null){
            return null;
        }
        MemberAttributeDto memberAttributeDto = MemberAttributeDto.builder()
                .memberAttributeSK(memberAttribute.getMemberAttributeSK())
                .memberSK(memberAttribute.getMember().getMemberSK())
                .attributeSK(memberAttribute.getAttribute().getAttributeSK())
                .attributeValue(memberAttribute.getAttributeValue())
                .createdDate(memberAttribute.getCreatedDate())
                .updatedDate(memberAttribute.getUpdatedDate())
                .build();
        return memberAttributeDto;
    }

    /**
     * Convert member attribute dtos to member attribute entities
     * @param memberAttributeDtos
     * @return
     */
    @Override
    public Set<MemberAttribute> memberAttributeDtosToMemberAttributes(Set<MemberAttributeDto> memberAttributeDtos) {
        if(memberAttributeDtos !=null && !memberAttributeDtos.isEmpty()){
            return memberAttributeDtos.stream().map(this::memberAttributeDtoToMemberAttribute).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert member attribute entities to member attribute dtos
     * @param memberAttributes
     * @return
     */
    @Override
    public Set<MemberAttributeDto> memberAttributesToMemberAttributeDtos(Set<MemberAttribute> memberAttributes) {
        if(memberAttributes !=null && !memberAttributes.isEmpty()){
            return memberAttributes.stream().map(this::memberAttributeToMemberAttributeDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
